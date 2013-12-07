package com.mitch.ancestors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Hero {

    public static final float WIDTH = 1.0f;
    public static final float HEIGHT = 1.0f;
    public final float MAX_VELOCITY = 90.0f;
    public final float ACCEL = 600f;

    public Texture asset;
    public Rectangle bounds;
    public Vector2 position;
    public Vector2 velocity;
    public Vector2 accel;

    /**
     * Constructor
     * @param start_x
     * @param start_y
     */
    public Hero(float start_x, float start_y) {
        position = new Vector2(start_x, start_y);
        bounds = new Rectangle(position.x - WIDTH/2, position.y - HEIGHT/2, WIDTH, HEIGHT);
        velocity = new Vector2();
        accel = new Vector2();
        asset = Assets.humans.get("hero_1");
    }

    /**
     * Once per frame update of position
     * @param deltaTime
     */
    public void update (float deltaTime) {
        processKeys();

        if (accel.len() > 0) {
            accel.scl(deltaTime);  // dv = a*dt
            velocity.add(accel.x, accel.y);  // vf = vi + dv

            // Limit velocity to max
            if (velocity.len() > MAX_VELOCITY) {
                velocity.nor().scl(MAX_VELOCITY);
            }
        }

        position.add(velocity.x * deltaTime, velocity.y * deltaTime);  // d = d*dt

        // TODO: Move bounds first to check collisions
        update_bounds(position);
    }

    /**
     * Look for Hero related key inputs
     */
    void processKeys() {
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            accel.x = -ACCEL;
        } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            accel.x = ACCEL;
        } else {
            velocity.x = 0.0f;
            accel.x = 0.0f;
        }

        if (Gdx.input.isKeyPressed(Keys.UP)) {
            accel.y = ACCEL;
        } else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            accel.y = -ACCEL;
        } else {
            velocity.y = 0.0f;
            accel.y = 0.0f;
        }
    }

    public void update_bounds(Vector2 position) {
        bounds.x = position.x - bounds.width / 2;
        bounds.y = position.y - bounds.height / 2;
    }
}
