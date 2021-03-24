package lab4;

import java.awt.geom.Rectangle2D;

/**
 * Фрактал Мандельброта.
 */
public class Mandelbrot extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }

    /**
     * Считаем количество итераций и возвращаем
     */
    public int numCounter(double x, double y) {
        int i = 0;

        double z = 0;
        double zi = 0;

        while (i < MAX_ITERATIONS && (z * z + zi * zi) < 4) {
            double zNew = z * z - zi * zi + x;
            double ziNew = 2 * z * zi + y;

            z = zNew;
            zi = ziNew;
            i += 1;
        }

        /**
         * Проверяет, что количество итераций достигло допутимого предела
         */
        if (i == MAX_ITERATIONS) {
            return -1;
        }

        return i;
    }


}