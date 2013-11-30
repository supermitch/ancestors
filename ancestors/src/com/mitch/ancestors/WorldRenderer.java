package com.mitch.ancestors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class WorldRenderer {
	
	World world;
	OrthographicCamera camera;
	SpriteBatch batch;
	BitmapFont font;
	
	public WorldRenderer(World world) {
		this.world = world;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 480, 320);
		font = new BitmapFont();	// Use Arial default
		batch = new SpriteBatch(5460);
	}
	
	public void render() {

		Gdx.gl.glClearColor(0, 0.2f, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//camera.update();
	
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		renderMap();
		renderItems();
		renderHero();
		renderHumans();
		renderMonsters();
		renderUI();

		batch.end();
	}
	
	private void renderMap() {
	}
	
	private void renderItems() {
	}
	
	private void renderHero() {
		batch.draw(world.hero.asset, world.hero.position.x, world.hero.position.y);
	}
	
	private void renderHumans() {
	}
	
	private void renderMonsters() {
		for (Monster monster : world.monsters){
			batch.draw(monster.asset, monster.position.x, monster.position.y);
		}
	}
	
	private void renderUI() {
		font.draw(batch, "World Screen!", 200, 240);
	}
	

}
