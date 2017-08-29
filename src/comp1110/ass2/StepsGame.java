package comp1110.ass2;

import java.util.*;

/**
 * This class provides the text interface for the Steps Game
 *
 * The game is based directly on Smart Games' IQ-Steps game
 * (http://www.smartgames.eu/en/smartgames/iq-steps)
 */
public class StepsGame {

    static int[] AA = {1,2,0,
                2,1,2,
                1,0,0};

    static int[] BA = {0,2,0,
                0,1,2,
                0,2,1};

    static int[] CA = {0,2,0,
                0,1,2,
                1,2,0};

    static int[] DA = {0,2,0,
                2,1,0,
                0,2,1};

    static int[] EA = {0,2,0,
                2,1,0,
                1,2,0};

    static int[] FA = {0,0,1,
                0,1,2,
                1,2,0};

    static int[] GA = {0,2,1,
                0,1,2,
                1,2,0};

    static int[] HA = {0,2,1,
                2,1,0,
                0,2,1};





    /**
     * Determine whether a piece placement is well-formed according to the following:
     * - it consists of exactly three characters
     * - the first character is in the range A .. H (shapes)
     * - the second character is in the range A .. H (orientations)
     * - the third character is in the range A .. Y and a .. y (locations)
     *
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     */
    static boolean isPiecePlacementWellFormed(String piecePlacement) {
        // FIXME Task 2: determine whether a piece placement is well-formed
        if (piecePlacement.length() == 3 && isValid(piecePlacement)) {
            return true;
        }
        else return false;
    }

    // Method checks if a string contains
    // String first converted to charArray
    private static boolean isValid(String input) {
        char[] arr = input.toCharArray();
        if ((arr[0] >= 65 && arr[0] <= 72) && (arr[1] >= 65 && arr[1] <= 72) && ((arr[2] >= 65 && arr[2] <= 89) || (arr[2] >= 97 && arr[2] <= 122))) {
            return true;
        }
        else return false;
    }

    /**
     * Determine whether a placement string is well-formed:
     *  - it consists of exactly N three-character piece placements (where N = 1 .. 8);
     *  - each piece placement is well-formed
     *  - no shape appears more than once in the placement
     *
     * @param placement A string describing a placement of one or more pieces
     * @return True if the placement is well-formed
     */
    static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed
        if (placement == null) {
            return false;
        }
        else if (placement.length() % 3 == 0 && containsDuplicates((collector(placement))) && mapisPiecePlacementWellFormed(getPiecePlacements(placement))){
            return true;
        }
        return false;
    }
    // Method collects shapes in the placements.
    private static String collector (String in) {
        String initial = "";
        for (int i = 0; i < in.length(); i++) {
            if (i % 3 == 0)
                initial += String.valueOf(in.charAt(i));
        }
        return initial;
    }

    // Checks for duplicates in argument ArrayList of strings.
