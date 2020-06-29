package words.permutation;

import java.util.*;

public class CycleList {
    List<Word> list;
    Set<Character> characterSet;

    public CycleList(List<Word> list) {
        this.list = new ArrayList<>(list);
        characterSet = new HashSet<>();
        list.forEach(w -> {
            characterSet.add(w.getFirstLetter());
            characterSet.add(w.getLastLetter());
        });
    }

    void regroup(char ch) {
        System.out.println("begin regroup: " + ch + ": " + list);
        int first = 0;
        while (first < list.size()) {
            if (list.get(first).getFirstLetter() == ch) break;
            first++;
        }
        List<Word> tempList = new ArrayList<>();
        list.stream().skip(first).forEachOrdered(tempList::add);
        list.stream().limit(first).forEachOrdered(tempList::add);
        list = tempList;
        System.out.println("end regroup: " + ch + ": " + list);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
