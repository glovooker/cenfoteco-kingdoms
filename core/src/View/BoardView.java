package View;

import Model.Board;
import Screens.PlayScreen;
import View.Actor.CastleActor;
import View.Actor.TileActor;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BoardView  {
    private Stage stageBoard;

    private TileActor tileActor;

    private CastleActor castleActorPLayer1;

    private CastleActor castleActorPLayer2;

    private Board board;

    private TileActor[][] matriz;

    private int linesInBoard;
    private int rowsInBoard;

    public BoardView(PlayScreen screen, Viewport gameport) {
        this.stageBoard = new Stage();
        this.stageBoard = new Stage(gameport);
        tileActor = new TileActor(screen);
        this.castleActorPLayer1 = new CastleActor(screen, "CastlePlayer2");
        this.castleActorPLayer2 = new CastleActor(screen, "castlePlayer1");
        this.board = new Board();
        linesInBoard = board.getLINES();
        rowsInBoard = board.getROWS();
        matriz = new TileActor[this.linesInBoard][this.rowsInBoard];
        initializeBoard(this.tileActor);
    }

    private void initializeBoard(TileActor tileActor) {
        int x, y = 0;

        for (int i = 0; i < this.linesInBoard; i++) {
            x = 0;
            boolean tileWhiteFirst = false;
            for (int j = 0; j < this.rowsInBoard; j++) {
                TileActor tileActorTemp = tileActor.clone();
                this.matriz[i][j] = tileActorTemp;
                tileActorTemp.setPosition(x, y);
                tileActorTemp.setZIndex(0);
                x += TileActor.SIZE;
                this.stageBoard.addActor(tileActorTemp);


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

                if(i == 0){
                    tileActorTemp.setRegionTexture("tileCastle");
                } else if (i == 21) {
                    tileActorTemp.setRegionTexture("tileCastle");
                }
            }

            y += TileActor.SIZE;
        }

        addingCastlesToBoard(0, castleActorPLayer1 );
        this.stageBoard.addActor(this.castleActorPLayer1);
        addingCastlesToBoard(21, this.castleActorPLayer2);
        this.stageBoard.addActor(this.castleActorPLayer2);
    }

    private void addingCastlesToBoard(int row, CastleActor castleActor){
        int x = ((int) (Math.random() * 20 + 0)) * 50;
        int y = row * 50;
        System.out.println(x + ", " + y);

        castleActor.getCastleModel().getCoordinates().setY(y);
        castleActor.getCastleModel().getCoordinates().setX(x);
        castleActor.setPosition(castleActor.getCastleModel().getCoordinates().getX(), castleActor.getCastleModel().getCoordinates().getY());
    }

    public Stage getStageBoard(){
        return this.stageBoard;
    }
}
