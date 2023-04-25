package View;

import BL.GameController;
import BL.characters_abstract_fabric.abstract_product.Army;
import Model.GameState;
import View.Components.ButtonComponent;
import BL.template.abstract_figure.Figure;
import BL.template.concrete.*;

import View.Screens.PlayScreen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

public class FiguresView extends Stage {
    private final Figure figureSeven;
    private final Figure figureFirst;
    private final Figure figureSecond;
    private final Figure figureSix;

    private final Figure figureThird;

    private final Figure figureFour;

    private final Figure figureFive;

    private final GameState gameState = GameController.getInstance().getGameState();

    public FiguresView(Viewport viewport, Stage boardStage, PlayScreen playScreen) {
        super(viewport);
        defineFiguresButtons();
        this.figureSeven = new Figure7(playScreen, boardStage);
        this.figureFirst = new Figure1(playScreen, boardStage);
        this.figureSecond = new Figure2(playScreen, boardStage);
        this.figureSix = new Figure6(playScreen, boardStage);
        this.figureThird = new Figure3(playScreen, boardStage);
        this.figureFour = new Figure4(playScreen, boardStage);
        this.figureFive = new Figure5(playScreen, boardStage);
    }

    private void summon(Figure figure){
        List<Army> armyList = this.gameState.getBoard().getArmyList();

        if(gameState.isPrefaReady()){
            Army army = (armyList.get(armyList.size() - 1));
            figure.creatingWay(army);
        }
    }

    private void defineFiguresButtons(){
        new ButtonComponent(this, "figure1.png", 155, 200, 30, 860, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                summon(figureFirst);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        new ButtonComponent(this, "figure2.png", 100, 110, 55, 790, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                summon(figureSecond);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        new ButtonComponent(this, "figure3.png", 100, 110, 55, 630, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                summon(figureThird);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        new ButtonComponent(this, "figure4.png", 80, 130, 15, 450, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                summon(figureFour);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        new ButtonComponent(this, "figure5.png", 80, 130, 127, 450, new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                summon(figureFive);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        new ButtonComponent(this, "figure6.png", 100, 110, 60, 300, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                summon(figureSix);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        new ButtonComponent(this, "figure7.png", 160, 160, 25, 90, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                summon(figureSeven);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }
}
