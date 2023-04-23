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

public class Figure6 extends Figure {
    int cont = 0;

    public Figure6(PlayScreen playScreen, Stage boardStage) {
        super(playScreen, boardStage);
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

        int initialX = coordinate.getX();
        TileActor nextTile = BoardView.getTileByPosition(coordinate.getX() + 1, coordinate.getY());

        if(nextTile.hasWayCreated()) {
            initialX = coordinate.getX() - 2;
        }

        int cont = 0;

        while(cont < 3) {
            if(cont == 1 && hasAnyCollisionOnY(initialX + cont, coordinate.getY(), direction)) {
                return true;
            }

            if(BoardView.getTileByPosition(initialX + cont, coordinate.getY()).hasWayCreated()) {
                return true;
            }

            cont++;
        }

        return false;
    }

    private boolean hasAnyCollisionOnY(int currentX, int initialY, Direction direction) {
        int cont = 1;

        while(cont < 3) {
            int nextY = direction == Direction.Up ? initialY + cont : initialY - cont;

            if(BoardView.getTileByPosition(currentX, nextY).hasWayCreated()) {
                return true;
            }

            cont++;
        }

        return false;
    }

    private boolean validateEdges(Coordinate coordinate, Direction direction) {
        int futureX = coordinate.getX() + 2;
        int futureY = direction == Direction.Up ? coordinate.getY() + 2 : coordinate.getY() - 2;

        return futureX < BoardView.getRowsInBoard() - 1 && (futureY >= 1 && futureY <= 20);
    }

    @Override
    protected void paintingWayUp(Coordinate coordinate) {
        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;
        TileActor tile;
        boolean plusY = false;
        boolean plusX = false;

        int initialY = coordinate.getY();

        checkingIsZero(coordinate);

        while(blockNumberToPaint > 0){

            if(blockNumberToPaint == Figure.AMOUNT_BLOCKS - 1){
                plusX = true;
                plusY = true;
            }

            if(plusX){
                coordinate.setX(coordinate.getX() + 1);
            }

            tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());;
            tile.setRegionTexture(Figure.tileCastlePlayer1);
            tile.setHasWayCreated(true);

            saveMiddleCoordinates(blockNumberToPaint, coordinate);
            if(blockNumberToPaint == Figure.AMOUNT_BLOCKS - 3){
                plusX = true;
                plusY = false;
                coordinate.setY(initialY);
            }

            if(plusY){
                coordinate.setY(coordinate.getY() + 1);
                plusX = false;
            }

            blockNumberToPaint--;
        }

       calculatingNextMovement(coordinate.getX(), coordinate.getY(), Direction.Up);
    }

    @Override
    protected void paintingWayDown(Coordinate coordinate) {
        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;
        TileActor tile;
        boolean minusY = false;
        boolean plusX = false;

        int initialY = coordinate.getY();

       checkingIsZero(coordinate);

        while(blockNumberToPaint > 0){

            if(blockNumberToPaint == Figure.AMOUNT_BLOCKS - 1){
                plusX = true;
                minusY = true;
            }

            if(plusX){
                coordinate.setX(coordinate.getX() + 1);
            }

            tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());
            tile.setRegionTexture(Figure.tileCastlePlayer2);
            tile.setHasWayCreated(true);

            saveMiddleCoordinates(blockNumberToPaint, coordinate);
            if(blockNumberToPaint == Figure.AMOUNT_BLOCKS - 3){
                plusX = true;
                minusY = false;
                coordinate.setY(initialY);
            }

            if(minusY){
                coordinate.setY(coordinate.getY() - 1);
                plusX = false;
            }


            blockNumberToPaint--;
        }
        calculatingNextMovement(coordinate.getX(), coordinate.getY(), Direction.Down);
    }

    private void checkingIsZero(Coordinate coordinate){
        if(coordinate.getX() + 1 < 19){
            TileActor nextTile = BoardView.getTileByPosition(coordinate.getX() + 1, coordinate.getY());

            if(nextTile.hasWayCreated()) {
                coordinate.setX(coordinate.getX() - 2);
            }
        }
    }

    @Override
    protected void calculatingNextMovement(int x, int y, Direction direction) {
        getState().getPlayerInTurn().getCoordinatesList().clear();

        if(x <= 16 && !BoardView.getTileByPosition(x + 1, y).hasWayCreated()){
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x + 1, y));
        }

        if(x >= 5 && !BoardView.getTileByPosition(x - 3, y).hasWayCreated()){
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x - 3, y));
        }

        if(direction == Direction.Up){
            if(y <= 19 && !BoardView.getTileByPosition(x - 1, y + 3).hasWayCreated()){
                getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x - 1, y + 3));
            }
        }else {
            if(y >= 5 && !BoardView.getTileByPosition(x - 1, y -3).hasWayCreated()){
                getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x - 1, y - 3));
            }
        }
    }

    @Override
    protected void saveMiddleCoordinates(int tileNumber, Coordinate coordinate) {
        if(tileNumber == 4){
            this.setMiddleCoordinates(new Coordinate(coordinate.getX(), coordinate.getY()));
            this.addInvadedByArmyToTile();
        }
    }
}
