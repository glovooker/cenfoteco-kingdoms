package BL.template.concrete;

import BL.prototype.TileActor;
import BL.template.abstract_figure.Figure;
import Enums.Direction;
import Model.Coordinate;
import View.BoardView;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class Figure1 extends Figure {
    public Figure1(Stage boardStage) {
        super(boardStage);
    }

    @Override
    protected List<Coordinate> filterInvalidCoordinates(List<Coordinate> coordinates, Direction direction) {
        List<Coordinate> result = new ArrayList<>();


        for (Coordinate coordinate : coordinates){

            if(!(hasAnyCollision(coordinate, direction)) && !(reachBorderRight(coordinate))){
                result.add(coordinate);
            }

            if(!(hasAnyCollisionToTheLeft(coordinate)) && !(reachBorderLeft(coordinate))){
                result.add(coordinate);
            }

        }

        return result;
    }

    @Override
    protected boolean hasAnyCollision(Coordinate coordinate, Direction direction) {
        int cont = 1;

        while (cont < AMOUNT_BLOCKS){

            if(coordinate.getX() + cont > BoardView.getRowsInBoard() - 1){
                return true;
            }


            TileActor tile = BoardView.getTileByPosition(coordinate.getX() + cont, coordinate.getY());


            if(tile.isBusy()){
                return true;
            }

            cont ++;

        }

        return false;
    }

    private boolean hasAnyCollisionToTheLeft(Coordinate coordinate){
        int cont = 1;

        while (cont < AMOUNT_BLOCKS){

            if(coordinate.getX() - cont < 0){
                return true;
            }

            TileActor tile = BoardView.getTileByPosition(coordinate.getX() - cont, coordinate.getY());

            if(tile.isBusy()){
                return true;
            }

            cont ++;
        }

        return false;
    }

    private boolean reachBorderRight(Coordinate coordinate){
        int futureXRight = coordinate.getX() - 1 + AMOUNT_BLOCKS;

        if(futureXRight > BoardView.getRowsInBoard() - 1){
            return true;
        }

        return false;
    }

    private boolean reachBorderLeft(Coordinate coordinate){
        int futureXLeft = coordinate.getX() + 1 - AMOUNT_BLOCKS;

        if(futureXLeft < 0){
            return true;
        }

        return false;
    }

    @Override
    protected void paintingWayUp(Coordinate coordinate) {
        String texture = "BlackWay";
        paint(texture, coordinate, Direction.Up);
    }

    private void paint(String texture, Coordinate coordinate, Direction direction){
        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;
        int initialX = coordinate.getX() + 1;
        int initialY = coordinate.getY();

        if(coordinate.getX() > (20-AMOUNT_BLOCKS) || board[initialY][initialX].isBusy()) {
            coordinate.setX(coordinate.getX() - AMOUNT_BLOCKS + 1);
        }

        while(blockNumberToPaint > 0 ){
            board[coordinate.getY()][coordinate.getX()].setRegionTexture(texture);
            board[coordinate.getY()][coordinate.getX()].setBusy(true);
            coordinate.setX((coordinate.getX() + 1));
            blockNumberToPaint--;
        }

        calculatingNextMovement(coordinate.getX(), coordinate.getY(), direction);
    }

    @Override
    protected void paintingWayDown(Coordinate coordinate) {
        String texture = "WhiteWay";
        paint(texture, coordinate, Direction.Down);
    }


    @Override
    protected void calculatingNextMovement(int x, int y, Direction direction) {
        getState().getPlayerInTurn().getCoordinatesList().clear();

        int newY = direction == Direction.Down ? y - 1 : y + 1;

        int initialX = x - AMOUNT_BLOCKS;
        int leftButtonX = initialX -1;

        if(initialX > 0 && !board[y][leftButtonX].isBusy()){
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(leftButtonX, y));
        }

        if(x < 19 && !board[y][x].isBusy()){
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x, y));
        }

        if(newY < 21 && newY > 0 && !board[newY][initialX+2].isBusy()){
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(initialX + 2, newY));
        }

    }
}
