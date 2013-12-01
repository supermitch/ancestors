package com.mitch.ancestors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Human {

    public static final float WIDTH = 1.0f;
    public static final float HEIGHT = 1.0f;
    public final float VELOCITY = 100.0f;

    public String assetName;
    public final Rectangle bounds;
    public final Vector2 position;
    public final Vector2 velocity;

    public Human(float start_x, float start_y) {
        position = new Vector2(start_x, start_y);
        bounds = new Rectangle(position.x - WIDTH/2, position.y - HEIGHT/2, WIDTH, HEIGHT);
        velocity = new Vector2();
        assetName = "kid_1";
    }

    public void update (float deltaTime) {
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.x = position.x - bounds.width / 2;
        bounds.y = position.y - bounds.height / 2;
    }
}
