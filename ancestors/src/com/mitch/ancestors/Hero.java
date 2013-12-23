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
        //Vector2 nextPos = new Vector2(position).add(deltaPos);

        deltaPos = Collision.tryMove(this, bounds, deltaPos);
        position.add(deltaPos);  // d = d*dt
    }

    public void stopMoving() {
        accel.set(0, 0);
        velocity.set(0, 0);
    }


    public void fixCollision(Entity entity) {


        System.out.println(velocity.x);
        System.out.println(bounds.x);
        System.out.println(bounds.width/2);
        System.out.println(entity.bounds.x - entity.bounds.width/2);
        if (velocity.x > 0 && (bounds.x + bounds.width/2) > (entity.bounds.x - entity.bounds.width/2)) {
            // We need to move left to just outside contact point
            System.out.println("moving back");
            position.x = entity.position.x - entity.bounds.width/2 - bounds.width/2;
        } else if (velocity.x < 0 && bounds.x < entity.bounds.x) {
            // We need to move right to outside contact point
            position.x = entity.position.x + entity.bounds.width/2 + bounds.width/2;
            System.out.println("moving forward");
        }
        if (velocity.y > 0 && bounds.y > entity.bounds.y) {
            // We need to move down to just outside contact
            position.y = entity.position.y - entity.bounds.height/2 - bounds.height/2;
        } else if (velocity.y < 0 && bounds.y < entity.bounds.y) {
            // We need to move UP to just outside contact
            position.y = entity.bounds.y + entity.bounds.height/2 + bounds.height/2;
        }
        if (velocity.len() == 0.0) {
            // Do we need to move? Only if they are also immobile.
        }

        stopMoving();
        System.out.println("Stopping");
    }

    public void pickup(Item item) {
        item.getCollected();
    }
}
