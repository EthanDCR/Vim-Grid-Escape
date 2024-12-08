
package com.example;
import com.badlogic.gdx.math.Rectangle;


public class Bullet {
    private float x;
    private float y;
    private float speed = 20;
    private float width = 10, height = 20;

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        y += speed; // Move the bullet upwards
    }


  public Rectangle getBoundingBox() {
        return new Rectangle(x, y, width, height);
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









