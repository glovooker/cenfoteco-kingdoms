package View;

import BL.GameController;
import Model.Board;
import Model.Castle;
import Model.Coordinate;
import View.Screens.PlayScreen;
import View.Actor.CastleActor;
import BL.prototype.TileActor;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BoardView  {
    private static Stage stageBoard;

    private TileActor tileActor;

    private CastleActor castleActorPLayer1;

    private CastleActor castleActorPLayer2;

    private Board board;

    private static TileActor[][] matriz;

    private static int linesInBoard;
    private static int rowsInBoard;

    private GameController gameController = GameController.getInstance();

    public BoardView(PlayScreen screen, Viewport gameport) {
        stageBoard = new Stage(gameport);
        tileActor = new TileActor(screen);
        this.castleActorPLayer1 = new CastleActor(screen, "CastlePlayer2");
        this.castleActorPLayer2 = new CastleActor(screen, "castlePlayer1");
        this.board = gameController.getGameState().getBoard();
        linesInBoard = Board.ROWS;
        rowsInBoard = Board.COLUMNS;
        matriz = new TileActor[linesInBoard][rowsInBoard];
        initializeBoard(this.tileActor);
    }

    private void initializeBoard(TileActor tileActor) {
        int x, y = 0;
        int[][] matrizNumber = board.getNumberMatriz();

        for (int i = 0; i < linesInBoard; i++) {
            x = 0;
            boolean tileWhiteFirst = false;
            for (int j = 0; j < rowsInBoard; j++) {
                TileActor tileActorTemp = tileActor.clone();
                matriz[i][j] = tileActorTemp;
                tileActorTemp.setPosition(x, y);
                tileActorTemp.setZIndex(0);
                x += TileActor.SIZE;
                stageBoard.addActor(tileActorTemp);


                if(i%2 == 0){
                    tileWhiteFirst = true;
                }


                if(tileWhiteFirst){
                    if(j%2 == 0) {
                        tileActorTemp.setRegionTexture("Tile2");
                    }
                    else {
                        tileActorTemp.setRegionTexture("Tile");
                    }
                } else {
                    if(j%2 == 0) {
                        tileActorTemp.setRegionTexture("Tile");
                    }
                    else {
                        tileActorTemp.setRegionTexture("Tile2");
                    }
                }

                if(i == 0 || matrizNumber[i][j] == 1){
                    tileActorTemp.setRegionTexture("TileCastle");
                } else if (i == 21 || matrizNumber[i][j] == 2) {
                    tileActorTemp.setRegionTexture("TileCastle2");
                }

            }

            y += TileActor.SIZE;
        }

        addingCastlesToBoard(0, gameController.getPlayer1().getCastle(), castleActorPLayer1);
        stageBoard.addActor(this.castleActorPLayer1);
        addingCastlesToBoard(21, gameController.getPlayer2().getCastle(), this.castleActorPLayer2);
        stageBoard.addActor(this.castleActorPLayer2);
    }

    public static TileActor[][] getMatriz(){
        return matriz;
    }

    public int[][] getNumberMatrix() {
        return this.board.getNumberMatriz();
    }

    private void addingCastlesToBoard(int row, Castle castle, CastleActor castleActor){
        int x = ((int) (Math.random() * 10 + 5));

        if(castle.getCoordinates() == null && castle.getCoordinates() == null){
            castle.setCoordinate(new Coordinate(x, row));
        }

        int posX = castle.getCoordinates().getX() * 50;
        int posY = castle.getCoordinates().getY() * 50;
        castleActor.setPosition(posX, posY);
    }

    public static Stage getStageBoard(){
        return stageBoard;
    }

    public static TileActor getTileByPosition(int x, int y) {
        return matriz[y][x];
    }

    public static int getLinesInBoard(){
        return linesInBoard;
    }

    public static int getRowsInBoard(){
        return rowsInBoard;
    }


}
