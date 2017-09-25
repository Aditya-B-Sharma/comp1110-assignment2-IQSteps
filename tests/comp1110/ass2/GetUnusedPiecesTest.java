package comp1110.ass2;

import org.junit.Test;

import java.util.Collections;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Khamis on 24/09/2017.
 */
public class GetUnusedPiecesTest {
    String objective = "DBgGAiFCNBGKCFlAFnHHSECP";
    String placement = "BGKFCNCFl";
    String unused = "DBgGAiAFnHHSECP";

    String[] ss = {"ABNJSHFSF","AKFSBFW","HDJGJHE","FJHHFHS","OPKFPSFJ"};

    @Test
    public void testGood(){
        assertTrue(Collections.singletonList(unused).equals(Collections.singletonList(StepsGame.getUnusedPieces(placement, objective))));
    }
    @Test
    public void testSize() {
        assertTrue(unused.length() == StepsGame.getUnusedPieces(placement, objective).length());
    }
    @Test
    public void testBad() {

    }
}
