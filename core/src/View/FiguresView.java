package View;

import Model.GameState;
import View.Components.ButtonComponent;
import BL.template.abstract_figure.Figure;
import BL.template.concrete.*;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;


public class FiguresView extends Stage {
    private ButtonComponent figure1;


    private ButtonComponent figure2;

    private ButtonComponent figure3;

    private ButtonComponent figure4;


    private ButtonComponent figure5;

    private ButtonComponent figure6;


    private ButtonComponent figure7;


    private ButtonComponent figure8;

    private Figure figureSeven;
    private Figure figureFirst;
    private Figure figureSecond;
    private Figure figureSix;

    private Figure figureThird;

    private Figure figureFour;
    private Figure figureFive;

    private GameState gameState = GameState.getStateInstance();

    public FiguresView(Viewport viewport, Stage boardStage) {
        super(viewport);
        defineFiguresButtons();
        this.figureSeven = new Figure7(boardStage);
        this.figureFirst = new Figure1(boardStage);
        this.figureSecond = new Figure2(boardStage);
        this.figureSix = new Figure6(boardStage);
        this.figureThird = new Figure3(boardStage);
        this.figureFour = new Figure4(boardStage);
        this.figureFive = new Figure5(boardStage);

    }

    private void summon(Figure figure){
        if(gameState.isPrefaReady()){
            if( figure.creatingWay()){
                this.gameState.setPrefaReady(false);
            }

        }
    }



    private void defineFiguresButtons(){
        figure1 = new ButtonComponent(this, "figure1.png", 165, 200, 25 ,890, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                summon(figureFirst);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        figure2 = new ButtonComponent(this, "figure2.png", 110, 110, 45 ,840, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                summon(figureSecond);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        figure3 = new ButtonComponent(this, "figure3.png", 110, 110, 45 ,700, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                summon(figureThird);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        figure4 = new ButtonComponent(this, "figure4.png", 150, 150, -15 ,530, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                summon(figureFour);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        figure5 = new ButtonComponent(this, "figure5.png", 150, 150, 75 ,530, new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                summon(figureFive);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        figure6 = new ButtonComponent(this, "figure6.png", 110, 110, 45 ,400, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                summon(figureSix);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        figure7 = new ButtonComponent(this, "figure7.png", 180, 180, 10 ,200, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                summon(figureSeven);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        figure8 = new ButtonComponent(this, "figure8.png", 105, 105, 50 ,80, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("hola8");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }







}
