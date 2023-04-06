package Observer.Interface;

import Model.GameState;
import Model.Player;

public interface Observer {
    void update(GameState state);
}
