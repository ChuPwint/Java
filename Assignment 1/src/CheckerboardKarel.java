import stanford.karel.Karel;

public class CheckerboardKarel extends Karel {
    public void run() {
        makeCheckBoard();
    }
    void makeCheckBoard(){
        while(notFacingNorth()){
            placeBoards();
        }
    }
    void placeBoards(){
        //for any mxn use
        putBeeper();
        moveNextLaneOrNot();
        moveNextLaneOrNot();
    }
    void moveNextLaneOrNot(){
        if(frontIsBlocked()){
            moveNextLane();
        }else{
            move();
        }
    }
    void moveNextLane(){
        if(facingEast()){
            turnNorth();
            if(frontIsClear()){
                move();
                turnLeft();
            }
        }
        else{
            turnNorth();
            //for checking if end of box
            if(frontIsClear()){
                move();
                turnRight();
            }
        }
    }
    void turnNorth(){
        while(notFacingNorth()){
            turnLeft();
        }
    }
    void turnRight(){
        for(int i = 0; i < 3; i++){
            turnLeft();
        }
    }
}