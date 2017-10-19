package comp1110.ass2;

import org.junit.Test;

import java.util.Collections;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Khamis on 24/09/2017.
 */

// Reference: Lecture Material
public class GetUnusedPiecesTest {

    /*Empty placement but non-empty objective*/
    String obj = "GHs";
    String emptPlace = "";
    String unused3 = "GHs";

    @Test
    public void testGood(){
        String objective = "DBgGAiFCNBGKCFlAFnHHSECP";
        String placement = "BGKFCNCFl";
        String unused = "DBgGAiAFnHHSECP";
        assertTrue("Expected " + unused + " but got: " + StepsGame.getUnusedPieces(placement, objective) ,Collections.singletonList(unused).equals(Collections.singletonList(StepsGame.getUnusedPieces(placement, objective))));
        assertTrue("Expected 0, but got: " + unused.length()%3, unused.length()%3 == 0);
    }

    @Test
    public void testNumberOfUnusedPieces() {
        String objective = "DBgGAiFCNBGKCFlAFnHHSECP";
        String placement = "BGKFCNCFl";
        String unused = "DBgGAiAFnHHSECP";
        //assertTrue(unused.length() == StepsGame.getUnusedPieces(placement, objective).length());
        assertTrue("For objective, "+ objective + " and " +" placement, " + placement + ", number of unused pieces should be " + unused.length()/3 + " but got " + StepsGame.getUnusedPieces(placement, objective).length()/3, unused.length() == StepsGame.getUnusedPieces(placement, objective).length());
    }

    @Test
    public void testEmptyPlacement() {
        assertTrue("For empty placement, expected " + unused3 + " but got: " + StepsGame.getUnusedPieces(emptPlace, obj), unused3.equals(StepsGame.getUnusedPieces(emptPlace, obj)));
    }
}
