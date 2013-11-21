package com.mitch.ancestors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	public static Texture hero;
	public static Texture key;
	
	public static Texture loadTexture(String filename) {
		return new Texture(Gdx.files.internal(filename));
	}
	
	public static void load() {
		hero = loadTexture("data/hero_1.png");
	}
}
