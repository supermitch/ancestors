package com.mitch.ancestors.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mitch.ancestors.Ancestors;

public class PauseScreen implements Screen {

    Ancestors game;
    OrthographicCamera camera;

    public PauseScreen(Ancestors my_game) {
        this.game = my_game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 320);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Paused...", 200, 240);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            game.setScreen(game.worldScreen);
        }
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
