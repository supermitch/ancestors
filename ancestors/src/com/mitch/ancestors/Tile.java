package com.mitch.ancestors;

import com.badlogic.gdx.graphics.Texture;

public class Tile extends Entity {

    boolean walkable;
    String tileType;
    public Texture asset;

    public Tile(int x, int y, String type) {
        position.set(x * WIDTH, y * HEIGHT);
        bounds.setCenter(position.x - WIDTH/2, position.y - HEIGHT/2);

        tileType = type;
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
