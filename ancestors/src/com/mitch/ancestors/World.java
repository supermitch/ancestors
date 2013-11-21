package com.mitch.ancestors;

import com.badlogic.gdx.Gdx;

public class World {
	public final Hero hero;
	
	public World() {
		this.hero = new Hero(0, 0);
	}
	
	public void update(float deltaTime) {
		hero.update(deltaTime);
	}
}
