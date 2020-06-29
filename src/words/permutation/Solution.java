package words.permutation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Solution {
    private static final String testSequence1 = "ab aa ba ac ab ca cc Aa Ab ba bA aC CC bb Ca BB";
    private static final String testSequence2 = "pE tZ pp ee ii dd OY mm BB Dx mm Do NN IX ps eE vS XE Ru Zt yU HH AA xD Ck " +
            "Gn EE LL Nx CC Sv YY hh gw hh Xm aP Rt LA bh vv tt NR vS GG mP dA NN NN Ee eH LL kC PP En AX ZG BB wg hn Bz Ep " +
            "hb OO uu xx GZ He Cw Ld KK Ad pE BB OO ku vh Sv Qo nh LT bb AA nG hM EE oQ CC sp Pg PP mX DD dL SS gP SS Py LL " +
            "ji EX RA Pa AR ss AL ZZ Uy Et KH RN OO xN wC Pm XI YO ij hv gg Ep Mh yP Eb oo BB QQ TL II bE yy ww nE uu bb tE " +
            "uR Ng zB PP pp gN tR NN HK XA GG tt uk oD";
    private static final String testSequence3 = "wk KK ii ie ET vv Vp vj VP og ww Wo cB bb VX xx RW ww NS Bz bB Ba uF FF FF FR fH hh qq qS rc bb Bn ki ii tk WA AA uu ud RR rU rv LL LU Bt pG KF YI ff FU WW wl KJ JJ dR oM ll lT pE EE dx fC WL pg GG ax XX mo MS gg gE qb BB Qs SS JP pp KT rr RP oo OQ oF Iy oY yy fC CC Qe EE eR RR ZF WW Wz mU JW AA Aw CC Co JB ZY Zh Ou GT QL LL Bi ii LL LQ ba qq qc HH hD mR MN xx xh Um XX XQ pm mm Nc cc CC cI lB dd dW cX xx WJ xM ct Fo NC ee EB pY YY HF HH HJ Wf zz ZK GJ Uv zA Ea zy dh HH SV vv oe Nm MM II It SJ JJ fu uu lu nm ST tT ON nn GP IP PP eM EL lP TT tH aQ eN Ne qx sc OG Te EE Me xx xC CC CB hZ DD dM iD bQ HC cc EE ex Yk kk mO YG GG mJ jj oE WI WW Wx ee Ed Oy OO of cL LL UF FF fQ KQ YY yV Rj jj kk ki Bp nQ QQ eA SN gS SS ve eb BB LQ uw iz xx xm uY YY yw dj jj PP Ph Yi dx GS hd DD YY yl ff fL uA AW JJ jO cq qq vE EE Wd no OO hF FF Cl LL KA aa dv sk Xc CC hx kH NN nw QQ qz Wf ff fQ uu UX LK HH hu ss sf KL BB BE Mg zz zv wC FW Yy yv ZO cB BB aN oo Op ao oo ag GG Gj kk kJ ih HH NN nJ iA YR RR rm zz ZX hM MM tS GG gj eJ jj mO aa AM Nn Nu Hp PP qq QN Px Uc mY EZ ZZ yZ aR lJ Bk qF cy yy zU Hq Dh HH pM MM nn NR cM fT An nn Ae Gm mm RN nn Wn sa wq QQ Bm MM VJ jj xN nn EE Et YV VV VJ JY lx nG GG Mo II iA rd DD lB CK KK GA AA nf WQ UU uh vv Vb DE EE wF ON qq Qr hs ss KA YL qB Gc cc Gp rg dW LX KE Fw ju ll Ln Xm uu UY yG mc CC po oo eN HH hi xx XP QK LH fq ii IE VO JL LL FF fb cK oQ vV ve FL ll bb Bc zx MM mF pL hr rr UU Uv KZ zz Bq CM aQ qq gg Gx cP pp vE Me oo on yc cc Qx jj jd II IE zz zi XI ii OO OD WU UU UU ue kR SK kk ib GG gA Ef FF mm mn Kc uj Tj xx xh Sz KK KS Eb BB SS Sg QQ QU yX LA Bh pO oo RR rv WW WN Bl AG GG VL PX xx dd df Um kR nK KK aa Au mr nm mm AA Ag cr RR JK KK jj jG aM mm uf Qa AA hJ Qe EE Pc cc Bj Bf Dp TT Tu qA ob oR pp Pn vK bV vv VV Vy cr to PP pT oW WW tU dE CC cG om mm ZZ zs KA QW EE eK zF lK II Il nc OV EK xW WW VV vk dd Dy oR Qj JJ VG GG ll lg AA AY yo DX xx NP PP SB BB Hc ZM mm xx xo YJ SS Sy oG GG Qf ff FT BB BW Vr rr rr RV sz zz oS Cg NM ss sU zq QQ aT TT UQ RR rQ ga aa aH wI II Md ss sY Jl Mc qb CM MM yR RR Wd dd AA Aj iH MM MT RF Wu UU aB Bb fB jd Mn aa AH rM MM lZ zz JV VV Jh hh nP PP pv UR hj oM uO zY iI ih AA AE Ur RR rr Rl dM Sk hi Pk rh cY GG GJ jQ Qq Wn NN Lu BB BM kk kM uA AA dd DU RR Rp EF ff ag SU nJ JJ ED DD rt tt Fq zO uu uY Nw WW Aq OJ JJ NN Ne BB be XF xx XV ph fs SS qq qU kk KS BB BS Cf ff jB wx XX uz ZZ sh HH Kv cU UU iu Rk lI ii WW we va UU UW ki II ip mz Vt TT RR rh xQ Mo Wy xd hJ JJ Qb pV vv Uv vv Bl lv zz zu ly yy nn nW Zm ND dD rr RA tv vv kG el CG pJ JJ px XX NN Nu cc cA kk kT GK kk UU uW Yz xq LY Nx xx YY yE PP PN WW Wr CC cE xO OO tt ta Lp My YY tt Ta xM mm HW uY YY DD DC CC cd jA AA Fj JJ hp PP VV vZ ss sR Gf ff SS SK Yu XX xp Cf QQ QF mU uu oo oS bb BM OE ee ox om RI pr fo oo yD dd nn np NP Vd kq qQ jT eY yy cB bb rr RK YY Yu ul oo op BB BE IY vv vh Dq qq vv VL nn Nv Po OO Ra ZW ww im EF ff TT TS BO oT TT XD dd SF ff ox xx Pu UU PP pu vp ij Md DD kH hh UL ul ll vH HH fc pp PW yy yl lM YZ Bf ik bU xx xw PA Sg nW KK kt nO bf ff xx Xk WK kk NS ss So OO jh hh Zy ah HH LL lu TN EI ii QL AK jk KK Jx uu uf uV fn dP pp Wm LE vv vU Gq WW ws ff FE WP PP GO bo jj jR FK IY FF FC MW ph vA AA rQ qq zW WW hh hv Hf FF zu uu ha ww wn ud dd ss SQ Sq QQ px Mk KK nZ ZZ DD DN mB BB hX CP pp Wz zz MC cc dj gT TT BO oo fh by Os qo OO PC CC PP pq HV wt tt lD YU uu pp PE bq Kn NN SS sh Uz Mh hh ZJ Og gg cc CF Xh ej mm mX zZ zh GG gn iT NK cV Qa QQ qZ ik kk mX yb OZ ui ii nn NC OC ag DX xx QX xx rr rE ff fH yM mm wF rW Kj jj NR vd vv VR AA Ai St EA aa er NB BB WG GG NN no uu Uc yZ Mo TE Np pp PJ fc cc lp mp pp nN NO kK Kz sk vr RR zb nx DO Oo rr RY bv MC CC Oj jj NM MM aE Rd ZS xl bc hz Zz UU UD Cp TM MU ZM MM BU eq qq ig GG Ha aa CH HH OO Ox nk Kk mA TF ff Gc sC uc WP zz zU rp eo sw WW ww WN OO Ou WB BB AA Ar CD dd DK nH NP pp Am BE ZZ zW XD DD JJ Jm SS sA gM Bf EI ii Rv xx xe xd WW wN ZJ jj Ru hX XX dd dr bz EE eQ pN tc ev Id dd ga qq qk hh hx nn nm pG xG Rs SS kk KR sY UU uz FR Tf MH HH zk kk cs FF fG Be nA Xu kd Dd SS Sg JG hp Tt tP Er RR bQ pi ii Vy yy yb BB qZ zz nn Nr pn NN AA AK oG gg AO yk KK BB Bc vU iK ph hh vO vt dW oE EE Qs Xi SG GG vv vW ds ss Zo oo XX xu cF ZZ zs hY YY hP PP Wd ne EE Qx JE xx XZ sw jj jo dd dI Cs GG gp Us ss AO OO NN No RR RX II iK iE JJ jW VV VI cf tT Tg kk kf Dl JO oo XX XT WF Bb bj QU Tt ti Pp Pc hH hM VV VU iY bb BA iR WW wO LL LX oy fP pp HA AA XZ zz QP PP gL qR qq Qj Ld xx xN cc Cf mn nn dV kF OO oS cw AN nn ly yy mr Oc CC mb Om zG gg pB Zi lz zS mm MZ SS sz tV Ne IW GG GV DE Zs JD DD CO av ff FA Cx XX Ke EE xb bb Zv vv Re EE mh BP Ei uu Un Az xz wm mw LV Ht oC cc qB fu FF fx ZJ jj cD zz zn YZ iX yk Mh fB qn AQ hp tH pl AB bb HH hl Xz ED dd JO UL LL sx XX Tk KK LV vv ee eT QQ qD lH hh CC CS BB Bc gS SS hf EG kS RR re xo Zl LL BX xx FF FS tt TM TT tS vG GG ET BB bG lz ZZ Gr RR HL ll JJ jw Gk CC CK hm AR RR MO oo Ag AA aJ qm nQ UQ QQ Iw WW fB ED Ro Zf kv VV zA cc cf HH HN AL tq zX tt tm re pp PI Wr Ep PP MM MJ Eo cc cN VY Gz Gy mZ ik MR oo OD ga gC Lr WM ph ll lR PP PN Rm mI iI QQ qf xh mM MD YE EE ii Ic uc MM mt hD Dd jw xp Gv VV fd dd EB bb Uq qq cc cP ow vt OA yy Yh oo oW GE Md DD LU ki Ut TT EE EY ee EA PO BB BN CO oo Sv LF ff US jK kk qf UU uy dD DJ ds hh Hv Hc NC SS sQ LU UU dE EE ah uN nn SU xx Xh Tv vv YY yS rW HL LL ZZ zp cI Cr rr au iu uU hh ha vm IR uu ux jv kt ss Sk aG hf xx xS YY Yc AR St tt cc ch tv Az MB bb kk ky KD DD VQ FF fE yu uu ZE eN NN AA Af NN nL Fw WR rr cc Cb JJ jg rr Ry QV xx XR Gx Zd DD LL lw Ii iC Ht tt UN di UB ZC cc xT tt YA AA Xy Eo oo fZ mm ME ss sh Zq QQ cw ww vh hh vT TT kk kN WK KK wa AA QN hx XX fQ QQ TC ee Ec jj Ja UM gA aa HH hl Mh Hh Oz Dg sz zz EE ep Sx xx JW Bl LL JJ jF Gz zz EZ Cw sd Rl ll Qr RR TT Tx wc qk kk oo OC rT tt Hi lE EE mm MZ TX XX zz ZX BJ HL yz zz fW ww zB NT SH hh Aw ww ei ii Ye ee Di iz zz Lw GS NN Nm ee EM Jk KK bm tI ii dV uu UV eB RM OO oa Uu Um dc cc Ow WW TT tU tv YI ww WZ fl MM Mr KK KW LL LH Ic on NN RU rA AA BB bx gg gj MM mG kS ss tk AL zm fq QQ KN vx ZR OO oN CV VA KK KM Ml QS SS xI CA WH iZ Ub bb Bc MK kk AP ZZ ZE jn yR ih Qo EJ Wb BB Tv id JF ff Xm mm Ev SE tq XX xf PG pM VY JJ JZ gA AA nB bb bC cc ps zK Kk NW ww SS sw NH HH Ld FZ sd dd ii IY hY YY On Nl uR wY nu UU jj Je up PP qo rz ZZ kk ki ab YY Yu ng GG Ci iI CN sQ EP PP Ut bq qq cc cO PN wj WA yy yV rd DD II Iw LL Lc vS oe vT YY Ys CD DD vr rr AC Tr RR rc PB Tr rr xP SZ Ys un NN Hn TE AA Av bb bW VJ jj yy YH cc cm xx XL gl ll ev Vv mP uO oo hr GG GY dL iB BB kk kw DC bb BW II ik Gl WW wk GC it TT oM pp pe vv VJ xQ ee EQ fE ff fC LJ JJ cu rr rV wy Hw WW Uy MM mN JZ jj JZ AL LL oO oP ll lp MM mo AV SN NN lh hh gD eu uu kw WW vl Pl jQ qq ws mm Mx zS Wb bb AK le go CN GU Ti RN PV dd dk ll lC YI ii gr hh hc aH ls PV vv aa AF JV vv wo Mp rr rN uu uP GN va as BB by ff Fu zf FF HH Hl xx xO mm Mp DO OO jA aa QE ee YV pR rr IV vv RA Fk Ov aT tt BB bW XL RR rC ll LH cH ll Lg tt tV Cq QT ws ss kk KG DD dZ dr GG GR MT TT qG ZZ zG KK KW PP Pd zA So oo tE EE hM ks xv wu LW RF FF zK KK kY YY eM fL av Go Mx qq qV yO EE en Qx US Vd rH hh NN nU cG Ii Im qo oo UQ qq UG Uz zz VH kB AA Ag JM mm cu LV HH Hf mW Ha Wj JJ OZ zz pz ZZ cU KC cc QQ QU ll LA ik ff Fh Nx XX Pt tt MO Wr NO AA aw yy Yh NE xx xs yI El LL Bq Dc uS ss Vq qq Ed gA fz zz ot Ym Xa aa vv VS nM kk Ki zl wc cc Jn NN qq Qf BF yy Yo aa An SM AA AE pp PQ Lf sp hh ha JV vv RW mq jb bB Ev at tt Xm mm EE Ev du uu Qn RG gg kS ss OJ jj xa AA Ac cc JJ Jp cc CZ Ca aa ji RR Rk Yu UU sl ds SS fW tT tu NG Fz mm MN cE ee ki fW ww ll lh xn MM My uN nn Sz GG gi aC CC RZ oo oq Ol uu UL JV VV yM vy yY JH hh mi BB bE II IX xz YY YR MM mX ky WB BB uT TT uY hi Ii qq Qn II il kj jj KK Kz Vc HH hv gg Gz SF FF xx Xw JJ JG uD Ki Qj uP pp Vu VP xZ nn Nk ff FS ll lB CT NQ cI ii du UU LL lR mN NN pD JL oZ ZZ DM Mm wL SC CC JF ff jQ KK Ki ut Tt GG Go vr Ag GG rr rv Eg gg kK kn lG YY yn Fc ww wI gg Gn zz Zn Ga qq qe of ff Ro RY yy TK WR rT Du Aj JJ GG gO CC CN zj oq ss sj GG GW tB vN NN Wo OO AA AW ah hh MM MA vv vt lF Zy VN NN Fj fh wA aa VV VJ bi qH zz zr Fm mm UF FF kk kR vb qp pp Vt kJ od ua mx xx LA NQ QQ ww wH hh Hk WW Wf Ai FB cB BB cc cY sJ KK kX aB bb OO oq So Ox XX AA aX MB VV vr Hb Fd ss Sk ZZ zl zY ff FJ ZE EE Td NV VV zi hj JJ OW PV mU Rk BB Ba kk KC EE er hp Pm ff FE vt dd dM iK kk Om OP XU uu mv zz ZO FX ks wj jj hs SS nq OM rm TQ Zx ZY YQ ik kk OB eQ QQ oj JJ OO OB LJ Yc lN lO TG oo OM XX XB VC Na EB dT yy Yp je ES JV vv jg gg QO oo SG qt yU Ix Yw js ss cH hh Az HH HM wX xx vz zz VP PP Kl ZZ Zw kz zz kP WW wA NU dF ff fo rr rG eo rv VV cb MM Ma Xs uL rL Au vR pp Pw li II zG ee EO QA yW Ri Yz ee Ey dd DE SS sd jh jj Jn vv vU PW pl LL xJ Fl sZ dd Dh fk kk HH hs bb BC gS rW WW rd PR rr pH HH tv SG hB HY yy jF pP Pf cn ZZ Zq Ef mn Jh TT To Js QY nj FH wP Ec CC SS Su Gp WJ Lf FF jz xQ tt Ta kq QQ QQ qw VL WO tr RR FQ qq fA AA Qf zs ss Tl ll sX EN wZ zz Ry II Ic Pu uu Rq Jk sO oo OA FU UU HH HS vE pC NW WW As SS Gb BB ny yy HH Hr Qb TT tw do qC dL gg GS nn NA FJ jj ee ew qt xx xG DD dr NN nA nz zz uX Hk ui zx tt Tp bH EE EZ Wv vv lf JS SS";
    private static final String testSequence4 = "a b aa ba ab";
    private static final String errorSequence1 = "ab cd ac";
    private static final String errorSequence2 = "ab ba ab";
    private static final String errorSequence3 = "rj KK YB Jr CC jr JJ lm JK DD dD rr dd MM BY Cr mm DU GG rC YY VG Mr KJ Dd GV rJ UD rM ml";



    public static void main(String[] args) throws WordArray.WordArrayException {
        WordArray wordArray = new WordArray(testSequence1.split(" "));
        String result = wordArray.getResultString();
        System.out.println("Check: " + check(result) + ", result: " + result);

        wordArray = new WordArray(testSequence2.split(" "));
        result = wordArray.getResultString();
        System.out.println("Check: " + check(result) + ", result: " + result);

        wordArray = new WordArray(testSequence3.split(" "));
        result = wordArray.getResultString();
        System.out.println("Check: " + check(result) + ", result: " + result);

//        wordArray = new WordArray(errorSequence1.split(" "));
//        wordArray = new WordArray(errorSequence2.split(" "));
//        wordArray = new WordArray(errorSequence3.split(" "));

//        System.out.println("=====================================================");
//        List<String> newList = generateArrayList(1000);
//        System.out.println(newList);
//        Collections.shuffle(newList);
//        System.out.println(newList);
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

    private static List<String> generateArrayList(int countThird) {
        boolean out = false;
        List<String> result = null;
        int count = 0;
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

            String[] strings = new String[list.size()];
            WordArray wordArray = null;
            try {
                wordArray = new WordArray(list.toArray(strings));
                if (check(wordArray.getResultString())) result = wordArray.getResultListStrings();
                out = true;
            } catch (WordArray.WordArrayException e) {
                count++;
                if (count % 100 == 0) System.out.println(count);
            }
        }
        System.out.println(count);
        return result;
    }

}
