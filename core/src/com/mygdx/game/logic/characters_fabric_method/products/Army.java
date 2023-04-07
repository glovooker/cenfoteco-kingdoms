package com.mygdx.game.logic.characters_fabric_method.products;

public abstract class Army {

    private String characterType;
    private String id;
    private int movement;
    private int life;
    private int attack;
    private int defense;
    private String owner;
    private String characterClass;


    public Army() {
        setId("");
        setMaxMove(0);
        setLife(0);
        setAttack(0);
        setDefense(0);
        setCharacterType("Army");
        setOwner("");
        setCharacterClass("Army");
    }


    public String getCharacterType() { return characterType; }
    public String getId() { return id; }
    public int getMaxMove() { return movement; }
    public int getLife() { return life; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public String getOwner() { return owner; }
    public String getCharacterClass() { return characterClass; }


    public void setCharacterType(String characterType) { this.characterType = characterType; }
    public void setId(String id) { this.id = id; }
    public void setMaxMove(int maxMove) { this.movement = maxMove; }
    public void setLife(int life) { this.life = life; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setOwner(String owner) { this.owner = owner; }
    public void setCharacterClass(String characterClass) { this.characterClass = characterClass; }


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
}