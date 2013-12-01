package com.mitch.ancestors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Item {

	float WIDTH;
	float HEIGHT;
	float VELOCITY;

	public String assetName;
	public String itemName;
	public final Rectangle bounds;
	public final Vector2 position;
	
	float weight;
	
	public Item(float start_x, float start_y, String _name) {
		position = new Vector2(start_x, start_y);
		bounds = new Rectangle(position.x - WIDTH/2, position.y - HEIGHT/2, WIDTH, HEIGHT);

		itemName = _name;
		if (itemName.equals("key")) {
			assetName = "key_1";
			this.weight = 0.5f;
		} else if (itemName.equals("sword")) {
			assetName = "sword_1";
			this.weight = 10.0f;
		}
	}
	
	public void update (float deltaTime) {
		// Can items move?
	}
}
