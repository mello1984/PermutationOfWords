package words.permutation;

import java.util.*;
import java.util.stream.Collectors;

class CyclicAlgorithm {
    private List<Word> words;
    private List<String> stringList;

    CyclicAlgorithm(String[] strings) throws WordArrayException {
        checkInput(strings);
        words = new LinkedList<>();
        Arrays.stream(strings).forEach(string -> words.add(new Word(string)));
        checkWordsArray();
        generateResultLists();
    }

    CyclicAlgorithm(List<String> stringList) throws WordArrayException {
        checkInput(stringList);
        words = new LinkedList<>();
        stringList.forEach(string -> words.add(new Word(string)));
        checkWordsArray();
        generateResultLists();
    }

    private void checkInput(String[] strings) throws WordArrayException {
        if (strings == null) throw new WordArrayException("The array of words is invalid: it's null");
        if (Arrays.equals(strings, new String[]{""}))
            throw new WordArrayException("The array of words is invalid: it's {\"\"}");
    }

    private void checkInput(List<String> stringList) throws WordArrayException {
        if (stringList == null) throw new WordArrayException("The list of words is invalid: it's null");
        if (stringList.size() == 0) throw new WordArrayException("The list of words is invalid: it's size is 0");
    }

    private void checkWordsArray() throws WordArrayException {
        checkLetters();
        checkConnectivity();
    }

    private void checkLetters() throws WordArrayException {
        Map<Character, Integer> firstLetters = new HashMap<>();
        Map<Character, Integer> lastLetters = new HashMap<>();
        words.forEach(w -> {
            firstLetters.merge(w.getFirstLetter(), 1, Integer::sum);
            lastLetters.merge(w.getLastLetter(), 1, Integer::sum);
        });

        if (firstLetters.size() != lastLetters.size())
            throw new WordArrayException(String.format("The array of words is invalid. The number of unique characters differs in the first and last character arrays " +
                    "first letters: %s, last letters: %s", firstLetters, lastLetters));

        if (firstLetters.entrySet().stream().anyMatch(x -> !x.getValue().equals(lastLetters.get(x.getKey()))))
            throw new WordArrayException(String.format("The array of words is invalid. The array of first characters does not match the array of last characters " +
                    "first letters: %s, last letters: %s", firstLetters, lastLetters));
    }

    private void checkConnectivity() throws WordArrayException {
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

    private void generateResultLists() {
        words = getResultFromCycleLists(getCycleLists());
        stringList = new ArrayList<>(words.size());
        words.forEach(word -> stringList.add(word.getString()));
    }

    private List<CycleList> getCycleLists() {
        List<CycleList> cycleLists = new ArrayList<>();
        List<Word> tempList = new LinkedList<>();
        while (words.size() > 0) {
            if (tempList.size() == 0) tempList.add(words.remove(0));

            List<Word> finalOut = tempList;
            Optional<Word> w = words.stream().filter(word -> word.getLastLetter() == finalOut.get(0).getFirstLetter() || word.getFirstLetter() == finalOut.get(finalOut.size() - 1).getLastLetter()).findFirst();
            if (w.isPresent()) {
                if (w.get().getLastLetter() == tempList.get(0).getFirstLetter()) tempList.add(0, w.get());
                else tempList.add(w.get());
                words.remove(w.get());
            }

            if (tempList.get(0).getFirstLetter() == tempList.get(tempList.size() - 1).getLastLetter()) {
                cycleLists.add(new CycleList(tempList));
                tempList = new LinkedList<>();
            }
        }
        return cycleLists;
    }

    private List<Word> getResultFromCycleLists(List<CycleList> listCycleList) {
        CycleList out = new CycleList(listCycleList.remove(0).list);
        int[] count = {0};
        while (listCycleList.size() > 0) {
            if (out.characterSet.stream().anyMatch(c -> listCycleList.get(count[0]).characterSet.contains(c)))
                joinCycleLists(out, listCycleList.get(count[0]), listCycleList);
            count[0] = count[0] >= listCycleList.size() - 1 ? 0 : count[0] + 1;
        }
        return out.list;
    }

    private void joinCycleLists(CycleList c1, CycleList c2, List<CycleList> listCycleList) {
        char joinChar = c1.characterSet.stream().filter(c -> c2.characterSet.contains(c)).findFirst().get();
        if (c2.list.get(0).getFirstLetter() != joinChar) c2.regroup(joinChar);
        if (c1.list.get(0).getFirstLetter() != joinChar) c1.regroup(joinChar);
        c1.list.addAll(c2.list);
        c1.characterSet.addAll(c2.characterSet);
        listCycleList.remove(c2);
    }

    public List<String> getResultListStrings() {
        return stringList;
    }

    public String[] getResultArrayStrings() {
        return stringList.toArray(new String[0]);
    }

    public String getResultString() {
        return String.join(" ", getResultArrayStrings());
    }

    static class WordArrayException extends Exception {
        public WordArrayException(String message) {
            super(message);
        }
    }

    private static class CycleList {
        List<Word> list;
        Set<Character> characterSet = new HashSet<>();

        public CycleList(List<Word> list) {
            this.list = new ArrayList<>(list);
            list.forEach(w -> {
                characterSet.add(w.getFirstLetter());
                characterSet.add(w.getLastLetter());
            });
        }

        void regroup(char ch) {
            int first = 0;
            while (first++ < list.size())
                if (list.get(first).getFirstLetter() == ch) break;
            List<Word> tempList = new ArrayList<>(list.size());
            list.stream().skip(first).forEachOrdered(tempList::add);
            list.stream().limit(first).forEachOrdered(tempList::add);
            list = tempList;
        }
    }


}