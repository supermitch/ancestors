package com.mitch.ancestors;

import java.util.Random;

import com.badlogic.gdx.utils.Array;

public class World {
	
	public final Hero hero;
	public final Monster slime;
	public final Monster spider;
	int[][] tiles;
	
	public World() {
		this.hero = new Hero(0, 0);
		this.slime = new Monster(20, 20, "slime");
		this.spider = new Monster(50, 50, "spider");
		loadTiles();
	}
	
	public void update(float deltaTime) {
		hero.update(deltaTime);
		
	}
	
	private void loadTiles() {
		Random rnd = new Random();
		tiles = new int[100][100];
		for (int x=0; x<100; x++) {
			for (int y=0; y<100; y++) {
				// TODO: Replace random tiles with actual map values
				tiles[x][y] = rnd.nextInt(10);
			}
		}
	}
}
