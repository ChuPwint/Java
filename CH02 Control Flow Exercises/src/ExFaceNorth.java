import stanford.karel.Karel;

public class ExFaceNorth extends Karel {

        public void run() {
                if(facingNorth()){
                        move();
                }
                if(facingEast()){
                        turnLeft();
                        move();
                }
                if(facingSouth()){
                        turnLeft();
                        turnLeft();
                        move();
                }
                if(facingWest()){
                        turnRight();
                        move();
                }
        }

        private void turnRight() {
                turnLeft();
                turnLeft();
                turnLeft();
        }

}
