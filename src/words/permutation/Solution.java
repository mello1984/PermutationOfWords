package words.permutation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Solution {
    private static final String testSequence1 = "ab aa ba ac ab ca cc Aa Ab ba bA aC CC bb Ca BB";
    private static final String testSequence2 = "rj KK YB Jr CC jr JJ lm JK DD dD rr dd MM BY Cr mm DU GG rC YY VG Mr KJ Dd GV rJ UD rM ml";
    private static final String testSequence3 = "pE tZ pp ee ii dd OY mm BB Dx mm Do NN IX ps eE vS XE Ru Zt yU HH AA xD Ck " +
            "Gn EE LL Nx CC Sv YY hh gw hh Xm aP Rt LA bh vv tt NR vS GG mP dA NN NN Ee eH LL kC PP En AX ZG BB wg hn Bz Ep " +
            "hb OO uu xx GZ He Cw Ld KK Ad pE BB OO ku vh Sv Qo nh LT bb AA nG hM EE oQ CC sp Pg PP mX DD dL SS gP SS Py LL " +
            "ji EX RA Pa AR ss AL ZZ Uy Et KH RN OO xN wC Pm XI YO ij hv gg Ep Mh yP Eb oo BB QQ TL II bE yy ww nE uu bb tE " +
            "uR Ng zB PP pp gN tR NN HK XA GG tt uk oD";
    private static final String testSequence4 = "a b aa ba ab";
    private static final String testSequence5 = "as b aa ba ab";
    private static final String testSequence6 = "dd ab aa ba ac ab ca cc Aa Ab ba bA aC CC bb Ca BB ad da";
    private static final String testSequence7 = "ab cd ac";

    public static void main(String[] args) throws WordArray.WordArrayException {
        WordArray wordArray = new WordArray(testSequence3.split(" "));
        String result = wordArray.getResultString();
        System.out.println("Result: " + result);
        System.out.println("Check: " + check(result));

        System.out.println("=====================================================");
        String newString = generateArray(5);
        System.out.println("New String: " + newString);
        wordArray = new WordArray(newString.split(" "));
        result = wordArray.getResultString();
        System.out.println("Result: " + result);
        System.out.println("Check: " + check(result));

    }

    private static boolean check(String string) {
        if (string == null || string.length() == 0) return false;
        if (Character.toLowerCase(string.charAt(0)) != Character.toLowerCase(string.charAt(string.length() - 1)))
            return false;

        String[] strings = string.split(" ");
        for (int i = 0; i < strings.length - 1; i++) {
            char l = Character.toLowerCase(strings[i].charAt(strings[i].length() - 1));
            char f = Character.toLowerCase(strings[i + 1].charAt(0));
            if (l != f) return false;
        }
        return true;
    }

    private static String generateArray(int countThird) {
        boolean out = false;
        String result = "";
        while (!out) {
            List<String> list = null;
            Random random = new Random();
            list = new LinkedList<>();
            for (int i = 0; i < countThird; i++) {
                int x = (random.nextInt(26) + 65) + (Math.random() > 0.5 ? 32 : 0);
                int y = (random.nextInt(26) + 65) + (Math.random() > 0.5 ? 32 : 0);
                list.add(String.valueOf((char) x) + (char) y);
                list.add(String.valueOf((char) y) + (char) x);
                list.add(String.valueOf((char) x) + (char) x);
            }
            Collections.shuffle(list);

            String[] strings = new String[list.size()];
            WordArray wordArray = null;
            try {
                wordArray = new WordArray(list.toArray(strings));
                if (check(wordArray.getResultString())) result = wordArray.getResultString();
                out = true;
            } catch (WordArray.WordArrayException e) {
                //Nothing
            }
        }
        return result;
    }

}
