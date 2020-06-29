package words.permutation;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

class WordArray {
    private List<Word> words;
    private List<CycleList> cycleLists;

    WordArray(String[] strings) throws WordArrayException {
        words = new LinkedList<>();
        if (strings == null) throw new WordArrayException("The array of words is invalid: it's null");
        Arrays.stream(strings).forEach(s -> words.add(new Word(s)));
        check();
        getCycleLists();
        getResultFromCycleLists();
    }

    private void check() throws WordArrayException {
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

        // Здесь проходит проверка на связность
        List<Set<Character>> list = new LinkedList<>();
        for (Word word : words) {
            AtomicBoolean next = new AtomicBoolean(false);
            list.forEach(set -> {
                if (set.contains(word.getFirstLetter()) || set.contains(word.getLastLetter())) {
                    set.add(word.getFirstLetter());
                    set.add(word.getLastLetter());
                    next.set(true);
                }
            });
            if (!next.get()) {
                list.add(new HashSet<>(Arrays.asList(word.getFirstLetter(), word.getLastLetter())));
                next.set(true);
            }
        }

        List<Set<Character>> newList = new LinkedList<>();
        while (list.size() > 0) {
            for (Set<Character> set : newList) {
                boolean repeat = true;
                while (repeat) {
                    repeat = false;
                    Iterator<Set<Character>> it = list.iterator();
                    while (it.hasNext()) {
                        Set<Character> intersectSet = new HashSet<>(set);
                        Set<Character> nextSet = new HashSet<>(it.next());
                        intersectSet.retainAll(nextSet);
                        if (intersectSet.size() > 0) {
                            set.addAll(nextSet);
                            it.remove();
                            repeat = true;
                        }
                    }
                }
            }
            if (list.size() > 0) newList.add(list.remove(0));
        }
        if (newList.size() > 1)
            throw new WordArrayException("The array of words isn't connected: " + newList.toString());
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

//    private void simplify() throws WordArrayException {
//        List<Word> sameWords = words.stream().filter(w -> !w.isDifferentLetters()).collect(Collectors.toList());
//        words.removeAll(sameWords);
//        for (Word sameWord : sameWords) {
//            for (Word word : words) {
//                if (word.getFirstLetter() == sameWord.getLastLetter() || word.getLastLetter() == sameWord.getFirstLetter()) {
//                    joinWords(word, sameWord);
//                    break;
//                }
//            }
//        }
//
//    }

//    private void joinWords(Word w1, Word w2) throws WordArrayException {
//        if (!(w1.getFirstLetter() == w2.getLastLetter()) && !(w1.getLastLetter() == w2.getFirstLetter()))
//            throw new WordArrayException(String.format("Can't join words: '%s' and '%s'", w1, w2));
//
//        if (w1.getFirstLetter() == w2.getLastLetter()) {
//            words.add(new Word(String.join(" ", w2.getString(), w1.getString())));
//            firstLetters.merge(w1.getFirstLetter(), -1, Integer::sum);
//            lastLetters.merge(w1.getFirstLetter(), -1, Integer::sum);
//        } else if (w1.getLastLetter() == w2.getFirstLetter()) {
//            words.add(new Word(String.join(" ", w1.getString(), w2.getString())));
//            firstLetters.merge(w1.getLastLetter(), -1, Integer::sum);
//            lastLetters.merge(w1.getLastLetter(), -1, Integer::sum);
//        }
//        words.remove(w1);
//        words.remove(w2);
//    }