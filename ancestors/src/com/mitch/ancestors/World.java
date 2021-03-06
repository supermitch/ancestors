package com.mitch.ancestors;

import java.util.Random;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;

public class World {

    public final Hero hero;

    Array<Item> items = new Array<Item>(false, 1);
    Array<Monster> monsters = new Array<Monster>(false, 1);
    Array<Human> humans = new Array<Human>(false, 1);
    Array<Tile> tiles = new Array<Tile>(false, 100_100);


    public World() {
        hero = new Hero(240, 160);

        items.add(new Item(250, 85, "key"));
        items.add(new Item(190, 250, "sword"));

        monsters.add(new Monster(20, 20, "slime"));
        monsters.add(new Monster(50, 50, "spider"));
        monsters.add(new Monster(100, 100, "slime"));

        humans.add(new Human(74, 38, "kid"));

        loadTiles();
    }

    public void update(float deltaTime) {

        Collision.check(this);

        for (Tile tile: tiles) {
            tile.update(deltaTime);
        }

        hero.update(deltaTime);

        for (Item item: items) {
            item.update(deltaTime);
            if (!item.inWorld) items.removeValue(item, true);
        }
        for (int i = 0; i < monsters.size; i++) {
            Monster monster = monsters.get(i);
            monster.update(deltaTime);
            if (!monster.inWorld) monsters.removeValue(monster, true);
        }
        for (Human human: humans) {
            human.update(deltaTime);
            if (!human.inWorld) humans.removeValue(human, true);
        }

    }

    private void loadTiles() {

        String[] tileTypes = {"grass", "sand", "rock"};
        Random rnd = new Random();

        for (int x=-100; x < 100; x++) {
            for (int y=-100; y < 100; y++) {
                tiles.add(new Tile(x, y, tileTypes[rnd.nextInt(tileTypes.length)]));
            }
        }
    }

    public Array<Entity> findNearestEntities(Entity entity, Vector2 position) {
        int count = 20;

        Array<Entity> nearest = new Array<Entity>(false, count);

        if (hero != entity) {
            nearest.add(hero);
        }

        for (Item item: items) {
            if (item == entity) continue;
            if (nearest.size < count) {
                nearest.add(item);
            }
        }
        for (Monster monster: monsters) {
            if (monster == entity) continue;
            if (nearest.size < count) {
                nearest.add(monster);
            }
        }
        for (Human human: humans) {
            if (human == entity) continue;
            if (nearest.size < count) {
                nearest.add(human);
            }
        }
        return nearest;
    }
}
