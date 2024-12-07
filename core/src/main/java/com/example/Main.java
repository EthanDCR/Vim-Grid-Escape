package com.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture cursor;
    private Texture background;
    private Texture alienTexture;
    private Texture bullet;
    private float playerX = 300;
    private float playerY = 50;
    private float bulletX = -1; // -1 indicates no active bullet
    private float bulletY = -1;
    private final int screenWidth = 1920;
    private final int screenHeight = 1080;

    private boolean movingRight = true;

    private ArrayList<Alien> aliens;

    @Override
    public void create() {
        background = new Texture(Gdx.files.internal("spacebackground.jpg"));
        cursor = new Texture(Gdx.files.internal("Untitled.png"));
        alienTexture = new Texture(Gdx.files.internal("amogus.gif"));
        bullet = new Texture(Gdx.files.internal("PinkBullet.png"));
        batch = new SpriteBatch();

        // Initialize the aliens with spread-out positions
        aliens = new ArrayList<>();
        int rows = 2;
        int cols = 5;
        int spacing = 100;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                aliens.add(new Alien(250 + col * spacing, 500 + row * spacing, 64, 64, "alien.png"));
            }
        }
    }



@Override
public void render() {
    ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

    handlePlayerMovement();
    handleShooting();

    // Update and render aliens
    boolean hitEdge = false;     for (Alien alien : aliens) {
        alien.move(movingRight ? 10 : -10, 0);

        if (alien.getX() + alien.getWidth() > screenWidth || alien.getX() < 0) {
            movingRight = !movingRight;  // Reverse the direction
            hitEdge = true; }
    }

    if (hitEdge) {
        for (Alien alien : aliens) {
            alien.move(0, -50); // Move all aliens down by 50 units
        }
    }

    batch.begin();
    batch.draw(background, 0, 0, screenWidth, screenHeight);
    batch.draw(cursor, playerX, playerY);

    if (bulletY > -1) {
        batch.draw(bullet, bulletX, bulletY);
    }

    for (Alien alien : aliens) {
        if (alien.isAlive()) {
            batch.draw(alienTexture, alien.getX(), alien.getY());
        }
    }
    batch.end();
}

    private void handlePlayerMovement() {
        if (Gdx.input.isKeyPressed(Keys.H)) {
            playerX -= 16;
        } else if (Gdx.input.isKeyPressed(Keys.L)) {
            playerX += 16;
        }

        if (playerX > screenWidth) {
            playerX = 0;
        } else if (playerX < 0) {
            playerX = screenWidth;
        }
    }

    private void handleShooting() {
        if (Gdx.input.isKeyJustPressed(Keys.X)) {
            bulletX = playerX + cursor.getWidth() / 2 - bullet.getWidth() / 2;
            bulletY = playerY + cursor.getHeight();
        }

        if (bulletY > -1) {
            bulletY += 20; // Bullet movement speed
            if (bulletY > screenHeight) {
                bulletY = -1; // Reset bullet off screen when it passes bottom
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        cursor.dispose();
        background.dispose();
        alienTexture.dispose();
        bullet.dispose();

    }
}
