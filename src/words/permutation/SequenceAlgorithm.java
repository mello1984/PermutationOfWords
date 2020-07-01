package words.permutation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class SequenceAlgorithm {
    List<Word> words;

    SequenceAlgorithm(String[] strings, boolean printResultOfEachTry) {
        words = new LinkedList<>();
        Arrays.stream(strings).forEach(s -> words.add(new Word(s)));
        findSequence(printResultOfEachTry);
    }

    private void findSequence(boolean printResultOfEachTry) {
        List<Word> result = null;
        for (int i = 0; i < words.size(); i++) {
            List<Word> clone = new LinkedList<>(words);
            result = new LinkedList<>();
            result.add(clone.remove(0));

            boolean next = true;
            while (next) {
                next = false;
                for (Word word : clone) {
                    if (word.getLastLetter() == result.get(0).getFirstLetter() || word.getFirstLetter() == result.get(result.size() - 1).getLastLetter()) {
                        if (word.getLastLetter() == result.get(0).getFirstLetter()) result.add(0, word);
                        else result.add(word);
                        clone.remove(word);
                        next = true;
                        break;
                    }
                }
            }
            if (printResultOfEachTry) printResultOfTry(result, i);
            if (clone.size() == 0) break;
            else words.add(words.remove(0));
        }
        words = result;
    }

    private void printResultOfTry(List<Word> result, int i) {
        StringBuilder sb = new StringBuilder();
        result.forEach(w -> sb.append(" ").append(w.getString()));
        System.out.printf("try %d, result size %d, result: %s%n", i, result.size(), sb.deleteCharAt(0).toString());
    }

    String getResultString() {
        StringBuilder sb = new StringBuilder();
        words.forEach(word -> sb.append(" ").append(word.getString()));
        return sb.deleteCharAt(0).toString();
    }
}
