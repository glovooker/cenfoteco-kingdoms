package View.Sprite;

import View.Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Castle extends Sprite {
    public Castle(PlayScreen screen, String img){
        super(screen.getCastlesAtlas().findRegion(img));
    }
}
