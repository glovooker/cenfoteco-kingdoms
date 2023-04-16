package View;

import View.Components.ButtonComponent;
import View.Template.Concrete.*;
import View.Template.Figure;
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

    private void defineFiguresButtons(){
        figure1 = new ButtonComponent(this, "figure1.png", 160, 160, 40,900, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                figureFirst.creatingWay();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        figure2 = new ButtonComponent(this, "figure2.png", 120, 120, 60 ,810, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                figureSecond.creatingWay();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        figure3 = new ButtonComponent(this, "figure3.png", 200, 200, 20 ,650, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                figureThird.creatingWay();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        figure4 = new ButtonComponent(this, "figure4.png", 180, 180, -35 ,500, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                figureFour.creatingWay();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        figure5 = new ButtonComponent(this, "figure5.png", 180, 180, 80 ,500, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                figureFive.creatingWay();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        figure6 = new ButtonComponent(this, "figure6.png", 200, 200, 20 ,350, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                figureSix.creatingWay();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        figure7 = new ButtonComponent(this, "figure7.png", 180, 180, 30 ,220, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                figureSeven.creatingWay();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        figure8 = new ButtonComponent(this, "figure8.png", 180, 180, 20 ,60, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("hola8");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }




}
