package BL.characters_abstract_fabric.abstract_fabric;

import BL.characters_abstract_fabric.abstract_product.Army;
import Model.Player;

public interface Abstract_Fabric_Army {
    public Army createArmy(Player owner);
}
