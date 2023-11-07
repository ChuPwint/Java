import acm.program.ConsoleProgram;

public class AreaOfCircle extends ConsoleProgram{
    public void run(){
        setFont("JetBrains Mono NL-26");
        println("This program calculates area of circle.");
        final double PIE = 3.1415;
        double radius = readDouble("Enter radius of circle: ");
        double area = PIE * radius * radius;
        println("The area of the circle is " + area);
    }
}
