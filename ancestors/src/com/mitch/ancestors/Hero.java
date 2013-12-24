package com.mitch.ancestors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Hero extends Entity {

    public final float MAX_VELOCITY = 90.0f;
    public final float ACCEL = 600f;

    public Texture asset;
    public Vector2 velocity;
    public Vector2 accel;

    /**
     * Constructor
     * @param start_x
     * @param start_y
     */
    public Hero(float start_x, float start_y) {
        position.set(start_x, start_y);
        bounds.setCenter(position);
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
        updatePosition(deltaTime);
        bounds.setCenter(position);
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

    /** Update position & velocity vectors given acceleration and deltaTime
     * 
     * @param deltaTime
     */
    private void updatePosition(float deltaTime) {
        if (accel.len() > 0) {
            accel.scl(deltaTime);  // dv = a * dt
            velocity.add(accel.x, accel.y);  // vf = vi + dv

            // Limit velocity to max
            if (velocity.len() > MAX_VELOCITY) {
                velocity.nor().scl(MAX_VELOCITY);
            }
        }
        Vector2 deltaPos = new Vector2(velocity.x * deltaTime,
                                    velocity.y * deltaTime);

        Vector2 newDelta = Collision.tryMove(this, bounds, deltaPos);
        if (!newDelta.equals(deltaPos)) {  // We hit something
            stopMoving();
        }
        position.add(newDelta);  // d = d*dt
    }

    public void stopMoving() {
        accel.set(0, 0);
        velocity.set(0, 0);
    }

    public void pickup(Item item) {
        item.getCollected();
    }
}
