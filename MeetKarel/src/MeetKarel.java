import stanford.karel.Karel;

public class MeetKarel extends Karel {
        public void run() {
                move();
                move();
                move();
                pickBeeper();
                turnLeft();
                move();
                move();
                turnLeft();
                turnLeft();
                turnLeft();
                move();
                putBeeper();
                move();
        }
}