package View;

import Screens.PlayScreen;
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

    private static final int HEIGHT = 150;
    private static final int WIDTH = 135;

    public ButtonsActionsView(PlayScreen screen, Viewport gamePort){
        super(gamePort);

        defineButtonAttack("AttackButton.png");
        defineButtonSpecialAttack("SpecialAttackButton.png");
        defineButtonTank("SummonTankButton.png");
        defineButtonArtillery("SummonArtilleryButton.png");
        defineButtonInfantery("SummonInfanteryButton.png");
        defineButtonEndTurn("EndTurnButton.png");
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
                System.out.println("Summon Artillery");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }
    public void defineButtonInfantery(String imgButton){
        buttonInfatery = new ButtonComponent(this, imgButton, WIDTH, HEIGHT, 145, 440, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Summon Infantery");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }

    public void defineButtonEndTurn(String imgButton){
        buttonEndTurn = new ButtonComponent(this, imgButton, WIDTH, HEIGHT,0, 320, new InputListener() {
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
                System.out.println("Summon Tank");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }

}
