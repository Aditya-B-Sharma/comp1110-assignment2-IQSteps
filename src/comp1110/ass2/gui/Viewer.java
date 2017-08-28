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

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */

    void makePlacement(String placement) {
        drawPegs();
        //List<String> traverse = StepsGame.getPiecePlacements(placement);


        // FIXME Task 4: implement the simple placement viewer
    }

    void drawPegs() {
        ImageView a = new ImageView();
        a.setImage(new Image(Viewer.class.getResource(URI_BASE + "AA.png").toString()));
        ImageView b = new ImageView();
        b.setImage(new Image(Viewer.class.getResource(URI_BASE + "BA.png").toString()));
        ImageView c = new ImageView();
        c.setImage(new Image(Viewer.class.getResource(URI_BASE + "CA.png").toString()));
        ImageView d = new ImageView();
        d.setImage(new Image(Viewer.class.getResource(URI_BASE + "DA.png").toString()));
        ImageView e = new ImageView();
        e.setImage(new Image(Viewer.class.getResource(URI_BASE + "EA.png").toString()));
        ImageView f = new ImageView();
        f.setImage(new Image(Viewer.class.getResource(URI_BASE + "FA.png").toString()));
        ImageView g = new ImageView();
        g.setImage(new Image(Viewer.class.getResource(URI_BASE + "GA.png").toString()));
        ImageView h = new ImageView();
        h.setImage(new Image(Viewer.class.getResource(URI_BASE + "HA.png").toString()));
        ImageView i = new ImageView();
        i.setImage(new Image(Viewer.class.getResource(URI_BASE + "AE.png").toString()));
        ImageView j = new ImageView();
        j.setImage(new Image(Viewer.class.getResource(URI_BASE + "BE.png").toString()));

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

        for (int y = 0; y<10; y++) {
            for (int z = 0; z<5; z++) {
                if (y % 2 == 0 && z % 2 == 0 ) {
                    Circle x = new Circle(20);
                    gridPane.add(x, y, z);
                } else if (y % 2 == 1 && z % 2 == 1) {
                    Circle x = new Circle(20);
                    gridPane.add(x, y, z);
                }
            }
        }

        for (Node node : gridPane.getChildren()) {
            gridPane.setMargin(node, new Insets(0, 0, 0, 15));
        }

        Insets valuePadding = new Insets(0, 0, 0, -105);
        //gridPane.add(a, 0, 0);
        gridPane.add(b, 0, 1);
        gridPane.setMargin(b, valuePadding);
        //gridPane.add(c1, 0, 0);
        //gridPane.add(c2, 2, 1);
        //gridPane.add(d, 3, 0);
        //gridPane.add(e, 4, 0);
        //gridPane.add(f, 5, 0);
        //gridPane.add(g, 6, 1);
        //gridPane.add(h, 7, 2);
        //gridPane.add(i, 1, 1);
        //gridPane.add(j, 2, 2);

        pegs.getChildren().addAll(gridPane);
    }

    // Remove previous window drawn
    void removePrevious() {
        pegs.getChildren().clear();

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
        drawPegs();

        root.getChildren().addAll(placements, pegs, controls);
        makeControls();



        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
