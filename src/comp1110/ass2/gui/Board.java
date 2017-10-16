package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.net.URI;
import java.util.ArrayList;

public class Board extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final double CIRCLE_SIZE = 22.5;
    private static final int MARGIN_X = 121;
    private static final int MARGIN_Y = 406;

    

//    private static final double BOARD_WIDTH = (2*BOARD_X) + (COLS * SQUARE_SIZE);
//    private static final double BOARD_HEIGHT = (2*BOARD_Y) + (ROWS * SQUARE_SIZE);

    // Need to somehow link the positions with each node.
    private static int[] pos = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                               10,11,12,13,14,15,16,17,18,19,
                               20,21,22,23,24,25,26,27,28,29,
                               30,31,32,33,34,35,36,37,38,39,
                               40,41,42,43,44,45,46,47,48,49};

    private static final String URI_BASE = "assets/";
    private static GridPane BOARD = new GridPane();
    final GridPane target = BOARD;

    //final String piece;

    //Create needed groups for different elements
    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group placements = new Group();
    private final Group pegs = new Group();
    TextField textField;
    private final Group pieces = new Group();
    /* the state of the pieces */
    static final String NOT_PLACED = " ";

    String[] piecestate = new String[8];


    /*Inner class to display the shapes*/
    class Piece extends ImageView {
        String piece;

    //Image image;
        /**
         * * Construct a particular square
         * @param //piece A character representing the type of square to be created.
         */


        Piece(String piece) {
            this.piece = piece;
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


//        public void setOrientation(String orientation) {
//
//        }


            setImage(new Image(Board.class.getResource(URI_BASE + piece.charAt(0) + toFetch + ".png").toString()));
        //setImage(new Image(Board.class.getResource(URI_BASE + piece.charAt(0) + toFetch + ".png").toString(), 150, 150, false, false));
        //image.setRotate(90*spinAmount);
        }


    }

    class DraggablePiece extends Piece {
        int homeX, homeY;
        double mouseX, mouseY;
        boolean flipped = false;
        ArrayList<Piece> piecess = new ArrayList<>();  // ArrayList that will consist of pieces

        DraggablePiece(String piece) {
            super(piece);
            setFocusTraversable(true);
            requestFocus();
            //piecestate[piece.charAt(0) - 65] = NOT_PLACED;
            switch (piece.charAt(0)) {
                case 'A':
                    homeX = 0;
                    homeY = 300;
                    break;
                case 'B':
                    homeX = 70;
                    homeY = 300;
                    break;
                case 'C':
                    homeX = 90;
                    homeY = 300;
                    break;
                case 'D':
                    homeX = 110;
                    homeY = 300;
                    break;
                case 'E':
                    homeX = 50;
                    homeY = 400;
                    break;
                case 'F':
                    homeX = 70;
                    homeY = 400;
                    break;
                case 'G':
                    homeX = 90;
                    homeY = 400;
                    break;
                case 'H':
                    homeX = 110;
                    homeY = 400;
                    break;
            }
            setLayoutX(homeX);
            setLayoutY(homeY);
            setOnScroll(new EventHandler<ScrollEvent>() {
                @Override
                public void handle(ScrollEvent event) {
                    setRotate((getRotate() + 90) % 360);
                    event.consume();
                }
            });


            setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode().equals(KeyCode.SPACE)) {
                        //setImage(new Image(Board.class.getResource(URI_BASE+ flip(piece)+".png").toString()));
                        System.out.println("Is working");
                        if (!flipped) {

                            flipped = true;

                            setImage(new Image(Board.class.getResource(URI_BASE + piece.charAt(0) + "E.png").toString()));

                        }
                        else  {
                            flipped = false;
                            setImage(new Image(Board.class.getResource(URI_BASE + piece.charAt(0) + "A.png").toString()));
                        }
                    }
                }
            });
            /*HELPED BY STEVE*/
            /*Remember to tell the game when it is flipped and when it isn't. Currently isn't implemented*/

            setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
                    });
            setOnMouseDragged(event -> {
                toFront();
                double movementX = event.getSceneX() - mouseX;
                double movementY = event.getSceneY() - mouseY;
                setLayoutX(getLayoutX() + movementX);
                setLayoutY(getLayoutY() + movementY);
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                event.consume();
            });
            setOnMouseReleased(event -> {
                snapToGrid();
            });

        }

        private void snapToGrid() {
            if (onBoard()) {
//                setLayoutX((BOARD_WIDTH/2) + (((getLayoutX() + (1.5*SQUARE_SIZE))> BOARD_WIDTH/2 ? 0 : -3) * SQUARE_SIZE));
//                setLayoutY((BOARD_HEIGHT/2) + ((getLayoutY() + (1.5*SQUARE_SIZE) > BOARD_HEIGHT/2 ? 0 : -3) * SQUARE_SIZE ));
//                setPosition();
                //setImage(new Image(Board.class.getResource(URI_BASE + piece + ".png").toString()));       // setting image back to correct dimensions once on board
            } else {
                snapToHome();
            }
            //makeExposed();
        }
//
//
        private boolean onBoard(){
            return (getLayoutX() >= 139 && getLayoutX() <= 769) && (getLayoutY() >=  83 && getLayoutY() <= 363);
        }
