package com.mitch.ancestors;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    // HashMaps containing textures
    public static HashMap<String, Texture> items = new HashMap<String, Texture>();
    public static HashMap<String, Texture> maps = new HashMap<String, Texture>();
    public static HashMap<String, Texture> humans = new HashMap<String, Texture>();
    public static HashMap<String, Texture> monsters = new HashMap<String, Texture>();

    public static Texture grass = loadTexture("images/map/grass_1.png");

    public static void load() {

        maps.put("grass_1", loadTexture("images/map/grass_1.png"));
        maps.put("ocean_1", loadTexture("images/map/ocean_1.png"));
        maps.put("rock_1", loadTexture("images/map/rock_1.png"));
        maps.put("sand_1", loadTexture("images/map/sand_1.png"));

        items.put("key_1", loadTexture("images/item/key_1.png"));
        items.put("sword_1", loadTexture("images/item/sword_1.png"));

        humans.put("hero_1", loadTexture("images/human/hero_1.png"));
        humans.put("kid_1", loadTexture("images/human/kid_1.png"));

        monsters.put("slime_1", loadTexture("images/monster/slime_1.png"));
        monsters.put("spider_1", loadTexture("images/monster/spider_1.png"));
    }

    public static Texture loadTexture(String filename) {
        return new Texture(Gdx.files.internal(filename));
    }
}
