package com.mitch.ancestors;

import java.lang.Math;

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
		camera.position.set(world.hero.position.x, world.hero.position.y, 0);
		
		font = new BitmapFont();	// Use Arial default
		
		batch = new SpriteBatch(5460);
		
		
	}
	
	public void render() {
		
		// Cam follow hero
		camera.position.set(world.hero.position.x, world.hero.position.y, 0);
		camera.update();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
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
		float x = world.hero.position.x;
		float y = world.hero.position.y;
		
		for (Tile tile : world.tiles) {
			if (Math.abs(tile.position.x - x) <= 240 + 20 && Math.abs(tile.position.y - y) <= 160 + 20) {
				batch.draw(tile.asset, tile.position.x, tile.position.y);
			}
		}
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
			batch.draw(Assets.monsters.get(monster.assetName), monster.position.x, monster.position.y);
		}
	}
	
	private void renderUI() {
		font.draw(batch, "World Screen!", 200, 240);
	}
	

}
