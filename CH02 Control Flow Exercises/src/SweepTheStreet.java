import stanford.karel.Karel;

public class SweepTheStreet extends Karel {

        public void run() {
                while (frontIsClear()) {
                        if (beepersPresent()) {
                                pickBeeper();
                        }
                        move();
                }

                if (beepersPresent()) {
                        pickBeeper();
                }
        }
}
