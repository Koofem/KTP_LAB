package lab5;

import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2;
        range.width = 4;
        range.height = 4;
    }

    /**
     * Считаем количество итераций и возвращаем
     */
    public int numIterations(double x, double y) {
        int i = 0;

        double z = 0;
        double zi = 0;

        while (i < MAX_ITERATIONS && (z * z + zi * zi) < 4) {
            double zNew = z * z - zi * zi + x;
            double ziNew = -2 * z * zi + y;

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

    /**
     * Метод используется combo box для отображения имени
     */
    public String toString() {
        return "Tricorn";
    }

}
