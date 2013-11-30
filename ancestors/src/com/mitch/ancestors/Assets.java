package com.mitch.ancestors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	// humans:
	public static Texture hero_1, kid_1;
	
	// map:
	public static Texture grass_1, ocean_1, rock_1, sand_1;
	
	// items:
	public static Texture key_1, sword_1;
	
	// monsters:
	public static Texture slime_1, spider_1;
	
	public static Texture loadTexture(String filename) {
		return new Texture(Gdx.files.internal(filename));
	}
	
	public static void load() {
		
		hero_1 = loadTexture("images/human/hero_1.png");
		kid_1 = loadTexture("images/human/kid_1.png");
		
		grass_1 = loadTexture("images/map/grass_1.png");
		ocean_1 = loadTexture("images/map/ocean_1.png");
		rock_1 = loadTexture("images/map/rock_1.png");
		sand_1 = loadTexture("images/map/sand_1.png");
		
		key_1 = loadTexture("images/item/key_1.png");
		sword_1 = loadTexture("images/item/sword_1.png");
		
		slime_1 = loadTexture("images/monster/slime_1.png");
		spider_1 = loadTexture("images/monster/spider_1.png");
	}
}
