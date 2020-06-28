package words.permutation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Analyzer {
    private static final String testSequence1 = "ab aa ba ac ab ca cc Aa Ab ba bA aC CC bb Ca BB";
    private static final String testSequence2 = "rj KK YB Jr CC jr JJ lm JK DD dD rr dd MM BY Cr mm DU GG rC YY VG Mr KJ Dd GV rJ UD rM ml";
    private static final String testSequence3 = "pE tZ pp ee ii dd OY mm BB Dx mm Do NN IX ps eE vS XE Ru Zt yU HH AA xD Ck " +
            "Gn EE LL Nx CC Sv YY hh gw hh Xm aP Rt LA bh vv tt NR vS GG mP dA NN NN Ee eH LL kC PP En AX ZG BB wg hn Bz Ep " +
            "hb OO uu xx GZ He Cw Ld KK Ad pE BB OO ku vh Sv Qo nh LT bb AA nG hM EE oQ CC sp Pg PP mX DD dL SS gP SS Py LL " +
            "ji EX RA Pa AR ss AL ZZ Uy Et KH RN OO xN wC Pm XI YO ij hv gg Ep Mh yP Eb oo BB QQ TL II bE yy ww nE uu bb tE " +
            "uR Ng zB PP pp gN tR NN HK XA GG tt uk oD";
    private static final String testSequence4 = "a b aa ba ab";
    private static final String testSequence5 = "as b aa ba ab";
    private static final String fileNameForTesting = "d:/test/test3.txt";
    private static final String fileNameForGeneration = "d:/test/test5.txt";


    public static void main(String[] args) throws IOException {
//        String[] words = Files.lines(Paths.get(fileNameForTesting)).flatMap(s -> Arrays.stream(s.split("\\s+"))).toArray(String[]::new);
        String[] words = testSequence2.split("\\s");
        System.out.println("Original array - " + words.length + " words: " + Arrays.toString(words));
        String[] simple = checkAndSimplify(words);
        StringBuilder orderedWords = getLine(simple);
        if (orderedWords.length() > 0)
            System.out.println("Changed array - " + orderedWords.toString().split("\\s").length + ": " + orderedWords.toString());
//        System.out.println(generateArray(50));
    }

    //Maybe I should make exceptions instead of messages
    private static String[] checkAndSimplify(String... words) {
        if (words == null || words.length == 0) {
            System.out.println("The array of words is invalid, " + words == null ? "it's null" : "it's length is 0");
            return new String[0];
        }

        Map<Character, Integer> firstLetters = new HashMap<>();
        Map<Character, Integer> lastLetters = new HashMap<>();

        Arrays.stream(words).forEach(s -> {
            firstLetters.merge(Character.toLowerCase(s.charAt(0)), 1, Integer::sum);
            lastLetters.merge(Character.toLowerCase(s.charAt(s.length() - 1)), 1, Integer::sum);
        });

        if (firstLetters.size() != lastLetters.size()) {
            System.out.println("The array of words is invalid. The number of unique characters differs in the first and last character arrays");
            return new String[0];
        }

        for (Map.Entry<Character, Integer> pair : firstLetters.entrySet())
            if (pair.getValue() != lastLetters.get(pair.getKey())) {
                System.out.printf("The array of words is invalid. The number of '%s' character in first letters is %d, and in last letters is %d%n", pair.getKey(), pair.getValue(), lastLetters.get(pair.getKey()));
                return new String[0];
            }
        System.out.println("Verification passed, letters: " + firstLetters);
        return simplify(words);
    }

    private static String[] simplify(String... words) {
        List<String> differing = new ArrayList<>(Arrays.asList(Arrays.stream(words).filter(s -> Character.toLowerCase(s.charAt(0)) != Character.toLowerCase(s.charAt(s.length() - 1))).toArray(String[]::new)));
        List<String> same = new ArrayList<>(Arrays.asList(Arrays.stream(words).filter(s -> Character.toLowerCase(s.charAt(0)) == Character.toLowerCase(s.charAt(s.length() - 1))).toArray(String[]::new)));

        System.out.println("differing: " + differing);
        System.out.println("same: " + same);
        System.out.println();
        for (int i = same.size() - 1; i >= 0; i--) {
            char ch = Character.toLowerCase(same.get(i).charAt(0));
            int j = 0;
            boolean next = false;
            while (j < differing.size() && !next) {
                char chD = Character.toLowerCase(differing.get(j).charAt(differing.get(j).length() - 1));
                if (ch == chD) {
                    differing.set(j, differing.get(j) + " " + same.remove(i));
                    next = true;
                }
                j++;
            }

        }
        System.out.println("differing: " + differing);
        System.out.println();

        return differing.toArray(new String[differing.size()]);

    }

    private static StringBuilder getLine(String... words) {
        StringBuilder sb = new StringBuilder();
        boolean resolve = false;
        int initialCount = 0;

        while (!resolve) {
            List<String> list = new LinkedList(Arrays.asList(words));

            int initialDiffering = list.size();
            while (initialCount < list.size() && !resolve) {
                sb = new StringBuilder().append(list.remove(initialCount));
                while (list.size() > 0) {
                    boolean added = checkListAndAddWord(sb, list);
                    if (!added) break;
                }
                if (list.size() == 0) resolve = true;
                else {
                    initialCount++;
                    System.out.printf("try: %3d, length: %3d, initial size: %3d%n",
                            initialCount, sb.toString().split("\\s").length, initialDiffering);
                    break;
                }
            }
            if (initialCount >= words.length) {
                System.out.println("The solution is not found");
                return new StringBuilder();
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
                else sb.insert(0, " ").insert(0, list.remove(i));
                next = true;
            }
            i++;
        }
        return next;
    }

    private static String generateArray(int countThird) {
        Random random = new Random();
        List<String> list = new LinkedList<>();
        for (int i = 0; i < countThird; i++) {
            int x = (random.nextInt(26) + 65) + (Math.random() > 0.5 ? 32 : 0);
            int y = (random.nextInt(26) + 65) + (Math.random() > 0.5 ? 32 : 0);
            list.add(String.valueOf((char) x) + (char) y);
            list.add(String.valueOf((char) y) + (char) x);
            list.add(String.valueOf((char) x) + (char) x);
        }
        Collections.shuffle(list);
        return String.join(" ", list);
    }

    private static void generateArray(int countThird, String fileName) throws IOException {
        Files.writeString(Paths.get(fileName), generateArray(countThird));
    }
}