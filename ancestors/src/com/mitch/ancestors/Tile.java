package com.mitch.ancestors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tile {

    boolean walkable;
    String tileType;
    public Texture asset;

    public static final float WIDTH = 1.0f;
    public static final float HEIGHT = 1.0f;

    public final Rectangle bounds;
    public final Vector2 position;

    public Tile(int x, int y, String _type) {
        position = new Vector2(x * 16, y * 16);
        bounds = new Rectangle(position.x - WIDTH/2, position.y - HEIGHT/2, WIDTH, HEIGHT);	

        tileType = _type;
        if (tileType.equals("grass")) {
            this.asset = Assets.maps.get("grass_1");
            walkable = true;
        } else if (tileType.equals("sand")) {
            this.asset = Assets.maps.get("sand_1");
            walkable = true;
        } else if (tileType.equals("rock")) {
            this.asset = Assets.maps.get("rock_1");
            walkable = false;
        } else {
            System.out.println("Invalid Tile type: " + tileType);
        }
    }

}
