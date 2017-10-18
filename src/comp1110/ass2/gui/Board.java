package comp1110.ass2.gui;

import comp1110.ass2.StepsGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final double CIRCLE_SIZE = 22.5;
    private static final String URI_BASE = "assets/";

    //Create needed groups for different elements
    private final Group root = new Group();
    private final Group placements = new Group();

    //List to hold pegs
    private ArrayList<Circle> pegs = new ArrayList<>();

    private ArrayList<String> pieces = new ArrayList<>();

    //Class to define pieceName since inner class instance variables for DraggablePiece cannot be changed
    class PieceName {
        String pieceName;

        public PieceName(String pieceName) {
            this.pieceName = pieceName;
        }

        void rotPiece() {
            switch (pieceName.charAt(1)) {
                case 'A':
                    pieceName = pieceName.charAt(0) + "B";
                    break;
                case 'B':
                    pieceName = pieceName.charAt(0) + "C";
                    break;
                case 'C':
                    pieceName = pieceName.charAt(0) + "D";
                    break;
                case 'D':
                    pieceName = pieceName.charAt(0) + "A";
                    break;
                case 'E':
                    pieceName = pieceName.charAt(0) + "F";
                    break;
                case 'F':
                    pieceName = pieceName.charAt(0) + "G";
                    break;
                case 'G':
                    pieceName = pieceName.charAt(0) + "H";
                    break;
                case 'H':
                    pieceName = pieceName.charAt(0) + "E";
                    break;
                    }
            }

            void flipPiece() {
                switch (pieceName.charAt(1)) {
                    case 'A':
                        pieceName = pieceName.charAt(0) + "E";
                        break;
                    case 'B':
                        pieceName = pieceName.charAt(0) + "F";
                        break;
                    case 'C':
                        pieceName = pieceName.charAt(0) + "G";
                        break;
                    case 'D':
                        pieceName = pieceName.charAt(0) + "H";
                        break;
                    case 'E':
                        pieceName = pieceName.charAt(0) + "A";
                        break;
                    case 'F':
                        pieceName = pieceName.charAt(0) + "B";
                        break;
                    case 'G':
                        pieceName = pieceName.charAt(0) + "C";
                        break;
                    case 'H':
                        pieceName = pieceName.charAt(0) + "D";
                        break;

                }
            }

        }


    public Circle findNearestPeg(double x, double y, int mod) {
        ArrayList<Double> distances = new ArrayList<>();
        for (Circle peg : pegs) {
            distances.add(peg.distance((x+mod),y));
        }
        double smallestDist = Collections.min(distances);
        System.out.println(distances);
        System.out.println(smallestDist);
        int index = distances.indexOf(smallestDist);
        Circle nearestCircle = pegs.get(index);
        System.out.println(nearestCircle.position);
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

        //x is +140 if flipped
            double xDistance = getCenterX() - (x+140);
            //System.out.println(xDistance);
            double yDistance = getCenterY() - (y+140);
            //System.out.println(yDistance);
            return Math.sqrt((Math.pow(Math.abs(xDistance), 2) + Math.pow(Math.abs(yDistance), 2)));
        }

    }

    /*Inner class to display the shapes*/
    class Piece extends ImageView {

        Piece(String piece) {

        }
    }

    class DraggablePiece extends ImageView {
        int homeX, homeY;
        double mouseX, mouseY;
        int mod1;
        int mod2;
        boolean flipped;
        PieceName piece;
        boolean placed;


        DraggablePiece(PieceName piece, double x, double y) {
            this.piece = piece;
            String toFetch = "";
            Character toCompare = piece.pieceName.charAt(1);
            if (piece.pieceName.charAt(0) >= 'A' && piece.pieceName.charAt(0) <= 'H') {
                if (toCompare >= 'A' && toCompare < 'E') {
                    toFetch = "A";
                } else if (toCompare >= 'E' && toCompare <= 'H'){
                    toFetch = "E";
                }
            }
            setImage(new Image(Board.class.getResource(URI_BASE + piece.pieceName.charAt(0) + toFetch + ".png").toString()));
            setFocusTraversable(true);
            switch (piece.pieceName.charAt(0)) {
                case 'A':
                    homeX = 111;
                    homeY = 386;
                    break;
                case 'B':
                    homeX = 269;
                    homeY = 386;
                    break;
                case 'C':
                    homeX = 457;
                    homeY = 386;
                    break;
                case 'D':
                    homeX = 647;
                    homeY = 386;
                    break;
                case 'E':
                    homeX = 111;
                    homeY = 536;
                    break;
                case 'F':
                    homeX = 269;
                    homeY = 536;
                    break;
                case 'G':
                    homeX = 457;
                    homeY = 536;
                    break;
                case 'H':
                    homeX = 647;
                    homeY = 536;
                    break;
            }
            setLayoutX(homeX);
            setLayoutY(homeY);
            setOnScroll(new EventHandler<ScrollEvent>() {
                @Override
                public void handle(ScrollEvent event) {
                    if (placed) {
                        String holder = piece.pieceName;
                        String holder2;
                        int index = 0;
                        Character current = ' ';
                        //piece.rotPiece();
                        //System.out.println("pieces : " + pieces);
                        //int index = pieces.indexOf()
                        //pieces = changePieceArray(piece.pieceName, pieces);
                        //System.out.println("pieces : " + pieces);
                        //take the piece from pieces and update it
                        System.out.println("pieces before: " + pieces);
                        //piece.rotPiece();

                        for (String s: pieces) {
                            if (s.contains(piece.pieceName)) {
                                System.out.println("s :" + s);
                                holder2 = s;
                                index = pieces.indexOf(s);
                                current = s.charAt(2);
                                String currentPeg = s.charAt(2) + "";
                                piece.rotPiece();
                                pieces.set(pieces.indexOf(s), piece.pieceName + currentPeg);
                            }
                        }

                        String placement = StepsGame.join(Arrays.asList(pieces.toString())).replaceAll("[\\s\\,\\[\\]]","");

                        //System.out.println("place" + placement);
                        if (StepsGame.isPlacementSequenceValid(placement)) {
                            setRotate((getRotate() + 90) % 360);
                        } else {
                            piece.pieceName = holder;
                            System.out.println(holder);
                            pieces.set(index, piece.pieceName + current);
                            System.out.println("pieces after: " + pieces);
                        }
                    } else {
                        piece.rotPiece();
                        setRotate((getRotate() + 90) % 360);

                    }
                    //System.out.println(piece.pieceName);
                    event.consume();
                }
            });


            setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode().equals(KeyCode.SPACE)) {
                        if (!flipped) {
                            placed = false;
                            changePieceArray(piece.pieceName, pieces);
                            System.out.println(pieces);
                            flipped = true;
                            mod1 = -70;
                            mod2 = 70;
                            piece.flipPiece();
                            setImage(new Image(Board.class.getResource(URI_BASE + piece.pieceName.charAt(0) + "E.png").toString()));
                            //System.out.println(piece.pieceName);

                        }
                        else  {
                            placed = false;
                            flipped = false;
                            mod1 = 0;
                            mod2 = 0;
                            piece.flipPiece();
                            setImage(new Image(Board.class.getResource(URI_BASE + piece.pieceName.charAt(0) + "A.png").toString()));
                            //System.out.println(piece.pieceName);
                            }
                        }
                }
            });
            /*HELPED BY STEVE*/

            setOnMousePressed(event -> {
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                setFitHeight(280);
                setFitWidth(280);
                placed=false;
                requestFocus();
            });
            setOnMouseDragged(event -> {
                toFront();
                double movementX = event.getSceneX() - mouseX;
                double movementY = event.getSceneY() - mouseY;
                setLayoutX(getLayoutX() + movementX);
                setLayoutY(getLayoutY() + movementY);
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
            });
            setOnMouseReleased(event -> {
                System.out.println(pieces);
                if (!placed) {
                    pieces = changePieceArray(piece.pieceName, pieces);
                Circle near = findNearestPeg(getLayoutX(), getLayoutY(), mod2);

                Character pos = near.position;
                String fullpiece;

                if (flipped) {
                    fullpiece = piece.pieceName + StepsGame.all.charAt((StepsGame.all.indexOf(pos))-1);
                } else {
                    fullpiece = piece.pieceName + pos; }
                    System.out.println("adding to pieces");
                pieces.add(fullpiece);

                String placement = StepsGame.join(Arrays.asList(pieces.toString())).replaceAll("[\\s\\,\\[\\]]","");

                //System.out.println("place" + placement);
                if (StepsGame.isPlacementSequenceValid(placement)) {
                //System.out.println("Nearest peg: " + near.position);
                //System.out.println("Mouse x and y : x : "+event.getSceneX() + " y :" + event.getSceneY());
                //System.out.println("Layout x and y of piece before placing: x : "+getLayoutX() + " y :" + getLayoutY());
                //setlayoutx is -140 if flipped
                    setLayoutX(near.getCenterX()-140+mod1);
                    setLayoutY(near.getCenterY()-140);
                    placed=true;
                } else {
                    pieces.remove(fullpiece);
                    placed = false;
                    setFitHeight(150);
                    setFitWidth(150);
                    setLayoutX(homeX);
                    setLayoutY(homeY);
                }
                }
                System.out.println(pieces);
                //System.out.println("Layout x and y of nearest peg: x : "+ near.getCenterX() + " y :" + near.getCenterY());
                //System.out.println("Layout x and y of piece after placing: x : " +getLayoutX() + " y :" + getLayoutY());
            });
        }
    }

    public ArrayList<String> changePieceArray(String pieceName, ArrayList<String> pieces) {
        String pieceToRemove = "";
        ArrayList<String> out = new ArrayList<>();
        for (String item : pieces) {
            Character itemPiece = item.charAt(0);
            if (itemPiece.equals(pieceName.charAt(0))) {
                pieceToRemove = item;
            }
        }
        pieces.remove(pieceToRemove);
        //System.out.println("remove piece:" + pieceToRemove);
        return pieces;
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
                DraggablePiece piece = new DraggablePiece(new PieceName(pieces[i]), x , y);
                x += 158;
                piece.setFitHeight(150);
                piece.setFitWidth(150);
                root.getChildren().add(piece);
                //System.out.println(piece.piece.pieceName);
            } else {
                DraggablePiece piece = new DraggablePiece(new PieceName(pieces[i]), xb, yb);
                xb += 158;
                piece.setFitHeight(150);
                piece.setFitWidth(150);
                root.getChildren().add(piece);
                //System.out.println(piece.piece.pieceName);
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
        //System.out.println(pegs);
        makePieces();
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
