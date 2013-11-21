package com.mitch.ancestors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class WorldScreen implements Screen {
	
	Ancestors game;
	World world;
	OrthographicCamera camera;

	public WorldScreen(Ancestors my_game) {
		this.game = my_game;
		
		world = new World();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 480, 320);
	}

	@Override
	public void render(float delta) {
		
		update(delta);
		Gdx.gl.glClearColor(0, 0.2f, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.font.draw(game.batch, "World Screen!", 200, 240);
		game.batch.draw(Assets.hero, world.hero.position.x, world.hero.position.y);
		game.batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.P)) {
			game.setScreen(game.pauseScreen);
		} 
		if (Gdx.input.isKeyPressed(Keys.M)) {
			game.setScreen(game.menuScreen);
		}
		if (Gdx.input.isKeyPressed(Keys.Q)) {
			Gdx.app.exit();
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			world.hero.velocity.x = -world.hero.VELOCITY;
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			//Gdx.app.log("MyTag", "Moving RIGHT");
			world.hero.velocity.x = world.hero.VELOCITY;
		} else {
			world.hero.velocity.x = 0.0f;
		}
		
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			world.hero.velocity.y = world.hero.VELOCITY;
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			world.hero.velocity.y = - world.hero.VELOCITY;
		} else {
			world.hero.velocity.y = 0.0f;
		}
		
	}

	public void update (float deltaTime) {
		world.update(deltaTime);
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
