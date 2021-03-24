package lab2;

/**
 * двумерный класс точки.
 **/
public class Point2d {
    /** координата X **/
    private double xCoord;

    /** координата Y **/
    private double yCoord;

    /** Конструктор инициализации **/
    public Point2d ( double x, double y) {
        xCoord = x;
        yCoord = y;
    }
    /** Конструктор по умолчанию. **/
    public Point2d () {
        //Вызовите конструктор с двумя параметрами и определите источник.
        this(0, 0);
    }
    /** Возвращение координаты X **/
    public double getX () {
        return xCoord;
    }
    /** Возвращение координаты Y **/
    public double getY () {
        return yCoord;
    }
    /** Установка значения координаты X. **/
    public void setX ( double val) {
        xCoord = val;
    }
    /** Установка значения координаты Y. **/
    public void setY ( double val) {
        yCoord = val;
    }

    /** Сравниваются 2 точки и вовзращается boolean **/
    public static boolean comparePoints(Point2d p1, Point2d p2) {
        boolean isEqual = false;


        if((p1.getX() == p2.getX()) && (p1.getY() == p2.getY())) {
            isEqual = true;
        }

        return isEqual;
    }
}