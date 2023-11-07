import stanford.karel.Karel;

public class ExGoNorthOrSouth extends Karel {

        public void run() {
                for(int i = 1; i < 3; i++){
                        move();
                }
                if(beepersPresent()){
                        turnLeft();
                        while(frontIsClear()){
                                move();
                        }
                        pickBeeper();
                }
                else{
                        turnRight();
                        while(frontIsClear()){
                                move();
                        }
                        pickBeeper();
                }
        }

        void turnRight() {
                for (int i = 0; i < 3; i++) {
                        turnLeft();
                }
        }

}
