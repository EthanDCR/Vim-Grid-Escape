package com.example;

import com.badlogic.gdx.math.Rectangle;

public class Alien {
    private float x, y;
    private float width, height; // Using float for consistency with GDX
    private boolean alive;
    private String imagePath;

    public Alien(float x, float y, float width, float height, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imagePath = imagePath;
        this.alive = true;
    }

    // Move the alien by the given deltas
    public void move(float deltaX, float deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    // Return the alien's bounding box for collision detection
    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, width, height);
    }

    // Setters and getters
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getImagePath() {
        return imagePath;
    }
}

