package View.Template;

import Enums.Direction;
import Model.Coordinate;
import Model.GameState;
import View.Actor.TileActor;
import View.BoardView;
import View.Components.ButtonComponent;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public abstract class Figure {
    private final GameState state = GameState.getStateInstance();

    private final List<ButtonComponent> buttonComponents = new ArrayList<>();

    protected Stage boardStage;

    public Figure(Stage boardStage) {
        this.boardStage = boardStage;
    }

    public final static int AMOUNT_BLOCKS = 5;

    protected final TileActor[][] board = BoardView.getMatriz();

    protected abstract List<Coordinate> filterInvalidCoordinates(List<Coordinate> coordinates, Direction direction);
    protected abstract boolean hasAnyCollision(Coordinate coordinate, Direction direction);
    protected abstract void paintingWayUp(Coordinate coordinate);
    protected abstract void paintingWayDown(Coordinate coordinate);

    private boolean isPaintingUp() {
        return getState().getPlayerInTurn().equals(getState().getPlayer1());
    }

    protected void createNextMoveButtons() {
        Direction direction = isPaintingUp() ? Direction.Up : Direction.Down;
        List<Coordinate> coordinates = filterInvalidCoordinates(this.state.getPlayerInTurn().getCoordinatesList(), direction);

        for (final Coordinate coordinate: coordinates) {
            ButtonComponent btnComp = new ButtonComponent(this.boardStage, "buttonNextMove.png", 50, 50, coordinate.getX() * 50,coordinate.getY() * 50, new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println(button);
                    if(isPaintingUp()){
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

    protected GameState getState() {
        return state;
    }

    public void creatingWay() {
        if(this.state.getPlayerInTurn().getCoordinatesList() == null) {
            this.state.getPlayerInTurn().initializeCoordinatesList();

            Coordinate initialCoordinate = this.state.getPlayerInTurn().getCastle().getCoordinates();
            if(isPaintingUp()){
                initialCoordinate.setY(initialCoordinate.getY() + 1);
                paintingWayUp(initialCoordinate);
            }else {
                initialCoordinate.setY(initialCoordinate.getY() - 1);
                paintingWayDown(initialCoordinate);
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

