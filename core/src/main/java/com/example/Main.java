
package com.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;

public class Main extends ApplicationAdapter {
    public enum GameState {
        START,
        PLAYING,
        YOU_WIN,
        YOU_LOSE
    }

    private SpriteBatch batch;
    private Texture cursor;
    private Texture background;
    private Texture alienTexture;
    private Texture bulletTexture;
    private Texture startScreen;
    private Texture winScreen;
    private Texture loseScreen;
    private float playerX = 300;
    private float playerY = 50;
    private final int screenWidth = 1920;
    private final int screenHeight = 1080;
    private ArrayList<Alien> aliens;
    private ArrayList<Bullet> bullets;
    private boolean movingRight = true;
    private GameState gameState = GameState.START;

    @Override
    public void create() {
        // Load resources
        background = new Texture(Gdx.files.internal("spacebackground.jpg"));
        cursor = new Texture(Gdx.files.internal("shipPink.png"));
        alienTexture = new Texture(Gdx.files.internal("shipGreen_manned.png"));
        bulletTexture = new Texture(Gdx.files.internal("cursorBullet.png"));
        startScreen = new Texture(Gdx.files.internal("startScreen.png"));
        winScreen = new Texture(Gdx.files.internal("winScreen.png"));
        loseScreen = new Texture(Gdx.files.internal("loseScreen.png"));
        batch = new SpriteBatch();

        aliens = new ArrayList<>();
        bullets = new ArrayList<>();
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

        batch.begin();
        switch (gameState) {
            case START:
                renderStartScreen();
                break;
            case PLAYING:
                renderGame();
                break;
            case YOU_WIN:
                renderWinScreen();
                break;
            case YOU_LOSE:
                renderLoseScreen();
                break;
        }
        batch.end();
    }

    private void renderStartScreen() {
        batch.draw(background, 0, 0, screenWidth, screenHeight);
        batch.draw(startScreen, screenWidth / 2 - 200, screenHeight / 2 - 100, 400, 200);

        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            gameState = GameState.PLAYING;
        }
    }

    private void renderGame() {
        handlePlayerMovement();
        handleShooting();

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

        handleCollisions();

        batch.draw(background, 0, 0, screenWidth, screenHeight);
        batch.draw(cursor, playerX, playerY);

        for (Bullet bullet : bullets) {
            batch.draw(bulletTexture, bullet.getX(), bullet.getY());
        }

        boolean allAliensDead = true;
        for (Alien alien : aliens) {
            if (alien.isAlive()) {
                allAliensDead = false;
                batch.draw(alienTexture, alien.getX(), alien.getY());
            }
        }

        if (allAliensDead) {
            gameState = GameState.YOU_WIN;
        }
    }

    private void renderWinScreen() {
        batch.draw(background, 0, 0, screenWidth, screenHeight);
        batch.draw(winScreen, screenWidth / 2 - 200, screenHeight / 2 - 100, 400, 200);

        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            restartGame();
        }
    }

    private void renderLoseScreen() {
        batch.draw(background, 0, 0, screenWidth, screenHeight);
        batch.draw(loseScreen, screenWidth / 2 - 200, screenHeight / 2 - 100, 400, 200);

        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {

           restartGame();
        }
    }

    private void handleCollisions() {
        for (Bullet bullet : bullets) {
            for (Alien alien : aliens) {
                if (alien.isAlive() && bullet.collidesWith(alien)) {
                    alien.setAlive(false);
                    bullet.setActive(false);
                }
            }
        }
        bullets.removeIf(bullet -> !bullet.isActive());
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
            bullets.add(new Bullet(playerX + cursor.getWidth() / 2 - bulletTexture.getWidth() / 2, playerY + cursor.getHeight()));
        }
    }

    private void restartGame() {
        gameState = GameState.START;
        aliens.clear();
        bullets.clear();
        create();
    }

    @Override
    public void dispose() {
        batch.dispose();
        cursor.dispose();
        background.dispose();
        alienTexture.dispose();
        bulletTexture.dispose();
        startScreen.dispose();
        winScreen.dispose();
        loseScreen.dispose();
    }
}

