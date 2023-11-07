import stanford.karel.Karel;

public class MoveBeeperToOtherSideOrComeBack extends Karel {
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
                } else {
                        turnLeft();
                        turnLeft();
                        move();
                        move();
                        move();

                }
        }

        public void turnRight() {
                for (int i = 0; i < 3; i++) {
                        turnLeft();
                }
        }
}
