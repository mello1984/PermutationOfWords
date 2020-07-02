package words.permutation;

import java.util.*;

/**
 * The input string is ordered so that the first and last letters of neighboring words match, and all words in the original sequence must be used.
 * For example, "ab ca bc" - > "ab bc ca". 2 alternative algorithms are executed: sequential increment (SequenceAlgorithm class) and cyclic algorithm (CyclicAlgorithm class)
 */
public class Solution {
    private static final String testSequence1 = "ab aa ba ac ab ca cc Aa Ab ba bA aC CC bb Ca BB";
    private static final String testSequence2 = "LK Ud aa LL WY OK lQ cK MK MM UU ll kk ss LJ HH dU dI aQ HJ ky ik mL MW jT KO JL lz ki Us WW QQ zl jj KK Id yk sU YW WB Ql KL JJ LL KK Tj JH OO ll Kc WM KM kk aC Lm Qa dd BW Ca WW";
    private static final String testSequence3 = "pE tZ pp ee ii dd OY mm BB Dx mm Do NN IX ps eE vS XE Ru Zt yU HH AA xD Ck Gn EE LL Nx CC Sv YY hh gw hh Xm aP Rt LA bh vv tt NR vS GG mP dA NN NN Ee eH LL kC PP En AX ZG BB wg hn Bz Ep hb OO uu xx GZ He Cw Ld KK Ad pE BB OO ku vh Sv Qo nh LT bb AA nG hM EE oQ CC sp Pg PP mX DD dL SS gP SS Py LL ji EX RA Pa AR ss AL ZZ Uy Et KH RN OO xN wC Pm XI YO ij hv gg Ep Mh yP Eb oo BB QQ TL II bE yy ww nE uu bb tE uR Ng zB PP pp gN tR NN HK XA GG tt uk oD";
    private static final String testSequence4 = "KK In BB uP ko jj ah ff oe KS qq MM Tn Ki tt ff tu uu nn oc wZ uZ jD wM Ta zR SK JA TT oP rk Rz qq eo Ij Kf aT EE fK fN qq qY Uj ub BF YY He BQ Yq QB aa eH ee Dq BW co cc jU Ny JJ Ee Nf WB Tt Mw eE RR bu ee EE nn TT Ul ee Bn Ac EN MY oo jj Pu oo bb Dj WW jx se nB mm lU yN tX es aR Zw ng qq XX ut gn ha rr iJ jI Xt MM YB AA mU ll Ws sW SS oo kr WW Rc Ji cR BB RR AJ qD gg YM iK BY Ra gS xj ok nI Po cA Sg Zu aa BB BB nT Um ZZ ww tT cc yy jj FB NE II";
    private static final String testSequence5 = "wk KK ii ie ET vv Vp vj VP og ww Wo cB bb VX xx RW ww NS Bz bB Ba uF FF FF FR fH hh qq qS rc bb Bn ki ii tk WA AA uu ud RR rU rv LL LU Bt pG KF YI ff FU WW wl KJ JJ dR oM ll lT pE EE dx fC WL pg GG ax XX mo MS gg gE qb BB Qs SS JP pp KT rr RP oo OQ oF Iy oY yy fC CC Qe EE eR RR ZF WW Wz mU JW AA Aw CC Co JB ZY Zh Ou GT QL LL Bi ii LL LQ ba qq qc HH hD mR MN xx xh Um XX XQ pm mm Nc cc CC cI lB dd dW cX xx WJ xM ct Fo NC ee EB pY YY HF HH HJ Wf zz ZK GJ Uv zA Ea zy dh HH SV vv oe Nm MM II It SJ JJ fu uu lu nm ST tT ON nn GP IP PP eM EL lP TT tH aQ eN Ne qx sc OG Te EE Me xx xC CC CB hZ DD dM iD bQ HC cc EE ex Yk kk mO YG GG mJ jj oE WI WW Wx ee Ed Oy OO of cL LL UF FF fQ KQ YY yV Rj jj kk ki Bp nQ QQ eA SN gS SS ve eb BB LQ uw iz xx xm uY YY yw dj jj PP Ph Yi dx GS hd DD YY yl ff fL uA AW JJ jO cq qq vE EE Wd no OO hF FF Cl LL KA aa dv sk Xc CC hx kH NN nw QQ qz Wf ff fQ uu UX LK HH hu ss sf KL BB BE Mg zz zv wC FW Yy yv ZO cB BB aN oo Op ao oo ag GG Gj kk kJ ih HH NN nJ iA YR RR rm zz ZX hM MM tS GG gj eJ jj mO aa AM Nn Nu Hp PP qq QN Px Uc mY EZ ZZ yZ aR lJ Bk qF cy yy zU Hq Dh HH pM MM nn NR cM fT An nn Ae Gm mm RN nn Wn sa wq QQ Bm MM VJ jj xN nn EE Et YV VV VJ JY lx nG GG Mo II iA rd DD lB CK KK GA AA nf WQ UU uh vv Vb DE EE wF ON qq Qr hs ss KA YL qB Gc cc Gp rg dW LX KE Fw ju ll Ln Xm uu UY yG mc CC po oo eN HH hi xx XP QK LH fq ii IE VO JL LL FF fb cK oQ vV ve FL ll bb Bc zx MM mF pL hr rr UU Uv KZ zz Bq CM aQ qq gg Gx cP pp vE Me oo on yc cc Qx jj jd II IE zz zi XI ii OO OD WU UU UU ue kR SK kk ib GG gA Ef FF mm mn Kc uj Tj xx xh Sz KK KS Eb BB SS Sg QQ QU yX LA Bh pO oo RR rv WW WN Bl AG GG VL PX xx dd df Um kR nK KK aa Au mr nm mm AA Ag cr RR JK KK jj jG aM mm uf Qa AA hJ Qe EE Pc cc Bj Bf Dp TT Tu qA ob oR pp Pn vK bV vv VV Vy cr to PP pT oW WW tU dE CC cG om mm ZZ zs KA QW EE eK zF lK II Il nc OV EK xW WW VV vk dd Dy oR Qj JJ VG GG ll lg AA AY yo DX xx NP PP SB BB Hc ZM mm xx xo YJ SS Sy oG GG Qf ff FT BB BW Vr rr rr RV sz zz oS Cg NM ss sU zq QQ aT TT UQ RR rQ ga aa aH wI II Md ss sY Jl Mc qb CM MM yR RR Wd dd AA Aj iH MM MT RF Wu UU aB Bb fB jd Mn aa AH rM MM lZ zz JV VV Jh hh nP PP pv UR hj oM uO zY iI ih AA AE Ur RR rr Rl dM Sk hi Pk rh cY GG GJ jQ Qq Wn NN Lu BB BM kk kM uA AA dd DU RR Rp EF ff ag SU nJ JJ ED DD rt tt Fq zO uu uY Nw WW Aq OJ JJ NN Ne BB be XF xx XV ph fs SS qq qU kk KS BB BS Cf ff jB wx XX uz ZZ sh HH Kv cU UU iu Rk lI ii WW we va UU UW ki II ip mz Vt TT RR rh xQ Mo Wy xd hJ JJ Qb pV vv Uv vv Bl lv zz zu ly yy nn nW Zm ND dD rr RA tv vv kG el CG pJ JJ px XX NN Nu cc cA kk kT GK kk UU uW Yz xq LY Nx xx YY yE PP PN WW Wr CC cE xO OO tt ta Lp My YY tt Ta xM mm HW uY YY DD DC CC cd jA AA Fj JJ hp PP VV vZ ss sR Gf ff SS SK Yu XX xp Cf QQ QF mU uu oo oS bb BM OE ee ox om RI pr fo oo yD dd nn np NP Vd kq qQ jT eY yy cB bb rr RK YY Yu ul oo op BB BE IY vv vh Dq qq vv VL nn Nv Po OO Ra ZW ww im EF ff TT TS BO oT TT XD dd SF ff ox xx Pu UU PP pu vp ij Md DD kH hh UL ul ll vH HH fc pp PW yy yl lM YZ Bf ik bU xx xw PA Sg nW KK kt nO bf ff xx Xk WK kk NS ss So OO jh hh Zy ah HH LL lu TN EI ii QL AK jk KK Jx uu uf uV fn dP pp Wm LE vv vU Gq WW ws ff FE WP PP GO bo jj jR FK IY FF FC MW ph vA AA rQ qq zW WW hh hv Hf FF zu uu ha ww wn ud dd ss SQ Sq QQ px Mk KK nZ ZZ DD DN mB BB hX CP pp Wz zz MC cc dj gT TT BO oo fh by Os qo OO PC CC PP pq HV wt tt lD YU uu pp PE bq Kn NN SS sh Uz Mh hh ZJ Og gg cc CF Xh ej mm mX zZ zh GG gn iT NK cV Qa QQ qZ ik kk mX yb OZ ui ii nn NC OC ag DX xx QX xx rr rE ff fH yM mm wF rW Kj jj NR vd vv VR AA Ai St EA aa er NB BB WG GG NN no uu Uc yZ Mo TE Np pp PJ fc cc lp mp pp nN NO kK Kz sk vr RR zb nx DO Oo rr RY bv MC CC Oj jj NM MM aE Rd ZS xl bc hz Zz UU UD Cp TM MU ZM MM BU eq qq ig GG Ha aa CH HH OO Ox nk Kk mA TF ff Gc sC uc WP zz zU rp eo sw WW ww WN OO Ou WB BB AA Ar CD dd DK nH NP pp Am BE ZZ zW XD DD JJ Jm SS sA gM Bf EI ii Rv xx xe xd WW wN ZJ jj Ru hX XX dd dr bz EE eQ pN tc ev Id dd ga qq qk hh hx nn nm pG xG Rs SS kk KR sY UU uz FR Tf MH HH zk kk cs FF fG Be nA Xu kd Dd SS Sg JG hp Tt tP Er RR bQ pi ii Vy yy yb BB qZ zz nn Nr pn NN AA AK oG gg AO yk KK BB Bc vU iK ph hh vO vt dW oE EE Qs Xi SG GG vv vW ds ss Zo oo XX xu cF ZZ zs hY YY hP PP Wd ne EE Qx JE xx XZ sw jj jo dd dI Cs GG gp Us ss AO OO NN No RR RX II iK iE JJ jW VV VI cf tT Tg kk kf Dl JO oo XX XT WF Bb bj QU Tt ti Pp Pc hH hM VV VU iY bb BA iR WW wO LL LX oy fP pp HA AA XZ zz QP PP gL qR qq Qj Ld xx xN cc Cf mn nn dV kF OO oS cw AN nn ly yy mr Oc CC mb Om zG gg pB Zi lz zS mm MZ SS sz tV Ne IW GG GV DE Zs JD DD CO av ff FA Cx XX Ke EE xb bb Zv vv Re EE mh BP Ei uu Un Az xz wm mw LV Ht oC cc qB fu FF fx ZJ jj cD zz zn YZ iX yk Mh fB qn AQ hp tH pl AB bb HH hl Xz ED dd JO UL LL sx XX Tk KK LV vv ee eT QQ qD lH hh CC CS BB Bc gS SS hf EG kS RR re xo Zl LL BX xx FF FS tt TM TT tS vG GG ET BB bG lz ZZ Gr RR HL ll JJ jw Gk CC CK hm AR RR MO oo Ag AA aJ qm nQ UQ QQ Iw WW fB ED Ro Zf kv VV zA cc cf HH HN AL tq zX tt tm re pp PI Wr Ep PP MM MJ Eo cc cN VY Gz Gy mZ ik MR oo OD ga gC Lr WM ph ll lR PP PN Rm mI iI QQ qf xh mM MD YE EE ii Ic uc MM mt hD Dd jw xp Gv VV fd dd EB bb Uq qq cc cP ow vt OA yy Yh oo oW GE Md DD LU ki Ut TT EE EY ee EA PO BB BN CO oo Sv LF ff US jK kk qf UU uy dD DJ ds hh Hv Hc NC SS sQ LU UU dE EE ah uN nn SU xx Xh Tv vv YY yS rW HL LL ZZ zp cI Cr rr au iu uU hh ha vm IR uu ux jv kt ss Sk aG hf xx xS YY Yc AR St tt cc ch tv Az MB bb kk ky KD DD VQ FF fE yu uu ZE eN NN AA Af NN nL Fw WR rr cc Cb JJ jg rr Ry QV xx XR Gx Zd DD LL lw Ii iC Ht tt UN di UB ZC cc xT tt YA AA Xy Eo oo fZ mm ME ss sh Zq QQ cw ww vh hh vT TT kk kN WK KK wa AA QN hx XX fQ QQ TC ee Ec jj Ja UM gA aa HH hl Mh Hh Oz Dg sz zz EE ep Sx xx JW Bl LL JJ jF Gz zz EZ Cw sd Rl ll Qr RR TT Tx wc qk kk oo OC rT tt Hi lE EE mm MZ TX XX zz ZX BJ HL yz zz fW ww zB NT SH hh Aw ww ei ii Ye ee Di iz zz Lw GS NN Nm ee EM Jk KK bm tI ii dV uu UV eB RM OO oa Uu Um dc cc Ow WW TT tU tv YI ww WZ fl MM Mr KK KW LL LH Ic on NN RU rA AA BB bx gg gj MM mG kS ss tk AL zm fq QQ KN vx ZR OO oN CV VA KK KM Ml QS SS xI CA WH iZ Ub bb Bc MK kk AP ZZ ZE jn yR ih Qo EJ Wb BB Tv id JF ff Xm mm Ev SE tq XX xf PG pM VY JJ JZ gA AA nB bb bC cc ps zK Kk NW ww SS sw NH HH Ld FZ sd dd ii IY hY YY On Nl uR wY nu UU jj Je up PP qo rz ZZ kk ki ab YY Yu ng GG Ci iI CN sQ EP PP Ut bq qq cc cO PN wj WA yy yV rd DD II Iw LL Lc vS oe vT YY Ys CD DD vr rr AC Tr RR rc PB Tr rr xP SZ Ys un NN Hn TE AA Av bb bW VJ jj yy YH cc cm xx XL gl ll ev Vv mP uO oo hr GG GY dL iB BB kk kw DC bb BW II ik Gl WW wk GC it TT oM pp pe vv VJ xQ ee EQ fE ff fC LJ JJ cu rr rV wy Hw WW Uy MM mN JZ jj JZ AL LL oO oP ll lp MM mo AV SN NN lh hh gD eu uu kw WW vl Pl jQ qq ws mm Mx zS Wb bb AK le go CN GU Ti RN PV dd dk ll lC YI ii gr hh hc aH ls PV vv aa AF JV vv wo Mp rr rN uu uP GN va as BB by ff Fu zf FF HH Hl xx xO mm Mp DO OO jA aa QE ee YV pR rr IV vv RA Fk Ov aT tt BB bW XL RR rC ll LH cH ll Lg tt tV Cq QT ws ss kk KG DD dZ dr GG GR MT TT qG ZZ zG KK KW PP Pd zA So oo tE EE hM ks xv wu LW RF FF zK KK kY YY eM fL av Go Mx qq qV yO EE en Qx US Vd rH hh NN nU cG Ii Im qo oo UQ qq UG Uz zz VH kB AA Ag JM mm cu LV HH Hf mW Ha Wj JJ OZ zz pz ZZ cU KC cc QQ QU ll LA ik ff Fh Nx XX Pt tt MO Wr NO AA aw yy Yh NE xx xs yI El LL Bq Dc uS ss Vq qq Ed gA fz zz ot Ym Xa aa vv VS nM kk Ki zl wc cc Jn NN qq Qf BF yy Yo aa An SM AA AE pp PQ Lf sp hh ha JV vv RW mq jb bB Ev at tt Xm mm EE Ev du uu Qn RG gg kS ss OJ jj xa AA Ac cc JJ Jp cc CZ Ca aa ji RR Rk Yu UU sl ds SS fW tT tu NG Fz mm MN cE ee ki fW ww ll lh xn MM My uN nn Sz GG gi aC CC RZ oo oq Ol uu UL JV VV yM vy yY JH hh mi BB bE II IX xz YY YR MM mX ky WB BB uT TT uY hi Ii qq Qn II il kj jj KK Kz Vc HH hv gg Gz SF FF xx Xw JJ JG uD Ki Qj uP pp Vu VP xZ nn Nk ff FS ll lB CT NQ cI ii du UU LL lR mN NN pD JL oZ ZZ DM Mm wL SC CC JF ff jQ KK Ki ut Tt GG Go vr Ag GG rr rv Eg gg kK kn lG YY yn Fc ww wI gg Gn zz Zn Ga qq qe of ff Ro RY yy TK WR rT Du Aj JJ GG gO CC CN zj oq ss sj GG GW tB vN NN Wo OO AA AW ah hh MM MA vv vt lF Zy VN NN Fj fh wA aa VV VJ bi qH zz zr Fm mm UF FF kk kR vb qp pp Vt kJ od ua mx xx LA NQ QQ ww wH hh Hk WW Wf Ai FB cB BB cc cY sJ KK kX aB bb OO oq So Ox XX AA aX MB VV vr Hb Fd ss Sk ZZ zl zY ff FJ ZE EE Td NV VV zi hj JJ OW PV mU Rk BB Ba kk KC EE er hp Pm ff FE vt dd dM iK kk Om OP XU uu mv zz ZO FX ks wj jj hs SS nq OM rm TQ Zx ZY YQ ik kk OB eQ QQ oj JJ OO OB LJ Yc lN lO TG oo OM XX XB VC Na EB dT yy Yp je ES JV vv jg gg QO oo SG qt yU Ix Yw js ss cH hh Az HH HM wX xx vz zz VP PP Kl ZZ Zw kz zz kP WW wA NU dF ff fo rr rG eo rv VV cb MM Ma Xs uL rL Au vR pp Pw li II zG ee EO QA yW Ri Yz ee Ey dd DE SS sd jh jj Jn vv vU PW pl LL xJ Fl sZ dd Dh fk kk HH hs bb BC gS rW WW rd PR rr pH HH tv SG hB HY yy jF pP Pf cn ZZ Zq Ef mn Jh TT To Js QY nj FH wP Ec CC SS Su Gp WJ Lf FF jz xQ tt Ta kq QQ QQ qw VL WO tr RR FQ qq fA AA Qf zs ss Tl ll sX EN wZ zz Ry II Ic Pu uu Rq Jk sO oo OA FU UU HH HS vE pC NW WW As SS Gb BB ny yy HH Hr Qb TT tw do qC dL gg GS nn NA FJ jj ee ew qt xx xG DD dr NN nA nz zz uX Hk ui zx tt Tp bH EE EZ Wv vv lf JS SS";
    private static final String testSequence6 = "KY MM zN dj Nw vv ss FF FF Il Tp PV Hv fD CL qq rH yU Cs oM cD Yu si gP kz JJ gg tt gg MQ uu rU ch em lU jT ZZ Ru WW cc VW ZZ vD gg xg uG aa WK MM cc vP KK Lu MK KU wj AA ii Bt WV RR KH cc as ff aa sj KL rr Jj zz LL GE fE WP iX GX CC MT hh fz rz jL OY Gq TD pl UA rw NN tt PP ND NH qb cc TK hh YT rr IQ ef qs rr UU zg eO Sr EX mm UU Ir HH ll Wi wB jj UY rr ga uu ap kk MM EQ SC nn jq Sf JJ tX Zk hL Lq NN Iw wv BS zI Cr tC yf zz vJ Dk kk ex BG co RL dN Ga DD qq XX wN jm DZ uu xz vH OO Al CC UZ KW xH UQ tH SC uo sj bb GG JJ un fX nm yy ii nn Jv df JJ Ok bn yy LL KK aE UD dF LB hf xC Dv pa ju jj FF UI IM mm HT ot tU rr VV Rb oU QQ ll Vg My gc HS BB rv LL Lb SS Ay WW GB Nw uu up JF fe uu EE JJ VA BB Oa BO rA ZZ XP Hv GP gg ii af AY EY Lu GG SS kg UY JU IL MM fe pV Yn iN EG ZP dd FS gz oz bb LL tu LV TH at rU Ez EJ ft CZ kk OO QU Jr qq gV qq nE te XX ef WT Ab wO Ec iy TT Sx QJ rx ai zz mm xc WW xv IU qt KK BB qQ Ay hl QI QQ DO vZ DD II xx uu wG cw PP il uR tq UX wr Yz aK hh oo MV yw VP uO tb CC aq ii SB ww QM kk LC Xt aa Ht HH gg gl Kx uI Iu ww TT hd UU jf tt ss jj nn hR xT lv ON cv ll rr YY YY KJ cX Pp iz RR eR xx Xh CM wT AA Gi iT ZS Cg va Nw bt jf OV Dc ll xx Bj VC fj Mc Iz QD eg vQ ag zC ZN DD Qt vD Ip QQ FF DD qQ Xs Mx CC qj CC gC hh YY uD hV PP ih gp dC qD II aa BB uI rm jd GD PP Cw hh zz jg Ud KK cX Va EE wS ut vw KH IP rC EE zz KK Km cg CC GH zZ ee DT LI Ry SS GG vK VV qB Uy yB LL Yg gO sR LL ii bA mL RR MV CC GG Jr oQ QE ZR ef SS kB PP XV Dq bR zn cc HQ TE HH UU UW Xj Kd YP XA BD kV tt zz kk Ai bQ kG Gq ee WO xP dd XO fe oo Jp Lk gg eT rr hT XX ZU da jz Yz to HH qB zx PI vu ee aa HH LU Pz jp tt qw mK rK vv DD tt YO nu AA cc gS cM SN bY xW rB lV KA Re ta VM qm mg ma Vd TY AA Bp AW Lb dl ew SS id Iu iV tF zf EE gP Px ww JK dd KP ub BM bb NN JJ hh wm rS ng Hr eI ff YY SF YV uu ii zz kF GM UU QQ Nr hk ur ZZ ZC iA iq Nd mm md CC Am zz Tk Sm KK Gc FX Je FE Ee Bn TT cc yy ll Eu Rz YE pp we Qv ZZ fh FF Jh rb NN KK iK NN kk QQ UU bb ld uH ml kk AF qf CL FN jf ff FF rr Fs Br ss KK jX YY WU oO We Qv Tx OG kk aa qi PP hc mm ak Ik DU Rk OO NW pp rr nP dK mN xx Hq af DD rr qO YK lf Jm gO ww he kq ll Ky II YD QQ tt KK tD pp bb LO pB ss uE vf Le WJ br Ci kk um RR dI ef Ie MM xz mm UC tt fi hJ fv pX Rh aa vr ww vv yF ui bh CC qq sA kU NO TM dI xx xP zz KK rr vv MK TT jw OX GP Sw JL pF EE eb YY uu Qb Ks lX eF Md yL TT ee hv BB rx kD ZE QQ tS hF tt VV hR gg rU YY DD Mo GT MM mm bv ll PP NI VV Io uZ bb UU Ai KK bL Wr yc ff TT On FF WW Te ww qT DT ef uu sf wN ww su CB lJ yy dd cW TT uu PI XX BC nJ KM Nk mc jj wy Qq ff jw LL fC JJ Cl qf Hx Gk YQ ss vc rS qR ll XQ zz mC aY fe ww xw ZV CE MY GG PP Mm VP qq bb DD Jd rr Lb Rb zj tt OB Id uu bh PG uu PB oc kk cc MJ oz fd CC ZZ BB OO gg uL qc ww Xf ss ze cD zM OL VV zY ii rQ lm OX SS fq jj PP mn fB hu DD PV EI Do MM ee ss zz XA aq Tf oU pp TT MM VM dd pT qG bb sf Ei tB ld II YA lE Lu rV Gc oo aa My sz pb XG rW QQ RK ww uu NS aJ Ec RO rr ll ue BB bL XM gY Cf bb Bx nn cG yi uv OH pg OO OO mm WW zI Wx iZ BT vv sO ta ad MM xN NZ OO vQ ww WF zk mm lB Wt SS yR SS eP gt bg NN ee ee El bb Th gg dP He CC ii ss OO CD II xa FN Cz QY KK LP KW ss Gm kh ew FJ Df sO Qu pB Ll wm JJ iu Pe RZ LL Ur JV hG Iz ll oz oN GF iB nn NC OR if rr PP QQ jj YU sg AM cE ZU xM rr eU cZ YE wY YB LP Cs pW rI uu ho NN Dj Tg vv lr Tk Gb NR br HH FF BB kQ MG CU vU rJ tt Wk bb sC zz kg KK Oo pt oo HH rb XX RR ww dd zz iG Rg tt pm KF Zu ff SS MM Za zt fS gz tP oo Og RR Rm Fj RR bX HN Ot YQ xS jd Vf jj SZ Mc vv Fv HJ HH qq aa pv pJ AA fP Vp TT ox yb kV En CN ml Dv LR cq ZZ VV Ha TG QZ hN TT RR tO qq ur op xx QE OO Xc rb jD Zn oo vF TT zc Ut TM ii uY Ci KI JJ ez gI Jn me DY dj gT wS aZ ZQ CN Bk fS NN Ue TW LJ DQ Dt Nu XX eb LL KK rh ZZ zP YY LL wl dd WK mm UU Zv Oc hh Or JJ PY FW lh XU gn TT GG GG TO BB Hq el OZ QQ vx Od aV Fk rr JJ KK AA kB NN OT JL dd qq Ea jj hh Bz Xl DD wS Jl hh xD eH Td IN nr EQ uu nn AA eQ cy qK uu dM EH RR NN Qk nY MM Ny qL LV II Tw DH nn mW Uo VL pV kk BY Oo dd SS DN cc yD ii pc WM Fn Br FK jw fJ Td Ap ow oo dd iU et kg Ya ZZ mj vH aa CC OO eR SX cc ga Ld sX rE gs nQ YU Av kk aw lI ca uh wO bq nn rr gR hX MX kk As NN Ty EE GE Wp vR bb pi DD iE we Gm gg kk Xc XU XX YB AG By UU BO tb OO jj bb DK fG MJ Yw AF ah hb PX tt KK Pj QE qq SC zn UW Cz Hr lV jz Qn HG pI FG zX qi YP zx cy FF AA PP zo bb jF kk LL vl ul gk nn VG ff Ht ig Bl Kq jj TT tf Rm GG Mz pp Sf CC ww eH Ul pp xF Du OO XS MW rr XM Qe mm kT CC hj yN Cm DT xJ QQ ee CC kI kX Xi Uv Xd HQ II zz Xo fv YY uf hh lV JM dU Ho Re sq DD Fa fs mw EU BB Qq ip IP BD tt td KK Cd Ow BS KZ MT pP ii SO mj Jf yc sd VC nB Ft SS OO mm FF MB xx Mc AA vv lp pp aa OO RI xx Qn ic ff dV TT EE YY pp WE ZZ ia xB QC EE ww xx LL nZ DG QH CU Vj Sz lw Er At DD wc Df YY sd Uk JJ iq zz Cd gi DM mR ii aa fs Ta Ng FF CS KK Rh zE Qc DB fT gr XP op MM gd lu Xg bZ kO aa uL Gf yA iW SS Pd Fy bL df zo vp oo PC Zm wC OO aF Gu zz dC OZ oo WW pp PL gk VL pM eQ tt tR me ds MS ee mA OL tt qq Gr zR Ka eE EF ai ff CH gv sN AA ZX Ww ii WW fa VV ZN gb jR qq ww rB JM fD rn GG XX mp Yj YY zC rr VV aa ee UE mJ aa MX HH ii II jj HH jh po FF LL ff cc TT tt pp PP MM ff bA cc XS tb OO TT Tq pm nn qa IP GV fz cM EC jJ dg jb ff Yx fV QY Ii WG Pv zn un OB Yr hi pS NW dm qG AV ie Yb XW XE yZ FA Zy Jd EY xx ii zz na Lu GJ BB KC tt wI od SS Oq BP Fx zq pu Xb Xz jd oo VV qq oD Ab FF Dg rs xT sa Kv FF Pg Pu zs XM NN Vj MM dd wL Qz xr nn cp fs Jv CC OO LL Ce Lq EW PL DG XO tt XU ii xx xx NO hh bb VV Ui fj uL iC CP KM bW Bq VM ii ff pj aa IR BB xJ qz bt cc GO II cc rr rr qL WU qH MM dd Dx pp ZK tA mm Vj SS vZ tX Ki ff HO EE Tx jP Ig dJ dk Ul WX Qo ff VV zz ET NN nz EW XX Dy NN yt eN MM QQ cc Pt kk gg mp Ur Vl hp ee MC Bs gg Hw LL RO St PP az nO vD Pf Nh BU Ee oO Ct II HE dg JJ Qm Zb Hi ff jj sf sC KR bq ph UK nz ZZ aH Ow UL cc sF Ti ua Wb vv yM nn CS ip XX Rs Ki rr Ud Fd wC oO Nz xx dd BB vy VG gj sY DD WW yh bG PI Ic mG DC JJ fD ll cm qq vh KK eE lH oS Tj tn PP dt ON UX lU JJ DT ww lH OW vv be YY vv jj NN js zs MM Pg oo Zn Lm ll LL le qr Ny zz kh kR QQ cq IE gg HQ Dd nL CC wf ge lA YY Dv DD Rb Ab MM MV gX JL pt gw hp pq Mk kk ap cE KT RR op yy ff zB OO Cc bb Sy ii FF PO CC KK rr tW XX WW gd YY bk ii pV LL jj pp Yz Hu Hl pP dT Eb hh zi Cw UZ Wx Lw jY hr dd xx Gw JX Wu dL ee oS Zv vf YU Sp Mo nO TD tt Is wH RR et vJ iA ho WW YM lL EG ww av ee kE HH Lm pp cc UU JJ SS rq tt Dv nC aa rH eC Bq hh SS VJ LL Od fP kL KA lw RR NW Qk KK WW yW jj lg cM mL fJ Br Fe uu yy uY aa Zs br ee Vl Bi DD qH ze aa xo FF uA XF EE Ey Vl Ek Ar jB DB PP mu jj SS Jx CC Sw CC Ne LL Bp NN zz ez po dT Jx ha TD aa qa Zt di uu vv fd mq ww RR Bw CV DD kQ aq lm zz mP uW hh Sr cM QX bp EC EE yy sr CK HC BL UU rg yy TC VV xj ff oo UB tt nz xx uQ mw NC PK vv HH ga vh uu rz XQ EE Xx MA cc aa gx if JJ TW uw su xi VY JJ QZ zz gg aO JJ UX MX wv jV tp IX JJ Og hV sK mG Gx jV AX LK wo ZZ xx vj WW QQ IK Bf TK JW nn Vo Eo DD xx rr pp aT WE MM ff vv qc OO GG br gN CE Vr OO an zz EZ xX ff Vh eu tH BB HH Ki eL dl nn hy fw qq ii lC UY xx rq rb ds cz ZZ Oe rY ii Hy Ni vv uu jx pp Mp PP tp yS GG AU rr ac xv kk kk Bk Xp iN dD SX aa ZZ ee dd oD yy zz xx zS xX NN SS za UU lt BB Ai yv dd tt AA aa Sf kW jj dd XZ CC GG LC So gg tf hN fj HH ag xK ii vv WN oo iK qq em Id by Cp gg bu yN wx iA oo gm gg XX cc kZ On GD hh Wc tl Pf ia Id fq QQ xW DD fy gg vv tZ oH EQ AA zY nn Rj rG Iw GV Nr UU No WW DD BB aG jV UU ww BJ Fa ff eE Os PX qB ee wE UC JE pA Wa mS ZO do us Mp aF wq bE UX eu ww ww ff GW ph zz II RN rN Vi Gb Ig ww VV qa HZ dU XX Au EY jj qp sZ ww ff xx QH kN vw Zi AY HH BB rO HH EE Eb LJ XX Ij is KW vg WT vA TB fe Gh qr kk kT Ew CS tg ix vv mm Sg au wl WW TT PG nb OS js YY Df Vp rl MM MM aw aa wk vv Vh KK UX kd sB cc Ty WN HH mm aq UA Rv yT yH dO XX Ly tH ty nZ pa zj OP wN yD YY FF DD tQ xx DD kk xG aa aa wW Ee ci oM mm Xg qq gN Oj TD mQ rB jv Ng ZD JQ ii PP dj VO EE ZE EZ Ht ka ff ar oh II kc FA vr pp CC qk FA yy Rt Os Zz PZ Cn tt kk YY Kc li mZ oI CY ii vD FF ww xe NF PP pC mm hh ml KT GT fC EE aW Qr yE NZ fa hb YY kb Oo Kr oh YE fu Vp ru aU ru Vk FF Ni mm yK ck AX oo ca hh xx MI yA hh kL ZZ QQ pM zD VV GG pi QX XX wI JG HK mm NN tt jI XJ VN wj uu GG Sw ee PP oV wt am Fh WW vs ss nn KD Dz hh UU Uo Dy Pp rN us dX LJ UU hh zz Rq YC fs YA xh mR ni cc Vk LZ cQ gX TG zg AF Hl Ys in Xt CQ GA yy rr pp jO Yu OD qa Nx vx ss ll oE ff NV FF cc PY vv oX Vl AA UH pp uP jx be aa bj WW Ns Dc ee iH AK bt yM zY nn hv II WN bR vF JJ Fz Ur AA ii cI BY bG YY PW wa kw wj LO QH CT SK GG mM yT ZZ dO DD zf dJ xj eJ BB ZZ UU nR lV So vu Ja DD ra rr qI II Pm XU gD Ny WK cO bA uu vb TT te at ft FF Iq rr sv Zc fS ss SS UU SB QQ ww Ka Fp qb Lh Ln Jf ff vv ag tt ww zQ Ua HH yN zz kk ac Wm nu SM uL Bq rr wg kk Mc Nh NN sf XU iC oo xr ei eW AK lm fi WA wa WW po SH vv Qe cK bb Lk kk Xk uv uN bb gg nt kM nQ ss XI KS JB Fv MD JH wu tz Ou UJ jm bR sz uu zz Lj VZ xx SX mq cC RR oo zo uj ZL mm dd sI II nF hk ax re ll NF ZO iK gI cx tw CV Cx qm dI rr rJ VV bb cG zF eh hx xY AA Do yy iI RR HU XS Pn Rn He HD kk Px XX dh Cf er Xx QQ ou GG Wy bE ZH aK zr HK ll jz JJ AU VX zj zr fl OR Nm CC rv vv gk ue mr FF ii ZQ II Ef HH EE";
    private static final String errorSequence1 = "ab cd ac";
    private static final String errorSequence2 = "ab ba ab";
    private static final String errorSequence3 = "rj KK YB Jr CC jr JJ lm JK DD dD rr dd MM BY Cr mm DU GG rC YY VG Mr KJ Dd GV rJ UD rM ml";
    private static final String errorSequence4 = "";

