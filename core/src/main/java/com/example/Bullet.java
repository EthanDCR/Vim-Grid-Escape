package com.example;

import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    private float x, y;
    private int width, height;
    private boolean active;

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 20;
        this.active = true;
    }

    public void update() {
        this.y += 10;
    }

    public boolean isOffScreen(int screenHeight) {
        return y > screenHeight;
    }

    public boolean collidesWith(Alien alien) {
        Rectangle bulletRect = new Rectangle(x, y, width, height);
        Rectangle alienRect = new Rectangle(alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight());
        return bulletRect.overlaps(alienRect);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

