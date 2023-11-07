import stanford.karel.Karel;

public class SweepTheStreets extends Karel {

        public void run() {
                sweepAll();
        }
        void sweepAll(){
                sweepStreet();
                turnNorth();
                while(frontIsClear()){
                        nextStreet();
                        sweepStreet();
                        turnNorth();
                }
        }
        void sweepStreet(){
                while(frontIsClear()) {
                        checkBeeper();
                        move();
                }
                checkBeeper();
                turnBackAndMove();
        }
        void checkBeeper(){
                if (beepersPresent()) {
                        pickBeeper();
                }
        }
        void turnBackAndMove(){
                turnLeft();
                turnLeft();
                while(frontIsClear()){
                        move();
                }
        }
        void turnNorth(){
                while(notFacingNorth()){
                        turnLeft();
                }
        }
        void nextStreet(){
               move();
               turnRight();
        }
        void turnRight(){
                for(int i = 0; i < 3; i++){
                        turnLeft();
                }
        }
}
