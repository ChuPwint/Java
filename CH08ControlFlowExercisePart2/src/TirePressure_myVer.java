import acm.program.ConsoleProgram;

public class TirePressure_myVer extends ConsoleProgram{
    public void run() {
        boolean goodPressure = true;

        int rightFront = readInt("Enter right front pressure: ");
        if(rightFront < 35 || rightFront > 45){
            println("Warning: pressure is out of range");
            goodPressure = false;
        }
        int leftFront = readInt("Enter left front pressure: ");
        if(leftFront < 35 || leftFront > 45){
            println("Warning: pressure is out of range");
            goodPressure = false;
        }

        int rightBack = readInt("Enter right front pressure: ");
        if(rightBack < 35 || rightBack > 45){
            println("Warning: pressure is out of range");
            goodPressure = false;
        }
        int leftBack = readInt("Enter right front pressure: ");
        if(leftBack < 35 || leftBack > 45){
            println("Warning: pressure is out of range");
            goodPressure = false;
        }

        if(goodPressure == false){
            println("Inflation is bad");
        }else if((rightFront == leftFront) && (rightBack == leftBack)){
            println("Inflation is OK.");
        }else{
            if(rightFront != leftFront){
                println("Check front tire pressure!");
            }else if(rightBack != leftBack){
                println("Check back tire pressures!");
            }
        }

        /*Pressure Building Exercise
        int frontDifference = Math.abs(rightFront - leftFront);
        int backDifference = Math.abs(rightBack - leftBack);
        if((goodPressure == true) && (frontDifference == 3 || backDifference == 3)){
            println("Inflation is OK");
        }*/
    }
}
