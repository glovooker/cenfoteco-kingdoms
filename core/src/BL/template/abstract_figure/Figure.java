package BL.template.abstract_figure;

import BL.GameController;
import BL.characters_abstract_fabric.abstract_product.Army;
import BL.prototype.TileActor;
import Enums.Direction;
import Model.Coordinate;
import Model.GameState;
import View.BoardView;
import View.Components.ButtonComponent;
import View.Screens.PlayScreen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public abstract class Figure {
    private Coordinate middleCoordinates;

    protected final static String tileCastlePlayer1 = "TileCastle";

    protected final static String tileCastlePlayer2 = "TileCastle2";

    private final GameState state = GameController.getInstance().getGameState();

    private final List<ButtonComponent> buttonComponents = new ArrayList<>();

    protected Stage boardStage;

    protected PlayScreen playScreen;

    public final static int AMOUNT_BLOCKS = 5;

    public final static int ONE_TILE = 1;

    public final static int SECOND_TILE = 2;

    protected final TileActor[][] board = BoardView.getMatriz();

    protected final BoardView boardView;

    public Figure(PlayScreen playScreen, Stage boardStage) {
        this.boardStage = boardStage;
        this.playScreen = playScreen;
        this.boardView = playScreen.getBoardView();
    }

    public void setMiddleCoordinates(Coordinate middleCoordinates) {
        this.middleCoordinates = middleCoordinates;
    }

    protected abstract List<Coordinate> filterInvalidCoordinates(List<Coordinate> coordinates, Direction direction);
    protected abstract boolean hasAnyCollision(Coordinate coordinate, Direction direction);
    protected abstract void paintingWayUp(Coordinate coordinate);
    protected abstract void paintingWayDown(Coordinate coordinate);

    private boolean isPaintingUp() {
        return getState().getPlayerInTurn().equals(getState().getPlayer1());
    }

    protected boolean createNextMoveButtons(final Army army) {
        Direction direction = isPaintingUp() ? Direction.Up : Direction.Down;
        List<Coordinate> coordinates = filterInvalidCoordinates(this.state.getPlayerInTurn().getCoordinatesList(), direction);

        if(coordinates.isEmpty()){
            return false;
        }

        for (final Coordinate coordinate: coordinates) {
            ButtonComponent btnComp = new ButtonComponent(this.boardStage, "buttonNextMove.png", 50, 50, coordinate.getX() * 50,coordinate.getY() * 50, new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if(isPaintingUp()){
                        paintingWayUp(coordinate);
                    }else {
                        paintingWayDown(coordinate);
                    }

                    positionArmyOnFigure(army);
                    cleanUpButtons();
                    return super.touchDown(event, x, y, pointer, button);
                }
            });

            this.buttonComponents.add(btnComp);
        }

        return true;
    }

    protected GameState getState() {
        return state;
    }

    public void creatingWay(Army army) {
        if(this.state.getPlayerInTurn().getCoordinatesList() == null) {
            this.state.getPlayerInTurn().initializeCoordinatesList();

            Coordinate initialCoordinate = this.state.getPlayerInTurn().getCastle().getCoordinates().clone();
            if(isPaintingUp()){
                initialCoordinate.setY(initialCoordinate.getY() + 1);
                paintingWayUp(initialCoordinate);
            }else {
                initialCoordinate.setY(initialCoordinate.getY() - 1);
                paintingWayDown(initialCoordinate);
            }

            positionArmyOnFigure(army);
        }
        else {
            this.createNextMoveButtons(army);
        }
    }

    protected abstract void calculatingNextMovement(int x, int y, Direction direction);

    protected abstract void saveMiddleCoordinates(int tileNumber, Coordinate coordinate);

    public Coordinate getMiddleCoordinates() {
        return middleCoordinates;
    }

    private void positionArmyOnFigure(Army army) {
        army.setPosition(middleCoordinates);
        state.setPrefaReady(false);
        this.playScreen.createArmyAsButtonByType(army);
        this.playScreen.getHudChest().updateLabels(state);
    }

    private void cleanUpButtons() {
        for (ButtonComponent buttonComp: this.buttonComponents) {
            buttonComp.getButton().remove();
        }

        this.buttonComponents.clear();
    }

    protected void addInvadedByArmyToTile(){
        TileActor tileBusyByArmy = BoardView.getTileByPosition(this.getMiddleCoordinates().getX(), this.getMiddleCoordinates().getY());
        tileBusyByArmy.setInvaded(true);
    }
}

