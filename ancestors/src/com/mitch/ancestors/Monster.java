package com.mitch.ancestors;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class Monster extends Entity {

    // States
    static final int RESTING = 0;
    static final int MOVING = 1;
    static final int HUNTING = 2;

    float MAX_VELOCITY;
    float ACCEL;

    public String assetName;
    public String species;
    public Vector2 velocity;
    Vector2 accel;
    int hp;

    int state = RESTING;
    float stateTime = 0;
    float restTime;
    float moveTime;

    public Monster(float start_x, float start_y, String _species) {
        position.set(start_x, start_y);
        bounds.setCenter(position);
        velocity = new Vector2();
        accel = new Vector2();

        species = _species;
        if (species.equals("slime")) {
            assetName = "slime_1";
            hp = 5;
            ACCEL = 20.0f;
            MAX_VELOCITY = 15.0f;
            restTime = 0.2f;
            moveTime = 1.0f;

        } else if (species.equals("spider")) {
            assetName = "spider_1";
            hp = 10;
            ACCEL = 500.0f;
            MAX_VELOCITY = 100.0f;
            restTime = 3.0f;
            moveTime = 1.0f;
        }
    }

    /**
     * Once per frame update of everything
     * @param deltaTime
     */
    public void update (float deltaTime) {
        actBehavior(deltaTime);
        updatePosition(deltaTime);
        bounds.setCenter(position);
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

    public void actBehavior(float deltaTime) {
        Random rand = new Random();

        switch (state) {
            case RESTING:
                if (stateTime > restTime) {
                    state = MOVING;
                    accel.x = rand.nextFloat() * 2 - 1;  // -1 to 1
                    accel.y = rand.nextFloat() * 2 - 1;
                    accel.nor().scl(ACCEL);
                    stateTime = 0.0f;
                } else {
                    // still resting...
                }
                break;
            case MOVING:
                if (stateTime > moveTime) {
                    state = RESTING;
                    accel.set(0, 0);
                    velocity.set(0, 0);
                    stateTime = 0.0f;
                } else {
                    // still moving...
                    // Continue accel'ing in current direction
                    accel.set(velocity.x, velocity.y).nor().scl(ACCEL);
                }
                break;
            default: break;
        }
        stateTime += deltaTime;
    }

}
