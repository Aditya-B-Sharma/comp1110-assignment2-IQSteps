package comp1110.ass2;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Stefan on 26/09/2017.
 */
public class getPossibleMoves {

    String[] testSimple = {"AA"};
    String[] testSimple2 = {"DD"};
    String[] testSimple3 = {"GG"};
    String[] locations = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y",
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y"};
    String[] testSimpleMoves = {"AAL","AAN","AAP","AAR","AAW","AAY","AAb","AAd","AAg","AAi","AAk","AAm"};
    String[] testSimpleMoves2 = {"DDL","DDN","DDP","DDR","DDW","DDY","DDb","DDd","DDg","DDi","DDk","DDm"};
    String[] testSimpleMoves3 = {"GGM","GGO","GGQ"};

    @Test
    public void testSimple() {
        assertTrue("Failed to get all possible moves, or has an invalid piece placement.", Arrays.equals((StepsGame.getPossibleMoves(testSimple,locations).toArray()),testSimpleMoves));
        assertTrue("Failed to get all possible moves, or has an invalid piece placement.", Arrays.equals((StepsGame.getPossibleMoves(testSimple2,locations).toArray()),testSimpleMoves2));
        assertTrue("Failed to get all possible moves, or has an invalid piece placement.", Arrays.equals((StepsGame.getPossibleMoves(testSimple3,locations).toArray()),testSimpleMoves3));
        //assertTrue("Failed to get all possible moves, or has an invalid piece placement.", Arrays.equals(testSimpleMovesL2.toArray(),testSimpleMoves2));
    }

}
