import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class Ex_6 extends GraphicsProgram {
    public void run() {
        final int BRICK_WIDTH = 30;
        final int BRICK_HEIGHT = 12;
        final int BRICKS_IN_BASE = 14;

        double base = getHeight() - BRICK_HEIGHT;
        double count = BRICKS_IN_BASE;

        for (int i = 0; i < BRICKS_IN_BASE; i++) {
            for (int j = 0; j < BRICKS_IN_BASE - i; j++) {
                double x = j * BRICK_WIDTH;
                double center = (getWidth() - (count * BRICK_WIDTH)) / 2.0;
                GRect brick = new GRect(center + x, base, BRICK_WIDTH, BRICK_HEIGHT);
                add(brick);
            }
            count--;
            base -=  BRICK_HEIGHT;
        }
    }
}