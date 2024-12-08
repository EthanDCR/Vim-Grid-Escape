package com.example;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import com.example.Alien;
import com.example.Bullet;

public class Main extends ApplicationAdapter {









    private SpriteBatch batch;
    private Texture cursor;
    private Texture background;
    private Texture alienTexture;
    private Texture bulletTexture;
    private float playerX = 300;
    private float playerY = 50;
    private final int screenWidth = 1920;
    private final int screenHeight = 1080;
    private ArrayList<Alien> aliens;
    private ArrayList<Bullet> bullets;
    private boolean movingRight = true;

    @Override
    public void create() {

        background = new Texture(Gdx.files.internal("spacebackground.jpg"));
        cursor = new Texture(Gdx.files.internal("shipPink.png"));
        alienTexture = new Texture(Gdx.files.internal("shipGreen_manned.png"));
        bulletTexture = new Texture(Gdx.files.internal("laserPink1.png"));
        batch = new SpriteBatch();

        aliens = new ArrayList<>();
        int rows = 2;
        int cols = 5;
        int spacing = 100;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                aliens.add(new Alien(250 + col * spacing, 500 + row * spacing, 64, 64, "alien.png"));
            }
        }

        bullets = new ArrayList<>();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        handlePlayerMovement();
        handleShooting();
        handleCollision();

        for (Alien alien : aliens) {
            alien.move(movingRight ? 5 : -5, 0);

            if (alien.getX() + alien.getWidth() > screenWidth || alien.getX() < 0) {
                movingRight = !movingRight;
                aliens.forEach(a -> a.move(0, -50));
            }
        }

        for (Bullet bullet : bullets) {
            bullet.update();
        }

        bullets.removeIf(bullet -> bullet.isOffScreen(screenHeight));

        batch.begin();
        batch.draw(background, 0, 0, screenWidth, screenHeight);
        batch.draw(cursor, playerX, playerY);

        for (Bullet bullet : bullets) {
            batch.draw(bulletTexture, bullet.getX(), bullet.getY());
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
            // Create a new bullet each time
            bullets.add(new Bullet(playerX + cursor.getWidth() / 2 - bulletTexture.getWidth() / 2, playerY + cursor.getHeight()));
        }
    }



private void handleCollision() {
    for (int i = 0; i < bullets.size(); i++) {
        Bullet bullet = bullets.get(i);

        for (int j = 0; j < aliens.size(); j++) {
            Alien alien = aliens.get(j);

            if (alien.isAlive() && bullet.getBoundingBox().overlaps(alien.getBoundingBox())) {
                alien.setAlive(false);
                bullets.remove(i);
                i--; // Adjust the index since we removed a bullet
                break;  }
        }
    }
}








    @Override
    public void dispose() {
        batch.dispose();
        cursor.dispose();
        background.dispose();
        alienTexture.dispose();
        bulletTexture.dispose();
    }
}

