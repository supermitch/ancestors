package com.mitch.ancestors;

public class Item extends Entity {

    float VELOCITY;

    public String assetName;
    public String itemName;

    float weight;

    public Item(float start_x, float start_y, String name) {
        position.set(start_x, start_y);
        bounds.setCenter(position.x - WIDTH/2, position.y - HEIGHT/2);

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
    }
}