//        private void setPosition() {
//            int x = (int) (getLayoutX() - BOARD_X) / LARGE_SQUARE_SIZE;
//            int y = (int) (getLayoutY() - BOARD_Y) / LARGE_SQUARE_SIZE;
//            int rotate = (int) getRotate() / 90;
//            char val = (char) ('A' + (4 * (x + (2*y)) + rotate));
//            piecestate[piece] = val+"";
//        }


        //setOnHover or setOnaction

        // Snaps to home
        private void snapToHome() {
            DraggablePiece p = new DraggablePiece(piece);
//            p.setFitWidth(120);
//            p.setFitHeight(120);
            if (piece.charAt(0) == 'A') {
                p.setLayoutX(121);
                p.setLayoutY(406);
                root.getChildren().add(p);
            }
            else if (piece.charAt(0) == 'B'){
                p.setLayoutX(279);
                p.setLayoutY(406);
                root.getChildren().add(p);
            }
        }
    }

    public void makePegs() {
        int x = 139;
        int y = 83;
        int xb = 209;
        int xc = 139;
        int xd = 209;
        int xe = 139;
        for (int i = 0; i < 50; i++) {
            Circle peg = new Circle(CIRCLE_SIZE);
            peg.setOpacity(0.3);
            pegs.getChildren().add(peg);
            if (i<=9 && i%2 == 0) {
                peg.setCenterX(x);
                peg.setCenterY(y);
                x += 140;
            }
            else if (i > 9 && i <= 19 && i%2==1) {
                peg.setCenterX(xb);
                peg.setCenterY(y+70);
                xb += 140;
            }
            else if (i > 19 && i <= 29 && i%2 == 0) {
                peg.setCenterX(xc);
                peg.setCenterY(y+140);
                xc += 140;
            }
            else if (i > 29 && i <= 39 && i%2==1) {
                peg.setCenterX(xd);
                peg.setCenterY(y+210);
                xd += 140;
            }
            else if (i > 39 && i <= 49 && i%2==0) {
                peg.setCenterX(xe);
                peg.setCenterY(y+280);
                xe += 140;
            }
        }
    }

//    private void makePegs() {
//        //GridPane gridPane = new GridPane();
//
//        BOARD.setPrefSize(BOARD_WIDTH, BOARD_HEIGHT); // Default width and height
//        //gridPane.setGridLinesVisible(true);
//        BOARD.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
//        //set padding to center gridpane
//
//        BOARD.setPadding(new Insets(15, 0, 0, 25));
//        BOARD.setAlignment(Pos.TOP_CENTER);     // Centers the board
//
//        //these loops will make a row / column at every iteration for the grid,
//        // since the game is 10 x 5, 10 columns and 5 rows will be made
//        for (int k = 0; k < 10; k ++) {
//            ColumnConstraints column = new ColumnConstraints(70);
//            BOARD.getColumnConstraints().add(column);
//        }
//        for (int l = 0; l < 5; l ++) {
//            RowConstraints row = new RowConstraints(70);
//            BOARD.getRowConstraints().add(row);
//        }
//
//        //decide our peg positions
//        for (int y = 0; y<10; y++) {
//            for (int z = 0; z<5; z++) {
//                if (y % 2 == 0 && z % 2 == 0 ) {
//                    Circle x = new Circle(CIRCLE_SIZE);
//                    x.setOpacity(0.3);
//                    BOARD.add(x, y, z);
//                } else if (y % 2 == 1 && z % 2 == 1) {
//                    Circle x = new Circle(CIRCLE_SIZE);
//                    x.setOpacity(0.3);
//                    BOARD.add(x, y, z);
//                }
//            }
//        }
//
//        int out = 0;
//        for (int y = 0; y<5; y++) {
//            for (int z = 0; z<10; z++) {
//                BOARD.add(new Text(out + ""), z, y);
//                out++;
//                }
//        }
//
//        //fix peg positions as they were underpadded on the left
//        //set up the pegs properly
//        for (Node node : BOARD.getChildren()) {
//            BOARD.setMargin(node, new Insets(0, 0, 0, 15));
//        }
//        //add all circles (pegs) to parent group
//
//        pegs.getChildren().addAll(BOARD);
//    }

    // Authorship: Khamis Buol u6080028
    private void makePieces() {
        String[] pieces = {"AA", "BA", "CA", "DA", "EA", "FA", "GA", "HA"};
        int x = 121;
        int xb = 121;
        int y = 406;
        int yb = 536;
        for (int i = 0; i < pieces.length; i++) {
            if (i/4 < 1) {
                DraggablePiece piece = new DraggablePiece(pieces[i]);
                piece.setFitHeight(120);
                piece.setFitWidth(120);
                piece.setLayoutX(x);
                piece.setLayoutY(y);
                x += 158;
                root.getChildren().add(piece);
            }
            else {
                DraggablePiece piece = new DraggablePiece(pieces[i]);
                piece.setFitHeight(120);
                piece.setFitWidth(120);
                piece.setLayoutX(xb);
                piece.setLayoutY(yb);
                xb += 158;
                root.getChildren().add(piece);
            }
        }
    }

    // FIXME Task 7: Implement a basic playable Steps Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement starting placements

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting starting placements

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("IQ Steps");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        root.getChildren().addAll(pegs, placements);
        makePegs();
        makePieces();
        //makeDraggableImages();
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public String flip(String piece) {
        String p = "";
        if (piece.charAt(1) == 'A') {
            p+=piece.charAt(0)+'E';
            return p;
        }
        else {
            p += piece.charAt(0) + 'A';
            return p;
        }
    }

}
