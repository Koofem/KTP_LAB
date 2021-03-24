package lab2;

/**
 * Класс точки Евклидового пространства.
 **/
public class Point3d {
    /** координата X **/
    private double xCoord;

    /** координата Y **/
    private double yCoord;

    /** координата Z **/
    private double zCoord;

    /** Конструктор инициализации **/
    public Point3d ( double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }
    /** Конструктор по умолчанию. **/
    public Point3d() {
        // Вызов конструктора без параметров
        this(0, 0, 0);
    }
    /** Возвращение координаты X **/
    public double getX() {
        return xCoord;
    }
    /** Возвращение координаты Y **/
    public double getY() {
        return yCoord;
    }

    /** Возвращение координаты Z **/
    public double getZ() {
        return zCoord;
    }

    /** Установка значения координаты X. **/
    public void setX( double val) {
        xCoord = val;
    }
    /** Установка значения координаты Y. **/
    public void setY( double val) {
        yCoord = val;
    }

    /** Установка значения координаты Z. **/
    public void setZ(double val) {
        zCoord = val;
    }

    /** Сравниваются 3 точки и вовзращается boolean **/
    public static boolean comparePoints(Point3d p1, Point3d p2) {
        boolean isEqual = false;


        if((p1.getX() == p2.getX()) && (p1.getY() == p2.getY()) && (p1.getZ() == p2.getZ())) {
            isEqual = true;
        }

        return isEqual;
    }

    public static double distanceTo(Point3d p1, Point3d p2) {
        // DecimalFormat df = new DecimalFormat("#.##");
        double distance;

        distance = Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2) + Math.pow(p2.getZ() - p1.getZ(), 2));

        return distance;
    }
}