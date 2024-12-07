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
    private Texture gameover;
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

// TODO get new images for all of this or make the art

    @Override
    public void create() {
        background = new Texture(Gdx.files.internal("spacebackground.jpg"));
        cursor = new Texture(Gdx.files.internal("Untitled.png"));
        alienTexture = new Texture(Gdx.files.internal("amogus.gif"));
        bullet = new Texture(Gdx.files.internal("PinkBullet.png"));
        batch = new SpriteBatch();



        aliens = new ArrayList<>(); //spread them aliens out
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

    boolean hitEdge = false;
    for (Alien alien : aliens) {
        alien.move(movingRight ? 5 : -5, 0);

        if (alien.getX() + alien.getWidth() > screenWidth || alien.getX() < 0) {
            movingRight = !movingRight;
            hitEdge = true; }

        if (isCollisionWithPlayer(alien)) {
            gameOver();
            return;
        }

        if (alien.getY() <= 50) {
            gameOver();
            return;          }

        if (isCollisionWithBullet(alien)) {
            alien.setAlive(false);
            bulletY = -1;  // Reset bullet off-screen
            break;
        }
    }

    if (hitEdge) {
        for (Alien alien : aliens) {
            alien.move(0, -50); // Move all aliens down by 50 units
        }
    }

    batch.begin();
    batch.draw(background, 0, 0, screenWidth, screenHeight);
    batch.draw(cursor, playerX, playerY);

    // Draw the bullet if it's active
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

private boolean isCollisionWithPlayer(Alien alien) {
    return alien.getX() < playerX + cursor.getWidth() &&
           alien.getX() + alien.getWidth() > playerX &&
           alien.getY() < playerY + cursor.getHeight() &&
           alien.getY() + alien.getHeight() > playerY;
}

private boolean isCollisionWithBullet(Alien alien) {
    return bulletX > alien.getX() &&
           bulletX < alien.getX() + alien.getWidth() &&
           bulletY > alien.getY() &&
           bulletY < alien.getY() + alien.getHeight();
}

private void gameOver() {
    resetGame();
    System.out.println("Game Over!");
}

private void resetGame() {
    playerX = 300;
    playerY = 50;
    bulletY = -1;  // Reset bullet
    aliens.clear();

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
            bulletY += 20;
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
