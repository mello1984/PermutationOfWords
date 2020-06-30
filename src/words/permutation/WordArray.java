package words.permutation;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class WordArray {
    private List<Word> words;
    private List<CycleList> cycleLists;

    WordArray(String[] strings) throws WordArrayException {
        words = new LinkedList<>();
        if (strings == null) throw new WordArrayException("The array of words is invalid: it's null");
        Arrays.stream(strings).forEach(s -> words.add(new Word(s)));
        checkWordsArray();
        getCycleLists();
        getResultFromCycleLists();
    }

    private boolean checkWordsArray() throws WordArrayException {
        checkLetters();
        checkWordsArrayConnectivity();
        return true;
    }

    private boolean checkWordsArrayConnectivity() throws WordArrayException {
        List<Set<Character>> list = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        for (Word word : words) {
            if (word.getFirstLetter() == word.getLastLetter()) continue;

            boolean containFirst = set.contains(word.getFirstLetter());
            boolean containSecond = set.contains(word.getLastLetter());
            set.add(word.getFirstLetter());
            set.add(word.getLastLetter());

            if (!containFirst && !containSecond) {
                list.add(new HashSet<>(Arrays.asList(word.getFirstLetter(), word.getLastLetter())));
            } else if (!containFirst || !containSecond) {
                list.forEach(s -> {
                    if (s.contains(word.getFirstLetter())) s.add(word.getLastLetter());
                    if (s.contains(word.getLastLetter())) s.add(word.getFirstLetter());
                });
            } else {
                List<Set<Character>> first = list.stream().filter(s -> s.contains(word.getFirstLetter())).collect(Collectors.toList());
                List<Set<Character>> last = list.stream().filter(s -> s.contains(word.getLastLetter())).collect(Collectors.toList());
                Set<Character> result = new HashSet<>(first.get(0));
                result.addAll(last.get(0));
                list.remove(first.get(0));
                list.remove(last.get(0));
                list.add(result);
            }
        }

        if (list.size() > 1) throw new WordArrayException("The array of words isn't connected: " + list.toString());
        return true;
    }

    private boolean checkLetters() throws WordArrayException {
        Map<Character, Integer> firstLetters = new HashMap<>();
        Map<Character, Integer> lastLetters = new HashMap<>();
        words.forEach(w -> {
            firstLetters.merge(w.getFirstLetter(), 1, Integer::sum);
            lastLetters.merge(w.getLastLetter(), 1, Integer::sum);
        });

        if (firstLetters.size() != lastLetters.size())
            throw new WordArrayException(String.format("The array of words is invalid. The number of unique characters differs in the first and last character arrays\n" +
                    "first letters: %s, last letters: %s", firstLetters, lastLetters));

        if (firstLetters.entrySet().stream().anyMatch(x -> x.getValue() != lastLetters.get(x.getKey())))
            throw new WordArrayException(String.format("The array of words is invalid. The array of first characters does not match the array of last characters\n" +
                    "first letters: %s, last letters: %s", firstLetters, lastLetters));

        return true;
    }

    private void getCycleLists() {
        cycleLists = new ArrayList<>();
        List<Word> clone = new LinkedList<>(words);
        List<Word> out = new LinkedList<>();
        while (clone.size() > 0) {
            if (out.size() == 0) {
                out.add(clone.remove(0));
                continue;
            }
            for (Word w : clone) {
                if (w.getFirstLetter() == out.get(out.size() - 1).getLastLetter()) {
                    out.add(w);
                    clone.remove(w);
                    break;
                } else if (w.getLastLetter() == out.get(0).getFirstLetter()) {
                    out.add(0, w);
                    clone.remove(w);
                    break;
                }
            }
            if (out.get(0).getFirstLetter() == out.get(out.size() - 1).getLastLetter()) {
                if (out.size() > 0) cycleLists.add(new CycleList(out));
                out = new LinkedList<>();
            }
        }
    }

    private void getResultFromCycleLists() {
        while (cycleLists.size() > 1) {
            for (int i = 1; i < cycleLists.size(); i++) {
                int I = i;
                if (cycleLists.get(0).characterSet.stream().anyMatch(c -> cycleLists.get(I).characterSet.contains(c))) {
                    joinCycleLists(cycleLists.get(0), cycleLists.get(i));
                    break;
                }

            }
        }
    }

    private void joinCycleLists(CycleList c1, CycleList c2) {
        char joinChar = c1.characterSet.stream().filter(c -> c2.characterSet.contains(c)).findFirst().get();
        if (c2.list.get(0).getFirstLetter() != joinChar) c2.regroup(joinChar);

        for (int i = 0; i < c1.list.size(); i++) {
            if (c1.list.get(i).getLastLetter() == joinChar) {
                c1.list.addAll(i + 1, c2.list);
                c1.characterSet.addAll(c2.characterSet);
                cycleLists.remove(c2);
                break;
            }
        }
    }

    public String getResultString() {
        StringBuilder out = new StringBuilder();
        cycleLists.get(0).list.forEach(w -> out.append(" ").append(w.getString()));
        return out.deleteCharAt(0).toString();
    }

    public List<String> getResultListStrings() {
        List<String> out = new ArrayList<>();
        cycleLists.get(0).list.forEach(w -> out.add(w.getString()));
        return out;
    }

    @Override
    public String toString() {
        return "WordArray@" + this.hashCode() + " {" +
                "\ncount words = " + words.size() +
                "\nwords = " + words + "}";
    }

    class WordArrayException extends Exception {
        public WordArrayException(String message) {
            super(message);
        }
    }
}

//    private boolean checkWordsArrayConnectivity() throws WordArrayException {
//        // Здесь проходит проверка на связность
//        List<Set<Character>> list = new LinkedList<>();
//        for (Word word : words) {
//            AtomicBoolean next = new AtomicBoolean(false);
//            list.forEach(set -> {
//                if (set.contains(word.getFirstLetter()) || set.contains(word.getLastLetter())) {
//                    set.add(word.getFirstLetter());
//                    set.add(word.getLastLetter());
//                    next.set(true);
//                }
//            });
//            if (!next.get()) list.add(new HashSet<>(Arrays.asList(word.getFirstLetter(), word.getLastLetter())));
//        }
//
//        List<Set<Character>> newList = new LinkedList<>();
//        while (list.size() > 0) {
//            for (Set<Character> set : newList) {
//                boolean repeat = true;
//                while (repeat) {
//                    repeat = false;
//                    Iterator<Set<Character>> it = list.iterator();
//                    while (it.hasNext()) {
//                        Set<Character> nextSet = new HashSet<>(it.next());
//                        if (set.stream().anyMatch(nextSet::contains)) {
//                            set.addAll(nextSet);
//                            it.remove();
//                            repeat = true;
//                        }
//                    }
//                }
//            }
//            if (list.size() > 0) newList.add(list.remove(0));
//        }
//        if (newList.size() > 1)
//            throw new WordArrayException("The array of words isn't connected: " + newList.toString());
//        return true;
//    }