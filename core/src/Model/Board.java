package Model;

import BL.characters_abstract_fabric.abstract_product.Army;

import java.util.ArrayList;
import java.util.List;

public class Board implements Cloneable {
    public static final int ROWS = 22;

    public static final int COLUMNS = 20;

    private int[][] numberMatriz = new int[ROWS][COLUMNS];

    private ArrayList<Army> armyList;

    public Board() {
        this.armyList = new ArrayList<>();
    }

    public List<Army> getArmyList() {
        return armyList;
    }

    public void setArmyList(ArrayList<Army> armyList) {
        this.armyList = armyList;
    }

    public int[][] getNumberMatriz() {
        return numberMatriz;
    }

    @Override
    public Board clone() {
        try {
            Board clone = (Board) super.clone();

            if(clone.armyList != null) {
                clone.armyList = new ArrayList<>();
                for (Army army: this.armyList) {
                    clone.armyList.add(army.clone());
                }

            }

            clone.numberMatriz = new int[ROWS][COLUMNS];

            for(int i = 0; i < ROWS; i++) {
                for(int j = 0; j < COLUMNS; j++) {
                    clone.numberMatriz[i][j] = this.numberMatriz[i][j];
                }
            }

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
