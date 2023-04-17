package View;

import BL.GameController;
import BL.characters_abstract_fabric.abstract_product.Army;
import View.Screens.PlayScreen;
import View.Components.ButtonComponent;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ButtonsActionsView extends Stage {

    private ButtonComponent buttonAttack;
    private ButtonComponent buttonSpecialAttack;
    private ButtonComponent buttonTank;
    private ButtonComponent buttonArtillery;
    private ButtonComponent buttonInfatery;
    private ButtonComponent buttonEndTurn;
    private ButtonComponent buttonLeft;
    private ButtonComponent buttonRight;
    private ButtonComponent buttonUp;
    private ButtonComponent buttonDown;
    private ButtonComponent buttonMovements;

    private static final int HEIGHT = 100;
    private static final int WIDTH = 140;
    private PlayScreen playScreen;

    private final GameController gameController = GameController.getInstance();

    public ButtonsActionsView(PlayScreen screen, Viewport gamePort){
        super(gamePort);

        playScreen = screen;

        defineButtonAttack("AttackButton.png");
        defineButtonSpecialAttack("SpecialAttackButton.png");
        defineButtonTank("SummonTankButton.png");
        defineButtonArtillery("SummonArtilleryButton.png");
        defineButtonInfantery("SummonInfanteryButton.png");
        defineButtonEndTurn("EndTurnButton.png");
        defineButtonMovements("MovementsButton.png");
        defineButtonLeft("Left.png");
        defineButtonRight("Right.png");
        defineButtonUp("Up.png");
        defineButtonDown("Down.png");
    }

    public void defineButtonAttack(String imgButton){
        buttonAttack = new ButtonComponent(this,imgButton , WIDTH, HEIGHT, 0, 440, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Attack");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }

    public void defineButtonSpecialAttack(String imgButton){
        buttonSpecialAttack = new ButtonComponent(this, imgButton, WIDTH, HEIGHT, 0, 380, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Special Attack");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void defineButtonArtillery(String imgButton){
        buttonArtillery = new ButtonComponent(this, imgButton, WIDTH, HEIGHT, 145, 320, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                playScreen.showArmyData(gameController.invocarArtilleria());
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }
    public void defineButtonInfantery(String imgButton){
        buttonInfatery = new ButtonComponent(this, imgButton, WIDTH, HEIGHT, 145, 440, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                playScreen.showArmyData(gameController.invocarInfanteria());
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }

    public void defineButtonMovements(String imgButton){
        buttonMovements = new ButtonComponent(this, imgButton, WIDTH, HEIGHT,0, 320, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Movements");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void defineButtonEndTurn(String imgButton){
        buttonEndTurn = new ButtonComponent(this, imgButton, WIDTH, HEIGHT,70, 260, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("End Turn");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
    public void defineButtonTank(String imgButton){
        buttonTank = new ButtonComponent(this, imgButton, WIDTH, HEIGHT, 145, 380, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                playScreen.showArmyData(gameController.invocarTanque());
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }

    public void defineButtonLeft(String imgButton){
        buttonLeft = new ButtonComponent(this, imgButton, WIDTH, HEIGHT, 0,80, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Left");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }
    public void defineButtonRight(String imgButton){
        buttonRight = new ButtonComponent(this, imgButton, WIDTH, HEIGHT, 140, 80, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Right");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }
    public void defineButtonUp(String imgButton){
        buttonUp = new ButtonComponent(this, imgButton, WIDTH, HEIGHT, 70, 165, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Up");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }
    public void defineButtonDown(String imgButton){
        buttonDown = new ButtonComponent(this, imgButton, WIDTH, HEIGHT, 70, 0, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Down");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }

}
