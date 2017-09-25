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

    private static int[] AA = {1,2,0,
                2,1,2,
                1,0,0};

    private static int[] BA = {0,2,0,
                0,1,2,
                0,2,1};

    private static int[] CA = {0,2,0,
                0,1,2,
                1,2,0};

    private static int[] DA = {0,2,0,
                2,1,0,
                0,2,1};

    private static int[] EA = {0,2,0,
                2,1,0,
                1,2,0};

    private static int[] FA = {0,0,1,
                0,1,2,
                1,2,0};

    private static int[] GA = {0,2,1,
                0,1,2,
                1,2,0};

    private static int[] HA = {0,2,1,
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
        return  (piecePlacement.length() == 3 && isValid(piecePlacement));
    }

    // Method checks if a string contains
    private static boolean isValid(String input) {
        char[] arr = input.toCharArray();
        return ((arr[0] >= 65 && arr[0] <= 72) && (arr[1] >= 65 && arr[1] <= 72) && ((arr[2] >= 65 && arr[2] <= 89) || (arr[2] >= 97 && arr[2] <= 122)));
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
    public static String collector (String in) {
        StringBuilder initial = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            if (i % 3 == 0)
                initial.append(String.valueOf(in.charAt(i)));
        }
        return initial.toString();
    }

    // Checks whether an input contains duplicates, i.e. whether a shape occurs more than once
    private static boolean containsDuplicates (String input) {
        ArrayList<Character> charList = new ArrayList<>();
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
       for (String item : arrayOfPlacements) {
           if (!(isPiecePlacementWellFormed(item))) {
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


    //transpose and flip will be used to respectively spin or flip the source image
    // this uses our explicit definitions of the pieces and changes them to make the piece given in placements
    // i.e to make AA -> AB or to make AA-> AE

    public static int[] transpose(int[] turn){
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
    public static int[] flip(int[] turn){
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
        StringBuilder out = new StringBuilder();
        for (int i : list){
            out.append(i);
        }
        return out.toString();
    }

    // Transposes or flips a piece placement n number of times.
    public static int[] transposeAmount(String piece){
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
    private static boolean boundaryCheck(Character pos, int xyPointer) {
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
        // String of all possible positions on the board
        String all = "ABCDEFGHIJKLMNOPQRSTUVWXYabcdefghijklmnopqrstuvwxy";
        // Location from origin of piece - depends on indexing tis variable
        int[] locationIndices = {-11,-10,-9,-1, 0 ,1,9,10,11};
        //System.out.println(toString(flip(AA)));
        HashSet<Character> upperList = new HashSet<>();
        HashSet<Character> checkList = new HashSet<>();
        List<String> places = getPiecePlacements(placement);
        if (isPlacementWellFormed(placement)) {
            for (String x : places) {

                Character pieceOriginPeg = x.charAt(2);
                int[] transposed = transposeAmount(x.charAt(0) + "" + x.charAt(1));

                ArrayList<Character> upChars = new ArrayList<>();
                    for (int j = 0; j < 9; j++) {
                        if (transposed[j] != 0) {

                            if (!boundaryCheck(pieceOriginPeg, j)) {
                                return false;
                            }

                            else if (transposed[j] == 1) {
                                Character currentChar = all.charAt(all.indexOf(pieceOriginPeg) + locationIndices[j]);
                                if (boundaryCheck(currentChar, 1)) {
                                    Character topChar = all.charAt(all.indexOf(currentChar) -10);
                                    if (upperList.contains(topChar)) {
                                        return false;
                                    }
                                }
                                if (boundaryCheck(currentChar, 3)) {
                                    Character leftChar = all.charAt(all.indexOf(currentChar) - 1 );
                                    if (upperList.contains(leftChar)) {
                                        return false;
                                    }
                                }
                                if (boundaryCheck(currentChar, 5)) {
                                    Character rightChar = all.charAt(all.indexOf(currentChar) + 1 );
                                    if (upperList.contains(rightChar)) {
                                        return false;
                                    }
                                }
                                if (boundaryCheck(currentChar, 7)) {
                                    Character botChar = all.charAt(all.indexOf(currentChar) + 10 );
                                    if (upperList.contains(botChar)) {
                                        return false;
                                    }
                                }
                            }
                            else if (checkList.add(all.charAt(all.indexOf(pieceOriginPeg) + locationIndices[j]))) {
                                checkList.add(all.charAt(all.indexOf(pieceOriginPeg) + locationIndices[j]));
                                if (transposed[j] == 2) {
                                    upChars.add(all.charAt(all.indexOf(pieceOriginPeg) + locationIndices[j]));
                                }
                            }
                            else {
                                return false;
                            }
                        }
                    }
                upperList.addAll(upChars);
                //System.out.println("list of upper characters (in upperlist hashset , appended once piece checking is finished): " + upperList);
            }
        }
        else {
            return false;
        }
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
        Set<String> allowedPlacements = new HashSet<>();                                                    // The Set in which valid next moves are added to

        ArrayList<String> pos = new ArrayList<>(getPiecePlacements(getUnusedPieces(placement, objective))); // Possible viable pieces as an array

        String[] posMoves = possibleMoves(permute(pos, placement));                                         // Array of first pieces of each permutation

        allowedPlacements.addAll(Arrays.asList(posMoves));

        //System.out.println("placement string: " + placement + " | allowed placements: " + allowedPlacements + " | objective string: " + objective );

        return allowedPlacements;
    }

    // FIXME Task 6: determine the correct order of piece placements

    // Finds all possible next moves
    private static String[] possibleMoves (ArrayList<List<String>> permutations) {
        int permSize = permutations.size();

        String[] out = new String[permSize];
        for (int i = 0; i < permSize; i++) {
            out[i] = permutations.get(i).get(0);
        }
        return out;
    }

    // Finds permutations of shapes
    private static ArrayList<List<String>> permute(List<String> unused, String placement) {
        ArrayList<List<String>> out = new ArrayList<>();

        if (unused.size() == 0) {
            out.add(new ArrayList<>());
            return out;
        }

        String firstElement = unused.remove(0);
        List<List<String>> permutations = permute(unused, placement);

        for (List<String> smallerPermutated : permutations) {

            for (int index = 0; index <= smallerPermutated.size(); index++) {
                List<String> temp = new ArrayList<>(smallerPermutated);

                if (!firstElement.isEmpty()) {
                temp.add(index, firstElement);

                if (isPlacementSequenceValid(placement + join(temp))) {
                out.add(temp);
                }

                }
            }

        }

        return out;
    }


    // Joins set of permutations
    private static String join (List<String> inn) {
        StringBuilder out = new StringBuilder();
        for (String s : inn) {
            out.append(s);
        }
        return out.toString();
    }

    //Finds unused pieces
    public static String getUnusedPieces(String placement, String objective) {
        String[] obj = objective.split("(?<=\\G.{3})");     //Pieces of in objective
        String[] place = placement.split("(?<=\\G.{3})");   // Pieces in placement
        ArrayList<String> out = new ArrayList<>(Arrays.asList(obj));
        StringBuilder outPiece = new StringBuilder();

        out.removeAll(Arrays.asList(place));

        for (String piece : out) {
            outPiece.append(piece);
        }

        return outPiece.toString();
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
        String[] output;

        /* STEP 1) STORE ALL POSSIBLE PIECE PLACEMENTS IN A DATA STRUCTURE */

        // All the pieces and their variations
        String[] shapesAndOrientations = {"AA","AB","AC","AD","AE","AF","AG","AH",
                                          "BA","BB","BC","BD","BE","BF","BG","BH",
                                          "CA","CB","CC","CD","CE","CF","CG","CH",
                                          "DA","DB","DC","DD","DE","DF","DG","DH",
                                          "EA","EB","EC","ED","EE","EF","EG","EH",
                                          "FA","FB","FC","FD","FE","FF","FG","FH",
                                          "GA","GB","GC","GD","GE","GF","GG","GH",
                                          "HA","HB","HC","HD","HE","HF","HG","HH"};
        // All locations
        String[] locations = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y",
                              "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y"};
        // All possible moves each piece can take
        ArrayList<String> possibleMoves = getPossibleMoves(shapesAndOrientations, locations);

        //All possible moves each REMAINING piece can take
        ArrayList<String> remainingMoves = updateRemainingMovies(placement, possibleMoves);

        /* STEP 2) INITIALIZE CONTAINERS FOR UPDATING AVAILABLE LOCATIONS AND POSSIBLE MOVE FOR REMAINING PIECES */

        // Updated data structures for available locations and possible moves for remaining pieces
        ArrayList<String> availableLocations;
        ArrayList<String> updatedRemainingMoves;
        System.out.println(possibleMoves);
        return null;
    }

    static ArrayList<String> getPossibleMoves(String[] shapesAndOrientations, String[] locations) {
        /* RETURNS AN ARRAYLIST OF ALL POSSIBLE PLACEMENTS OF EACH INDIVIDUAL PIECE */
        ArrayList<String> possibleMoves = new ArrayList<>();
        for (String s : shapesAndOrientations) {
            for (String l : locations) {
                if (isPlacementSequenceValid(s + l)) {
                    possibleMoves.add(s + l);
                }
            }
        }
        return possibleMoves;
    }

    static ArrayList<String> updateRemainingMovies(String placement, ArrayList<String> moves) {
        /* RETURNS AN ARRAYLIST OF ALL POSSIBLE PLACEMENTS OF EACH REMAINING PIECE */
        ArrayList<String> remainingMoves = moves;
        char[] placementChars = placement.toCharArray();
        for (String p : moves) {
            char[] characters = p.toCharArray();
            if (characters[0] == placementChars[0]) {
                remainingMoves.remove(p);
            }
        }
        return remainingMoves;
    }


    public static void main(String[] args) {
        String objective = "BGSAHQEFBGCgCDNHFlDAiFHn";
        String placement = "BGSEFBAHQGCgCDNDAiHFlFHn";
        System.out.println(getViablePiecePlacements(placement, objective));
        getSolutions("BGS");
        System.out.println(isPlacementSequenceValid("AEg"));
    }
}


