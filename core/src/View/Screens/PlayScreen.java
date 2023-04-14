package View.Screens;

import BL.GameController;
import View.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ReinoCenfotecos;


public class PlayScreen implements Screen {

    private ReinoCenfotecos game;

    private OrthographicCamera gameCam;

    private Viewport gamePort;

    private TmxMapLoader mapLoader;

    private TiledMap map;

    private OrthogonalTiledMapRenderer renderer;

    private World world;

    private Box2DDebugRenderer b2dr;

    private BoardView boardView;

    private Stage chestStage;

    private Stage boardStage;

    private TextureAtlas atlasBoard;

    private TextureRegion playerInGame;

    private Texture player;

    private TextureRegion heartRegion;

    private Texture heart;

    private TextureAtlas atlasFigures;
    private TextureAtlas atlasDice;
    private TextureAtlas atlasArmy;


    private TextureAtlas castles;

    private TextureRegion timeRegion;

    private Texture time;

    private DiceView diceView;

    private ArmyView armyView;

    private ButtonsActionsView buttonsActionsView;

    private ChestView chest;
    private Stage figuresView;

    private InformationBar informationBar;


    public PlayScreen(Music music){
        music.stop();
        atlasBoard = new TextureAtlas("boardAtlas.atlas");
        atlasFigures = new TextureAtlas("figuresAtlas.atlas");
        castles = new TextureAtlas("Castles.atlas");
        atlasDice = new TextureAtlas("dicePackAtlas.atlas");
        atlasArmy = new TextureAtlas("armyPackAtlas.atlas");
        player = new Texture("player.png");
        playerInGame = new TextureRegion(player, 5, 5, 1000, 100);
        heart = new Texture("heart.png");
        heartRegion = new TextureRegion(heart, 5, 5, 100, 100);
        time = new Texture("player.png");
        timeRegion = new TextureRegion(time, 5, 5, 800, 100);
        this.game = ReinoCenfotecos.getInstance();
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(1600, 1600, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("World.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight()/2, 0);
        world = new World(new Vector2(0, -10), true);

        b2dr = new Box2DDebugRenderer();
        this.boardView = new BoardView(this,gamePort);
        this.boardStage = BoardView.getStageBoard();

        this.boardStage.getRoot().setX(250);
        this.boardStage.getRoot().setY(400);

        this.figuresView = new FiguresView(this.gamePort, this.boardStage);
        this.figuresView.getRoot().setX(0);
        this.figuresView.getRoot().setY(400);

        this.chestStage = new Stage(gamePort);
        this.chestStage.getRoot().setX(0);
        this.chestStage.getRoot().setY(0);


        this.diceView = new DiceView(this, gamePort);
        this.diceView.getRoot().setX(800);
        this.diceView.getRoot().setY(500);

        this.armyView = new ArmyView(this, gamePort);
        this.armyView.getRoot().setX(400);
        this.armyView.getRoot().setY(200);

        this.buttonsActionsView = new ButtonsActionsView(this, gamePort);
        this.buttonsActionsView.getRoot().setX(1300);
        this.buttonsActionsView.getRoot().setY(875);

        chest = new ChestView(chestStage);
        informationBar = new InformationBar(gamePort);

        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(chestStage);
        inputMultiplexer.addProcessor(figuresView);
        inputMultiplexer.addProcessor(boardStage);
        inputMultiplexer.addProcessor(diceView);
        inputMultiplexer.addProcessor(armyView);
        inputMultiplexer.addProcessor(buttonsActionsView);
    }


    public void update(){
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        b2dr.render(world, gameCam.combined);
        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();
        game.batch.draw(playerInGame, 1350, 1460);
        game.batch.draw(heartRegion, 1280, 1473, 80,80);
        game.batch.draw(timeRegion,13, 1460);

        game.batch.end();

        boardStage.act(Gdx.graphics.getDeltaTime());
        boardStage.draw();

        chestStage.act(Gdx.graphics.getDeltaTime());
        chestStage.draw();

        diceView.act(Gdx.graphics.getDeltaTime());
        diceView.draw();

        armyView.act(Gdx.graphics.getDeltaTime());
        armyView.draw();

        buttonsActionsView.act(Gdx.graphics.getDeltaTime());
        buttonsActionsView.draw();

        informationBar.getStage().act(Gdx.graphics.getDeltaTime());
        informationBar.getStage().draw();

       figuresView.act(Gdx.graphics.getDeltaTime());
       figuresView.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        chestStage.draw();
        boardStage.draw();
        figuresView.draw();
        diceView.draw();
        armyView.draw();
        buttonsActionsView.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        chestStage.dispose();
        boardStage.dispose();
        figuresView.dispose();
        player.dispose();
        diceView.dispose();
        armyView.dispose();
        buttonsActionsView.dispose();
    }

    public World getWorld(){
        return world;
    }

    public TiledMap getMap(){
        return map;
    }

    public TextureAtlas getBoardAtlas(){
        return this.atlasBoard;
    }

    public TextureAtlas getFiguresAtlas(){
        return this.atlasFigures;
    }

    public TextureAtlas getCastlesAtlas(){
        return this.castles;
    }

    public TextureAtlas getDiceAtlas(){
        return this.atlasDice;
    }

    public TextureAtlas getArmyAtlas(){
        return this.atlasArmy;
    }


}
