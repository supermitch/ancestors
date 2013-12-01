package com.mitch.ancestors.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.mitch.ancestors.Ancestors;
import com.mitch.ancestors.World;
import com.mitch.ancestors.WorldRenderer;

public class WorldScreen implements Screen {

    Ancestors game;
    World world;
    WorldRenderer worldRenderer;

    public WorldScreen(Ancestors my_game) {
        this.game = my_game;

        world = new World();
        worldRenderer = new WorldRenderer(world);
    }

    @Override
    public void render(float delta) {
        processKeys();
        update(delta);
        worldRenderer.render();
    }

    /**
     * Screen handling related keyPresses
     */
    void processKeys() {
        if (Gdx.input.isKeyPressed(Keys.P)) {
            game.setScreen(game.pauseScreen);
        } else if (Gdx.input.isKeyPressed(Keys.M)) {
            game.setScreen(game.menuScreen);
        } else if (Gdx.input.isKeyPressed(Keys.Q)) {
            Gdx.app.exit();
        }
    }

    public void update (float deltaTime) {
        world.update(deltaTime);
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }
}
