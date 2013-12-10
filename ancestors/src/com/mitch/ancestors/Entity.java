package com.mitch.ancestors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {
    public Texture asset;

    public float WIDTH = 16.0f;
    public float HEIGHT = 16.0f;

    public Vector2 position = new Vector2();
    public Rectangle bounds = new Rectangle(0 - WIDTH/2, 0 - HEIGHT/2,
                                            WIDTH, HEIGHT);
}
