package Model;

import BL.characters_abstract_fabric.abstract_product.Army;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int LINES = 22;
    private static final int ROWS = 20;

    private List<Army> armyList;

    public Board() {
        this.armyList = new ArrayList<>();
    }

    public List<Army> getArmyList() {
        return armyList;
    }

    public void setArmyList(List<Army> armyList) {
        this.armyList = armyList;
    }

    public int getLINES() {
        return LINES;
    }

    public int getROWS() {
        return ROWS;
    }
}
