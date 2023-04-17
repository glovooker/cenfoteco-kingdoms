package BL.template.concrete;

import BL.prototype.TileActor;
import BL.template.abstract_figure.Figure;
import Enums.Direction;
import Model.Coordinate;
import View.BoardView;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class Figure4 extends Figure {



    public Figure4(Stage boardStage) {
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
        if(validateEdges(coordinate,direction)){
            return true;
        }

        int futureX = coordinate.getX();
        int futureY = coordinate.getY();
        int cont = 0;
        boolean minusX = false;
        boolean plusY = true;

        while(cont < Figure.AMOUNT_BLOCKS){
             if(BoardView.getTileByPosition(futureX, futureY).isBusy()){
                 return true;
             }

            if(cont == Figure.AMOUNT_BLOCKS - 2){
                plusY = false;
                minusX = true;
            }

            if(minusX){
               futureX = futureX - 1;
            }

            if(plusY){
               futureY = direction == Direction.Down ? futureY - 1 : futureY + 1;
            }

            cont++;
        }

        return false;
    }

    private boolean validateEdges(Coordinate coordinate, Direction direction){
        int futureY = direction == Direction.Down ? coordinate.getY() - 3 : coordinate.getY() + 3;
        return futureY < 1 || futureY > 20 || coordinate.getX() - 1 < 0;
    }

    @Override
    protected void paintingWayUp(Coordinate coordinate) {
        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;
        TileActor tile;
        boolean minusX = false;
        boolean plusY = true;

        while(blockNumberToPaint > 0){

            tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());;
            tile.setRegionTexture("BlackWay");
            tile.setBusy(true);


            if(blockNumberToPaint == Figure.AMOUNT_BLOCKS - 3){
                plusY = false;
                minusX = true;
            }

            if(minusX){
                coordinate.setX(coordinate.getX() - 1);
            }

            if(plusY){
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
        boolean minusX = false;
        boolean plusY = true;

        while(blockNumberToPaint > 0){

            tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());;
            tile.setRegionTexture("WhiteWay");
            tile.setBusy(true);

            if(blockNumberToPaint == Figure.AMOUNT_BLOCKS - 3){
                plusY = false;
                minusX = true;
            }

            if(minusX){
                coordinate.setX(coordinate.getX() - 1);
            }

            if(plusY){
                coordinate.setY(coordinate.getY() - 1);
            }

            blockNumberToPaint--;
        }
        calculatingNextMovement(coordinate.getX(), coordinate.getY(), Direction.Down);
    }

    @Override
    protected void calculatingNextMovement(int x, int y, Direction direction) {
        getState().getPlayerInTurn().getCoordinatesList().clear();
        int futureX, futureY ;

        if(direction == Direction.Down){
            if(x < 18 && y >= 4 && !BoardView.getTileByPosition(x,y).isBusy()){
                getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x, y));
            }

            futureX = x + 2;
            futureY = y - 1;

            if(futureY >= 4 && !BoardView.getTileByPosition(futureX,futureY).isBusy()){
                getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(futureX,futureY));
            }

            futureX = futureX + 1;
            futureY = y;

            if(futureX >= 0 &&  futureX <= 19 && !BoardView.getTileByPosition(futureX,futureY).isBusy()){
                getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(futureX,futureY));
            }
        }


        if(direction == Direction.Up){
            if(x >= 1 && y <= 18 && !BoardView.getTileByPosition(x,y).isBusy()){
                getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x, y));
            }
            futureX = x + 2;
            futureY = y + 1;

            if(futureY <= 18 && !BoardView.getTileByPosition(futureX,futureY).isBusy()){
                getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(futureX,futureY));
            }

            futureX = futureX + 1;
            futureY = y;

            if(futureY <= 18 && futureX >=0 && futureX <= 19 && !BoardView.getTileByPosition(futureX,futureY).isBusy()){
                getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(futureX,futureY));
            }
        }

    }
}