    public static void main(String[] args) throws CyclicAlgorithm.WordArrayException {
        System.out.println("============ Run CyclicAlgorithm ============");
        testString(testSequence1, 1);
        testString(testSequence2, 2);
        testString(testSequence3, 3);
        testString(testSequence4, 4);
        testString(testSequence5, 5);
        testString(testSequence6, 6);

        testErrorString(errorSequence1, 1);
        testErrorString(errorSequence2, 2);
        testErrorString(errorSequence3, 3);
        testErrorString(errorSequence4, 4);

        System.out.println("\n============ Run SequenceAlgorithm ============");
        testSequenceAlgorithm(testSequence1, 1);
        testSequenceAlgorithm(testSequence2, 2); //Can't resolve this sequence
        testSequenceAlgorithm(testSequence3, 3); //Can't resolve this sequence
        testSequenceAlgorithm(new StringBuilder(testSequence1).reverse().toString(), 4);
        testSequenceAlgorithm(new StringBuilder(testSequence2).reverse().toString(), 5); //Can't resolve this sequence
        testSequenceAlgorithm(new StringBuilder(testSequence3).reverse().toString(), 6); //Can't resolve this sequence

        System.out.println("\n============ Run CyclicAlgorithm generating array of strings ============");
        long start = System.nanoTime();
        List<String> newList = generateArrayList(10_000);
        Collections.shuffle(newList);
        long generatingTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println(String.format("List generated, words: %d: %s", newList.size(), newList));
        testString(String.join(" ", newList), newList.size());
        long checkTime = (System.nanoTime() - start - generatingTime) / 1_000_000;
        System.out.printf("generatingTime, ms: %d, checkTime, ms: %d, all time: %d", generatingTime, checkTime, generatingTime + checkTime);
    }

