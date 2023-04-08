package View;

import Screens.PlayScreen;
import View.Components.ButtonComponent;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

public class DiceView extends Stage {

    private TextureRegion firstDiceDefault;

    private ButtonComponent rollDice;

    private Image attackImg;

    private TextureRegion secondDiceDefault;

    private Image specialAttackImg;

    private TextureRegion thirdDiceDefault;

    private Image tankImg;

    private String imgButton;

    private static final int SIZE = 130;

    public DiceView(PlayScreen screen, Viewport gamePort){
        super(gamePort);

        defineFirstDice(screen);
        defineSecondDice(screen);
        defineThirdDice(screen);

    }

    public void defineFirstDice(PlayScreen screen){
        firstDiceDefault = screen.getDiceAtlas().findRegion("attack");
        attackImg = new Image();
        attackImg.setDrawable(new TextureRegionDrawable(firstDiceDefault));
        attackImg.setSize(SIZE,SIZE);
        attackImg.setPosition(648, 440);
        this.addActor(attackImg);
    }


    public void defineSecondDice(PlayScreen screen){
        secondDiceDefault = screen.getDiceAtlas().findRegion("specialAttack");
        specialAttackImg = new Image();
        specialAttackImg.setDrawable(new TextureRegionDrawable(secondDiceDefault));
        specialAttackImg.setSize(SIZE,SIZE);
        specialAttackImg.setPosition(500, 440);
        this.addActor(specialAttackImg);
    }


    public void defineThirdDice(PlayScreen screen){
        thirdDiceDefault = screen.getDiceAtlas().findRegion("tank");
        tankImg = new Image();
        tankImg.setDrawable(new TextureRegionDrawable(thirdDiceDefault));
        tankImg.setSize(SIZE,SIZE);
        tankImg.setPosition(568, 270);
        this.addActor(tankImg);
    }


    private void defineButton(String img){
        rollDice = new ButtonComponent(this, img, 50, 50, 100, 100, new InputListener() {
            //poner la funcion de rodar;
        });
    }




}
