package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
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

    private ArrayList<Circle> pegs = new ArrayList<>();

    public Circle findNearestPeg(double mouseX, double mouseY) {
        ArrayList<Double> distances = new ArrayList<>();
        for (Circle peg : pegs) {
            distances.add(peg.distance(mouseX,mouseY));
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
            setCenterX(centerX);
            setCenterY(centerY);
            Label pos = new Label(Character.toString(position));
            pos.setLayoutX(centerX);
            pos.setLayoutY(centerY);
            root.getChildren().add(pos);
        }

        public double distance(double mousex, double mousey){
            double xDistance = getCenterX() - mousex;
            double yDistance = getCenterY() - mousey;
            return Math.sqrt((Math.pow(xDistance, 2) + Math.pow(yDistance, 2)));
        }
    }

    /*Inner class to display the shapes*/
    class Piece extends ImageView {
        private String piece;

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
    }

    class DraggablePiece extends Piece {
        int homeX, homeY;
        double mouseX, mouseY;
        boolean flipped = false;

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


            setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode().equals(KeyCode.SPACE)) {
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
                Circle near = findNearestPeg(getLayoutX() ,getLayoutY());
                System.out.println(getLayoutX() + "" + getLayoutY());
                setLayoutX(near.getCenterX());
                setLayoutY(near.getCenterY());
            });
        }
    }

    public void makePegs() {
        int x = 139;
        int y = 83;
        int xb = 209;
        int xc = 139;
        int xd = 209;
        int xe = 139;
        int yb = 153;
        int yc = 223;
        int yd = 293;
        int ye = 363;
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
        System.out.println(pegs);
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
        makePieces();
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
