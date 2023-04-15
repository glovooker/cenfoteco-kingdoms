package View;

import Model.Army;
import View.Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;

public class ArmyView extends Stage {

    private TextureRegion armyDefault;

    private Image armyImg;
    private Label movementsLabel;
    private Label hitPointsLabel;
    private Label attacksLabel;
    private Label defenseLabel;
    private Label categoryLabel;
    private Label specialAttackLabel;
    private Army army = new Army();

    private static final int SIZE = 130;

    public ArmyView(PlayScreen screen, Viewport gamePort){
        super(gamePort);


        defineLabels();
        defineArmy(screen);
    }

    private void defineLabels() {
        movementsLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        movementsLabel.setFontScale(2f);
        movementsLabel.setPosition(760, 35);
        this.addActor(movementsLabel);

        hitPointsLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        hitPointsLabel.setFontScale(2f);
        hitPointsLabel.setPosition(980, 35);
        this.addActor(hitPointsLabel);

        attacksLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        attacksLabel.setFontScale(2f);
        attacksLabel.setPosition(760, -10);
        this.addActor(attacksLabel);

        defenseLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        defenseLabel.setFontScale(2f);
        defenseLabel.setPosition(980, -10);
        this.addActor(defenseLabel);

        categoryLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        categoryLabel.setFontScale(2f);
        categoryLabel.setPosition(830, -55);
        this.addActor(categoryLabel);

        specialAttackLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        specialAttackLabel.setFontScale(2f);
        specialAttackLabel.setPosition(800, -100);
        this.addActor(specialAttackLabel);
    }

    public void defineArmy(PlayScreen screen){


        army.setMovements(5);
        army.setHitPoints(10);
        army.setAttacks(3);
        army.setDefense(2);
        army.setCategory("Infantry");
        army.setSpecialAttack("Charge");

        // Actualiza los valores de los labels con las estadísticas del ejército
        movementsLabel.setText("Movements: " + army.getMovements());
        hitPointsLabel.setText("Hit Points: " + army.getHitPoints());
        attacksLabel.setText("Attacks: " + army.getAttacks());
        defenseLabel.setText("Defense: " + army.getDefense());
        categoryLabel.setText("Category: " + army.getCategory());
        specialAttackLabel.setText("Special Attack: " + army.getSpecialAttack());


        armyDefault = screen.getArmyAtlas().findRegion("guardian");
        armyImg = new Image();
        armyImg.setDrawable(new TextureRegionDrawable(armyDefault));
        armyImg.setSize(SIZE,SIZE);
        armyImg.setPosition(600, -100);
        this.addActor(armyImg);
    }



}
