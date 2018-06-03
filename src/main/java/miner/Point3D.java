package miner;

import javafx.beans.NamedArg;
import javafx.geometry.Point2D;

public class Point3D {

    public static final Point3D ZERO = new Point3D(0.0 , 0.0 , 0.0);


    private double x;
    private double y;
    private double z;

    public Point3D(@NamedArg("x") double x, @NamedArg("z") double z , @NamedArg("y") double y){
       this.x=x;
       this.y=y;
       this.z=z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }




    //преобразование кубических в осевые
    public static Point2D getPoint2D(Point3D point){
        double q = point.getX();
        double r = point.getZ();

        return new Point2D(q,r);
    }
    //  преобразование осевых в кубические

    /**
     *
     * @param q = Х
     * @param r = Y
     * @return
     */
    public static Point3D getPoint2D(int q, int r) {
        double x1 = q;
        double z1 = r;
        double y1 = -x1-z1;
        return new Point3D(x1,z1,y1);
    }
}
