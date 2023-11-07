import acm.program.ConsoleProgram;

public class Testing extends ConsoleProgram {
    String s = "abc@noodle.com";
    String s1 = "abc@jaboo.com";
    public void run() {
        boolean a = s.equals(s1);
        print(a);
    }
}
