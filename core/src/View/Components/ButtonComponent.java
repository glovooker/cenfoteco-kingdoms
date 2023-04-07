package View.Components;

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

    public ImageButton getButton() {
        return button;
    }

    /* public ButtonComponent(Stage stage, FigureActor actor,  InputListener inputListener){
        myTexRegionDrawable = new TextureRegionDrawable(actor.getRegionTexture());
        button = new ImageButton(myTexRegionDrawable);
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);
        button.addListener(inputListener);
    }

    */




}