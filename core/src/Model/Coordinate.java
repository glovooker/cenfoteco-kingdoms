package Model;

public class Coordinate implements Cloneable {
    private int x;
    private int y;

    public Coordinate() {

    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public boolean isAdjacentTo(Coordinate coordinate) {
        return coordinate.getY() == (this.getY() + 1) ||
               coordinate.getY() == (this.getY() - 1) ||
               coordinate.getX() == (this.getX() + 1) ||
               coordinate.getX() == (this.getX() - 1);
    }

    @Override
    public Coordinate clone() {
        try {
            return (Coordinate) super.clone();
        }
        catch(CloneNotSupportedException e) {
            return this;
        }
    }
}
