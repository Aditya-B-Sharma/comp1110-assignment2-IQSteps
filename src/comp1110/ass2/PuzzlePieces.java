package comp1110.ass2;

/**
 * Created by Stefan on 22/08/2017.
 */
public enum PuzzlePieces {
    /*AB, AA, AC, AD, AE, AF, AG, AH,
    BA, BB, BC, BD, BE, BF, BG, BH,
    CA, CB, CC, CD, CE, CF, CG, CH,
    DA, DB, DC, DD, DE, DF, DG, DH,
    EA, EB, EC, ED, EE, EF, EG, EH,
    FA, FB, FC, FD, FE, FF, FG, FH,
    GA, GB, GC, GD, GE, GF, GG, GH,
    HA, HB, HC, HD, HE, HF, HG, HH; */

    A(1), B(2), C(3), D(4), E(5), G(6), F(7), H(8);

    private final int origin;

    PuzzlePieces(int origin) {
        this.origin = origin;
    }

}
