package View.Template.Concrete;

import Enums.Direction;
import Model.Coordinates;
import View.Actor.TileActor;
import View.BoardView;
import View.Template.Figure;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Figure7 extends Figure {
    public Figure7(Stage boardStage) {
        super(boardStage);

    }

    @Override
    protected void paintingWayUp(Coordinates coordinates) {
        TileActor[][] board = BoardView.getMatriz();

        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;

        while(!(blockNumberToPaint == 0 )){
            board[coordinates.getY()][coordinates.getX()].setRegionTexture("BlackWay");
            coordinates.setY(coordinates.getY() + 1);
            blockNumberToPaint --;
        }

        calculatingNextMovement(coordinates.getX(), coordinates.getY(), Direction.Up);
    }

    @Override
    protected void paintingWayDown(Coordinates coordinates) {
        TileActor[][] board = BoardView.getMatriz();

        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;

        while(!(blockNumberToPaint == 0 )){
            board[coordinates.getY()][coordinates.getX()].setRegionTexture("WhiteWay");
            coordinates.setY(coordinates.getY() - 1);
            blockNumberToPaint --;
        }

        calculatingNextMovement(coordinates.getX(), coordinates.getY(), Direction.Down);
    }




    @Override
    protected void calculatingNextMovement(int x, int y, Direction direction) {
        getState().getPlayer().getCoordinatesList().clear();

        int newY = direction == Direction.Down ? y + 1 : y -1;
        if(x > 0){
            getState().getPlayer().getCoordinatesList().add(new Coordinates(x-1, newY));
        }

        if(x < 19){

            getState().getPlayer().getCoordinatesList().add(new Coordinates(x+1, newY));
        }

        if(direction == Direction.Up && y < 19){
            getState().getPlayer().getCoordinatesList().add(new Coordinates(x, y));
        }

        if(direction == Direction.Down && y > 0){
            getState().getPlayer().getCoordinatesList().add(new Coordinates(x, y));
        }
    }
}
