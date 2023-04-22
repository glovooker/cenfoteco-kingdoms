package BL.template.concrete;

import BL.template.abstract_figure.Figure;
import Enums.Direction;
import BL.prototype.TileActor;
import Model.Coordinate;
import View.BoardView;
import View.Screens.PlayScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class Figure7 extends Figure {
    public Figure7(PlayScreen playScreen, Stage boardStage) {
        super(playScreen, boardStage);
    }

    @Override
    protected List<Coordinate> filterInvalidCoordinates(List<Coordinate> coordinates, Direction direction) {
        List<Coordinate> result = new ArrayList<>();

        for(Coordinate coordinate : coordinates) {
            int futureLastY = direction == Direction.Up
                    ? coordinate.getY() + AMOUNT_BLOCKS
                    : coordinate.getY() - AMOUNT_BLOCKS;

            switch (direction) {
                case Up: {
                    if(futureLastY < 21 && !hasAnyCollision(coordinate, direction)) {
                        result.add(coordinate);
                    }

                    break;
                }
                case Down:
                    if(futureLastY > 0 && !hasAnyCollision(coordinate, direction)) {
                        result.add(coordinate);
                    }

                    break;
            }
        }

        return result;
    }

    @Override
    protected boolean hasAnyCollision(Coordinate coordinate, Direction direction) {
        int cont = 0;
        int futureY = coordinate.getY();

        while(cont < AMOUNT_BLOCKS) {
            TileActor tile = BoardView.getTileByPosition(coordinate.getX(), futureY);

            if(tile.isBusy())
            {
                return true;
            }

            futureY = direction == Direction.Up
                    ? futureY + 1
                    : futureY - 1;
            cont++;
        }

        return false;
    }

    @Override
    protected void paintingWayUp(Coordinate coordinate) {
        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;

        while(!(blockNumberToPaint == 0 )){
            TileActor tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());
            tile.setRegionTexture(Figure.tileCastlePlayer1);
            tile.setBusy(true);
            coordinate.setY(coordinate.getY() + 1);
            saveMiddleCoordinates(blockNumberToPaint, coordinate);
            blockNumberToPaint --;
        }

        calculatingNextMovement(coordinate.getX(), coordinate.getY(), Direction.Up);
    }

    @Override
    protected void paintingWayDown(Coordinate coordinate) {
        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;

        while(!(blockNumberToPaint == 0 )){
            TileActor tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());
            tile.setRegionTexture(Figure.tileCastlePlayer2);
            tile.setBusy(true);
            coordinate.setY(coordinate.getY() - 1);
            saveMiddleCoordinates(blockNumberToPaint, coordinate);
            blockNumberToPaint --;
        }

        calculatingNextMovement(coordinate.getX(), coordinate.getY(), Direction.Down);
    }

    @Override
    protected void calculatingNextMovement(int x, int y, Direction direction) {
        getState().getPlayerInTurn().getCoordinatesList().clear();

        int newY = direction == Direction.Down ? y + 1 : y -1;
        if(x > 0){
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x-1, newY));
        }

        if(x < 19){

            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x+1, newY));
        }

        if(direction == Direction.Up && y < 19){
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x, y));
        }

        if(direction == Direction.Down && y > 0){
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x, y));
        }
    }

    @Override
    protected void saveMiddleCoordinates(int tileNumber, Coordinate coordinate) {
        if(tileNumber == 4){
            this.setMiddleCoordinates(new Coordinate(coordinate.getX(), coordinate.getY()));
        }
    }
}
