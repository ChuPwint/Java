import stanford.karel.Karel;

public class MoveTenTimesWithFor extends Karel {
        public void run() {
                for (int i = 0; i < 9; i++) {
                        move();
                }
        }
}
