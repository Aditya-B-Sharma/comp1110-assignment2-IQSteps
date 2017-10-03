package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Board extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int SQUARE_SIZE = 60;
    private static final int PIECE_IMAGE_SIZE = (int) ((3*SQUARE_SIZE)*1.33);

    private static final String URI_BASE = "assets/";
    private static GridPane BOARD = new GridPane();

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

    private void makePegs() {
        //GridPane gridPane = new GridPane();

        BOARD.setPrefSize(BOARD_WIDTH, BOARD_HEIGHT); // Default width and height
        //gridPane.setGridLinesVisible(true);
        BOARD.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        //set padding to center gridpane

        BOARD.setPadding(new Insets(15, 0, 0, 25));

        //these loops will make a row / column at every iteration for the grid,
        // since the game is 10 x 5, 10 columns and 5 rows will be made
        for (int k = 0; k < 10; k ++) {
            ColumnConstraints column = new ColumnConstraints(70);
            BOARD.getColumnConstraints().add(column);
        }
        for (int l = 0; l < 5; l ++) {
            RowConstraints row = new RowConstraints(70);
            BOARD.getRowConstraints().add(row);
        }

        //decide our peg positions
        for (int y = 0; y<10; y++) {
            for (int z = 0; z<5; z++) {
                if (y % 2 == 0 && z % 2 == 0 ) {
                    Circle x = new Circle(20);
                    x.setOpacity(0.3);
                    BOARD.add(x, y, z);
                } else if (y % 2 == 1 && z % 2 == 1) {
                    Circle x = new Circle(20);
                    x.setOpacity(0.3);
                    BOARD.add(x, y, z);
                }
            }
        }

        //fix peg positions as they were underpadded on the left
        //set up the pegs properly
        for (Node node : BOARD.getChildren()) {
            BOARD.setMargin(node, new Insets(0, 0, 0, 15));
        }
        //add all circles (pegs) to parent group

        pegs.getChildren().addAll(BOARD);
    }
    // FIXME Task 7: Implement a basic playable Steps Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement starting placements

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting starting placements

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("IQ Steps");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        root.getChildren().add(pegs);
        pegs.relocate(100, 20);
        makePegs();

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
