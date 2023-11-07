import stanford.karel.Karel;

public class MoveWhileFrontIsClear extends Karel {

        public void run() {
                while (frontIsClear()) {
                        move();
                }

                putBeeper();
                turnLeft();
        }
}
