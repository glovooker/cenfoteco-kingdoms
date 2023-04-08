package Model;

public class Castle {
    private int life = 3;

    private int id;

    private Coordinate coordinate;

    public Castle() {
        this.coordinate = new Coordinate();
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

    @Override
    public String toString() {
        return "Castle{" +
                "life=" + life +
                ", id=" + id +
                ", coordinates=" + coordinate +
                '}';
    }
}
