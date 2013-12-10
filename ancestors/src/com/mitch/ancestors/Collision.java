package com.mitch.ancestors;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Collision {

    static Rectangle inter = new Rectangle();

    public static void check(World _world) {
        int i = 0, j = 0;

        World world = _world;
        // Hero collisions
        for (Item item : world.items) {
            collisionCheck(world.hero, item);
        }
        for (Monster monster : world.monsters) {
            collisionCheck(world.hero, monster);
        }
        for (Human human : world.humans) {
            collisionCheck(world.hero, human);
        }

        // Monster collisions
        for (i=0; i < world.monsters.size; i++) {
            for (j=0; j < world.monsters.size; j++) {
                if (i != j) {
                    collisionCheck(world.monsters.get(i), world.monsters.get(j));
                }
                for (Item item : world.items) {
                    collisionCheck(world.monsters.get(i), item);
                }
                for (Human human : world.humans) {
                    collisionCheck(world.monsters.get(i), human);
                }
            }
        }

        // Human collisions
        for (i=0; i < world.humans.size; i++) {
            for (j=0; j < world.humans.size; j++) {
                if (i != j) {
                    collisionCheck(world.humans.get(i), world.humans.get(j));
                }
            }
            for (Item item : world.items) {
                collisionCheck(world.humans.get(i), item);
            }
        }
    }

    private static void collisionCheck(Entity obj1, Entity obj2) {
        if (Intersector.intersectRectangles(obj1.bounds, obj2.bounds, inter)) {
            // Get object Classes for echo
            String _class1 = obj1.getClass().toString();
            String sub1 = _class1.substring(_class1.lastIndexOf('.') + 1);
            String _class2 = obj2.getClass().toString();
            String sub2 = _class2.substring(_class2.lastIndexOf('.') + 1);
            System.out.println(sub1 + "-" + sub2 + " collision");
        }
    }


}
