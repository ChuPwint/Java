import stanford.karel.Karel;

public class MakeBeeperRowInClassEg extends Karel {
        public void run() {
                putBeeper();
                while(frontIsClear()) {
                        move();
                        putBeeper();
                }
        }
}
