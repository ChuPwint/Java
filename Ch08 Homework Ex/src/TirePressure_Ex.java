import acm.program.ConsoleProgram;

public class TirePressure_Ex extends ConsoleProgram{
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

        int rightBack = readInt("Enter right back pressure: ");
        if(rightBack < 35 || rightBack > 45){
            println("Warning: pressure is out of range");
            goodPressure = false;
        }
        int leftBack = readInt("Enter left back pressure: ");
        if(leftBack < 35 || leftBack > 45){
            println("Warning: pressure is out of range");
            goodPressure = false;
        }

        if(goodPressure == false){
            println("Inflation is bad");
        }else if((rightFront == leftFront) && (rightBack == leftBack)){
            println("Inflation is OK.");
        }
    }
}
