package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
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

public class Board extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int SQUARE_SIZE = 60;
    private static final int PIECE_IMAGE_SIZE = (int) ((3*SQUARE_SIZE)*1.33);

    private static final String URI_BASE = "assets/";
    private static GridPane BOARD = new GridPane();
    final GridPane target = BOARD;

    //Create needed groups for different elements
    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group placements = new Group();
    private final Group pegs = new Group();
    TextField textField;
    /* the state of the pieces */
    char[] piecestate = new char[8];
    static final char NOT_PLACED = ' ';



    /*Inner class to display the shapes*/
    class Piece extends ImageView {

    String piece;
    Image image;
        /**
         * * Construct a particular square
         * @param //piece A character representing the type of square to be created.
         */

        Piece(){
        this.piece = piece;
        this.image = image;
        }

        Piece(String piece) {
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
        setImage(new Image(Board.class.getResource(URI_BASE + piece.charAt(0) + toFetch + ".png").toString()));
        //image.setRotate(90*spinAmount);
        }

    }

    class DraggablePiece extends Piece {
        int homeX, homeY;
        double mouseX, mouseY;

        DraggablePiece(String piece) {
            super(piece);
            setFocusTraversable(true);
            requestFocus();
            piecestate[piece.charAt(0) - 'A'] = NOT_PLACED;
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
                    System.out.println("event consumed");
                }
            });
            setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode().equals(KeyCode.SPACE)) {
                        if (piece.charAt(1) >= 'A' && piece.charAt(1) <= 'D') {
                            setImage(new Image(Board.class.getResource(URI_BASE + piece.charAt(0) + "E.png").toString()));
                            String x = "";
                            x += piece.charAt(0);
                            x += 'E';
                            piece = x;
                            }
                        else if (piece.charAt(1) >= 'E' && piece.charAt(1) <= 'H'){
                            setImage(new Image(Board.class.getResource(URI_BASE + piece.charAt(0) + "A.png").toString()));
                            }
                    }
                }
                });
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
                //snapToGrid();
            });
            /*setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Dragboard db = startDragAndDrop(TransferMode.ANY);
                    ClipboardContent cbContent = new ClipboardContent();
                    cbContent.putImage(image);
                    db.setContent(cbContent);
                    setVisible(false);
                    System.out.println("Drag detected");
                    event.consume();
                }
            });
            setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    if (event.getGestureSource() != target && event.getDragboard().hasImage()){
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    System.out.println("Drag over");
                    event.consume();
                }
            });
            setOnDragEntered(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    if(event.getGestureSource() != target && event.getDragboard().hasImage()){
                        setVisible(false);
                        target.setOpacity(0.7);

                    }
                    System.out.println("Drag entered");
                    event.consume();
                }
            });
            setOnDragExited(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    setVisible(true);
                    target.setOpacity(1);
                    System.out.println("Drag exited");

                    event.consume();
                }
            });
            setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    Node node = event.getPickResult().getIntersectedNode();
                    if(node != target && db.hasImage()){

                        Integer cIndex = BOARD.getColumnIndex(node);
                        Integer rIndex = BOARD.getRowIndex(node);
                        int x = cIndex == null ? 0 : cIndex;
                        int y = rIndex == null ? 0 : rIndex;
                        //target.setText(db.getImage()); --- must be changed to target.add(source, col, row)
                        //target.add(source, 5, 5, 1, 1);
                        //Places at 0,0 - will need to take coordinates once that is implemented
                        ImageView image = new ImageView(db.getImage());

                        // TODO: set image size; use correct column/row span
                        //BOARD.add(image, x, y, 1, 1);
                        success = true;
                    }
                    //let the source know whether the image was successfully transferred and used
                    event.setDropCompleted(success);
                    System.out.println("Drag dropped");
                    event.consume();

                }
            }); */


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

        int out = 0;
        for (int y = 0; y<5; y++) {
            for (int z = 0; z<10; z++) {
                BOARD.add(new Text(out + ""), z, y);
                out++;
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

    private void makeDraggableImages() {
        Piece AA = new Piece("AA");
        System.out.println(AA);
    }
    // FIXME Task 7: Implement a basic playable Steps Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement starting placements

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting starting placements

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("IQ Steps");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        //root.getChildren().add(new DraggablePiece("AA"));
        //DraggablePiece piece = new DraggablePiece("AAL");
        //placements.getChildren().add(piece);
        root.getChildren().addAll(pegs, placements);
        //pegs.relocate(100, 20);
        makePegs();
        root.getChildren().add(new DraggablePiece("AA"));
        //makeDraggableImages();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
