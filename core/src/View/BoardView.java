package View;

import BL.GameController;
import Model.Board;
import Model.Castle;
import Model.Coordinate;
import View.Components.ButtonComponent;
import View.Screens.PlayScreen;
import BL.prototype.TileActor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BoardView  {
    private final Stage stageBoard;

    private final Board board;

    private static TileActor[][] matriz;

    private static int linesInBoard;
    private static int rowsInBoard;

    private final GameController gameController = GameController.getInstance();

    private ButtonComponent castleActorPlayer1;
    private ButtonComponent castleActorPlayer2;

    private PlayScreen playScreen;

    public BoardView(PlayScreen screen, Viewport gameport) {
        stageBoard = new Stage(gameport);
        this.stageBoard.setDebugAll(true);
        TileActor tileActor = new TileActor(screen);
        this.board = gameController.getGameState().getBoard();
        linesInBoard = Board.ROWS;
        rowsInBoard = Board.COLUMNS;
        matriz = new TileActor[linesInBoard][rowsInBoard];
        playScreen = screen;
        initializeBoard(tileActor);
        initializeCastleButtons();
    }

    private void initializeCastleButtons() {
        addingCastlesToBoard(0, gameController.getPlayer1().getCastle());
        addingCastlesToBoard(21, gameController.getPlayer2().getCastle());
        int castle1XPosition = gameController.getPlayer1().getCastle().getCoordinates().getX() * 50;
        int castle2XPosition = gameController.getPlayer2().getCastle().getCoordinates().getX() * 50;

        this.castleActorPlayer1 = new ButtonComponent(this.stageBoard, "castlePlayer1.png", 60, 60, castle1XPosition, 0, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(gameController.getPlayerInTurn().getName().equals(gameController.getPlayer2().getName())
                        && gameController.getPlayerInTurn().isUsingRegularAttack()
                ) {
                    gameController.attackEnemyOrCastle(null, gameController.getPlayer1().getCastle());

                    if(gameController.getPlayer1().getCastle().getLife() == 0) {
                        castleActorPlayer1.getButton().remove();
                    }

                    gameController.getPlayerInTurn().setUsingRegularAttack(false);
                    playScreen.getHudChest().updateLabels(gameController.getGameState());
                }

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        this.castleActorPlayer2 = new ButtonComponent(this.stageBoard, "castlePlayer2.png", 60, 60, castle2XPosition, 21 * 50, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(gameController.getPlayerInTurn().getName().equals(gameController.getPlayer1().getName())
                        && gameController.getPlayerInTurn().isUsingRegularAttack()
                ) {
                    gameController.attackEnemyOrCastle(null, gameController.getPlayer2().getCastle());

                    if(gameController.getPlayer2().getCastle().getLife() == 0) {
                        castleActorPlayer2.getButton().remove();
                    }

                    gameController.getPlayerInTurn().setUsingRegularAttack(false);
                    playScreen.getHudChest().updateLabels(gameController.getGameState());
                }

                return super.touchDown(event, x, y, pointer, button);
            }
        });
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
    }

    public static TileActor[][] getMatriz(){
        return matriz;
    }

    public int[][] getNumberMatrix() {
        return this.board.getNumberMatriz();
    }

    private void addingCastlesToBoard(int row, Castle castle){
        int x = ((int) (Math.random() * 10 + 5));

        if(castle.getCoordinates() == null && castle.getCoordinates() == null){
            castle.setCoordinate(new Coordinate(x, row));
        }
    }

    public Stage getStageBoard(){
        return this.stageBoard;
    }

    public static TileActor getTileByPosition(int x, int y) {
        return matriz[y][x];
    }

    public static int getRowsInBoard(){
        return rowsInBoard;
    }
}
