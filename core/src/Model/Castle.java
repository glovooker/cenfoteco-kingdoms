package Model;

public class Castle {
    private int life = 3;

    private int id;

    private Coordinates coordinates;



    public Castle() {
        this.coordinates = new Coordinates();
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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Castle{" +
                "life=" + life +
                ", id=" + id +
                ", coordinates=" + coordinates +
                '}';
    }
}
