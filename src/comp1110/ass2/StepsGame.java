package comp1110.ass2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

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
        ArrayList<String> ugh;
        ugh = collector(placement);

        if (placement.length() % 3 == 0 && checkDuplicates(ugh)) {
            return true;
        }
        return false;
    }
    // Method collects shapes in the placements.
    private static ArrayList<String> collector (String in) {
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < in.length(); i++) {
            if (i % 3 == 0)
                out.add(String.valueOf(in.charAt(i)));
        }
        return out;
    }
    // checks if input contains any duplicate
    private static boolean checkDuplicates (ArrayList<String> in) {
        for (int i = 0; i < in.size(); i++) {
            int bool = 0;
            for (int j = 0; j < in.size(); j++) {
                if (in.get(i).equals(in.get(j))) {
                    bool++;
                }
            }
            if (bool > 1) {
                return true;
            }
        }
        return false;
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
    static boolean isPlacementSequenceValid(String placement) {
        // FIXME Task 5: determine whether a placement sequence is valid
        return false;
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
