package View.Components;

import BL.GameController;
import Model.GameState;
import Model.Player;
import BL.observer.interfaces_observer.Observer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class HudMain implements Observer {

    private Label playerInGame;

    private Label life;

    private Label seconds;

    public HudMain(Stage stage, Player playerInTurn){
        initialize(stage, playerInTurn);
    }

    private void initialize(Stage stage, Player playerInTurn){
        String player = playerInTurn.getName();

        playerInGame = new Label(player, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        life = new Label(String.format("%01d", playerInTurn.getCastle().getLife()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        seconds = new Label(String.format("%01d", 60), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        playerInGame.setPosition(1330, 15 );
        playerInGame.setFontScale(3);
        life.setPosition(1268, 11 );
        life.setFontScale(2.5f);
        seconds.setPosition(40, 12 );
        seconds.setFontScale(2.5f);

        GameController controller = GameController.getInstance();
        controller.getTimer().addObservers(this);

        stage.addActor(playerInGame);
        stage.addActor(life);
        stage.addActor(seconds);
    }

    public Label getPlayerInGame() {
        return playerInGame;
    }

    public Label getLife() {
        return life;
    }

    public Label getSeconds() {
        return seconds;
    }

    @Override
    public void update(GameState state) {
        this.seconds.setText(String.format("%01d", state.getTime()));
        if(state.getTime() < 0){
            seconds.setPosition(-4, 12 );
            this.seconds.setText("Time's up");
        }else{
            seconds.setPosition(40, 12 );
        }

        this.playerInGame.setText(state.getPlayerInTurn().getName());
        this.life.setText(state.getPlayerInTurn().getCastle().getLife());
    }
}
