package View;

import BL.GameController;
import Model.Player;
import View.Components.HudChest;
import View.Components.HudMain;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;
import sun.text.resources.ext.BreakIteratorResources_th;

public class InformationBar {

    private Label labelPlayerGaming;

    private Label labelLife;

    private Label labelSecond;

    private Stage stage;

    private Player playerInTurn;

    private HudMain hud;



    private final GameController gameController = GameController.getInstance();

    public InformationBar(Viewport gamePort){
        this.stage = new Stage(gamePort);
        playerInTurn = gameController.getPlayerInTurn();
        hud  = new HudMain(this.stage, playerInTurn);
        labelPlayerGaming = hud.getPlayerInGame();
        labelLife = hud.getLife();
        labelSecond = hud.getSeconds();
        this.stage.getRoot().setX(36);
        this.stage.getRoot().setY(1496);

    }

    public Stage getStage(){
        return this.stage;
    }
}
