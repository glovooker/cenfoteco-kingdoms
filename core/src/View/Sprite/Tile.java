package View.Sprite;

import View.Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile extends Sprite {
    private PlayScreen screen;

    public Tile(PlayScreen screen) {
        super(screen.getBoardAtlas().findRegion("Tile"));
        this.screen = screen;

    }

    public void setTextureRegion(String newTextureRegion){
         TextureRegion textureRegion = screen.getBoardAtlas().findRegion(newTextureRegion);
        this.setRegion(textureRegion);
    }



}
