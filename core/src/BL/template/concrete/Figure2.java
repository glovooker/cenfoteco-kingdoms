package BL.template.concrete;

import BL.prototype.TileActor;
import BL.template.abstract_figure.Figure;
import Enums.Direction;
import Model.Coordinate;

import View.BoardView;
import View.Screens.PlayScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class Figure2 extends Figure {

    public Figure2(PlayScreen playScreen, Stage boardStage) {
        super(playScreen, boardStage);
    }

    @Override
    protected List<Coordinate> filterInvalidCoordinates(List<Coordinate> coordinates, Direction direction) {
        List<Coordinate> result = new ArrayList<>();

        for (Coordinate coordinate : coordinates){

            if(!(hasAnyCollision(coordinate, direction))){
                result.add(coordinate);
            }

        }
        return result;
    }

    @Override
    protected boolean hasAnyCollision(Coordinate coordinate, Direction direction) {
        int contX = 0;

        if(validateEdges(coordinate, direction)){
            return true;
        }

        TileActor nextTile = BoardView.getTileByPosition(coordinate.getX() + 1, coordinate.getY());

        int initialX = nextTile.hasWayCreated() ?  coordinate.getX() - 1 : coordinate.getX();
        int currentY = coordinate.getY();

        while (contX < 3){
            int currentX =  initialX + contX;

            TileActor tile = BoardView.getTileByPosition(currentX, currentY);

            if(tile.hasWayCreated()){
                return true;
            }

            if(contX == 1) {
                if(hasAnyCollisionOnY(currentX, coordinate.getY(), direction)) {
                    return true;
                }

                currentY = direction == Direction.Up ? currentY + 2 : currentY -2;
            }

            contX ++;
        }

        return false;
    }

    private  boolean hasAnyCollisionOnY(int x, int y, Direction direction) {
        int contY = 1;

        while(contY < 2) {
            int currentY = direction == Direction.Up ? y + contY : y - contY;
            TileActor tile = BoardView.getTileByPosition(x, currentY);

            if(tile.hasWayCreated()){
                return true;
            }

            contY++;
        }

        return false;
    }

    private boolean validateEdges(Coordinate coordinate, Direction direction){
        int newY = direction == Direction.Down ? coordinate.getY() - 2 : coordinate.getY() + 2;

        if(newY <= 0 || newY > BoardView.getRowsInBoard()){
            return true;
        }

        return coordinate.getX() + 2 > BoardView.getRowsInBoard() - 1 || coordinate.getX() <= 0;
    }

    @Override
    protected void paintingWayUp(Coordinate coordinate) {
        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;
        TileActor tile;

        if(coordinate.getX() <= 0 || coordinate.getX() >= BoardView.getRowsInBoard() - 3) {
            // TODO: Tumba que tumba que tumba tumba tumba
            return;
        }

        TileActor nextTile = BoardView.getTileByPosition(coordinate.getX() + 1, coordinate.getY());

        if(nextTile.hasWayCreated()) {
            coordinate.setX(coordinate.getX() - 1);
        }

        while(!(blockNumberToPaint == Figure.AMOUNT_BLOCKS - 3 )){
            tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());;
            tile.setRegionTexture(Figure.tileCastlePlayer1);
            tile.setHasWayCreated(true);

            if(blockNumberToPaint == Figure.AMOUNT_BLOCKS - 1){
                paintingHelperUp(coordinate);
            }

            coordinate.setX(coordinate.getX() + 1);

            blockNumberToPaint --;
        }

        calculatingNextMovement(coordinate.getX(), coordinate.getY(), Direction.Up);

    }

    private void paintingHelperUp(Coordinate coordinate){
        int blockNumber = 2;

        while( blockNumber != 0){
            coordinate.setY(coordinate.getY() + 1);
            saveMiddleCoordinates(blockNumber, coordinate);
            TileActor tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());
            tile.setRegionTexture(Figure.tileCastlePlayer1);
            tile.setHasWayCreated(true);

            blockNumber --;
        }
    }


    @Override
    protected void paintingWayDown(Coordinate coordinate) {
        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;
        TileActor tile;

        if(coordinate.getX() <= 0 || coordinate.getX() >= BoardView.getRowsInBoard() - 3) {
            // TODO: Tumba que tumba que tumba tumba tumba
            return;
        }

        TileActor nextTile = BoardView.getTileByPosition(coordinate.getX() + 1, coordinate.getY());

        if(nextTile.hasWayCreated()) {
            coordinate.setX(coordinate.getX() - 1);
        }

        while(blockNumberToPaint != (Figure.AMOUNT_BLOCKS - 3)){
            tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());
            tile.setRegionTexture(Figure.tileCastlePlayer2);
            tile.setHasWayCreated(true);

            if(blockNumberToPaint == Figure.AMOUNT_BLOCKS - 1){
                paintingHelperDown(coordinate);
            }

            coordinate.setX(coordinate.getX() + 1);
            blockNumberToPaint --;
        }

        calculatingNextMovement(coordinate.getX(), coordinate.getY(), Direction.Down);
    }

    private void paintingHelperDown(Coordinate coordinate){
        int blockNumber = 2;

        while(blockNumber != 0){
            coordinate.setY(coordinate.getY() - 1);
            saveMiddleCoordinates(blockNumber, coordinate);
            TileActor tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());
            tile.setRegionTexture(Figure.tileCastlePlayer2);
            tile.setHasWayCreated(true);

            blockNumber --;
        }

    }

    @Override
    protected void calculatingNextMovement(int x, int y, Direction direction) {
        getState().getPlayerInTurn().getCoordinatesList().clear();

        int newY = direction == Direction.Down ? y - 1 : y + 1;
        int newX = x - 1;

        if(x >= 0 && !board[y][x].hasWayCreated()){
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x, y));
        }

        if(newY >= 0 && !board[newY][newX].hasWayCreated()){
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(newX, newY));
        }

    }

    @Override
    protected void saveMiddleCoordinates(int tileNumber, Coordinate coordinate) {
        if(tileNumber == 2){
            this.setMiddleCoordinates(new Coordinate(coordinate.getX(), coordinate.getY()));
            this.addInvadedByArmyToTile();
        }
    }
}
