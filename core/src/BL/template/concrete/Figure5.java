package BL.template.concrete;

import BL.prototype.TileActor;
import BL.template.abstract_figure.Figure;
import Enums.Direction;
import Model.Coordinate;
import View.BoardView;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class Figure5 extends Figure {


    public Figure5(Stage boardStage) {
        super(boardStage);
    }

    @Override
    protected List<Coordinate> filterInvalidCoordinates(List<Coordinate> coordinates, Direction direction) {
        List<Coordinate> result = new ArrayList<>();

        for (Coordinate coordinate: coordinates){
            if(!hasAnyCollision(coordinate, direction)){
                result.add(coordinate);
            }
        }

        return result;
    }

    @Override
    protected boolean hasAnyCollision(Coordinate coordinate, Direction direction) {
        if(!validateEdges(coordinate, direction)) {
            return true;
        }

        TileActor nextTile = BoardView.getTileByPosition(coordinate.getX() + 1, coordinate.getY());
        int currentX = coordinate.getX();

        if(nextTile.isBusy()) {
            currentX = coordinate.getX() - 1;
        }

        int cont = 0;
        currentX++;
        int currentY = coordinate.getY();

        while(cont < Figure.AMOUNT_BLOCKS) {
            if(currentX < 0 || BoardView.getTileByPosition(currentX, currentY).isBusy()) {
                return true;
            }

            if(cont == 0) {
                currentX--;
            }
            else {
                currentY = direction == Direction.Up ? currentY + 1 : currentY - 1;
            }

            cont++;
        }

        return false;
    }

    private boolean validateEdges(Coordinate coordinate, Direction direction) {
        int futureX = coordinate.getX() + 1;
        int futureY = direction == Direction.Up ? coordinate.getY() + 3 : coordinate.getY() - 3;

        return futureX < BoardView.getRowsInBoard() && (futureY >= 1 && futureY <= 20);
    }

    @Override
    protected void paintingWayUp(Coordinate coordinate) {
        int blockNumber = Figure.AMOUNT_BLOCKS;
        TileActor tile;
        checkNextTile(coordinate);

        coordinate.setX(coordinate.getX() + 1);
        boolean minusX = true;
        boolean plusY = false;


        while (blockNumber >= 0){
            tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());
            tile.setRegionTexture("BlackWay");
            tile.setBusy(true);

            if(blockNumber == Figure.AMOUNT_BLOCKS - 2){
                plusY = true;
            }

            if(minusX){
                coordinate.setX(coordinate.getX() - 1);
                minusX = false;
            }

            if(plusY){
                coordinate.setY(coordinate.getY() + 1);
            }

            blockNumber--;

        }

        calculatingNextMovement(coordinate.getX(), coordinate.getY(), Direction.Up);
    }

    @Override
    protected void paintingWayDown(Coordinate coordinate) {
        int blockNumber = Figure.AMOUNT_BLOCKS;
        TileActor tile;
        checkNextTile(coordinate);

        coordinate.setX(coordinate.getX() + 1);
        boolean minusX = true;
        boolean plusY = false;


        while (blockNumber >= 0){
            tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());
            tile.setRegionTexture("WhiteWay");
            tile.setBusy(true);

            if(blockNumber == Figure.AMOUNT_BLOCKS - 2){
                plusY = true;
            }

            if(minusX){
                coordinate.setX(coordinate.getX() - 1);
                minusX = false;
            }

            if(plusY){
                coordinate.setY(coordinate.getY() - 1);
            }

            blockNumber--;
        }

        calculatingNextMovement(coordinate.getX(), coordinate.getY(), Direction.Down);
    }

    private void checkNextTile(Coordinate coordinate){
        if(coordinate.getX() + 1 < 19){
            TileActor nextTile = BoardView.getTileByPosition(coordinate.getX() + 1, coordinate.getY());

            if(nextTile.isBusy()) {
                coordinate.setX(coordinate.getX() - 1);
            }
        }
    }

    @Override
    protected void calculatingNextMovement(int x, int y, Direction direction) {
        getState().getPlayerInTurn().getCoordinatesList().clear();
        int futureLeftX = x - 1;
        int futureRightX = x + 1;
        int yForXAxis = direction == Direction.Up ? y-1 : y+1;

        if(futureLeftX >= 0 && futureLeftX <= 19 && !BoardView.getTileByPosition(futureLeftX, yForXAxis).isBusy()) {
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(futureLeftX, yForXAxis));
        }

        if(futureRightX >= 0 && futureRightX <= 19 && !BoardView.getTileByPosition(futureRightX, yForXAxis).isBusy()) {
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(futureRightX, yForXAxis));
        }

        if(y >= 1 && y <= 20 && !BoardView.getTileByPosition(x, y).isBusy()) {
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x, y));
        }


    }
}
