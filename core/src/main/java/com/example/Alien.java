
package com.example;
public class Alien {
        private float x, y;
        private int width, height;
        private boolean alive;
        private String imagePath;

        public Alien(float x, float y, int width, int height, String imagePath) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.imagePath = imagePath;
            this.alive = true;
        }

        public void move(float deltaX, float deltaY) {
            this.x += deltaX;
            this.y += deltaY;
        }

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

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public String getImagePath() {
            return imagePath;
        }
    }

