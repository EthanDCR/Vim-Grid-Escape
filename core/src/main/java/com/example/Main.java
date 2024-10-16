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
    private Texture alien;
    float x = 50;
    float y = 50;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("galaxy.jpg"));
        cursor = new Texture(Gdx.files.internal("cursor22.png"));
        alien = new Texture(Gdx.files.internal("green-box.png"));

        desiredwidth = Gdx.graphics.getWidth();
        desiredheight = Gdx.graphics.getHeight();

    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(cursor, x, y);
        batch.draw(alien, 400, 550);
        batch.end();
        y = 10;

        if (Gdx.input.isKeyPressed(Keys.H)) {
            x = x - 2;
        } else if (Gdx.input.isKeyPressed(Keys.L)) {
            x = x + 2;
        } else if (Gdx.input.isKeyPressed(Keys.E) || Gdx.input.isKeyPressed(Keys.W)) {
            x = x + 4;
        } else if (Gdx.input.isKeyPressed(Keys.B)) {
            x = x - 4;
        }

        if (Gdx.input.isKeyPressed(Keys.H)) {
            x = x - 2;
        } else if (Gdx.input.isKeyPressed(Keys.L)) {
            x = x + 2;
        } else if (Gdx.input.isKeyPressed(Keys.E) || Gdx.input.isKeyPressed(Keys.W)) {
            x = x + 4;
        } else if (Gdx.input.isKeyPressed(Keys.B)) {
            x = x - 4;
        }

        if (x > 799) {
            x = 1;
        } else if (x < 1) {
            x = 799;
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
        cursor.dispose();
    }
}
