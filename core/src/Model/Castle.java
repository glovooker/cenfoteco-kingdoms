package Model;

public class Castle implements Cloneable {
    private int life = 3;

    private int id;

    private Coordinate coordinate;



    public Castle() {

    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coordinate getCoordinates() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    protected Castle clone()  {
        try {
            Castle clone = (Castle) super.clone();

            clone.coordinate = this.coordinate != null ? (Coordinate) this.coordinate.clone() : null;
            return clone;
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
