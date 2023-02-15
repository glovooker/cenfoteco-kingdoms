package com.mygdx.game;

class Artillery extends Army implements iUltimates {

    public Artillery() {
    }

    public Artillery(String id, String characterType) {
        super(id, 4, 4, 6, 10, "artillery:"+characterType);
    }

    @Override
    public void specialAttack() {
        switch (getCharacterType()) {
            case "artillery:long_ranged":
                // Artillery: Allow to attack an enemy or an enemy structure from two tiles away
                // TODO: Implement artillery:long_ranged attack
                break;
            case "artillery:attack_buffer":
                // Artillery: Multiplies its own attack by 2 for 1 turn;
                setAttack(getAttack() * 2);
                break;
            case "artillery:defense_buffer":
                // Artillery: Multiplies its own defense by 2 for 1 turn;
                setDefense(getDefense() * 2);
                break;
            case "artillery:defense_debuffer":
                // Artillery: Lowers an enemy's defense by 10% if infantry or 5% if artillery
                // TODO: Implement artillery:defense_debuffer attack
                // TODO: Ask what happens when applying this to a tank
                break;
        }
    }
}
