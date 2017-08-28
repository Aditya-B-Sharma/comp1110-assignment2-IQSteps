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
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
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
    private static final int VIEWER_WIDTH = 750;
    private static final int MARGINWIDTH = 650;
    private static final int VIEWER_HEIGHT = 500;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group placements = new Group();
    private final Group pegs = new Group();
    TextField textField;

    // Source each root asset we need. Then decide on the image to use and orientation.
    ImageView decideImage(int y){
        ImageView AA, BA, CA, DA, EA, FA, GA, HA, AE, BE, CE, DE, EE, FE, GE, HE;
        AA=BA=CA=DA=EA=FA=GA=HA=AE=BE=CE=DE=EE=FE=GE=HE = new ImageView();
        AA.setImage(new Image(Viewer.class.getResource(URI_BASE + "AA.png").toString()));
        BA.setImage(new Image(Viewer.class.getResource(URI_BASE + "BA.png").toString()));
        CA.setImage(new Image(Viewer.class.getResource(URI_BASE + "CA.png").toString()));
        DA.setImage(new Image(Viewer.class.getResource(URI_BASE + "DA.png").toString()));
        EA.setImage(new Image(Viewer.class.getResource(URI_BASE + "EA.png").toString()));
        FA.setImage(new Image(Viewer.class.getResource(URI_BASE + "FA.png").toString()));
        GA.setImage(new Image(Viewer.class.getResource(URI_BASE + "GA.png").toString()));
        HA.setImage(new Image(Viewer.class.getResource(URI_BASE + "HA.png").toString()));
        AE.setImage(new Image(Viewer.class.getResource(URI_BASE + "AE.png").toString()));
        BE.setImage(new Image(Viewer.class.getResource(URI_BASE + "BE.png").toString()));
        CE.setImage(new Image(Viewer.class.getResource(URI_BASE + "CE.png").toString()));
        DE.setImage(new Image(Viewer.class.getResource(URI_BASE + "DE.png").toString()));
        EE.setImage(new Image(Viewer.class.getResource(URI_BASE + "EE.png").toString()));
        FE.setImage(new Image(Viewer.class.getResource(URI_BASE + "FE.png").toString()));
        GE.setImage(new Image(Viewer.class.getResource(URI_BASE + "GE.png").toString()));
        HE.setImage(new Image(Viewer.class.getResource(URI_BASE + "HE.png").toString()));
        if (y == 1) {
            return BA;
        } else {
        return AA;
        }

    }

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */

    void makePlacement(String placement) {
        makePegs();
        List<String> traverse = StepsGame.getPiecePlacements(placement);


        // FIXME Task 4: implement the simple placement viewer
    }
    void makePegs() {
        GridPane gridPane = new GridPane();

        gridPane.setPrefSize(VIEWER_WIDTH, VIEWER_HEIGHT); // Default width and height
        //gridPane.setGridLinesVisible(true);
        gridPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        gridPane.setPadding(new Insets(15, 0, 0, 25));

        for (int k = 0; k < 10; k ++) {
            ColumnConstraints column = new ColumnConstraints(70);
            gridPane.getColumnConstraints().add(column);
        }
        for (int l = 0; l < 5; l ++) {
            RowConstraints row = new RowConstraints(70);
            gridPane.getRowConstraints().add(row);
        }

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

        for (Node node : gridPane.getChildren()) {
            gridPane.setMargin(node, new Insets(0, 0, 0, 15));
        }

        pegs.getChildren().addAll(gridPane);
    }



    void drawPieces(List<String> givenPlacements) {

        GridPane gridPane = new GridPane();

        gridPane.setPrefSize(VIEWER_WIDTH, VIEWER_HEIGHT); // Default width and height
        gridPane.setGridLinesVisible(true);
        gridPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        gridPane.setPadding(new Insets(15, 0, 0, 25));

        for (int k = 0; k < 10; k ++) {
            ColumnConstraints column = new ColumnConstraints(70);
            gridPane.getColumnConstraints().add(column);
        }
        for (int l = 0; l < 5; l ++) {
            RowConstraints row = new RowConstraints(70);
            gridPane.getRowConstraints().add(row);
        }


        for (Node node : gridPane.getChildren()) {
            gridPane.setMargin(node, new Insets(0, 0, 0, 15));
        }

        Insets valuePadding = new Insets(0, 0, 0, -105);
        //gridPane.add(a, 0, 0);
        //ImageView x = decideImage();
        //gridPane.add(x, 1, 1);
        //gridPane.setMargin(x, valuePadding);
        //gridPane.add(c1, 0, 0);
        //gridPane.add(c2, 2, 1);
        //gridPane.add(d, 3, 0);
        //gridPane.add(e, 4, 0);
        //gridPane.add(f, 5, 0);
        //gridPane.add(g, 6, 1);
        //gridPane.add(h, 7, 2);
        //gridPane.add(i, 1, 1);
        //gridPane.add(j, 2, 2);

        placements.getChildren().addAll(gridPane);
    }

    // Remove previous window drawn
    void removePrevious() {
        pegs.getChildren().clear();
        placements.getChildren().clear();

    }

    // Breaks placement string into its puzzle piece components
    private static String breakPlacementString(String placement) {
        return "";
    }

    // Get x position of piece
    int getXPos() {
        return 0;
    }

    // Get y position of piece
    int getYPos() {
        return 0;
    }

    // Displays image so the origin and orientation is correct
    private static void displayImage(int x, int y) {

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
                removePrevious();
                makePlacement(textField.getText());
                textField.clear();
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
        makePegs();
        makeControls();



        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
