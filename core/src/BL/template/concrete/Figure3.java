package BL.template.concrete;

import BL.prototype.TileActor;
import BL.template.abstract_figure.Figure;
import Enums.Direction;
import Model.Coordinate;
import View.BoardView;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class Figure3 extends Figure {
    public Figure3(Stage boardStage) {
        super(boardStage);
    }

    @Override
    protected List<Coordinate> filterInvalidCoordinates(List<Coordinate> coordinates, Direction direction) {
        List<Coordinate> result = new ArrayList<>();

        for (Coordinate coordinate : coordinates){
            if(!hasAnyCollision(coordinate, direction)){
                result.add(coordinate);
            }
        }
        return result;
    }

    @Override
    protected boolean hasAnyCollision(Coordinate coordinate, Direction direction) {
        int cont = 0;
        int newY = coordinate.getY();
        TileActor tile;
        boolean minusX = false;
        boolean addY = true;
        boolean plusX = false;

        int x = coordinate.getX();

        while (cont < Figure.AMOUNT_BLOCKS){
            tile = BoardView.getTileByPosition(x, newY);


            if(tile.isBusy() || coordinate.getX() == 0 || coordinate.getX() >= 19 || coordinate.getY() < 3 || coordinate.getY() > 18){
                return true;
            }

            if(addY){
                newY = direction == Direction.Up ? newY + 1  : newY - 1;
            }


            if(cont == Figure.AMOUNT_BLOCKS - 4){
                minusX = true;
                addY = false;
            }

            if(plusX){
                x = x + 1;
            }

            if(minusX){
                x = x - 1;
                minusX = false;
                plusX = true;
            }

            cont++;

        }
        return false;
    }

    @Override
    protected void paintingWayUp(Coordinate coordinate) {
        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;
        TileActor tile;
        boolean addY = true;
        boolean plusX = false;

        while(blockNumberToPaint > 0){

            if(blockNumberToPaint == Figure.AMOUNT_BLOCKS - 2){
                addY = false;
                plusX = true;
                coordinate.setX(coordinate.getX() - 1);
            }

            tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());;
            tile.setRegionTexture("BlackWay");
            tile.setBusy(true);

            if(plusX){
                coordinate.setX(coordinate.getX() + 1);
            }

            if(addY){
                coordinate.setY(coordinate.getY() + 1);
            }

            blockNumberToPaint--;
        }

        calculatingNextMovement(coordinate.getX(), coordinate.getY(), Direction.Up);
    }


    @Override
    protected void paintingWayDown(Coordinate coordinate) {
        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;
        TileActor tile;
        boolean minusY = true;
        boolean minusX = false;

        while(blockNumberToPaint > 0){

            if(blockNumberToPaint == Figure.AMOUNT_BLOCKS - 2){
                minusY = false;
                minusX = true;
                coordinate.setX(coordinate.getX() + 1);
            }
            tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());;
            tile.setRegionTexture("WhiteWay");
            tile.setBusy(true);

            if(minusX){
                coordinate.setX(coordinate.getX() - 1);
            }

            if(minusY){
                coordinate.setY(coordinate.getY() - 1);
            }


            blockNumberToPaint--;
        }


        calculatingNextMovement(coordinate.getX(), coordinate.getY(), Direction.Down);
    }

    @Override
    protected void calculatingNextMovement(int x, int y, Direction direction) {
        getState().getPlayerInTurn().getCoordinatesList().clear();
        int oppositeX = direction == Direction.Up ? x - 4 : x + 4;
        int middleX = direction == Direction.Up ? x - 2 : x + 2;
        int newY = direction == Direction.Up ? y + 1 : y -1;

        if(x >= 0 && x <= 19 && !BoardView.getTileByPosition(x, y).isBusy()) {
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x, y));
        }

        if(oppositeX >= 0 && oppositeX <= 19 && !BoardView.getTileByPosition(oppositeX, y).isBusy()) {
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(oppositeX, y));
        }

        if(newY > 0 && newY <= 20 && middleX >= 0 && middleX <= 19 && !BoardView.getTileByPosition(middleX, newY).isBusy()) {
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(middleX, newY));
        }
    }
}
