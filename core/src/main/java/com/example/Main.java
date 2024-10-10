package com.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends ApplicationAdapter {
    private int desiredwidth;
    private int desiredheight;
    private SpriteBatch batch;
    private Texture cursor;
    private Texture background;
    float x = 50;
    float y = 50;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("galaxy.jpg"));
        cursor = new Texture(Gdx.files.internal("cursor2.png"));

        desiredwidth = Gdx.graphics.getWidth();
        desiredheight = Gdx.graphics.getHeight();

    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(cursor, x, y);
        batch.end();
        y = 10;

        if (Gdx.input.isKeyPressed(Keys.H)) {
            x = x - 4;
        } else if (Gdx.input.isKeyPressed(Keys.L)) {
            x = x + 4;
        } else if (Gdx.input.isKeyPressed(Keys.E) || Gdx.input.isKeyPressed(Keys.W)) {
            x = x + 8;
        } else if (Gdx.input.isKeyPressed(Keys.B)) {
            x = x - 8;
        }

        if (Gdx.input.isKeyPressed(Keys.H)) {
            x = x - 4;
        } else if (Gdx.input.isKeyPressed(Keys.L)) {
            x = x + 4;
        } else if (Gdx.input.isKeyPressed(Keys.E) || Gdx.input.isKeyPressed(Keys.W)) {
            x = x + 8;
        } else if (Gdx.input.isKeyPressed(Keys.B)) {
            x = x - 8;
        } else if (x > 800) {
            x = 0;
        } else if (x > 0) {
            x = 800;
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
        cursor.dispose();
    }
}
