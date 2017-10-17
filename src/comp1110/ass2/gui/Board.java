package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.Collections;

public class Board extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final double CIRCLE_SIZE = 22.5;

    // Need to somehow link the positions with each node.
    private static int[] pos = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                               10,11,12,13,14,15,16,17,18,19,
                               20,21,22,23,24,25,26,27,28,29,
                               30,31,32,33,34,35,36,37,38,39,
                               40,41,42,43,44,45,46,47,48,49};

    private static final String URI_BASE = "assets/";

    //Create needed groups for different elements
    private final Group root = new Group();
    private final Group placements = new Group();
    boolean flipped = false;

    private ArrayList<Circle> pegs = new ArrayList<>();

    public Circle findNearestPeg(double x, double y) {
        ArrayList<Double> distances = new ArrayList<>();
        for (Circle peg : pegs) {
            distances.add(peg.distance(x,y));
        }
        double smallestDist = Collections.min(distances);
        int index = distances.indexOf(smallestDist);
        Circle nearestCircle = pegs.get(index);
        return nearestCircle;

    }


    class Circle extends javafx.scene.shape.Circle {

        private Character position;


        public Circle(double radius) {
            super(radius);
        }

        public Circle(Character position, double radius, double opacity, double centerX, double centerY) {
            super(radius);
            this.position = position;
            setOpacity(opacity);
            setCenterX(centerX+70);
            setCenterY(centerY+70);
            Label pos = new Label(Character.toString(position));
            pos.setLayoutX(centerX+70);
            pos.setLayoutY(centerY+70);
            root.getChildren().add(pos);
        }

        public double distance(double x, double y){
            double xDistance = getCenterX() - (x+70);
            System.out.println(xDistance);
            double yDistance = getCenterY() - (y+70);
            System.out.println(yDistance);
            return Math.sqrt((Math.pow(Math.abs(xDistance), 2) + Math.pow(Math.abs(yDistance), 2)));
        }

    }

    /*Inner class to display the shapes*/
    class Piece extends ImageView {
        public String piece;

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
        setImage(new Image(Board.class.getResource(URI_BASE + piece.charAt(0) + toFetch + ".png").toString()));
        }
        public void flip() {
            if (piece.charAt(1) == 'A') {
                piece = piece.charAt(0)+"E";
            }
            else {
                piece = piece.charAt(0) + "A";
            }
        }
    }

    class DraggablePiece extends Piece {
        int homeX, homeY;
        double mouseX, mouseY;


        DraggablePiece(String piece, double x, double y) {
            super(piece);
            setFocusTraversable(true);
            requestFocus();
            setLayoutX(x);
            setLayoutY(y);
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


//            setOnKeyPressed(new EventHandler<KeyEvent>() {
//                @Override
//                public void handle(KeyEvent event) {
//                    if (event.getCode().equals(KeyCode.SPACE)) {
//
//                        //setImage(new Image(Board.class.getResource(URI_BASE + piece.flip.toString())));
//
//                        System.out.println("Is working");
//                        if (!flipped) {
//                            flipped = true;
//                            setImage(new Image(Board.class.getResource(URI_BASE + piece.charAt(0) + "E.png").toString()));
//
//                        }
//                        else  {
//                            flipped = false;
//                            setImage(new Image(Board.class.getResource(URI_BASE + piece.charAt(0) + "A.png").toString()));
//                        }
//                    }
//                }
//            });
            //setOnMouseClicked();

            setOnMouseClicked(event -> {
                MouseButton button = event.getButton();
                if (button == MouseButton.SECONDARY) {

                    if (!flipped) {
                        flipped = true;
                        setImage(new Image(Board.class.getResource(URI_BASE + piece.charAt(0) + "E.png").toString()));

                    } else {
                        flipped = false;
                        setImage(new Image(Board.class.getResource(URI_BASE + piece.charAt(0) + "A.png").toString()));
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
                Circle near = findNearestPeg(getLayoutX(), getLayoutY());
                System.out.println("Nearest peg: " + near.position);
                System.out.println("Mouse x and y : x : "+event.getSceneX() + " y :" + event.getSceneY());
                System.out.println("Layout x and y of piece before placing: x : "+getLayoutX() + " y :" + getLayoutY());
                setLayoutX(near.getCenterX()-70);
                setLayoutY(near.getCenterY()-70);
                System.out.println("Layout x and y of nearest peg: x : "+ near.getCenterX() + " y :" + near.getCenterY());
                System.out.println("Layout x and y of piece after placing: x : " +getLayoutX() + " y :" + getLayoutY());
            });
        }
    }

    public void makePegs() {
        int x = 69;
        int y = 13;
        int xb = 139;
        int xc = 69;
        int xd = 139;
        int xe = 69;
        int yb = 83;
        int yc = 153;
        int yd = 223;
        int ye = 293;
        char pos = 'A';
        for (int i = 0; i < 50; i++) {
            if (i<=9 && i%2 == 0) {
                Circle peg = new Circle(pos, CIRCLE_SIZE, 0.3, x, y);
                x += 140;
                root.getChildren().add(peg);
                pegs.add(peg);
            }
            else if (i > 9 && i <= 19 && i%2==1) {
                Circle peg = new Circle(pos, CIRCLE_SIZE, 0.3, xb, yb);
                xb += 140;
                root.getChildren().add(peg);
                pegs.add(peg);
            }
            else if (i > 19 && i <= 29 && i%2 == 0) {
                Circle peg = new Circle(pos, CIRCLE_SIZE, 0.3, xc, yc);
                xc += 140;
                root.getChildren().add(peg);
                pegs.add(peg);
            }
            else if (i > 29 && i <= 39 && i%2==1) {
                Circle peg = new Circle(pos, CIRCLE_SIZE, 0.3, xd, yd);
                xd += 140;
                root.getChildren().add(peg);
                pegs.add(peg);
            }
            else if (i > 39 && i <= 49 && i%2==0) {
                Circle peg = new Circle(pos, CIRCLE_SIZE, 0.3, xe, ye);
                xe += 140;
                root.getChildren().add(peg);
                pegs.add(peg);
            }
            pos += 1;
            if (pos >= 90 && pos <= 96){
                pos = 97;
            }
        }
    }

    // Authorship: Khamis Buol u6080028
    private void makePieces() {
        String[] pieces = {"AA", "BA", "CA", "DA", "EA", "FA", "GA", "HA"};
        int x = 121;
        int xb = 121;
        int y = 406;
        int yb = 536;
        for (int i = 0; i < pieces.length; i++) {
            if (i/4 < 1) {
                DraggablePiece piece = new DraggablePiece(pieces[i], x , y);
                x += 158;
                root.getChildren().add(piece);
            }
            else {
                DraggablePiece piece = new DraggablePiece(pieces[i], xb, yb);
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
        root.getChildren().addAll(placements);
        makePegs();
        System.out.println(pegs);
        makePieces();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
