package com.bug.game;

public class Bounds {

    private float X,Y,Size;

    Bounds(int X, int Y, int Size) {
        this.X = X;
        this.Y = Y;
        this.Size = Size;
    }

    Bounds(float X, float Y, float Size) {
        this.X = X;
        this.Y = Y;
        this.Size = Size;
    }

    Bounds() {
        this.X = 0;
        this.Y = 0;
        this.Size = 0;
    }

    public float getX() {
        // TODO - implement Bounds.getX123
        return this.X;
    }


    public float getY() {
        // TODO - implement Bounds.getY
        return this.Y;
    }


    public float getSize() {
        // TODO - implement Bounds.getSize
        return this.Size;
    }


}