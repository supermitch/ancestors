package com.mitch.ancestors;

import com.badlogic.gdx.math.Vector2;

public class Human extends Entity {

    public final float VELOCITY = 100.0f;

    public String assetName;
    public String name;
    public final Vector2 velocity;

    public Human(float start_x, float start_y, String name) {
        position.set(start_x, start_y);
        bounds.setCenter(position.x - WIDTH/2, position.y - HEIGHT/2);

        velocity = new Vector2();
        this.name = name;
        if (name.equals("kid")) {
            assetName = "kid_1";
        }
    }

    public void update (float deltaTime) {
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        update_bounds(position);
    }

    public void update_bounds(Vector2 position) {
        bounds.x = position.x - bounds.width / 2;
        bounds.y = position.y - bounds.height / 2;
    }
}
