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

    // Gives covered positions of an inputted piecePlacement
//    public int[] getCoveredPositions(String piecePlacement) {
//        String shape = String.valueOf(piecePlacement.charAt(1));
//        switch(shape) {
//            case "A":
//                int[] posA = {1, 2, 0, 2, 1, 2, 1, 0, 0};
//                return posA;
//            break;
//            case "B":
//                int[] posB = {1, 2, 0, 2, 1, 2, 1, 0, 0};
//                return posB;
//                break;
//            case "C":
//                int[] posC = {1, 2, 0, 2, 1, 2, 1, 0, 0};
//                return posC;
//            break;
//            case "D":
//                int[] posD = {1, 2, 0, 2, 1, 2, 1, 0, 0};
//                return posD;
//            break;
//            case "E":
//                int[] posE = {1, 2, 0, 2, 1, 2, 1, 0, 0};
//                return posE;
//            break;
//            case "F":
//                int[] posF = {1, 2, 0, 2, 1, 2, 1, 0, 0};
//                return posF;
//            break;
//            case "G":
//                int[] posG = {1, 2, 0, 2, 1, 2, 1, 0, 0};
//                return posG;
//            break;
//            case "H":
//                int[] posH = {1, 2, 0, 2, 1, 2, 1, 0, 0};
//                return posH;
//            break;
//            default:
//                return null;
//        }
//    }

    // flips pieces to show mirror form -- need to implement mirror method part
    public static int[] flipPiece (int[] positions) {
        int[] out = new int[positions.length];
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == 2) {
                out[i] = 1;
            }
            else if (positions[i] == 1) {
                out[i] = 2;
            }
            else out[i] = 0;
        }
        return out;
    }
    public static int[] transpose(String placement) {
        return null;
    }
}
