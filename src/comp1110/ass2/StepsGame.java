package comp1110.ass2;

import java.util.*;

/**
 * This class provides the text interface for the Steps Game
 *
 * The game is based directly on Smart Games' IQ-Steps game
 * (http://www.smartgames.eu/en/smartgames/iq-steps)
 */
public class StepsGame {

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

    int[] allPositions = {0,1,2,3,4,5,6,7,8,9,
                         10,11,12,13,14,15,16,17,18,19,
                         20,21,22,23,24,25,26,27,28,29,
                         30,31,32,33,34,35,36,37,38,39,
                         40,41,42,43,44,45,46,47,48,49};

    ArrayList<Integer> postions = new ArrayList<>( /* allpositions */);

    static boolean isPlacementSequenceValid(String placement) {
        // FIXME Task 5: determine whether a placement sequence is valid
        return false;
    }

    // placement as String
    private static boolean isValidPlacement(String placement) { return false; }

    private static void updateValidLocations(String placement) {

    }

    // method to remove 'masked' locations. Returns updated positions.
    public static int[] remove(int[] numbers, int target) {
        int count = 0;
        for (int number: numbers) {
            if (number == target) {
                count++;
            }
        }
        if (count == 0) {
            return numbers;
        }
        int[] result = new int[numbers.length - count];
        int index = 0;
        for (int value : numbers) {
            if (value != target) {
                result[index] = value;
                index++;
            }
        }
        return result;
    }
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
