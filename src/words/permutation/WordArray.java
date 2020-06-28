package words.permutation;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

class WordArray {
    List<Word> words;
    Map<Character, Integer> firstLetters;
    Map<Character, Integer> lastLetters;

    WordArray(String[] strings) throws WordArrayException {
        words = new LinkedList<>();
        firstLetters = new HashMap<>();
        lastLetters = new HashMap<>();

        if (strings == null) throw new WordArrayException("The array of words is invalid: it's null");
        for (String string : strings) {
            Word word = new Word(string);
            words.add(word);
            if (word.isDifferentLetters()) {
                firstLetters.merge(word.getFirstLetter(), 1, Integer::sum);
                lastLetters.merge(word.getFirstLetter(), 1, Integer::sum);
            }
        }
        check();
    }

    private void check() throws WordArrayException {
        if (firstLetters.size() != lastLetters.size())
            throw new WordArrayException("The array of words is invalid. The number of unique characters differs in the first and last character arrays");

        for (Map.Entry<Character, Integer> pair : firstLetters.entrySet()) {
            if (pair.getValue() != lastLetters.get(pair.getKey()))
                throw new WordArrayException(String.format("The array of words is invalid. The number of '%s' character in first letters is %d, and in last letters is %d%n",
                        pair.getKey(), pair.getValue(), lastLetters.get(pair.getKey())));
        }

        // Здесь нужна проверка на связность
        List<Set<Character>> list = new ArrayList<>();
        for (Word word : words) {
            AtomicBoolean next = new AtomicBoolean(false);
            while (!next.get()) {
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
        }

        list.forEach(System.out::println);

        System.out.println("Verification passed");
    }

    class WordArrayException extends Exception {
        public WordArrayException(String message) {
            super(message);
        }
    }
}
