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
    private SpriteBatch batch;
    private Texture image;
    private Texture background;
    float x = 50;
    float y = 50;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("BinaryGameBackground.png"));
        image = new Texture(Gdx.files.internal("Untitled.png"));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(image, x, y);
        batch.end();
        if (Gdx.input.isKeyPressed(Keys.K)) {
            y = y + 5;
        } else if (Gdx.input.isKeyPressed(Keys.J)) {
            y = y - 5;
        } else if (Gdx.input.isKeyPressed(Keys.H)) {
            x = x - 5;
        } else if (Gdx.input.isKeyPressed(Keys.L)) {
            x = x + 5;
        } else if (Gdx.input.isKeyPressed(Keys.E) || Gdx.input.isKeyPressed(Keys.W)) {
            x = x + 10;
        } else if (Gdx.input.isKeyPressed(Keys.B)) {
            x = x - 10;
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