/*    private static boolean containskDuplicates (String in) {
        for (int i = 0; i < in.length(); i++) {
            int bool = 0;
            for (int j = 0; j < in.length(); j++) {
                if (String.valueOf(i).equals(String.valueOf(j))) {
                    bool++;
                }
            }
            if (bool > 1) {
                return true;
            }
        }
        return false;
    }*/
    private static boolean containsDuplicates (String input) {
        ArrayList<Character> charList = new ArrayList<Character>();
        HashSet<Character> letterSet = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            charList.add(input.charAt(i));
        }
        for (Character i : charList) {
            if (letterSet.add(i) == false) {
                return false;
            }
            else {
                letterSet.add(i);
            }
//        }
//        for (int i = 0; i < x.length(); i++) {
//            Character addition = x.charAt(i);
//            if (letterSet.add(addition) == false) {
//                return false;
//            }
//        }
        }
        return true;
    }

    //
    public static List<String> getPiecePlacements(String placement) {
        String[] out = placement.split("(?<=\\G.{3})");
        return Arrays.asList(out);
    }

    // I speculate that this method might be the one causing the issue
    private static boolean mapisPiecePlacementWellFormed(List<String> arrayOfPlacements) {
       for (int i = 0; i < arrayOfPlacements.size(); i++) {
           System.out.println(arrayOfPlacements.get(i));
           if (!(isPiecePlacementWellFormed(arrayOfPlacements.get(i)))) {
               return false;
           }
       }
        return true;
    }


    //Method that if piece is well formed. I was going to convert the placement into array of strings, each consisting of 3 characters.
    // I was then going to apply the method made in task 2 onto each multiple of 3 strings. Using a loop. Do whatever you think is more efficient.

    /**
     * Determine whether a placement sequence is valid.  To be valid, the placement
     * sequence must be well-formed and each piece placement must be a valid placement
     * (with the pieces ordered according to the order in which they are played).
     *
     * @param placement A placement sequence string
     * @return True if the placement sequence is valid
     */


    // Need to use getPiecePlacements method so I can map some other methods onto each pieceplacement
    static String all = "ABCDEFGHIJKLMNOPQRSTUVWXYabcdefghijklmnopqrstuvwxy";

    static int[] transpose(int[] turn){
        int[] output = new int[9];
        output[0] = turn[6];
        output[1] = turn[3];
        output[2] = turn[0];
        output[3] = turn[7];
        output[4] = turn[4];
        output[5] = turn[1];
        output[6] = turn[8];
        output[7] = turn[5];
        output[8] = turn[2];
        return output;
    }

    static int[] flip(int[] turn){
        int[] output = new int[9];
        output[0] = turn[2];
        output[1] = turn[1];
        output[2] = turn[0];
        output[3] = turn[5];
        output[4] = turn[4];
        output[5] = turn[3];
        output[6] = turn[8];
        output[7] = turn[7];
        output[8] = turn[6];

        for (int i = 0; i<output.length; i++) {
            if (output[i] == 1) {
                output[i] = 2;
            } else if (output[i] == 2) {
                output[i] = 1;
            }
        }
        return output;
    }


    static String toString(int[] list) {
        String out = "";
        for (int i : list){
            out += i;
        }
        return out;
    }

    static int[] transposeAmount(String piece){
        int[] root = new int[9];
        int spinBy = 0;
        boolean flip = false;
        Character firstChar = piece.charAt(0);
        Character secondChar = piece.charAt(1);
        if (secondChar >= 'E' && secondChar <= 'H') {
            flip = true;
        }
        if (secondChar >= 'A' && secondChar <= 'D') {
            spinBy = secondChar % 'A';
        } else if (secondChar >= 'E' && secondChar <= 'H') {
            spinBy = secondChar % 'E';
        }
        switch (firstChar) {
            case 'A':
                root = AA;
                break;
            case 'B':
                root = BA;
                break;
            case 'C':
                root = CA;
                break;
            case 'D':
                root = DA;
                break;
            case 'E':
                root = EA;
                break;
            case 'F':
                root = FA;
                break;
            case 'G':
                root = GA;
                break;
            case 'H':
                root = HA;
                break;
        }
        if (flip) {
            root = flip(root);
        }
        switch (spinBy) {
            case 0:
                break;
            case 1:
                root = transpose(root);
                break;
            case 2:
                root = transpose(root);
                root = transpose(root);
                break;
            case 3:
                root = transpose(root);
                root = transpose(root);
                root = transpose(root);
                break;
        }
        return root;
    }


    static boolean isPlacementSequenceValid(String placement) {
        int[] locationIndices = {-11,-10,-9,-1, 0 ,1,9,10,11};
        System.out.println(toString(flip(AA)));
        String pegs = "ACEGILNPRTUWYbdgikmoprtvx";
        String exclude = "ABCDEFGHIJKUTfpqrstuvwxyeo";
        HashSet<Character> checkList = new HashSet<Character>();
        List<String> places = getPiecePlacements(placement);
        if (isPlacementWellFormed(placement)) {
            for (int i = 0; i < places.size(); i++) {
                String x = places.get(i);
                System.out.println(x);
                int[] transposed = transposeAmount(x.charAt(0) + "" + x.charAt(1));
                    for (int j = 0; j < 9; j++) {
                        if (transposed[j] != 0) {
                            System.out.println(j);
                            try {
                                if (checkList.add(all.charAt(all.indexOf(x.charAt(2)) + locationIndices[j]))) {
                                    checkList.add(all.charAt(all.indexOf(x.charAt(2)) + locationIndices[j]));
                                    System.out.println(checkList);
                                } else {
                                    return false;
                                }
                            } catch (StringIndexOutOfBoundsException e) {
                                return false;
                            }
                        }
                    }
            }
        } else {return false;}
        return true;
        }
    // FIXME Task 5: determine whether a placement sequence is valid

    /**
     * Given a string describing a placement of pieces and a string describing
     * an (unordered) objective, return a set of all possible next viable
     * piece placements.   A viable piece placement must be a piece that is
     * not already placed (ie not in the placement string), and which will not
     * obstruct any other unplaced piece.
     *
     * @param placement A valid sequence of piece placements where each piece placement is drawn from the objective
     * @param objective A valid game objective, but not necessarily a valid placement string
     * @return An set of viable piece placements
     */
    static Set<String> getViablePiecePlacements(String placement, String objective) {
        // FIXME Task 6: determine the correct order of piece placements
        return null;
    }

    /**
     * Return an array of all solutions to the game, given a starting placement.
     *
     * @param placement  A valid piece placement string.
     * @return An array of strings, each describing a solution to the game given the
     * starting point provided by placement.
     */
    static String[] getSolutions(String placement) {
        // FIXME Task 9: determine all solutions to the game, given a particular starting placement
        return null;
    }
}
