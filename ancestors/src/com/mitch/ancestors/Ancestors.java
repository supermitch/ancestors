package com.mitch.ancestors;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mitch.ancestors.screens.MenuScreen;
import com.mitch.ancestors.screens.PauseScreen;
import com.mitch.ancestors.screens.SplashScreen;
import com.mitch.ancestors.screens.WorldScreen;

public class Ancestors extends Game {
	
	public SpriteBatch batch;
	public BitmapFont font;
	
	public WorldScreen worldScreen;
	public PauseScreen pauseScreen;
	public MenuScreen menuScreen;
	
	public void create() {

		font = new BitmapFont();	// Use Arial default
		batch = new SpriteBatch();
		
		Assets.load();
		
		worldScreen = new WorldScreen(this);
		pauseScreen = new PauseScreen(this);
		menuScreen = new MenuScreen(this);
		
		setScreen(new SplashScreen(this));
	}
	
	public void render() {
		super.render();
	}
	
	public void dispose() { }
	
	public void resize(int width, int height) { }
	
	public void pause() { }
	
	public void resume() { }
}