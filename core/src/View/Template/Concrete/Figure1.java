package View.Template.Concrete;

import Enums.Direction;
import Model.Coordinates;
import View.Actor.TileActor;
import View.BoardView;
import View.Template.Figure;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Figure1 extends Figure {



    public Figure1(Stage boardStage) {
        super(boardStage);
    }

    @Override
    protected void paintingWayUp(Coordinates coordinates) {
        TileActor[][] board = BoardView.getMatriz();

        int blockNumberToPaint = Figure.AMOUNT_BLOCKS;
        if(!(coordinates.getX() < 3  )){
            coordinates.setX(coordinates.getX() - 1);
        }


        while(!(blockNumberToPaint == 0 )){
            board[coordinates.getY()][coordinates.getX()].setRegionTexture("BlackWay");
            coordinates.setX(coordinates.getX() + 1);
            blockNumberToPaint --;
        }
    }



    @Override
    protected void paintingWayDown(Coordinates coordinates) {

    }


    @Override
    protected void calculatingNextMovement(int x, int y, Direction direction) {

    }
}
