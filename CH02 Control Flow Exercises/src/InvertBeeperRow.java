import stanford.karel.Karel;

public class InvertBeeperRow extends Karel {

        public void run() {
                while (frontIsClear()) {
                        if (beepersPresent()) {
                                pickBeeper();
                        } else {
                                putBeeper();
                        }
                        move();
                }

                if (beepersPresent()) {
                        pickBeeper();
                } else {
                        putBeeper();
                }
        }
}
