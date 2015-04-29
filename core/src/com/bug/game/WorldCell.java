package com.bug.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class WorldCell extends Actor {

    private String Name="Null";  //Какому жуку пренадлежит
    private int CellID;
    private Bounds bound;   //Позиция на поле
    private Texture cell_texture;
    private Sprite Sprite;


    WorldCell(String Name, int CellID, Bounds bound) {

        this.Name = Name;
        this.CellID = CellID;
        this.bound = bound;


        cell_texture = new Texture("1.bmp");




        Sprite = new Sprite(cell_texture);

    }

    WorldCell() {


        cell_texture = new Texture("1.bmp");
        Sprite = new Sprite(cell_texture);


    }

    public void SetCellCords(int X,int Y,int Size) {
        bound = new Bounds(X,Y,Size);
    }

    public Bounds GetCellCords() {
        return bound;
    }

    public void SetCellID (int CellID) {
        this.CellID = CellID;
    }

    public int getCellID () {
        return this.CellID;
    }

    public void setName (String Name) {
        if (Name == "red") {
            cell_texture = new Texture("red.bmp");
        }
        if (Name == "blue") {
            cell_texture = new Texture("blue.bmp");
        }
        else{
            cell_texture = new Texture("1.bmp");
        }
        this.Name = Name;

        Sprite = new Sprite(cell_texture);
    }


    @Override
    public void draw(Batch batch, float alpha) {
               Sprite.setPosition(bound.getX(), bound.getY());
               Sprite.setSize(bound.getSize(),bound.getSize());
               Sprite.draw(batch);
    }
}