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
        return null;
    }

    @Override
    protected boolean hasAnyCollision(Coordinate coordinate, Direction direction) {
        return false;
    }

    @Override
    protected void paintingWayUp(Coordinate coordinate) {
        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;
        TileActor tile;
        int cont = 0;

        while(!(blockNumberToPaint == 0 )){
            tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());
            tile.setRegionTexture("BlackWay");
            tile.setBusy(true);
            coordinate.setX(coordinate.getX() - 1);


            if(cont == 2 || cont == 3){
                coordinate.setY(coordinate.getY() + 1);
            }

            if(cont == 4){
                coordinate.setX(coordinate.getX() - 1);
            }

            cont ++;
            blockNumberToPaint --;
        }








    }

    private void helperPaintingWayUp(Coordinate coordinate){
        int blockNumber = Figure.AMOUNT_BLOCKS - 2;

        while(blockNumber != 0){
            TileActor tile = BoardView.getTileByPosition(coordinate.getX(), coordinate.getY());
            tile.setRegionTexture("BlackWay");
            tile.setBusy(true);
            coordinate.setY(coordinate.getY() + 1);
            blockNumber --;
        }

    }

    @Override
    protected void paintingWayDown(Coordinate coordinate) {

    }

    @Override
    protected void calculatingNextMovement(int x, int y, Direction direction) {

    }
}
