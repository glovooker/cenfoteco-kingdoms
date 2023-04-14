package View;

import View.Components.ButtonComponent;
import View.Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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

    private String imgButton= "C:\\Users\\josih\\Desktop\\pixil-frame-0.png";

    private static final int SIZE = 90;

    public DiceView(PlayScreen screen, Viewport gamePort){
        super(gamePort);

        defineFirstDice(screen);
        defineSecondDice(screen);
        defineThirdDice(screen);

    }

    public void defineFirstDice(PlayScreen screen){

        firstDiceDefault = screen.getDiceAtlas().findRegion("artillery");
        attackImg = new Image();
        attackImg.setDrawable(new TextureRegionDrawable(firstDiceDefault));
        attackImg.setSize(SIZE,SIZE);
        attackImg.setPosition(640, 170);
        this.addActor(attackImg);
    }


    public void defineSecondDice(PlayScreen screen){
        secondDiceDefault = screen.getDiceAtlas().findRegion("tank");
        specialAttackImg = new Image();
        specialAttackImg.setDrawable(new TextureRegionDrawable(secondDiceDefault));
        specialAttackImg.setSize(SIZE,SIZE);
        specialAttackImg.setPosition(540, 170);
        this.addActor(specialAttackImg);
    }


    public void defineThirdDice(PlayScreen screen){
        thirdDiceDefault = screen.getDiceAtlas().findRegion("specialAttack");
        tankImg = new Image();
        tankImg.setDrawable(new TextureRegionDrawable(thirdDiceDefault));
        tankImg.setSize(SIZE,SIZE);
        tankImg.setPosition(590, 60);
        this.addActor(tankImg);
    }


    private void defineButton(String img){
        rollDice = new ButtonComponent(this, img, 180, 180, 550, -70, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("ROLL DICE");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }




}
