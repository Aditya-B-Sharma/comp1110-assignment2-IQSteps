package comp1110.ass2.gui;

import comp1110.ass2.StepsGame;
import gittest.A;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.List;


/**
 * A very simple viewer for piece placements in the steps game.
 *
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int ROWS = 5;
    private static final int COLS = 10;
    private static final int SQUARE_SIZE = 60;
    private static final int PIECE_IMAGE_SIZE = (int) ((3*SQUARE_SIZE)*1.33);
    private static final int VIEWER_WIDTH = 933;
    private static final int MARGINWIDTH = 650;
    private static final int VIEWER_HEIGHT = 700;
    private static final String URI_BASE = "assets/";

    //Create needed groups for different elements
    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group placements = new Group();
    private final Group pegs = new Group();
    TextField textField;


    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */
    //we use getPiecePlacements from StepsGame to simplify this

    void makePlacement(String placement) {
        Label labelx = new Label("Error: Placement must be valid.");
        if (placement.isEmpty()){
            removePrevious();
            pegs.getChildren().add(labelx);
            makePegs();
//        } else if (!StepsGame.isPlacementSequenceValid(placement)){
//            removePrevious();
//            pegs.getChildren().add(labelx);
//            makePegs();
        }
         else {
            List<String> traverse = StepsGame.getPiecePlacements(placement);
            drawPieces(traverse);
            makePegs();
        }
        // FIXME Task 4: implement the simple placement viewer
    }

    //create pegs
    private void makePegs() {
        GridPane gridPane = new GridPane();

        gridPane.setPrefSize(VIEWER_WIDTH, VIEWER_HEIGHT); // Default width and height
        //set padding to center gridpane
        //these loops will make a row / column at every iteration for the grid,
        // since the game is 10 x 5, 10 columns and 5 rows will be made
        for (int k = 0; k < 10; k ++) {
            ColumnConstraints column = new ColumnConstraints(70);
            gridPane.getColumnConstraints().add(column);
        }
        for (int l = 0; l < 5; l ++) {
            RowConstraints row = new RowConstraints(70);
            gridPane.getRowConstraints().add(row);
        }

        //decide our peg positions
        for (int y = 0; y<10; y++) {
            for (int z = 0; z<5; z++) {
                if (y % 2 == 0 && z % 2 == 0 ) {
                    Circle x = new Circle(20);
                    x.setOpacity(0.3);
                    gridPane.add(x, y, z);
                } else if (y % 2 == 1 && z % 2 == 1) {
                    Circle x = new Circle(20);
                    x.setOpacity(0.3);
                    gridPane.add(x, y, z);
                }
            }
        }

        //fix peg positions as they were underpadded on the left
        //set up the pegs properly
        //add all circles (pegs) to parent group

        pegs.getChildren().addAll(gridPane);
    }

    // Source each root asset we need. Then decide on the image to use and orientation.
    //this returns the imageview type to be used, which has the relevant image
    private ImageView decideImage(String piece){
        String toFetch = "";
        int spinAmount = 0;
        Character toCompare = piece.charAt(1);
        if (piece.charAt(0) >= 'A' && piece.charAt(0) <= 'H') {
            if (toCompare >= 'A' && toCompare < 'E') {
                spinAmount = toCompare%'A';
                toFetch = "A";
            } else if (toCompare >= 'E' && toCompare <= 'H'){
                spinAmount = toCompare%'E';
                toFetch = "E";
            }
        }
        ImageView outputImage = new ImageView();
        Image image = new Image((Viewer.class.getResource(URI_BASE + piece.charAt(0) + toFetch + ".png").toString()));
        outputImage.setImage(image);
        if (spinAmount!=0){
            outputImage.setRotate(90*spinAmount);
        }
        return outputImage;

    }

    //draw the piece given the imageview type we receive, on top of gridpane
    private void drawPieces(List<String> givenPlacements) {

        GridPane gridPane = new GridPane();

        gridPane.setPrefSize(VIEWER_WIDTH, VIEWER_HEIGHT); // Default width and height

        for (int k = 0; k < 10; k ++) {
            ColumnConstraints column = new ColumnConstraints(70);
            gridPane.getColumnConstraints().add(column);
        }
        for (int l = 0; l < 5; l ++) {
            RowConstraints row = new RowConstraints(70);
            gridPane.getRowConstraints().add(row);
        }

        //place image files (pieces) and set their padding
        // go through all given placements and do this
        for (int x = 0; x < givenPlacements.size(); x++) {
            ImageView image = decideImage(givenPlacements.get(x));
            gridPane.add(image, getXPos(givenPlacements.get(x)), getYPos(givenPlacements.get(x)));
            gridPane.setLayoutX(-120);
        }
        placements.getChildren().addAll(gridPane);
    }

    // Remove previous window drawn
    //clears the groups
    private void removePrevious() {
        pegs.getChildren().clear();
        placements.getChildren().clear();

    }

    // Breaks placement string into its puzzle piece components
    private static String breakPlacementString(String placement) {
        return "";
    }
    // I did this in makePlacement using the public function getPiecePlacements from StepsGame

    // Get x position of piece
    private int getXPos(String piece) {
        int out = 0;
        Character letter = piece.charAt(2);
        if (Character.isUpperCase(letter) && letter != 'Z') {
            out = (letter%'A') % 10;
          //Debugging ->  System.out.println(out);
        } else if (Character.isLowerCase(letter) && letter!= 'z') {
            out = (letter % 'a') % 10;
          //Debugging ->  System.out.println(out);
        }
        return out;
    }
    // Get y position of piece
    private int getYPos(String piece) {
        int out = 0;
        Character letter = piece.charAt(2);
        if (Character.isUpperCase(letter) && letter != 'Z') {
            if (letter >= 'A' && letter <= 'J') {
                out = 0;
            } else if (letter >= 'K' && letter <= 'T'){
                out = 1;
            } else { out = 2; }
        } else if (Character.isLowerCase(letter) && letter != 'z') {
            if (letter >= 'a' && letter <= 'e') {
                out = 2;
            } else if (letter >= 'f' && letter <= 'o'){
                out = 3;
            } else { out = 4; }
        }
        //Debugging -> System.out.println(out);
        return out;
    }
    // Displays image so the origin and orientation is correct
    private static void displayImage(int x, int y) {
        //done in make placement
    }
    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("pressed");
                removePrevious();
                makePlacement(textField.getText());
                //textField.clear();
            }
        });
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)){
                    removePrevious();
                    makePlacement(textField.getText());
                    //textField.clear();
                }
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("StepsGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        root.getChildren().addAll(pegs, placements, controls);
        pegs.relocate(133.29, 80);
        placements.relocate(133.29, 80);
        controls.relocate(133.29, 0);
        makePegs();
        makeControls();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
