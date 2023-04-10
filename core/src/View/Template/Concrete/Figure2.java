package View.Template.Concrete;

import Enums.Direction;
import Model.Coordinate;
import View.Actor.TileActor;
import View.BoardView;
import View.Template.Figure;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.List;

public class Figure2 extends Figure {

    public Figure2(Stage boardStage) {
        super(boardStage);
    }

    @Override
    protected List<Coordinate> filterInvalidCoordinates(List<Coordinate> coordinates, Direction direction) {
        return coordinates;
    }

    @Override
    protected boolean hasAnyCollision(Coordinate coordinate, Direction direction) {
        return false;
    }

    @Override
    protected void paintingWayUp(Coordinate coordinate) {
        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;
        TileActor tile;
        if(coordinate.getX() <= 0) {
            // TODO: Tumba que tumba que tumba tumba tumba
            return;
        }

        TileActor prevTile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());

        if(prevTile.isBusy()) {
            coordinate.setX(coordinate.getX() + 1);
        }

        while(!(blockNumberToPaint == Figure.AMOUNT_BLOCKS - 3 )){
            tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());;
            tile.setRegionTexture("BlackWay");
            tile.setBusy(true);

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
            TileActor tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());
            tile.setRegionTexture("BlackWay");
            tile.setBusy(true);

            blockNumber --;
        }
    }


    @Override
    protected void paintingWayDown(Coordinate coordinate) {
        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;
        TileActor tile;

        if(coordinate.getX() <= 0) {
            // TODO: Tumba que tumba que tumba tumba tumba
            return;
        }

        TileActor prevTile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());

        if(prevTile.isBusy()) {
            coordinate.setX(coordinate.getX() + 1);
        }

        while(blockNumberToPaint != (Figure.AMOUNT_BLOCKS - 3)){
            tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());
            tile.setRegionTexture("WhiteWay");
            tile.setBusy(true);

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
            TileActor tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());
            tile.setRegionTexture("WhiteWay");
            tile.setBusy(true);

            blockNumber --;
        }

    }

    @Override
    protected void calculatingNextMovement(int x, int y, Direction direction) {
        getState().getPlayerInTurn().getCoordinatesList().clear();

        int newY = direction == Direction.Down ? y - 1 : y + 1;
        int newX = direction == Direction.Down ? x - 1 : x + 1;

        if(x >= 0 && !board[y][x].isBusy()){
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(x, y));
        }

        if(newY >= 0 && !board[newY][newX].isBusy()){
            getState().getPlayerInTurn().getCoordinatesList().add(new Coordinate(newX, newY));
        }

    }
}
