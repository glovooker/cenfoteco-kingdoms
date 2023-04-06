package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.logic.characters_fabric_method.products.Army;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	float elapsed;
	//CharacterManager characterManager = new CharacterManager();

	@Override
	public void create () {
		batch = new SpriteBatch();
//		img = new Texture("sprites/tank/dragon/dragon-iddle.gif");


//		for (int i = 0; i < 5; i++){
//			characterManager.createArmy("tank", "player");
//		}
	}

	@Override
	public void render () {
		elapsed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(1, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
//		Army army = characterManager.getArmyPlayerList().get(1);
//		String _type = army.getCharacterType();
//		String _class = army.getCharacterClass();
//
//		batch.draw(getAnimation("sprites/"+_type+"/"+_class+"/"+_class+"-iddle.gif").getKeyFrame(elapsed), 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		img.dispose();
	}

	public Animation<TextureRegion> getAnimation(String path) {
		return GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal(path).read());
	}
}
