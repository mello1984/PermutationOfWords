package words.permutation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Analyzer {
    private static final String testSequence1 = "ab aa ba ac ab ca cc Aa Ab ba bA aC CC bb Ca BB";
    private static final String testSequence2 = "Hl Ui fz Ij jj Zq ys lH jj jI sr EX Cy zf ks lN mm XE yC Nl yc sy ul MM " +
            "DE lu fD rs ED OO iU qZ sk cy Df ss";
    private static final String testSequence3 = "WG vj NU ar GP DB hI ew YY Lf WW bm EE sc Qy ql lq LD ae pe mb mJ JQ Xe " +
            "iH Qo wO jj Pq gT An gY KP zO UN PY sz kk zj Do xx Qx Tg JM ft Ow nA jh sb qP tf BD hj Vv zs Pq Cc iT xp gX " +
            "Yg eX Hi rj oQ qI SX xZ oQ Ti ra OT Ih Zx OV ea zz aI Nc rL HH px oa qP XP ut yQ MM wF Zv FF ii OO xx cN MM " +
            "Ae PG EE xQ AT YY tu XS DL TO cs Lr Xg jv jz PK kB Qo pp jj oQ eA cC Jm Ia Rh gx Wn fL QJ Mc NL TA OO tt Hg " +
            "DI Bk Iq PX yy hR ut wS jr MJ cM YP oD gH nW rr tu dd tJ Fw Oz SX XS Jt we Sw vP GW bs xg VO ao Qo we kk Pv " +
            "LN kk ew ep vZ ID xx vV";
    private static final String testSequence4 = "a b aa ba ab";
    private static final String fileNameForTesting = "d:/test/test3.txt";
    private static final String fileNameForGeneration = "d:/test/test5.txt";


    public static void main(String[] args) throws IOException {
//        String[] words = Files.lines(Paths.get(fileNameForTesting)).flatMap(s -> Arrays.stream(s.split("\\s+"))).toArray(String[]::new);
        String[] words = testSequence1.split("\\s");
        check(words);
        System.out.println("Original array - " + words.length + " words: " + Arrays.toString(words));
        System.out.println("Changed array - " + getLine(words).toString().split("\\s").length + ": " + getLine(words).toString());
//        System.out.println(generateArray(10, 0.2));
    }

    private static boolean check(String... words) {
        Map<Character, Integer> firstLetters = new HashMap<>();
        Map<Character, Integer> lastLetters = new HashMap<>();

        Arrays.stream(words).forEach(s -> {
            firstLetters.merge(Character.toLowerCase(s.charAt(0)), 1, Integer::sum);
            lastLetters.merge(Character.toLowerCase(s.charAt(s.length() - 1)), 1, Integer::sum);
        });

        if (firstLetters.size() != lastLetters.size()) {
            System.out.println("The array of words is invalid. The number of unique characters differs in the first and last character arrays");
            return false;
        }

        for (Map.Entry<Character, Integer> pair : firstLetters.entrySet())
            if (pair.getValue() != lastLetters.get(pair.getKey())) {
                System.out.printf("The array of words is invalid. The number of '%s' character in first letters is %d, and in last letters is %d%n", pair.getKey(), pair.getValue(), lastLetters.get(pair.getKey()));
                return false;
            }
        return true;
    }

    public static StringBuilder getLine(String... words) {
        if (words == null || words.length == 0) return new StringBuilder();

        StringBuilder sb = new StringBuilder();
        boolean resolve = false;
        boolean needReverse = false;
        int initialCount = 0;
        int difSize = 0;
        while (!resolve && !needReverse) {
            List<String> differing = new LinkedList(Arrays.asList(Arrays.stream(words).filter(s -> Character.toLowerCase(s.charAt(0)) != Character.toLowerCase(s.charAt(s.length() - 1))).toArray(String[]::new)));
            List<String> same = new LinkedList(Arrays.asList(Arrays.stream(words).filter(s -> Character.toLowerCase(s.charAt(0)) == Character.toLowerCase(s.charAt(s.length() - 1))).toArray(String[]::new)));

            int s = same.size();
            int d = differing.size();
            difSize = differing.size();
            while (initialCount < differing.size() && !resolve) {

                sb = new StringBuilder().append(differing.remove(initialCount));

                while (same.size() > 0 || differing.size() > 0) {
                    boolean added = checkListAndAddWord(sb, same);
                    if (!added) added = checkListAndAddWord(sb, differing);
                    if (!added) break;
                }

                if (same.size() == 0 && differing.size() == 0) resolve = true;
                else {
                    initialCount++;
//                    System.out.println("try: " + initialCount + ", length: " + sb.toString().split(" ").length +
//                            " differing: " + differing.size() + " same: " + same.size() + " all worlds: " +
//                            (sb.toString().split(" ").length + differing.size() + same.size()) +
//                            " initial size d: " + d + " s: " + s);
                    break;
                }
            }
            if (initialCount >= words.length - s) needReverse = true;
        }

        needReverse = false;
        initialCount = difSize - 1;
        while (!resolve && !needReverse) {
            List<String> differing = new LinkedList(Arrays.asList(Arrays.stream(words).filter(s -> Character.toLowerCase(s.charAt(0)) != Character.toLowerCase(s.charAt(s.length() - 1))).toArray(String[]::new)));
            List<String> same = new LinkedList(Arrays.asList(Arrays.stream(words).filter(s -> Character.toLowerCase(s.charAt(0)) == Character.toLowerCase(s.charAt(s.length() - 1))).toArray(String[]::new)));

            while (initialCount >= 0 && !resolve) {
                int s = same.size();
                int d = differing.size();
                sb = new StringBuilder().append(differing.remove(initialCount));
                while (same.size() > 0 || differing.size() > 0) {
                    boolean added = checkListAndAddWord(sb, same);
                    if (!added) added = checkListAndAddWordReverse(sb, differing);
                    if (!added) break;
                }

                if (same.size() == 0 && differing.size() == 0) resolve = true;
                else {
                    initialCount--;
//                    System.out.println("try: " + initialCount + ", length: " + sb.toString().split(" ").length +
//                            " differing: " + differing.size() + " same: " + same.size() + " all worlds: " +
//                            (sb.toString().split(" ").length + differing.size() + same.size()) +
//                            " initial size d: " + d + " s: " + s);
                    break;
                }
            }
        }

        return sb;
    }

    private static boolean checkListAndAddWord(StringBuilder sb, List<String> list) {
        boolean next = false;
        int i = 0;
        while (i < list.size() && !next) {
            char listFirst = Character.toLowerCase(list.get(i).charAt(0));
            char listLast = Character.toLowerCase(list.get(i).charAt(list.get(i).length() - 1));
            char sbFirst = Character.toLowerCase(sb.charAt(0));
            char sbLast = Character.toLowerCase(sb.charAt(sb.length() - 1));

            if (listFirst == sbLast || listLast == sbFirst) {
                if (listFirst == sbLast) sb.append(" ").append(list.remove(i));
                else sb.insert(0, list.remove(i) + " ");
                next = true;
            }
            i++;
        }
        return next;
    }

    private static boolean checkListAndAddWordReverse(StringBuilder sb, List<String> list) {
        boolean next = false;
        int i = list.size() - 1;
        while (i >= 0 && !next) {
            char listFirst = Character.toLowerCase(list.get(i).charAt(0));
            char listLast = Character.toLowerCase(list.get(i).charAt(list.get(i).length() - 1));
            char sbFirst = Character.toLowerCase(sb.charAt(0));
            char sbLast = Character.toLowerCase(sb.charAt(sb.length() - 1));

            if (listFirst == sbLast || listLast == sbFirst) {
                if (listFirst == sbLast) sb.append(" ").append(list.remove(i));
                else sb.insert(0, list.remove(i) + " ");
                next = true;
            }
            i--;
        }
        return next;
    }

    private static String generateArray(int countHalf, double sameProbability) {
        Random random = new Random();
        List<String> list = new LinkedList<>();
        for (int i = 0; i < countHalf; i++) {
            int x = (random.nextInt(26) + 65) + (Math.random() > 0.5 ? 32 : 0);
            int y = (random.nextInt(26) + 65) + (Math.random() > 0.5 ? 32 : 0);
            if (Math.random() > sameProbability) {
                list.add(String.valueOf((char) x) + (char) y);
                list.add(String.valueOf((char) y) + (char) x);
            } else {
                list.add(String.valueOf((char) x) + (char) x);
                list.add(String.valueOf((char) y) + (char) y);
            }
        }
        Collections.shuffle(list);
        return String.join(" ", list);
    }

    private static void generateArray(int countHalf, double sameProbability, String fileName) throws IOException {
        Files.writeString(Paths.get(fileName), generateArray(countHalf, sameProbability));
    }
}

