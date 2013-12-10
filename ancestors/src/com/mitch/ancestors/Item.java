package com.mitch.ancestors;

import com.badlogic.gdx.math.Vector2;

public class Item extends Entity {

    public String assetName;
    public String itemName;

    float weight;

    public Item(float x, float y, String name) {
        position.set(x, y);
        bounds.setCenter(position);

        itemName = name;
        if (itemName.equals("key")) {
            assetName = "key_1";
            this.weight = 0.5f;
        } else if (itemName.equals("sword")) {
            assetName = "sword_1";
            this.weight = 10.0f;
        }
    }

    public void update (float deltaTime) {
        // Can items move?
        bounds.setCenter(position);
    }
}
