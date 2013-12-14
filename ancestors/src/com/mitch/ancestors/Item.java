package com.mitch.ancestors;

import com.badlogic.gdx.math.Vector2;

public class Item extends Entity {

    public String assetName;
    public String itemName;
    public String pickupSound;

    float weight;

    boolean inWorld = true;  // Appear in the world? (Set False to remove)

    public Item(float x, float y, String name) {
        position.set(x, y);
        bounds.setCenter(position);

        itemName = name;
        if (itemName.equals("key")) {
            assetName = "key_1";
            pickupSound = "pickup";
            weight = 0.5f;
        } else if (itemName.equals("sword")) {
            assetName = "sword_1";
            pickupSound = "pickup";
            weight = 10.0f;
        }
    }

    public void update (float deltaTime) {
        bounds.setCenter(position);
    }

    /**
     * Function is called when the item is picked up.
     */
    public void getCollected() {
        inWorld = false;
        playPickupSound();
    }

    public void playPickupSound() {
        Assets.sounds.get(pickupSound).play(1.0f);
    }
}
