package com.mitch.ancestors;

import java.lang.Math;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class WorldRenderer {

    static boolean RENDER_BOUNDS = true;
    World world;
    OrthographicCamera camera;
    SpriteBatch batch;
    BitmapFont font;
    ShapeRenderer shapeRenderer;

    public WorldRenderer(World world) {
        this.world = world;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 320);
        camera.position.set(world.hero.position.x, world.hero.position.y, 0);

        font = new BitmapFont();	// Use Arial default

        batch = new SpriteBatch(5460);
        shapeRenderer = new ShapeRenderer();

    }

    public void render() {

        // Cam follow hero
        camera.position.set(world.hero.position.x, world.hero.position.y, 0);
        camera.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeType.Line);

        batch.begin();

        renderMap();
        renderHero();
        renderItems();
        renderMonsters();
        renderHumans();
        renderUI();

        batch.end();
        shapeRenderer.end();
    }

    private void renderMap() {
        float x = world.hero.position.x;
        float y = world.hero.position.y;

        for (Tile tile : world.tiles) {
            if (Math.abs(tile.position.x - x) <= 240 + 20 && Math.abs(tile.position.y - y) <= 160 + 20) {
                batch.draw(tile.asset, tile.position.x, tile.position.y);
            }
        }
    }

    private void renderItems() {
        for (Item item : world.items){
            batch.draw(Assets.items.get(item.assetName),
                       item.position.x, item.position.y);
            if (RENDER_BOUNDS) {
                shapeRenderer.setColor(0, 1, 0, 1);
                shapeRenderer.rect(item.position.x, item.position.y,
                        item.WIDTH, item.HEIGHT);
            }
        }
    }

    private void renderHero() {
        batch.draw(world.hero.asset,
                   world.hero.position.x, world.hero.position.y);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(world.hero.position.x, world.hero.position.y,
                           world.hero.WIDTH, world.hero.HEIGHT);
    }

    private void renderMonsters() {
        for (Monster monster : world.monsters){
            batch.draw(Assets.monsters.get(monster.assetName),
                       monster.position.x, monster.position.y);
            shapeRenderer.setColor(1, 0, 0, 1);
            shapeRenderer.rect(monster.position.x, monster.position.y,
                               monster.WIDTH, monster.HEIGHT);
        }
    }

    private void renderHumans() {
        for (Human human : world.humans){
            batch.draw(Assets.humans.get(human.assetName),
                       human.position.x, human.position.y);
            shapeRenderer.setColor(0, 0, 1, 1);
            shapeRenderer.rect(human.position.x, human.position.y,
                               human.WIDTH, human.HEIGHT);
        }
    }
    private void renderUI() {
        font.draw(batch, "World Screen!", 200, 240);
    }

}
