package comp1110.ass2;

import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Khamis on 24/09/2017.
 */
public class CollectorTest {
    String[] ss = {"CBAABGAJF", "AHFGBFBS", "AHFGSGD", "FHSHWBGFGSGHWQ", "FHSBFDGW", "IFS"};

    @Test
//    public void testEmpty() {
//        String empty = "";
//        String emptyc = "";
//        assertTrue(Collections.singletonList(emptyc).equals(Collections.singletonList(StepsGame.collector(empty))));
//    }
//    @Test
    public void testCollector() {
        String empty = "";
        String one = "A";
        String arb = "BGSAHQEFBGCgCDNHFlDAiFHn";
        String arbc = "BAEGCHDF";

        assertTrue(Collections.singletonList(arbc).equals(Collections.singletonList(StepsGame.collector(arb))));
    }
//    @Test
//    public void testRandom() {
//
//    }
}
