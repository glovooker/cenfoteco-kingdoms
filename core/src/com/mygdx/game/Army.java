package com.mygdx.game;

class Army {
    private String id;
    private int maxMove;
    private int life;
    private int attack;
    private int defense;
    private String characterType;
    // private Player owner;
    
    public Army() {
    }
    
    public Army(
        String id, 
        int maxMove, 
        int life, 
        int attack, 
        int defense, 
        String characterType 
        // Player owner
        ) {
        setId(id);
        setMaxMove(maxMove);
        setLife(life);
        setAttack(attack);
        setDefense(defense);
        setCharacterType(characterType);
        // setOwner(owner);
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public int getMaxMove() {
        return maxMove;
    }
    
    public void setMaxMove(int maxMove) {
        this.maxMove = maxMove;
    }
    
    public int getLife() {
        return life;
    }
    
    public void setLife(int life) {
        this.life = life;
    }
    
    public int getAttack() {
        return attack;
    }
    
    public void setAttack(int attack) {
        this.attack = attack;
    }
    
    public int getDefense() {
        return defense;
    }
    
    public void setDefense(int defense) {
        this.defense = defense;
    }
    
    public String getCharacterType() {
        return characterType;
    }
    
    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }
    
    // public Player getOwner() {
    //     return owner;
    // }
    
    // public void setOwner(Player owner) {
    //     this.owner = owner;
    // }
}