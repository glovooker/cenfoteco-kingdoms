package View.Components;

import BL.observer.interfaces_observer.Observer;
import Model.GameState;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class HudMovements implements Observer {
    private Label movementsAmount;


    public HudMovements(Stage stage) {
        movementsAmount = new Label(String.format("%01d", 0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        movementsAmount.setPosition(127, 120 );
        movementsAmount.setFontScale(3);
        stage.addActor(movementsAmount);
    }

    public Label getMovementsAmount() {
        return movementsAmount;
    }

    @Override
    public void update(GameState state) {
        if(state.getTime() < 0){
            this.movementsAmount.setText("0");
        }
    }
}
