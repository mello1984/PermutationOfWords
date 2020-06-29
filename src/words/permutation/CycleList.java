package words.permutation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CycleList {
    List<Word> list;
    Map<Character, Integer> firstLetters;
    Map<Character, Integer> lastLetters;

    public CycleList(List<Word> list) {
        this.list = list;
        firstLetters = new HashMap<>();
        lastLetters = new HashMap<>();
        list.forEach(w -> {
            firstLetters.merge(w.getFirstLetter(), 1, Integer::sum);
            lastLetters.merge(w.getLastLetter(), 1, Integer::sum);
        });
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
