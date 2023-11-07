import acm.program.ConsoleProgram;

public class CalculateTemperature extends ConsoleProgram{
    public void run(){
        setFont("JetBrains Mono NL-26");
        println("This program calculates fahrenheit to celsius.");
        double fahrenheit = readDouble("Please enter Fahrenheit in degrees: ");
        double celsius = (5 * (fahrenheit - 32))/9;
        println("The Temperature in celsius is " + celsius + " degrees.");
    }
}
