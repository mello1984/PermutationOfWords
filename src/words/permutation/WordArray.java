package words.permutation;

import java.util.*;
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
    }


    private void checkWordsArray() throws WordArrayException {
        checkLetters();
        checkWordsArrayConnectivity();
    }

    private void checkLetters() throws WordArrayException {
        Map<Character, Integer> firstLetters = new HashMap<>();
        Map<Character, Integer> lastLetters = new HashMap<>();
        words.forEach(w -> {
            firstLetters.merge(w.getFirstLetter(), 1, Integer::sum);
            lastLetters.merge(w.getLastLetter(), 1, Integer::sum);
        });

        if (firstLetters.size() != lastLetters.size())
            throw new WordArrayException(String.format("The array of words is invalid. The number of unique characters differs in the first and last character arrays" +
                    "first letters: %s, last letters: %s", firstLetters, lastLetters));

        if (firstLetters.entrySet().stream().anyMatch(x -> !x.getValue().equals(lastLetters.get(x.getKey()))))
            throw new WordArrayException(String.format("The array of words is invalid. The array of first characters does not match the array of last characters" +
                    "first letters: %s, last letters: %s", firstLetters, lastLetters));
    }

    private void checkWordsArrayConnectivity() throws WordArrayException {
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
    }

    private void getCycleLists() {
        cycleLists = new ArrayList<>();
        List<Word> out = new LinkedList<>();
        while (words.size() > 0) {
            if (out.size() == 0) out.add(words.remove(0));

            List<Word> finalOut = out;
            Optional<Word> w = words.stream().filter(word -> word.getLastLetter() == finalOut.get(0).getFirstLetter() || word.getFirstLetter() == finalOut.get(finalOut.size() - 1).getLastLetter()).findFirst();
            if (w.isPresent()) {
                if (w.get().getLastLetter() == out.get(0).getFirstLetter()) out.add(0, w.get());
                else out.add(w.get());
                words.remove(w.get());
            }

            if (out.get(0).getFirstLetter() == out.get(out.size() - 1).getLastLetter()) {
                cycleLists.add(new CycleList(out));
                out = new LinkedList<>();
            }
        }
        getResultFromCycleLists();
    }

    private void getResultFromCycleLists() {
        CycleList out = new CycleList(cycleLists.remove(0).list);
        int[] count = {0};
        while (cycleLists.size() > 0) {
            if (out.characterSet.stream().anyMatch(c -> cycleLists.get(count[0]).characterSet.contains(c)))
                joinCycleLists(out, cycleLists.get(count[0]));
            count[0] = count[0] >= cycleLists.size() - 1 ? 0 : count[0] + 1;
        }
        words = out.list;
    }

    private void joinCycleLists(CycleList c1, CycleList c2) {
        char joinChar = c1.characterSet.stream().filter(c -> c2.characterSet.contains(c)).findFirst().get();
        if (c2.list.get(0).getFirstLetter() != joinChar) c2.regroup(joinChar);
        if (c1.list.get(0).getFirstLetter() != joinChar) c1.regroup(joinChar);
        c1.list.addAll(c2.list);
        c1.characterSet.addAll(c2.characterSet);
        cycleLists.remove(c2);
    }

    public String getResultString() {
        StringBuilder out = new StringBuilder();
        words.forEach(w -> out.append(" ").append(w.getString()));
        return out.deleteCharAt(0).toString();
    }

    public List<String> getResultListStrings() {
        List<String> out = new ArrayList<>();
        words.forEach(w -> out.add(w.getString()));
        return out;
    }

    static class WordArrayException extends Exception {
        public WordArrayException(String message) {
            super(message);
        }
    }
}