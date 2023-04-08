package BL.template.abstract_figure;

import Enums.Direction;
import Model.Coordinates;
import Model.GameState;
import View.Components.ButtonComponent;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;


public abstract class Figure {

    private GameState state = GameState.getStateInstance();

    private List<ButtonComponent> buttonComponents = new ArrayList<>();

    protected Stage boardStage;

    public Figure(Stage boardStage) {
        this.boardStage = boardStage;
    }

    public final static int AMOUNT_BLOCKS = 4;


    protected abstract void paintingWayUp(Coordinates coordinates);
    protected abstract void paintingWayDown(Coordinates coordinates);

    private boolean currentPlayer() {
        return getState().getPlayer().equals(getState().getPlayer1());
    }

    protected void createNextMoveButtons() {
       List<Coordinates> coordinates = this.state.getPlayer().getCoordinatesList();

        for (final Coordinates coordinate: coordinates) {
            System.out.println(coordinate);
            ButtonComponent btnComp = new ButtonComponent(this.boardStage, "buttonNextMove.png", 50, 50, coordinate.getX() * 50,coordinate.getY() * 50, new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println(button);
                    if(currentPlayer()){
                        paintingWayUp(coordinate);
                    }else {
                        paintingWayDown(coordinate);
                    }


                    cleanUpButtons();
                    return super.touchDown(event, x, y, pointer, button);
                }
            });

            this.buttonComponents.add(btnComp);
        }
    }

    public GameState getState() {
        return state;
    }

    public void creatingWay() {
        if(this.state.getPlayer().getCoordinatesList().isEmpty()) {
            Coordinates initialCoordinates = this.state.getPlayer().getCastle().getCoordinates();
            if(currentPlayer()){
                initialCoordinates.setY(initialCoordinates.getY() + 1);
                paintingWayUp(initialCoordinates);
            }else {
                initialCoordinates.setY(initialCoordinates.getY() - 1);
                paintingWayDown(initialCoordinates);
            }
        }
        else {
            this.createNextMoveButtons();
        }
    }

    protected abstract void calculatingNextMovement(int x, int y, Direction direction);

    private void cleanUpButtons() {
        for (ButtonComponent buttonComp: this.buttonComponents) {
            buttonComp.getButton().remove();
        }

        this.buttonComponents.clear();
    }
}

