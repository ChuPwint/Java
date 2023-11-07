import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {
        public static final int APPLICATION_WIDTH = 400;
        public static final int APPLICATION_HEIGHT = 600;

        final int PDL_WIDTH = 400;       // paddle width
        final int PDL_HEIGHT = 10;      // paddle height
        final int PDL_Y_OFFSET = 30;    // distance between bottom wall and bottom of paddle

        private final int BALL_RADIUS = 10;     // size of ball will be (2 * BALL_RADIUS), you can change this value according to your taste
        private final double BALL_SIZE = 2 * BALL_RADIUS;

        final int BRICKS_PER_ROW = 10;
        final int BRICK_ROWS = 10;
        final int BRICK_GAP = 4;        // horizontal and vertical gap between bricks rows and brick columns
        final int BRICK_WIDTH = (APPLICATION_WIDTH - (BRICKS_PER_ROW - 1) * BRICK_GAP) / BRICKS_PER_ROW;
        final int BRICK_HEIGHT = 8;
        final int BRICK_Y_OFFSET = 70;  // distance from y = 0 (above wall) to top of the first row of bricks

        final int BRICKS_IN_ROW_WIDTH = (BRICK_WIDTH * BRICKS_PER_ROW) + (BRICK_GAP * (BRICKS_PER_ROW - 1));
        final double LEFT_MARGIN = (APPLICATION_WIDTH - BRICKS_IN_ROW_WIDTH) / 2.0; //START OF X ROW IN BRICKS

        final int TURNS = 3;            // Number of turns for one game

        private GOval ball = null;
        private GRect paddle = null;
        private int remainingBricks;    // to store remaining brick count
        private boolean IsGroundFloor;  // to check if hit bottom wall

        private double vx = 0;          // horizontal velocity
        private double vy = 0;          // vertical velocity

        private double labelX = 0;      //label horizontal Centering
        private final double labelY = APPLICATION_HEIGHT / 2.0;       //label vertical Centering

        private double colludedPointX;
        private double colludedPointY;

        public void run() {
                startGame();
        }

        void startGame() {
                int chance = TURNS;
                while(chance > 0) {
                        IsGroundFloor = false;
                        createBricks();
                        createBall();
                        createPaddle();
                        addMouseListeners();
                        initVelocity();
                        if(IsGroundFloor && chance > 1) {
                                moveBall();
                                chance--;
                                checkWinLose();
                        }else if(chance == 1){                          //if IsGroundFloor but lastChance
                                moveBall();
                                chance--;
                                checkWinLose();
                        }else {
                                moveBall();
                                GameOver();
                        }
                }
        }

        void checkWinLose() {
                if(remainingBricks != 0) {
                        removeAll();
                        String a = "You lost! Click to play next turn.";
                        GLabel lose = new GLabel(a);
                        lose.setFont("JetBrains Mono NL-Bold-18");
                        labelX = (APPLICATION_WIDTH - lose.getWidth()) / 2.0;
                        lose.setLocation(labelX, labelY);
                        lose.setColor(Color.RED);
                        add(lose);
                        waitForClick();
                        remove(lose);
                }else {
                        println("In win loop!");
                        removeAll();
                        String b = "You win! Click to play again.";
                        GLabel win = new GLabel(b);
                        win.setFont("JetBrains Mono NL-Bold-18");
                        labelX = (APPLICATION_WIDTH - win.getWidth()) / 2.0;
                        win.setLocation(labelX, labelY);
                        win.setColor(Color.RED);
                        add(win);
                        waitForClick();
                        remove(win);
                }
        }

        void GameOver() {
                removeAll();
                String c = "Game Over! Click to restart.";
                GLabel gameOver = new GLabel(c);
                gameOver.setFont("JetBrains Mono NL-Bold-18");
                labelX = (APPLICATION_WIDTH - gameOver.getWidth()) / 2.0;
                gameOver.setLocation(labelX, labelY);
                gameOver.setColor(Color.RED);
                add(gameOver);
                waitForClick();
                remove(gameOver);
                startGame();
        }

        void createBricks() {
                Color[] colorArray = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW,
                                        Color.YELLOW, Color.GREEN, Color.GREEN, Color.CYAN, Color.CYAN};

                for (int i = 0; i < BRICK_ROWS; i++) {
                        for (int j = 0; j < BRICKS_PER_ROW; j++) {
                                double x = LEFT_MARGIN + (j * BRICK_WIDTH) + (j * BRICK_GAP);
                                double y = BRICK_Y_OFFSET + (i * BRICK_HEIGHT) + (i * BRICK_GAP);
                                GRect brick = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
                                brick.setFilled(true);
                                brick.setFillColor(colorArray[i]);
                                add (brick);
                        }
                }
                remainingBricks = BRICKS_PER_ROW * BRICK_ROWS;
        }

        void createPaddle() {
                double x = ((APPLICATION_WIDTH - PDL_WIDTH) / 2.0);
                double y = APPLICATION_HEIGHT - (PDL_Y_OFFSET + PDL_HEIGHT);
                paddle = new GRect(x, y, PDL_WIDTH, PDL_HEIGHT);
                paddle.setFilled(true);
                add(paddle);
        }

        void createBall(){
                double xCircle = (APPLICATION_WIDTH - BALL_SIZE)/ 2.0;
                double yCircle = (APPLICATION_HEIGHT - BALL_SIZE) / 2.0;
                ball = new GOval(xCircle, yCircle, BALL_SIZE, BALL_SIZE);
                ball.setFilled(true);
                add(ball);
        }

        void initVelocity() {
                RandomGenerator r = RandomGenerator.getInstance();
                vy = 3;
                vx = r.nextDouble(1.0, 3.0);
                if(r.nextBoolean(0.5)) { vx = -vx; }
        }

        void moveBall() {
                waitForClick();
                final int delay = 1;
                while(!IsGroundFloor) {
                        ball.move(vx, vy);
                        if(ball.getY() + BALL_SIZE >= APPLICATION_HEIGHT) { //groundFloor
                                IsGroundFloor = true;
                        }

                        if(ball.getY() <= 0) { //topWall
                                vy = -vy;
                        }

                        if(ball.getX() + BALL_SIZE >= APPLICATION_WIDTH || ball.getX() <= 0) {  //Right & Left walls
                                vx = -vx;
                        }
                        pause(delay);
                        handleCollusion();
                }
        }

        void handleCollusion() {
                GObject obj = colludedObj();
                if(obj == paddle) {
                        ballAndPaddle();
                }
                else if(obj == null) {
                        //do nth
                }
                else {
                        remove(obj);
                        remainingBricks--;
                        vy = -vy;
                }
        }

        void ballAndPaddle() {
                if(colludedPointY == paddle.getY() && (colludedPointX >= paddle.getX() && colludedPointX <= paddle.getX() + PDL_WIDTH)) {
                        if(vy < 0) { vy = +vy; }
                        else { vy = -vy; }
                }else {
                        if(vx < 0) { vx = +vx; }
                        else { vx = -vx; }
                }
        }

        GObject colludedObj() {
                final int setRange = 1;
                //(x + r, y - 1) upperSide
                double x1 = ball.getX() + BALL_RADIUS;
                double y1 = ball.getY() - setRange;

                //(x - 1, y + r) leftSide
                double x2 = ball.getX() - setRange;
                double y2 = ball.getY() + BALL_RADIUS;

                //(x + 2r + 1, y + r) rightSide
                double x3 = x1 + BALL_RADIUS + setRange;
                //double y3 = y2;

                //(x + r, y + 2r + 1) lowerSide
                //double x4 = x1;
                double y4 = y2 + BALL_RADIUS + setRange;

                GObject obj = getElementAt(x1, y1);
                if(obj != null) {
                        colludedPointX = x1;
                        colludedPointY = y1;
                        return obj;
                }

                obj = getElementAt(x2, y2);
                if(obj != null) {
                        colludedPointX = x2;
                        colludedPointY = y2;
                        return obj;
                }

                obj = getElementAt(x3, y2);
                if(obj != null) {
                        colludedPointX = x3;
                        colludedPointY = y2;
                        return obj;
                }

                obj = getElementAt(x1, y4);
                if(obj != null) {
                        colludedPointX = x1;
                        colludedPointY = y4;
                        return obj;
                }

                return null;
        }

        public void mouseMoved (MouseEvent e) {
                double mouseX = e.getX();
                double mouseY = e.getY();
                double paddleCenter = PDL_WIDTH / 2.0;
                final int movableRange = 10;
                int pX = 1;

                //to limit paddle within application size
                if(mouseX + paddleCenter >= APPLICATION_WIDTH) {
                        mouseX = APPLICATION_WIDTH - (PDL_WIDTH / 2.0);
                }

                if(mouseX - paddleCenter <= 0) {
                        mouseX = (PDL_WIDTH / 2.0);
                }

                if(mouseY >= paddle.getY() - movableRange) {  //to move paddle only within movable (Y Range)
                        while(((paddle.getX() + paddleCenter) >= mouseX)) {
                                paddle.move(-pX, 0);
                        }

                        while((paddle.getX() + paddleCenter) <= mouseX) {
                                paddle.move(pX, 0);
                        }
                }
        }
}