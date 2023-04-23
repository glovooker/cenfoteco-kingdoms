package View;

import BL.GameController;
import BL.characters_abstract_fabric.abstract_product.Army;
import View.Screens.PlayScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ArmyView extends Stage {

    private Image currentArmyImg;

    private TextureRegion armyDefault;

    private Image armyImg;
    private Label movementsLabel;
    private Label hitPointsLabel;
    private Label attacksLabel;
    private Label defenseLabel;
    private Label categoryLabel;
    private Label characterLabel;
    private Label specialAttackLabel;

    private final GameController gameController = GameController.getInstance();
    private static final int SIZE = 130;
    private PlayScreen playScreen;

    public ArmyView(PlayScreen screen, Viewport gamePort){
        super(gamePort);
        playScreen = screen;
        defineLabels();
        defineArmy(null);
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
        categoryLabel.setPosition(760, -55);
        this.addActor(categoryLabel);

        characterLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        characterLabel.setFontScale(2f);
        characterLabel.setPosition(760, -100);
        this.addActor(characterLabel);

        specialAttackLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        specialAttackLabel.setFontScale(2f);
        specialAttackLabel.setPosition(800, -100);
        this.addActor(specialAttackLabel);
    }

    public void defineArmy(Army armyToInvoque){

        if (currentArmyImg != null) {
            currentArmyImg.remove();
        }
        // Actualiza los valores de los labels con las estadísticas del ejército
        if(armyToInvoque == null){
            movementsLabel.setText("Movements: ");
            hitPointsLabel.setText("Hit Points: ");
            attacksLabel.setText("Attacks: ");
            defenseLabel.setText("Defense: ");
            categoryLabel.setText("Category: ");
            characterLabel.setText("Character: ");
            //specialAttackLabel.setText("Special Attack: " + army.getSpecialAttack());
            currentArmyImg = armyImg;

        } else {

            movementsLabel.setText("Movements: " + armyToInvoque.getMovements());
            hitPointsLabel.setText("Life: " + armyToInvoque.getLife());
            attacksLabel.setText("Attacks: " + armyToInvoque.getAttack());
            defenseLabel.setText("Defense: " + armyToInvoque.getDefense());
            categoryLabel.setText("Category: " + armyToInvoque.getCharacterType());
            characterLabel.setText("Character: " + armyToInvoque.getCharacterClass());
            //specialAttackLabel.setText("Special Attack: " + army.getSpecialAttack());

            switch (armyToInvoque.getCharacterClass()) {
                case "ratallero":
                    armyDefault = playScreen.getArmyAtlas().findRegion("knight-iddle");
                    armyImg = new Image();
                    armyImg.setDrawable(new TextureRegionDrawable(armyDefault));
                    armyImg.setSize(SIZE, SIZE);
                    armyImg.setPosition(600, -100);
                    this.addActor(armyImg);
                    currentArmyImg = armyImg;
                    break;
                case "orco":
                    armyDefault = playScreen.getArmyAtlas().findRegion("orcus-iddle");
                    armyImg = new Image();
                    armyImg.setDrawable(new TextureRegionDrawable(armyDefault));
                    armyImg.setSize(SIZE, SIZE);
                    armyImg.setPosition(600, -100);
                    this.addActor(armyImg);
                    currentArmyImg = armyImg;

                    break;
                case "chaman":
                    armyDefault = playScreen.getArmyAtlas().findRegion("shaman-idle");
                    armyImg = new Image();
                    armyImg.setDrawable(new TextureRegionDrawable(armyDefault));
                    armyImg.setSize(SIZE, SIZE);
                    armyImg.setPosition(600, -100);
                    this.addActor(armyImg);
                    currentArmyImg = armyImg;

                    break;
                case "escudero":
                    armyDefault = playScreen.getArmyAtlas().findRegion("squire-idle");
                    armyImg = new Image();
                    armyImg.setDrawable(new TextureRegionDrawable(armyDefault));
                    armyImg.setSize(SIZE, SIZE);
                    armyImg.setPosition(600, -100);
                    this.addActor(armyImg);
                    currentArmyImg = armyImg;

                    break;
                case "archero":
                    armyDefault = playScreen.getArmyAtlas().findRegion("archer");
                    armyImg = new Image();
                    armyImg.setDrawable(new TextureRegionDrawable(armyDefault));
                    armyImg.setSize(SIZE, SIZE);
                    armyImg.setPosition(600, -100);
                    this.addActor(armyImg);
                    currentArmyImg = armyImg;

                    break;
                case "daemon":
                    armyDefault = playScreen.getArmyAtlas().findRegion("daemon");
                    armyImg = new Image();
                    armyImg.setDrawable(new TextureRegionDrawable(armyDefault));
                    armyImg.setSize(SIZE, SIZE);
                    armyImg.setPosition(600, -100);
                    this.addActor(armyImg);
                    currentArmyImg = armyImg;
                    break;
                case "bruja":
                    armyDefault = playScreen.getArmyAtlas().findRegion("witch-iddle");
                    armyImg = new Image();
                    armyImg.setDrawable(new TextureRegionDrawable(armyDefault));
                    armyImg.setSize(SIZE, SIZE);
                    armyImg.setPosition(600, -100);
                    this.addActor(armyImg);
                    currentArmyImg = armyImg;
                    break;
//                case "mago":
//                    armyDefault = playScreen.getArmyAtlas().findRegion("");
//                    armyImg = new Image();
//                    armyImg.setDrawable(new TextureRegionDrawable(armyDefault));
//                    armyImg.setSize(SIZE, SIZE);
//                    armyImg.setPosition(600, -100);
//                    this.addActor(armyImg);
                      //currentArmyImg = armyImg;

//                    break;
                case "dragon":
                    armyDefault = playScreen.getArmyAtlas().findRegion("dragon");
                    armyImg = new Image();
                    armyImg.setDrawable(new TextureRegionDrawable(armyDefault));
                    armyImg.setSize(SIZE, SIZE);
                    armyImg.setPosition(600, -100);
                    this.addActor(armyImg);
                    currentArmyImg = armyImg;
                    break;
                case "golem":
                    armyDefault = playScreen.getArmyAtlas().findRegion("golem");
                    armyImg = new Image();
                    armyImg.setDrawable(new TextureRegionDrawable(armyDefault));
                    armyImg.setSize(SIZE, SIZE);
                    armyImg.setPosition(600, -100);
                    this.addActor(armyImg);
                    currentArmyImg = armyImg;
                    break;
                case "guardian":
                    armyDefault = playScreen.getArmyAtlas().findRegion("guardian");
                    armyImg = new Image();
                    armyImg.setDrawable(new TextureRegionDrawable(armyDefault));
                    armyImg.setSize(SIZE, SIZE);
                    armyImg.setPosition(600, -100);
                    this.addActor(armyImg);
                    currentArmyImg = armyImg;
                    break;
                case "kamikaze":
                    armyDefault = playScreen.getArmyAtlas().findRegion("kamikaze-iddle");
                    armyImg = new Image();
                    armyImg.setDrawable(new TextureRegionDrawable(armyDefault));
                    armyImg.setSize(SIZE, SIZE);
                    armyImg.setPosition(600, -100);
                    this.addActor(armyImg);
                    currentArmyImg = armyImg;
                    break;
            }
        }
    }
}
