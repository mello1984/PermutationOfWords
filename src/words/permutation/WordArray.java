package words.permutation;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class WordArray {
    List<Word> words;
    Map<Character, Integer> firstLetters;
    Map<Character, Integer> lastLetters;
    List<CycleList> cycleLists;

    WordArray(String[] strings) throws WordArrayException {
        words = new LinkedList<>();
        firstLetters = new HashMap<>();
        lastLetters = new HashMap<>();

        if (strings == null) throw new WordArrayException("The array of words is invalid: it's null");
        for (String string : strings) {
            Word word = new Word(string);
            words.add(word);
            firstLetters.merge(word.getFirstLetter(), 1, Integer::sum);
            lastLetters.merge(word.getLastLetter(), 1, Integer::sum);
        }
        System.out.println(this);
        System.out.println("check===========");
        check();

        simplify();
        System.out.println(this);
        System.out.println("================");
        getCycleLists();
        System.out.println(this);
        System.out.println("================");
        System.out.println(cycleLists);
    }

    private void check() throws WordArrayException {
        if (firstLetters.size() != lastLetters.size())
            throw new WordArrayException(String.format("The array of words is invalid. The number of unique characters differs in the first and last character arrays\n" +
                    "first letters: %s, last letters: %s", firstLetters, lastLetters));

        for (Map.Entry<Character, Integer> pair : firstLetters.entrySet()) {
            if (pair.getValue() != lastLetters.get(pair.getKey()))
                throw new WordArrayException(String.format("The array of words is invalid. The number of '%s' character in first letters is %d, and in last letters is %d%n",
                        pair.getKey(), pair.getValue(), lastLetters.get(pair.getKey())));
        }

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

        System.out.println("Verification passed");
    }

    private void simplify() throws WordArrayException {
        List<Word> sameWords = words.stream().filter(w -> !w.isDifferentLetters()).collect(Collectors.toList());
        words.removeAll(sameWords);
        for (Word sameWord : sameWords) {
            for (Word word : words) {
                if (word.getFirstLetter() == sameWord.getLastLetter() || word.getLastLetter() == sameWord.getFirstLetter()) {
                    joinWords(word, sameWord);
                    break;
                }
            }
        }

    }

    private void joinWords(Word w1, Word w2) throws WordArrayException {
        if (!(w1.getFirstLetter() == w2.getLastLetter()) && !(w1.getLastLetter() == w2.getFirstLetter()))
            throw new WordArrayException(String.format("Can't join words: '%s' and '%s'", w1, w2));

        if (w1.getFirstLetter() == w2.getLastLetter()) {
            words.add(new Word(String.join(" ", w2.getString(), w1.getString())));
            firstLetters.merge(w1.getFirstLetter(), -1, Integer::sum);
            lastLetters.merge(w1.getFirstLetter(), -1, Integer::sum);
        } else if (w1.getLastLetter() == w2.getFirstLetter()) {
            words.add(new Word(String.join(" ", w1.getString(), w2.getString())));
            firstLetters.merge(w1.getLastLetter(), -1, Integer::sum);
            lastLetters.merge(w1.getLastLetter(), -1, Integer::sum);
        }
        words.remove(w1);
        words.remove(w2);
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

    @Override
    public String toString() {
        int d = 0;
        for (Word w : words) if (w.isDifferentLetters()) d++;
        return "WordArray@" + this.hashCode() + "{" +
                "\ncount words = " + words.size() +
                "\ncount differedLettersWords = " + d +
                "\nwords = " + words +
                "\nletters = " + firstLetters +
                "\n}";
    }

    class WordArrayException extends Exception {
        public WordArrayException(String message) {
            super(message);
        }
    }
}
