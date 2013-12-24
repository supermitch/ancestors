package com.mitch.ancestors;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Collision {

    static Rectangle inter = new Rectangle();
    static World world;
    
    public Collision(World world) {
        this.world = world;
    }

    public static void check(World _world) {
        int i = 0, j = 0;

        World world = _world;

        // Hero collisions
        for (Item item : world.items) {
            if (collisionCheck(world.hero, item)) {
                world.hero.pickup(item);
            }
        }
        for (Monster monster : world.monsters) {
            if (collisionCheck(world.hero, monster)) {
                // pass
            }
        }
        for (Human human : world.humans) {
            if (collisionCheck(world.hero, human)) {
                // pass
            };
        }

        // Monster collisions
        for (i=0; i < world.monsters.size; i++) {
            Monster monster = world.monsters.get(i);
            for (j=0; j < world.monsters.size; j++) {
                if (i != j) {
                    collisionCheck(monster, world.monsters.get(j));
                }
                for (Item item : world.items) {
                    collisionCheck(monster, item);
                }
                for (Human human : world.humans) {
                    collisionCheck(monster, human);
                }
            }
        }

        // Human collisions
        for (i=0; i < world.humans.size; i++) {
            Human human = world.humans.get(i);
            for (j=0; j < world.humans.size; j++) {
                if (i != j) {
                    collisionCheck(human, world.humans.get(j));
                }
            }
            for (Item item : world.items) {
                collisionCheck(human, item);
            }
        }
    }

    private static boolean collisionCheck(Entity obj1, Entity obj2) {
        if (Intersector.intersectRectangles(obj1.bounds, obj2.bounds, inter)) {
            String _class1 = obj1.getClass().toString();
            String sub1 = _class1.substring(_class1.lastIndexOf('.') + 1);
            String _class2 = obj2.getClass().toString();
            String sub2 = _class2.substring(_class2.lastIndexOf('.') + 1);
            System.out.println(sub1 + "-" + sub2 + " collision");
            return true;
        } else {
            return false;
        }
    }

    public static Vector2 tryMove(Entity entity, Rectangle currentBounds, Vector2 deltaPos) {

        Vector2 currentPos = new Vector2();
        currentBounds.getCenter(currentPos);
        Rectangle newBounds = new Rectangle(currentBounds);
        newBounds.setCenter(currentPos.add(deltaPos));

        Array<Entity> nearest = world.findNearestEntities(entity,
                currentBounds.getCenter(currentPos));
        for (int i = 0; i < nearest.size; i++) {
            Entity object = nearest.get(i);
            if (Intersector.intersectRectangles(newBounds,
                                                object.bounds,
                                                inter)) {
                if (!object.walkable) {
                    // Need to extrapolate to next position
                    float dX = deltaPos.x;
                    float dY = deltaPos.y;
                    float objEdge = 0.0f;
                    float entEdge = 0.0f;

                    System.out.println("[x: " + dX + "][y: " + dY + "]");

                    float gapX = 0.0f;
                    if (dX > 0.0) {
                        objEdge = object.bounds.x;
                        entEdge = currentBounds.x + currentBounds.getWidth();
                        gapX = objEdge - entEdge;
                        if (gapX < 0.0) gapX = 0.0f;
                    } else if (dX < 0.0) {
                        objEdge = object.bounds.x + object.bounds.getWidth();
                        entEdge = currentBounds.x;
                        gapX = objEdge - entEdge;
                        if (gapX > 0.0) gapX = 0.0f;
                    }
                    System.out.println("objEdge: " + objEdge + " | entEdge: " + entEdge);
                    float gapY = 0.0f;
                    if (dY > 0.0) {
                        objEdge = object.bounds.y;
                        entEdge = currentBounds.y + currentBounds.getHeight();
                        gapY = objEdge - entEdge;
                        if (gapY < 0.0) gapY = 0.0f;
                    } else if (dY < 0.0) {
                        objEdge = object.bounds.y + object.bounds.getHeight();
                        entEdge = currentBounds.y;
                        gapY = objEdge - entEdge;
                       if (gapY > 0.0) gapY = 0.0f;
                    }
                    System.out.println("currentBounds: " + currentBounds);
                    System.out.println("object bounds: " + object.bounds);
                    System.out.println("objEdge: " + objEdge + " | entEdge: " + entEdge);
                    deltaPos = new Vector2(gapX, gapY);
                    System.out.println("deltaPos: " + deltaPos);
                }
            }
        }
        return deltaPos;
    }

}
