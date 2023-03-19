package com.mygdx.game.logic.products;

public abstract class Army {

    private String characterType;
    private String id;
    private int movement;
    private int life;
    private int attack;
    private int defense;
    // private Player owner;



    public Army() {
        setId("");
        setMaxMove(0);
        setLife(0);
        setAttack(0);
        setDefense(0);
        setCharacterType("Army");
    }


    public Army(String id, int maxMove, int life, int attack, int defense, String characterType) {
        setCharacterType(characterType);
        setId(id);
        setMaxMove(maxMove);
        setLife(life);
        setAttack(attack);
        setDefense(defense);
        // setOwner(owner);
    }

    public String getCharacterType() { return characterType; }
    public String getId() { return id; }
    public int getMaxMove() { return movement; }
    public int getLife() { return life; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    // public Player getOwner() { return owner; }


    public void setCharacterType(String characterType) { this.characterType = characterType; }
    public void setId(String id) { this.id = id; }
    public void setMaxMove(int maxMove) { this.movement = maxMove; }
    public void setLife(int life) { this.life = life; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    // public void setOwner(Player owner) { this.owner = owner; }


    public int calculateMovement(int minMove, int maxMove) {
        return (int) (Math.random() * (maxMove - minMove + 1) + minMove);
    }
}