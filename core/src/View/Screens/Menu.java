package View.Screens;

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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ReinoCenfotecos;

public class Menu implements Screen {

    private ReinoCenfotecos game;
    private OrthographicCamera gameCam;

    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;

    private OrthogonalTiledMapRenderer renderer;

    private World world;

    private ButtonComponent multiPLayer;

    private ButtonComponent onePLayer;

    private ButtonComponent loadGame;

    private Texture cenfotecoLetter;

    private Texture kingdomLetter;

    private Box2DDebugRenderer b2dr;
    private Stage stage;

    private Music music;

    private final GameController gameController = GameController.getInstance();

    private InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();

    public Menu(){
        this.game = ReinoCenfotecos.getInstance();

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

        defineTextures();
    }


    private void update(){
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void show() {
        music = ReinoCenfotecos.manager.get("audio/MenuMusic.mp3", Music.class);
        music.setLooping(true);
        music.play();

        inputMultiplexer.addProcessor(stage);
    }

    public void defineTextures(){
       onePLayer =  new ButtonComponent(this.stage, "multiPlayer.png", 600, 500, 380,730, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameController.initialize();
                inputMultiplexer.removeProcessor(stage);
                music.stop();
                game.setPlayScreen(true);

                return super.touchDown(event, x, y, pointer, button);
            }
        });

       loadGame = new ButtonComponent(this.stage, "loadGame.png", 500, 450, 435, 400, new InputListener(){
           @Override
           public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               if(gameController.getMementoController().getGameStateSaved() != null) {
                   music.stop();
                   inputMultiplexer.removeProcessor(stage);
                   game.setPlayScreen(false);
               }

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
