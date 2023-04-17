package BL.prototype;

import View.Screens.PlayScreen;
import View.Sprite.Tile;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TileActor extends Actor implements Clonable<TileActor> {
    private final Tile tile;

    private final PlayScreen screen;

    public static int SIZE = 50;

    private boolean isBusy;


    public TileActor(PlayScreen screen) {
        this.screen = screen;
        this.tile = new Tile(this.screen);
        this.tile.setSize(TileActor.SIZE,TileActor.SIZE);
        this.isBusy = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.tile.setPosition(this.getX(), this.getY());
        this.tile.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public TileActor clone() {
        return new TileActor(this.screen);
    }

    public void setRegionTexture(String newRegionTexture){
        this.tile.setTextureRegion(newRegionTexture);
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    @Override
    public String toString() {
        return "TileActor{" +
                "tile=" + tile +
                ", screen=" + screen +
                ", isBusy=" + isBusy +
                '}';
    }
}
