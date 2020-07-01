package words.permutation;

import java.util.*;

class CycleList {
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
        while (first++ < list.size()) {
            if (list.get(first).getFirstLetter() == ch) break;
        }
        List<Word> tempList = new ArrayList<>();
        list.stream().skip(first).forEachOrdered(tempList::add);
        list.stream().limit(first).forEachOrdered(tempList::add);
        list = tempList;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
