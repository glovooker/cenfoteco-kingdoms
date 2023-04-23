package View;

import BL.GameController;
import Model.Player;
import View.Components.ButtonComponent;
import View.Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class DiceView extends Stage {

    private TextureRegion firstDiceDefault;

    private ButtonComponent rollDice;

    private Image attackImg;

    private TextureRegion secondDiceDefault;

    private Image specialAttackImg;

    private TextureRegion thirdDiceDefault;

    private Image tankImg;

    private String imgButton= "rollButton.png";

    private static final int SIZE = 90;

    private int infanteria = 0;
    private int artilleria = 0;
    private int tanque = 0;
    private int infanteria2 = 0;
    private int artilleria2 = 0;
    private int tanque2 = 0;
    private int attack = 0;
    private int sattack = 0;
    private int move = 0;

    private PlayScreen playScreen;

    private final GameController gameController = GameController.getInstance();

    public DiceView(PlayScreen screen, Viewport gamePort){
        super(gamePort);
        playScreen = screen;
        defineButton(imgButton);
        defineFirstDice(playScreen);
        defineSecondDice(playScreen);
        defineThirdDice(playScreen);

    }

    public boolean defineFirstDice(PlayScreen screen){

        if (infanteria != 0 || artilleria != 0 || tanque != 0) {
           if (infanteria == 1) {
                firstDiceDefault = screen.getDiceAtlas().findRegion("infantry");
            } else if (artilleria == 1) {
                firstDiceDefault = screen.getDiceAtlas().findRegion("artillery");
            } else if (tanque == 1) {
                firstDiceDefault = screen.getDiceAtlas().findRegion("tank");
            }
        } else {
            firstDiceDefault = screen.getDiceAtlas().findRegion("tank");
        }

        attackImg = new Image();
        attackImg.setDrawable(new TextureRegionDrawable(firstDiceDefault));
        attackImg.setSize(SIZE,SIZE);
        attackImg.setPosition(650, 160);
        this.addActor(attackImg);

        return true;
    }


    public void defineSecondDice(PlayScreen screen){

        if (infanteria != 0 || artilleria != 0 || tanque != 0) {
            if (infanteria2 == 1) {
                secondDiceDefault = screen.getDiceAtlas().findRegion("infantry");
            } else if (artilleria2 == 1) {
                secondDiceDefault = screen.getDiceAtlas().findRegion("artillery");
            } else if (tanque2 == 1) {
                secondDiceDefault = screen.getDiceAtlas().findRegion("tank");
            }
        } else {
            secondDiceDefault = screen.getDiceAtlas().findRegion("tank");
        }

        specialAttackImg = new Image();
        specialAttackImg.setDrawable(new TextureRegionDrawable(secondDiceDefault));
        specialAttackImg.setSize(SIZE,SIZE);
        specialAttackImg.setPosition(550, 160);
        this.addActor(specialAttackImg);
    }


    public void defineThirdDice(PlayScreen screen){
        if (attack != 0 || sattack != 0 || move != 0) {
            if (attack == 1) {
                thirdDiceDefault = screen.getDiceAtlas().findRegion("attack");
            } else if (sattack == 1) {
                thirdDiceDefault = screen.getDiceAtlas().findRegion("specialAttack");
            } else {
                switch (move) {
                    case 1:
                        thirdDiceDefault = screen.getDiceAtlas().findRegion("oneDice");
                        break;
                    case 2:
                        thirdDiceDefault = screen.getDiceAtlas().findRegion("twoDice");
                        break;
                    case 3:
                        thirdDiceDefault = screen.getDiceAtlas().findRegion("threeDice");
                        break;
                    case 4:
                        thirdDiceDefault = screen.getDiceAtlas().findRegion("fourDice");
                        break;
                    case 5:
                        thirdDiceDefault = screen.getDiceAtlas().findRegion("fiveDice");
                        break;
                    case 6:
                        thirdDiceDefault = screen.getDiceAtlas().findRegion("sixDice");
                        break;
                }
            }
        } else {
            thirdDiceDefault = screen.getDiceAtlas().findRegion("specialAttack");
        }

        tankImg = new Image();
        tankImg.setDrawable(new TextureRegionDrawable(thirdDiceDefault));
        tankImg.setSize(SIZE,SIZE);
        tankImg.setPosition(600, 50);
        this.addActor(tankImg);
    }


    private void defineButton(String img){
        rollDice = new ButtonComponent(this, img, 180, 180, 555, -80, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Player player = gameController.getGameState().getPlayerInTurn();
                if(player.isRollDice()){
                    rollDices(gameController.lanzarDados());
                    player.setRollDice(false);
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void rollDices(ArrayList<Integer> dados){
        // The result of the dice is the following by index:
        /*
            0:  amount of infantry dice
            1:  amount of gunner dice
            2:  amount of tank dice
            3:  amount of attack dice
            4:  amount of special attack dice
         */

        infanteria = 0;
        infanteria2 = 0;
        artilleria = 0;
        artilleria2 = 0;
        tanque = 0;
        tanque2 = 0;
        attack = 0;
        sattack = 0;
        move = 0;


        if(dados.get(0) == 2){
            infanteria = 1;
            infanteria2 = 1;
        } else if(dados.get(0) == 1){
            infanteria = 1;
        }

        if(dados.get(1) == 2){
            artilleria = 1;
            artilleria2 = 1;
        } else if (dados.get(1) == 1 && infanteria == 1) {
            artilleria2 = 1;
        } else if(dados.get(1) == 1 && infanteria2 == 1) {
            artilleria = 1;
        } else if (dados.get(1)==1) {
            artilleria = 1;
        }


        if(dados.get(2) == 2){
            tanque = 1;
            tanque2 = 1;
        } else if (dados.get(2) == 1 && (infanteria == 1 || artilleria == 1)) {
            tanque2 = 1;
        } else if (dados.get(2) == 1 && (infanteria2 == 1 || artilleria2 == 1)) {
            tanque = 1;
        }

        if(dados.get(3) == 1){
            attack = 1;
        } else if(dados.get(4) == 1){
            sattack = 1;
        } else if(dados.get(5) != 0){
            move = dados.get(5);
        }

        defineFirstDice(playScreen);
        defineSecondDice(playScreen);
        defineThirdDice(playScreen);

    }

}
