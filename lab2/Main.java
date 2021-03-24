package lab2;

class Main {
    public static void main(String[] args) {
        // Создание точек 2d
        Point2d myPoint = new Point2d ();//создает точку (0,0)
        Point2d myOtherPoint = new Point2d (5,3);//создает точку (5,3)
        Point2d aThirdPoint = new Point2d (0,0);

        System.out.println("Точка myPoint " + myPoint.getX() + " "+ myPoint.getY());
        System.out.println("Точка myOtherPoint " + myOtherPoint.getX()+ " "+ myOtherPoint.getY());
        System.out.println("Точка aThirdPoint " +  aThirdPoint.getX()+ " "+ aThirdPoint.getY());
        System.out.println("Сравнение 2 2d точек, значение: " + Point2d.comparePoints(myOtherPoint, aThirdPoint));

        // Создание точек 3d
        Point3d p1 = new Point3d ();//создает точку (0,0,0)
        Point3d p2 = new Point3d (5,3,0);//создает точку (5,3,0)
        Point3d p3 = new Point3d (1,0,0);
        System.out.println("Точка p1 " + p1.getX()+ " "+ p1.getY()+ " "+p1.getZ());
        System.out.println("Точка p2 " + p2.getX()+ " "+ p2.getY()+ " "+p2.getZ());
        System.out.println("Точка p3 " + p3.getX()+ " "+ p3.getY()+ " "+p3.getZ());
        System.out.println("Сравнение 2 3d точек (p1 и p2), значение: " + Point3d.comparePoints(p1, p2));

        System.out.println("distanceTo " + Point3d.distanceTo(p1, p2));

        computeArea(p1, p2, p3);
       

    }

    public static double computeArea(Point3d p1, Point3d p2, Point3d p3) {
        double hero, s, a, b, c;

        if (Point3d.comparePoints(p1, p2) || Point3d.comparePoints(p1, p3) || Point3d.comparePoints(p2, p3)) {
            System.out.println("Some points are equal");


            return 0;
        } else {
            a = Point3d.distanceTo(p1, p2);
            b = Point3d.distanceTo(p2, p3);
            c = Point3d.distanceTo(p1, p3);
    
            s = (a + b + c) / 2;
    
            hero = (double) Math.sqrt(s * (s - a) * (s - b) * (s - c));
    
            System.out.println("Area of ​​a triangle: " + hero);
            return 0;
        }
    }
}