package View.Actor;

import Screens.PlayScreen;
import View.Sprite.Castle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CastleActor extends Actor {

    private final Castle castle;

    private final PlayScreen screen;

    public static int SIZE = 60;

    private String img;

    private Model.Castle castleModel;

    public CastleActor(PlayScreen screen, String img) {
        this.img = img;
        this.screen = screen;
        this.castle = new Castle(this.screen, this.img);
        this.castle.setSize(CastleActor.SIZE, CastleActor.SIZE);
        this.castleModel = new Model.Castle();
    }

    public Model.Castle getCastleModel() {
        return castleModel;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.castle.setPosition(this.getX(), this.getY());
        this.castle.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
