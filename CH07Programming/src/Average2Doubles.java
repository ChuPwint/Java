/*
 * File: Average2Doubles.java
 * --------------------------
 * This program averages two double-precision floating-point numbers. */


import acm.program.ConsoleProgram;

public class Average2Doubles extends ConsoleProgram {
        public void run() {
                setFont("JetBrains Mono NL-26");
                println("This program averages two numbers.");
                double n1 = readDouble("Enter n1: ");
                double n2 = readDouble("Enter n2: ");
                double average = (n1 + n2) / 2;
                println("The average is " + average + ".");
        }
}