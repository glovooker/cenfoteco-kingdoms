package Screens;

import BL.GameController;
import View.Components.ButtonComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ReinoCenfoteco;

public class Menu implements Screen {

    private ReinoCenfoteco game;
    private OrthographicCamera gameCam;

    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;

    private OrthogonalTiledMapRenderer renderer;

    private World world;

    private ButtonComponent multiPLayer;

    private ButtonComponent onePLayer;

    private Texture cenfotecoLetter;

    private Texture kingdomLetter;

    private Box2DDebugRenderer b2dr;
    private Stage stage;

    private Music music;

    private final GameController gameController = GameController.getInstance();

    public Menu(){
        this.game = ReinoCenfoteco.getInstance();

        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(1600, 1600, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("MenuGame.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();
        this.stage = new Stage(gamePort);
        this.stage.getRoot().setX(120);
        this.stage.getRoot().setY(100);
        music = ReinoCenfoteco.manager.get("audio/MenuMusic.mp3", Music.class);
        music.setLooping(true);
        music.play();
        defineTextures();

        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(stage);
    }


    private void update(){
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void show() {

    }

    public void defineTextures(){
       onePLayer =  new ButtonComponent(this.stage, "multiPlayer.png", 500, 500, 425,750, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameController.choosingStartPlayer();
                game.setPlayScreen(music);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

       this.cenfotecoLetter = new Texture("CenfotecoLetter.png");
        this.kingdomLetter = new Texture("kingdomsLetter.png");

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

        game.batch.draw(this.cenfotecoLetter, 550, 1400, 450, 100);
        game.batch.draw(this.kingdomLetter, 600, 1300, 360, 60);
        game.batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        System.out.println("" + width + "x" + height);
        gamePort.update(width, height);
        stage.draw();
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
        this.stage.dispose();
    }
}
