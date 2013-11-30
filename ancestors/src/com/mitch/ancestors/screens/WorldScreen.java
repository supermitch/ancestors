package com.mitch.ancestors.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mitch.ancestors.Ancestors;
import com.mitch.ancestors.Assets;
import com.mitch.ancestors.World;
import com.mitch.ancestors.WorldRenderer;

public class WorldScreen implements Screen {
	
	Ancestors game;
	World world;
	WorldRenderer worldRenderer;

	public WorldScreen(Ancestors my_game) {
		this.game = my_game;
		
		world = new World();
		worldRenderer = new WorldRenderer(world);

	}

	@Override
	public void render(float delta) {
		
		update(delta);
		worldRenderer.render();
		
		// Screen handling:
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
