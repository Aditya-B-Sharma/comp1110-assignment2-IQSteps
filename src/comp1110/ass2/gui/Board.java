package comp1110.ass2.gui;


import comp1110.ass2.StepsGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Board extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final double CIRCLE_SIZE = 22.5;
    private static final String URI_BASE = "assets/";

    //Create needed groups for different elements
    private final Group root = new Group();
    private final Group placements = new Group();
    private final Group startScreen = new Group();

    /* Music for when game is finished */
    private final URL resource = getClass().getResource(URI_BASE+"congratulations.wav");
    private final Media media = new Media(resource.toString());
    private final MediaPlayer mediaPlayer = new MediaPlayer(media);

    //List to hold pegs
    private ArrayList<Circle> pegs = new ArrayList<>();

    private ArrayList<String> pieces = new ArrayList<>();
    Label isComplete = new Label("CONGRATULATIONS!!!!");

    Label current = new Label();

    ArrayList<String> startPieces = new ArrayList<>();


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

    void glowPiece(PieceName piece) {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.YELLOW);
        glow.setOffsetX(0f);
        glow.setOffsetY(0f);
        glow.setHeight(10);

    }
    public void glowPeg(Circle peg, Boolean toGlow) {
        peg.getCenterX();
        peg.getCenterY();
        peg.getEffect();
        DropShadow glow = new DropShadow();
        if (!toGlow) {
            glow.setColor(Color.GREEN);
            glow.setBlurType(BlurType.GAUSSIAN);
            glow.setSpread(20);
            glow.setOffsetX(0f);
            glow.setOffsetY(0f);
            glow.setHeight(15);
            peg.setEffect(glow);
        } else {
            peg.setEffect(null);
//            glow.setColor(Color.rgb(255,255,255));
//            glow.setOffsetX(0f);
//            glow.setOffsetY(0f);
//            glow.setHeight(10);
//            peg.setEffect(glow);

        }



    }



    public Circle findNearestPeg(double x, double y, int mod) {
        ArrayList<Double> distances = new ArrayList<>();
        for (Circle peg : pegs) {
            distances.add(peg.distance((x+mod),y));
        }
        double smallestDist = Collections.min(distances);
//        System.out.println(distances);
//        System.out.println(smallestDist);
        int index = distances.indexOf(smallestDist);
        Circle nearestCircle = pegs.get(index);
//        System.out.println(nearestCircle.position);
        return nearestCircle;

    }


    class Circle extends javafx.scene.shape.Circle {

        private Character position;


        public Circle(double radius) {
            super(radius);
        }

        // Need to add case for if shape is flipped. It keeps thinking the node is the one on the right
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
        Character pos;
        boolean StartingPiece;


        DraggablePiece(PieceName piece, double x, double y) {
        DraggablePiece self = this;
            this.piece = piece;
            root.getChildren().remove(current);
            current =  new Label(Boolean.toString(placed));
            root.getChildren().add(current);

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
                    scrollPiece(self);
                    //System.out.println(piece.pieceName);
                    event.consume();
                }
            });


            setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    DropShadow glow = new DropShadow();
                    glow.setColor(Color.LIGHTBLUE);
                    glow.setOffsetX(0f);
                    glow.setOffsetY(0f);
                    glow.setHeight(10);
                    if (event.getCode().equals(KeyCode.SPACE)) {
                        flipPiece(self);
                        }
                        // Key event to give hints
                        else if (event.getCode().equals(KeyCode.H)) {
                        Circle near = findNearestPeg(getLayoutX(), getLayoutY(), mod2);
                        //Circle glowingNear = near;
                        //glowPeg(glowingNear);

//                                    root.getChildren().set(root.getChildren().indexOf(near), glowingNear);
//                                    new KeyFrame(Duration.seconds(1));
//                                    root.getChildren().set(root.getChildren().indexOf(glowingNear), near);final Timeline timeline = new Timeline();
                        final Timeline timeline = new Timeline();
                        timeline.setCycleCount(2);
                        timeline.setAutoReverse(true);
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), ae -> {
                            glowPeg(near, true);

                        }));
                        timeline.play();
                        glowPeg(near, false);
                        //timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new KeyValue (near.translateYProperty(),  5)));
                        root.getChildren().set(root.getChildren().indexOf(near), near);



                        //setImage(new Image(Board.class.getResource(URI_BASE + piece.pieceName + ".png").toString()));
                        //root.setEffect(glow);
                        // HIGHLIGHT SPECIFIC PIECE AND SET TO PROPER ROTATION...THEN FLASH THE CENTER NODE IT NEEDS TO GO ONTO
                    }
                    }
            });
            setOnMousePressed(event -> {
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                setFitHeight(280);
                setFitWidth(280);
                placed=false;
                root.getChildren().remove(current);
                current =  new Label(Boolean.toString(placed));
                root.getChildren().add(current);
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
                placePiece(self);
//                System.out.println(pieces);

                //System.out.println("Layout x and y of nearest peg: x : "+ near.getCenterX() + " y :" + near.getCenterY());
                //System.out.println("Layout x and y of piece after placing: x : " +getLayoutX() + " y :" + getLayoutY());
            });
        }
    }

    public void scrollPiece(DraggablePiece p){
        if (p.placed) {
            root.getChildren().remove(current);
            current =  new Label(Boolean.toString(p.placed));
            root.getChildren().add(current);
            String holder = p.piece.pieceName;
            String holder2;
            int index = 0;
            Character current = ' ';
            //piece.rotPiece();
            //System.out.println("pieces : " + pieces);
            //int index = pieces.indexOf()
            //pieces = changePieceArray(piece.pieceName, pieces);
            //System.out.println("pieces : " + pieces);
            //take the piece from pieces and update it
//            System.out.println("pieces before: " + pieces);
            //piece.rotPiece();

            for (String s: pieces) {
                if (s.contains(p.piece.pieceName)) {
//                    System.out.println("s :" + s);
                    holder2 = s;
                    index = pieces.indexOf(s);
                    current = s.charAt(2);
                    String currentPeg = s.charAt(2) + "";
                    p.piece.rotPiece();
                    pieces.set(pieces.indexOf(s), p.piece.pieceName + currentPeg);
                }
            }

            String placement = StepsGame.join(Arrays.asList(pieces.toString())).replaceAll("[\\s\\,\\[\\]]","");

            //System.out.println("place" + placement);
            if (StepsGame.isPlacementSequenceValid(placement)) {
                p.setRotate((p.getRotate() + 90) % 360);
            } else {
                p.piece.pieceName = holder;
//                System.out.println(holder);
                pieces.set(index, p.piece.pieceName + current);
//                System.out.println("pieces after: " + pieces);
            }
        } else {
            p.piece.rotPiece();
            p.setRotate((p.getRotate() + 90) % 360);

        }
        //System.out.println(piece.pieceName)

    }

    public void flipPiece(DraggablePiece p){
            if (!p.flipped) {
                p.placed = false;

                root.getChildren().remove(current);
                current =  new Label(Boolean.toString(p.placed));
                root.getChildren().add(current);

                changePieceArray(p.piece.pieceName, pieces);

//                System.out.println(pieces);

                p.flipped = true;
                p.mod1 = -70;
                p.mod2 = 70;
                p.piece.flipPiece();
                p.setImage(new Image(Board.class.getResource(URI_BASE + p.piece.pieceName.charAt(0) + "E.png").toString()));
//                            setFitHeight(150);
//                            setFitWidth(150);
//                            setLayoutX(homeX);
//                            setLayoutY(homeY);
                //System.out.println(piece.pieceName);

            }
            else  {
                p.placed = false;
                root.getChildren().remove(current);
                current =  new Label(Boolean.toString(p.placed));
                root.getChildren().add(current);
                p.flipped = false;
                p.mod1 = 0;
                p.mod2 = 0;
                p.piece.flipPiece();
                p.setImage(new Image(Board.class.getResource(URI_BASE + p.piece.pieceName.charAt(0) + "A.png").toString()));
//                            setFitHeight(150);
//                            setFitWidth(150);
//                            setLayoutX(homeX);
//                            setLayoutY(homeY);
                //System.out.println(piece.pieceName);
            }
        }

    public void placePiece(DraggablePiece p) {
        if (!p.placed) {
            pieces = changePieceArray(p.piece.pieceName, pieces);
            Circle near = findNearestPeg(p.getLayoutX(), p.getLayoutY(), p.mod2);
            //System.out.println("p layoutX" + p.getLayoutX() + "p layout y" + p.getLayoutY());
            //System.out.println("near:" + near.position);

            p.pos = near.position;
            String fullpiece;

            if (p.flipped) {
                fullpiece = p.piece.pieceName + StepsGame.all.charAt((StepsGame.all.indexOf(p.pos))-1);
            } else {
                fullpiece = p.piece.pieceName + p.pos;}
//            System.out.println("adding to pieces");
            pieces.add(fullpiece);

            String placement = StepsGame.join(Arrays.asList(pieces.toString())).replaceAll("[\\s\\,\\[\\]]","");

            //System.out.println("place" + placement);
            if (StepsGame.isPlacementSequenceValid(placement)) {
                //System.out.println("Nearest peg: " + near.position);
                //System.out.println("Mouse x and y : x : "+event.getSceneX() + " y :" + event.getSceneY());
                //System.out.println("Layout x and y of piece before placing: x : "+getLayoutX() + " y :" + getLayoutY());
                //setlayoutx is -140 if flipped
                p.setLayoutX(near.getCenterX()-140+p.mod1);
                p.setLayoutY(near.getCenterY()-140);
                p.placed=true;
                root.getChildren().remove(current);
                current =  new Label(Boolean.toString(p.placed));
                root.getChildren().add(current);
//                System.out.println(p.placed);
                if (pieces.size() == 8) {
                    isComplete.setLayoutY(150);
                    isComplete.setLayoutX((BOARD_WIDTH/2.25)-75);
                    isComplete.setScaleX(3);
                    isComplete.setScaleY(3);
                    mediaPlayer.play();

                    // set to image later
                    root.getChildren().add(isComplete);

                }
            } else {
                pieces.remove(fullpiece);
                p.placed = false;
                root.getChildren().remove(current);
                current =  new Label(Boolean.toString(p.placed));
                root.getChildren().add(current);
                p.setFitHeight(150);
                p.setFitWidth(150);
                p.setLayoutX(p.homeX);
                p.setLayoutY(p.homeY);
            }
        }
//        System.out.println(pieces);

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

    ArrayList<DraggablePiece> DraggablePieceList = new ArrayList<>();
    // Authorship: Khamis Buol u6080028
    private void makePieces() {
        String[] pieces = {"AA", "BA", "CA", "DA", "EA", "FA", "GA", "HA"};
        int x = 121;
        //int xb = 121;
        int y = 406;
        int yb = 536;
        for (int i = 0; i < pieces.length; i++) {
            if (i/4 < 1) {
                DraggablePiece piece = new DraggablePiece(new PieceName(pieces[i]), x , y);
                x += 158;
                piece.setFitHeight(150);
                piece.setFitWidth(150);
                DraggablePieceList.add(piece);
                //System.out.println(piece.piece.pieceName);
            } else {
                DraggablePiece piece = new DraggablePiece(new PieceName(pieces[i]), x, yb);
                x += 158;
                piece.setFitHeight(150);
                piece.setFitWidth(150);
                DraggablePieceList.add(piece);
            }
        }
//        System.out.println(DraggablePieceList);
    }

    private double returnX(Character pos, int mod2, boolean flipped) {
    if (flipped) {
        pos = (char) ((int) pos + 1);
    }
        for (Node node : root.getChildren()) {
            if (node.toString().contains("Circle")) {
                //System.out.println("circle peg" + (((Circle) node).position));
                if ((((Circle) node).position) == pos) {
                    System.out.println("pos " + (((Circle) node).position));
                    System.out.println("mod2 " + mod2);
                    System.out.println("given x " + (((Circle) node).getCenterX()-140-mod2));
                    return ((Circle) node).getCenterX()-140-mod2;
                }
            }
        }
        return 0.0;
    }

    private double returnY(Character pos, boolean flipped) {
        if (flipped) {
            pos = (char) ((int) pos + 1);
        }
        for (Node node : root.getChildren()) {
            if (node.toString().contains("Circle")) {
                if ((((Circle) node).position) == pos) {
                    return ((Circle) node).getCenterY()-140;
                }
            }
        }
        return 0.0;
    }

    public void setter(DraggablePiece p, String s) {
        //System.out.println(p.mod1);
        //flip piece
        if (s.charAt(1) >= 'A' && s.charAt(1) <= 'D') {
            flipPiece(p);
            flipPiece(p);
            System.out.println("piece name after flip:" + p.piece.pieceName);
            //p.mod1 = 0;
            //p.mod2 = 0;
        } else if (s.charAt(1) >= 'E' && s.charAt(1) <= 'H') {
            //p.flipped = false;
            flipPiece(p);
            flipPiece(p);
            flipPiece(p);
            System.out.println("piece name after flip:" + p.piece.pieceName);
        }
        //System.out.println(p.mod1);

        switch (s.charAt(1)) {
            case 'A':
                break;
            case 'B':
                scrollPiece(p);
                break;
            case 'C':
                scrollPiece(p);
                scrollPiece(p);
                break;
            case 'D':
                scrollPiece(p);
                scrollPiece(p);
                scrollPiece(p);
                break;
            case 'E':
                break;
            case 'F':
                scrollPiece(p);
                break;
            case 'G':
                scrollPiece(p);
                scrollPiece(p);
                break;
            case 'H':
                scrollPiece(p);
                scrollPiece(p);
                scrollPiece(p);
                break;
        }
        System.out.println("piece name after spin " + p.piece.pieceName);
        //rotate piece
        //place piece
        if (p.flipped) {
            //System.out.println("flipped mod" + p.mod1);
            p.setLayoutX(returnX(s.charAt(2), p.mod2, true));
            System.out.println("flipped p piece's current x layout " + p.getLayoutX());
            p.setLayoutY(returnY(s.charAt(2), true));
        } else {
            p.setLayoutX(returnX(s.charAt(2), p.mod2, false));
            System.out.println("2nd");
            p.setLayoutY(returnY(s.charAt(2), false));
        }
        //p.setLayoutY(returnY(s.charAt(2)));
        System.out.println("pieces current y " + p.getLayoutY());
        p.setFitWidth(280);
        p.setFitHeight(280);
        placePiece(p);

    }


    // FIXME Task 7: Implement a basic playable Steps Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement starting placements

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting starting placements

    public static String difficulty(double difficulty) {
        // Task 8 starting placements
        String[][] challenges = {
                //Easy
                {"BGKADgHAiDHnEDkGFS","BGKGCgDHnCElACiHHQFFO",""},
                //Medium
                {"CEnAESHGlFAP","FBgBElEFBCCW","BGKFCNCFl"},
                //Hard
                {"EFBHBR","HHOFBg","EEfAEn"}
        };
        Random ran = new Random();
        int i = ran.nextInt(3);
        if (difficulty < 2.5) {
            return challenges[0][i];
        }
        else if (difficulty < 5.0) {
            return challenges[1][i];
        }
        else {
            return challenges[2][i];
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {


        startPieces.add("BGS");
        startPieces.add("AHQ");
        startPieces.add("EFB");
        startPieces.add("GCg");
        startPieces.add("CDN");
        startPieces.add("HFl");
        startPieces.add("DAi");
        startPieces.add("FHn");
        ArrayList<String> poses = new ArrayList<>();

        primaryStage.setTitle("IQ Steps");
        String first = "AAL";
        Scene scene = new Scene(startScreen, BOARD_WIDTH, BOARD_HEIGHT);
        Scene scene2 = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        Button button = new Button("Start Game");
        button.setLayoutY(200);
        button.setLayoutX((BOARD_WIDTH/2.25)-75);
        button.setGraphic(new ImageView(new Image(Board.class.getResource(URI_BASE + "BGSAHQEFBGCgCDNHFlDAiFHn.png").toString())));

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                primaryStage.setScene(scene2);
                primaryStage.show();

            // get piece from list
                for (String s : startPieces) {
                    for (DraggablePiece p : DraggablePieceList) {
                        Character pieceCharOne = p.piece.pieceName.charAt(0);
                        Character pieceCharTwo = p.piece.pieceName.charAt(1);
                        if (pieceCharOne == s.charAt(0)) {
                            switch (s.charAt(0)) {
                                case 'A':
                                    setter(p, s);
                                    //System.out.println(p.pos);
                                    root.getChildren().add(p);
                                    //System.out.println(pieces);
                                    break;
                                case 'B':
                                    setter(p, s);
                                    //System.out.println(p.pos);
                                    root.getChildren().add(p);
                                    //System.out.println(pieces);
                                    break;
                                case 'C':
                                    setter(p, s);
                                    //System.out.println(p.pos);
                                    root.getChildren().add(p);
                                    //System.out.println(pieces);
                                    break;
                                case 'D':
                                    setter(p, s);
                                    //System.out.println(p.pos);
                                    root.getChildren().add(p);
                                    //System.out.println(pieces);
                                    break;
                                case 'E':
                                    setter(p, s);
                                    //System.out.println(p.pos);
                                    root.getChildren().add(p);
                                    //System.out.println(pieces);
                                    break;
                                case 'F':
                                    setter(p, s);
                                    //System.out.println(p.pos);
                                    root.getChildren().add(p);
                                    //System.out.println(pieces);
                                    break;
                                case 'G':
                                    System.out.println("before setter" + p.piece.pieceName);
                                    setter(p, s);
                                    //System.out.println(p.pos);
                                    root.getChildren().add(p);
                                    //System.out.println(pieces);
                                    break;
                                case 'H':
                                    pieces.add(s);
                                    //System.out.println("before setter" + p.piece.pieceName);
                                    setter(p, s);
                                    //System.out.println(p.pos);
                                    root.getChildren().add(p);
                                    //System.out.println(pieces);
                                    break;

                            }
                        }
                    }
                }

            // match piece up with piece from list
            // flip piece as needed
            // rotate piece as needed
            // place piece

//                    System.out.println(p);
//                    for (String s : startPieces) {
//                        if (p.piece.pieceName == (s.charAt(0) + "" + s.charAt(1))) {
//                            System.out.println(s.charAt(2));
//                            System.out.println(p.piece.pieceName);
//                            placePiece(p);     //not working
//                            System.out.println(p.piece.pieceName);
//                        }
//                    }
//                }
//                root.getChildren().addAll(DraggablePieceList);
//                for (String s : startPieces) {
//                    String pieceVal = s.charAt(0) + "" + s.charAt(1) ;
//                    Character posToCheck = s.charAt(2);
//                    for (Node node : root.getChildren()) {
//                        if (node.toString().contains("Circle")) {
//                            if ((((Circle) node).position) == posToCheck) {
//                                DraggablePieceList.removeIf(draggablePiece -> {draggablePiece.piece.pieceName = pieceVal; return true;});
//                            }
//                        }
//                    }
//                }
                //System.out.println(DraggablePieceList);
            }
        });
        startScreen.getChildren().addAll(button);
        root.getChildren().addAll(placements);
        makePegs();
        //System.out.println(pegs);
        makePieces();
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
