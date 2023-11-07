/*
 * File: Checkerboard.java
 * -----------------------
 * This program draws a checkerboard. The dimensions of the
 * checkerboard are specified by the constants N_ROWS and
 * N_COLUMNS, and the size of the squares is chosen so
 * that the checkerboard fills the available vertical space. */

import acm.graphics.*;
import acm.program.*;

public class Checkerboard extends GraphicsProgram {
        public void run() {
                /* Private constants */
                final int N_ROWS = 8;
                final int N_COLUMNS = 8;
                double sqSize = (double) getHeight() / N_ROWS;
                for (int i = 0; i < N_ROWS; i++) {
                        for (int j = 0; j < N_COLUMNS; j++) {
                                double x = j * sqSize;
                                double y = i * sqSize;
                                GRect sq = new GRect(x, y, sqSize, sqSize);
                                sq.setFilled((i + j) % 2 != 0);
                                add(sq);
                        }
                }
        }
}