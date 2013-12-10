package com.mitch.ancestors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {
    public Texture asset;

    public static float WIDTH = 16.0f;
    public static float HEIGHT = 16.0f;

    public Vector2 position = new Vector2(0, 0);
    public Rectangle bounds = new Rectangle(position.x - WIDTH/2,
                                            position.y - HEIGHT/2,
                                            WIDTH, HEIGHT);
}
