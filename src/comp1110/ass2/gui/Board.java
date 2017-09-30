package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Board extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int ROWS = 5;
    private static final int COLS = 10;
    private static final int SQUARE_SIZE = 60;
    private static final int PIECE_IMAGE_SIZE = (int) ((3*SQUARE_SIZE)*1.33);
    private static final int MARGINWIDTH = 650;

    private static final String URI_BASE = "assets/";

    //Create needed groups for different elements
    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group placements = new Group();
    private final Group pegs = new Group();
    TextField textField;

    /*Inner class to display the shapes*/
    class FXShape extends ImageView {
        int shape;

        FXShape(String shape) {
            if (!(shape.charAt(0) >= 'A' && shape.charAt(0) <= 'H')) {
                throw new IllegalArgumentException("Bad shape: \"" + shape + "\"");
            }
            setImage(new Image(Board.class.getResourceAsStream(URI_BASE + shape + ".png").toString()));
//                this.shape = shape-65;
            setFitHeight(PIECE_IMAGE_SIZE);
            setFitWidth(PIECE_IMAGE_SIZE);
        }

        FXShape(String shape, char position) {
            this(shape);
            if (!((position >= 'A' && position <= 'Y') || (position >= 'a' && position <= 'y'))) {
                throw new IllegalArgumentException("Bad position string: " + position);
            }
        }
    }
    // FIXME Task 7: Implement a basic playable Steps Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement starting placements

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting starting placements

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
