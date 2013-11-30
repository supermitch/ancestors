package com.mitch.ancestors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;

public class Monster {

	float WIDTH;
	float HEIGHT;
	float VELOCITY;

	public Texture asset;
	public String species;
	public final Rectangle bounds;
	public final Vector2 position;
	public final Vector2 velocity;
	int hp;
	
	public Monster(float start_x, float start_y, String _species) {
		position = new Vector2(start_x, start_y);
		bounds = new Rectangle(position.x - WIDTH/2, position.y - HEIGHT/2, WIDTH, HEIGHT);
		velocity = new Vector2();

		species = _species;
		if (species.equals("slime")) {
			asset = Assets.slime_1;
			this.hp = 5;
		} else if (species.equals("spider")) {
			asset = Assets.spider_1;
			this.hp = 10;
		}
	}
	
	public void update (float deltaTime) {
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - bounds.width / 2;
		bounds.y = position.y - bounds.height / 2;
	}
}