    private static void testString(String testSequence, int n) throws CyclicAlgorithm.WordArrayException {
        CyclicAlgorithm wordArray = new CyclicAlgorithm(testSequence.split(" "));
        String result = wordArray.getResultString();
        System.out.println(String.format("Check%d: %b, words: %d (from %d), result: %s", n, check(result), result.split(" ").length, testSequence.split(" ").length, result));
    }

    private static void testErrorString(String errorSequence, int n) {
        try {
            CyclicAlgorithm wordArray = new CyclicAlgorithm(errorSequence.split(" "));
        } catch (CyclicAlgorithm.WordArrayException e) {
            System.out.println(String.format("ErrorCheck%d: %s: %s", n, e.getClass().getSimpleName(), e.getMessage()));
        }
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
        List<String> result = new LinkedList<>();

        int treshold = 7000;
        if (countThird > treshold) {
            result.addAll(generateArrayList(treshold));
            countThird -= treshold;
            generateArrayList(countThird);
        }

        boolean out = false;
        while (!out) {
            List<String> list = generateList(countThird);
            out = checkGeneratedList(list);
            if (out) result.addAll(list);
        }
        return result;
    }

    private static boolean checkGeneratedList(List<String> list) {
        try {
            if (check(new CyclicAlgorithm(list).getResultString())) return true;
        } catch (CyclicAlgorithm.WordArrayException ignored) {
        }
        return false;
    }

    private static List<String> generateList(int countThird) {
        Random random = new Random();
        List<String> list = new LinkedList<>();
        for (int i = 0; i < countThird; i++) {
            int x = (random.nextInt(26) + 65) + (Math.random() > 0.5 ? 32 : 0);
            int y = (random.nextInt(26) + 65) + (Math.random() > 0.5 ? 32 : 0);
            list.add(String.valueOf((char) x) + (char) y);
            list.add(String.valueOf((char) y) + (char) x);
            list.add(String.valueOf((char) x) + (char) x);
        }
        return list;
    }

    private static void testSequenceAlgorithm(String testSequence, int n) {
        SequenceAlgorithm s = new SequenceAlgorithm(testSequence.split(" "), false);
        String result = s.getResultString();
        boolean b = check(result) && result.split(" ").length == testSequence.split(" ").length;
        System.out.println(String.format("CheckSequenceAlgorithm%d: %b, words: %d (%d), result: %s", n, b, result.split(" ").length, testSequence.split(" ").length, result));
    }
}
