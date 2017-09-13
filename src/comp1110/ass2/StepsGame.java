package comp1110.ass2;

import com.sun.deploy.util.StringUtils;
import gittest.C;

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

    // Checks whether an input contains duplicates, i.e. whether a shape occurs more than once
    private static boolean containsDuplicates (String input) {
        ArrayList<Character> charList = new ArrayList<Character>();
        HashSet<Character> letterSet = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            charList.add(input.charAt(i));
        }
        for (Character i : charList) {
            if (!letterSet.add(i)) {
                return false;
            }
            else {
                letterSet.add(i);
            }
        }
        return true;
    }

    //Splits placement input into pieceplacements, each of length 3.
    // Idea from: https://stackoverflow.com/questions/3760152/split-string-to-equal-length-substrings-in-java
    public static List<String> getPiecePlacements(String placement) {
        String[] out = placement.split("(?<=\\G.{3})");
        return Arrays.asList(out);
    }

    // Maps the piecePlacementWellFormed method from task 1 onto the entire each piece placement
    private static boolean mapisPiecePlacementWellFormed(List<String> arrayOfPlacements) {
       for (int i = 0; i < arrayOfPlacements.size(); i++) {
           //System.out.println(arrayOfPlacements.get(i));
           if (!(isPiecePlacementWellFormed(arrayOfPlacements.get(i)))) {
               return false;
           }
       }
        return true;
    }

    /**
     * Determine whether a placement sequence is valid.  To be valid, the placement
     * sequence must be well-formed and each piece placement must be a valid placement
     * (with the pieces ordered according to the order in which they are played).
     *
     * @param placement A placement sequence string
     * @return True if the placement sequence is valid
     */


    // String of all possible positions on the board
    static String all = "ABCDEFGHIJKLMNOPQRSTUVWXYabcdefghijklmnopqrstuvwxy";

    //transpose and flip will be used to respectively spin or flip the source image
    // this uses our explicit definitions of the pieces and changes them to make the piece given in placements
    // i.e to make AA -> AB or to make AA-> AE

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

    // flips the int array, i.e. mirrors the array
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


    // toString for debugging
    static String toString(int[] list) {
        String out = "";
        for (int i : list){
            out += i;
        }
        return out;
    }

    // Transposes or flips a piece placement n number of times.
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

    //Checks whether the position of a piece placement is valid or not
    // i.e if a position B has a piece placed upon it where the pieces coords
    // for the piece directly above at (i.e piece origin - 10) would be invalid,
    // we need to make a bounds checker
    // this is explicit
    static boolean boundaryCheck(Character pos, int xyPointer) {
        String sideL = "KUf";
        String sideR = "Teo";
        switch (pos) {
            case 'A':
                if (xyPointer == 0 ||xyPointer == 1 ||xyPointer == 2 ||xyPointer == 3||xyPointer == 6){
                    return false;
                }
                break;
            case 'J':
                if (xyPointer == 0 ||xyPointer == 1 ||xyPointer == 2 ||xyPointer == 5||xyPointer == 8){
                    return false;
                }
                break;
            case 'p':
                if (xyPointer == 0 ||xyPointer == 3 ||xyPointer == 6 ||xyPointer == 7||xyPointer == 8){
                    return false;
                }
                break;
            case 'y':
                if (xyPointer == 2 ||xyPointer == 5 ||xyPointer == 6 ||xyPointer == 7||xyPointer == 8){
                    return false;
                }
                break;

        }
        if ((pos % 'B' < 8) &&(xyPointer == 0 || xyPointer == 1 ||xyPointer == 2)){
            return false;
        } else if ((pos % 'q' < 8) && (xyPointer == 6 || xyPointer == 7 ||xyPointer == 8)) {
            return false;
        } else if ((sideL.contains(pos+"")) && (xyPointer == 0 || xyPointer == 3 ||xyPointer == 6)) {
            return false;
        } else if ((sideR.contains(pos+"")) && (xyPointer == 2 || xyPointer == 5 ||xyPointer == 8)) {
            return false;
        }
        return true;
    }

    // check if a placement is valid by first checking wellformed and then transposing our variable root pieces as needed,
    // the doing our boundary check wherever our piece has a 1 or 2, (meaning there would be a circle there)
    // then look through our ALL string containing all positions and apply the relative index location indices
    //to get the piece at j position from the middle of our piece.
    // the indices work like this:
    // -11 = top left , -10 = above, -9= top right
    // -1 = left, 0 = middle piece (origin) , +1 = right
    // +9 = bottom left, +10 = directly below, +11 = bottom right
    public static boolean isPlacementSequenceValid(String placement) {
        int[] locationIndices = {-11,-10,-9,-1, 0 ,1,9,10,11};
        //System.out.println(toString(flip(AA)));
        String pegs = "ACEGILNPRTUWYbdgikmoprtvx";
        String exclude = "ABCDEFGHIJKUTfpqrstuvwxyeo";
        HashSet<Character> upperList = new HashSet<Character>();
        HashSet<Character> checkList = new HashSet<Character>();
        List<String> places = getPiecePlacements(placement);
        if (isPlacementWellFormed(placement)) {
            for (int i = 0; i < places.size(); i++) {
                String x = places.get(i);
                Character pieceOriginPeg = x.charAt(2);
                System.out.println(x);
                int[] transposed = transposeAmount(x.charAt(0) + "" + x.charAt(1));
                ArrayList<Character> upchars = new ArrayList<Character>();
                    for (int j = 0; j < 9; j++) {
                        if (transposed[j] != 0) {
                            //System.out.println(j);
                            if (!boundaryCheck(pieceOriginPeg, j)) {
                                //System.out.println(x);
                                return false;
                            } else if (transposed[j] == 1) {
                                //System.out.println(x.charAt(2) + locationIndices[j]);
                                Character currentChar = all.charAt(all.indexOf(pieceOriginPeg) + locationIndices[j]);
                                System.out.println("currentChar: "+ currentChar);
                                System.out.println("lower peg: " + currentChar);
                                Character topChar = null;
                                Character rightChar = null;
                                Character botChar = null;
                                Character leftChar = null;
                                if (boundaryCheck(currentChar, 1)) {
                                    System.out.println("upperlist at topchar: " + upperList);
                                    topChar = all.charAt(all.indexOf(currentChar) -10);
                                    if (upperList.contains(topChar)) {
                                        System.out.println("topchar false" + ": " + topChar);
                                        return false;
                                    }
                                }
                                if (boundaryCheck(currentChar, 3)) {
                                    System.out.println("upperlist at leftchar: " + upperList);
                                    leftChar = all.charAt(all.indexOf(currentChar) - 1 );
                                    if (upperList.contains(leftChar)) {
                                        System.out.println("leftchar false" + ": " + leftChar);
                                        return false;
                                    }
                                }
                                if (boundaryCheck(currentChar, 5)) {
                                    System.out.println("upperlist at rightchar: " + upperList);
                                    rightChar = all.charAt(all.indexOf(currentChar) + 1 );
                                    if (upperList.contains(rightChar)) {
                                        System.out.println("rightchar false" + ": " + rightChar);
                                        return false;
                                    }
                                }
                                if (boundaryCheck(currentChar, 7)) {
                                    System.out.println("upperlist at botchar: " + upperList);
                                    botChar = all.charAt(all.indexOf(currentChar) + 10 );
                                    if (upperList.contains(botChar)) {
                                        System.out.println("botchar false" + ": " + botChar);
                                        return false;
                                    }
                                }
                            }
                            else if (checkList.add(all.charAt(all.indexOf(pieceOriginPeg) + locationIndices[j]))) {
                                checkList.add(all.charAt(all.indexOf(pieceOriginPeg) + locationIndices[j]));
                                if (transposed[j] == 2) {
                                    upchars.add(all.charAt(all.indexOf(pieceOriginPeg) + locationIndices[j]));
                                    System.out.println("list of upper characters (not appended to upperlist yet) : " + upchars.toString());
                                }
                                //System.out.println(checkList);
                            } else {
                                return false;
                            }
                        }
                    }
                upperList.addAll(upchars);
                System.out.println("list of upper characters (in upperlist hashset , appended once piece checking is finished): " + upperList);
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
        Set<String> possibleNextMoves = new HashSet<String>();
        List<String> init = new ArrayList<String>();
        List<String> end = getPiecePlacements(objective);
        List<String> remainder = new ArrayList<String>();
        if (!placement.isEmpty()) {
            init = getPiecePlacements(placement);
        }
        for (int i = 0; i< end.size() ; i++) {
            if (!init.contains(end.get(i))) {
                remainder.add(end.get(i));
            }
        }
        System.out.println("initial list: " + init);
        System.out.println("objective list: " + end);
        System.out.println("remainder list: " + remainder);
        for (String piece : remainder) {
            for (String otherPieces : remainder) {
                if (piece == otherPieces) {
                    continue;
                }
            }
        }

        possibleNextMoves.addAll(end);


        // FIXME Task 6: determine the correct order of piece placements
        return possibleNextMoves;
    }

    static String backtrack(List<String> init, List<String> remainder) {
        String output = init.toString();
        return output;

    }

    /**
     * Return an array of all unique (unordered) solutions to the game, given a
     * starting placement.   A given unique solution may have more than one than
     * one placement sequence, however, only a single (unordered) solution should
     * be returned for each such case.
     *
     * @param placement  A valid piece placement string.
     * @return An array of strings, each describing a unique unordered solution to
     * the game given the starting point provided by placement.
     */
    static String[] getSolutions(String placement) {
        // FIXME Task 9: determine all solutions to the game, given a particular starting placement
       ArrayList<String> solutions = new ArrayList<>();
       String[] output = new String[solutions.size()];
       Set<String> viablePiecePlacements = getViablePiecePlacements(placement, solution(placement));
       return null;
    }

    private static String solution(String placement) {
        return null;
    }


    public static void main(String[] args) {
        //System.out.println(isPlacementSequenceValid("CEQEHS"));
    }
}


