


package com.example;
public class Bullet {
    private float x;
    private float y;
    private float speed = 20;

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        y += speed; // Move the bullet upwards
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isOffScreen(int screenHeight) {
        return y > screenHeight;
    }
}









