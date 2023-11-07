import stanford.karel.Karel;

public class MoveBeeperToOtherSide extends Karel {
        public void run() {
                for (int i = 0; i < 3; i++) {
                        move();
                }

                if (beepersPresent()) {
                        pickBeeper();
                        turnLeft();
                        move();
                        turnRight();
                        move();
                        turnRight();
                        move();
                        putBeeper();
                        turnLeft();
                }
        }

        void turnRight() {
                for (int i = 0; i < 3; i++) {
                        turnLeft();
                }
        }
}
