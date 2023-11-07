/*
 * File: InchesToCentimeters.java
 * ------------------------------
 * This program converts inches to centimeters. */

import acm.program.ConsoleProgram;

public class InchesToCentimeters extends ConsoleProgram {

        public void run() {
                setFont("JetBrains Mono NL-26");
                println("This program converts inches to centimeters.");
                final double CENTIMETERS_PER_INCH = 2.54;
                double inches = readDouble("Enter value in inches: ");
                double cm = inches * CENTIMETERS_PER_INCH;
                println(inches + "in = " + cm + "cm");

                //println(10 + 5);
        }

}