package BL.characters_abstract_fabric.abstract_product;

import Model.Castle;
import BL.prototype.Clonable;
import Model.Coordinate;
import Model.Player;

public abstract class Army implements Cloneable {

    private String characterType;
    private String id;
    private int movement;
    private int life;
    private int attack;
    private int defense;

    private Player owner;
    private String characterClass;

    private String specialAttack;

    private Coordinate position;


    public Army() {
        setId("");
        setMovements(0);
        setLife(0);
        setAttack(0);
        setDefense(0);
        setCharacterType("Army");
        setCharacterClass("Army");
    }


    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public String getCharacterType() { return characterType; }
    public String getId() { return id; }
    public int getMovements() { return movement; }
    public int getLife() { return life; }
    public int getAttack() { return attack; }
    public String getSpecialAttack() {
        return specialAttack;
    }
    public int getDefense() { return defense; }

    public String getCharacterClass() { return characterClass; }

    public void setCharacterType(String characterType) { this.characterType = characterType; }
    public void setId(String id) { this.id = id; }

    public void setMovements(int moves) {
        if(moves < 0) {
            moves = 0;
        }

        this.movement = moves;
    }

    public void setLife(int life) { this.life = life; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }

    public void setCharacterClass(String characterClass) { this.characterClass = characterClass; }

    public void setSpecialAttack(String specialAttack) {
        this.specialAttack = specialAttack;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int calculateMovement(int minMove, int maxMove) {
        return (int) (Math.random() * (maxMove - minMove + 1) + minMove);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return getId().equals(army.getId());
    }

    public abstract void attack(Army armyToAttack);

    @Override
    public Army clone() {
        try {
            Army clone = (Army) super.clone();
            clone.owner = this.owner.clone();
            clone.position = this.position.clone();

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    public void attackCastle(Castle castle){
        castle.setLife(castle.getLife()-1);
    }
}