package com.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture cursor;
    private Texture background;
    private Texture alien;
    private Texture bullet;

    private float playerX = 300;
    private float playerY = 50;
    private float bulletX = -1; // -1 indicates no active bullet
    private float bulletY = -1;
    private final int screenWidth = 1920;
    private final int screenHeight = 1080;

    @Override
    public void create() {
        background = new Texture(Gdx.files.internal("spacebackground.jpg"));
        cursor = new Texture(Gdx.files.internal("Untitled.png"));
        alien = new Texture(Gdx.files.internal("amogus.gif"));
        bullet = new Texture(Gdx.files.internal("PinkBullet.png"));
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        handlePlayerMovement();

        handleShooting();

        batch.begin();
        batch.draw(background, 0, 0, screenWidth, screenHeight);
        batch.draw(cursor, playerX, playerY);

        if (bulletY > -1) { // Draw bullet if active
            batch.draw(bullet, bulletX, bulletY);
        }

        batch.draw(alien, 250, 250); // Temporary static alien position
        batch.end();
    }

    private void handlePlayerMovement() {
        if (Gdx.input.isKeyPressed(Keys.H)) {
            playerX -= 16;
        } else if (Gdx.input.isKeyPressed(Keys.L)) {
            playerX += 16;
        }

        // Wrap-around logic
        if (playerX > screenWidth) {
            playerX = 0;
        } else if (playerX < 0) {
            playerX = screenWidth;
        }
    }

    private void handleShooting() {
        if (Gdx.input.isKeyJustPressed(Keys.K) && bulletY == -1) { // Fire new bullet
            bulletX = playerX + cursor.getWidth() / 2 - bullet.getWidth() / 2;
            bulletY = playerY + cursor.getHeight();
        }

        if (bulletY > -1) {
            bulletY += 20;
            if (bulletY > screenHeight) {
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        cursor.dispose();
        background.dispose();
        alien.dispose();
        bullet.dispose();
    }
}

