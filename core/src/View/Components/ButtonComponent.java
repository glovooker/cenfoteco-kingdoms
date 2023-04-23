package View.Components;

import Model.Coordinate;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class ButtonComponent
{
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton button;

    public ButtonComponent(Stage stage, String path, int width, int height, int positionX, int positionY, InputListener inputListener){
        myTexture = new Texture(Gdx.files.internal(path));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable);
        stage.addActor(button);
        button.setSize(width,height);
        button.setPosition(positionX,positionY);
        button.addListener(inputListener);
    }

    public ButtonComponent(Stage stage, String path, int width, int height, int positionX, int positionY){
        myTexture = new Texture(Gdx.files.internal(path));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable);
        stage.addActor(button);
        button.setSize(width,height);
        button.setPosition(positionX,positionY);
    }

    public void setInputListener(InputListener inputListener) {
        button.addListener(inputListener);
    }

    public void setPosition(Coordinate coordinate) {
        this.setPosition(coordinate, 1);
    }

    public void setPosition(Coordinate coordinate, int scale) {
        button.setPosition(coordinate.getX() * scale, coordinate.getY() * scale);
    }

    public ImageButton getButton() {
        return button;
    }

}