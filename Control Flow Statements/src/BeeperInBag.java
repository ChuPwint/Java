import stanford.karel.Karel;

public class BeeperInBag extends Karel {
        public void run() {
                for (int i = 0; i < 3; i++) {
                        move();
                }

                while (beepersPresent()) {
                        pickBeeper();
                }
        }
}
