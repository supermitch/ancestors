package com.mitch.ancestors;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;

public class Monster {

    // States
    static final int RESTING = 0;
    static final int MOVING = 1;
    static final int HUNTING = 2;

    float WIDTH;
    float HEIGHT;
    float MAX_VELOCITY;
    float ACCEL;

    public String assetName;
    public String species;
    public Rectangle bounds;
    public Vector2 position;
    public Vector2 velocity;
    Vector2 accel;
    int hp;

    int state = RESTING;
    float stateTime = 0;
    float restTime;
    float moveTime;

    public Monster(float start_x, float start_y, String _species) {
        position = new Vector2(start_x, start_y);
        bounds = new Rectangle(position.x - WIDTH/2, position.y - HEIGHT/2, WIDTH, HEIGHT);
        velocity = new Vector2();
        accel = new Vector2();

        species = _species;
        if (species.equals("slime")) {
            assetName = "slime_1";
            hp = 5;
            ACCEL = 200;
            MAX_VELOCITY = 20.0f;
            restTime = 0.2f;
            moveTime = 1.0f;

        } else if (species.equals("spider")) {
            assetName = "spider_1";
            hp = 10;
            ACCEL = 500;
            MAX_VELOCITY = 500.0f;
            restTime = 3.0f;
            moveTime = 3.0f;
        }
    }

    /**
     * Once per frame update of position
     * @param deltaTime
     */
    public void update (float deltaTime) {

        actBehavior(deltaTime);

        if (accel.len() > 0) {
            accel.scl(deltaTime);  // dv = a*dt
            velocity.add(accel.x, accel.y);  // vf = vi + dv

            // Limit velocity to max
            if (velocity.len() > MAX_VELOCITY) {
                velocity.nor().scl(MAX_VELOCITY);
            }
        }

        position.add(velocity.x*deltaTime, velocity.y*deltaTime);  // d = d*dt

        // TODO: Move bounds first to check collisions
        bounds.x = position.x - bounds.width / 2;
        bounds.y = position.y - bounds.height / 2;
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
                    stateTime = 0;
                }
                break;
            case MOVING:
                if (stateTime > moveTime) {
                    state = RESTING;
                    accel.set(0, 0);
                    velocity.set(0, 0);
                    stateTime = 0;
                }
                break;
            default: break;
        }
        stateTime += deltaTime;
    }
}
