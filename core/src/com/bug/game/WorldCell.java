package com.bug.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class WorldCell extends Actor {

    private String Name="1";  //Какому жуку пренадлежит
    private int CellID;
    private Bounds bound;   //Позиция на поле
    private Texture CellTexture;
    public Sprite Sprite;
    World world;
    MyGdxGame game;


    WorldCell(String Name, int CellID, Bounds bound) {

        this.Name = Name;
        this.CellID = CellID;
        this.bound = bound;


        CellTexture = new Texture(Name+".bmp");




        Sprite = new Sprite(CellTexture);
        setHeight(bound.getSize());
        setWidth(bound.getSize());
        setPosition(bound.getX(), bound.getY());
        setTouchable(Touchable.enabled);

    }

    WorldCell() {


        CellTexture = new Texture("1.bmp");
        Sprite = new Sprite(CellTexture);


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
        this.Name = Name;

            CellTexture = new Texture(this.Name+".bmp");
            Sprite = new Sprite(CellTexture);
    }


    @Override
    public void draw(Batch batch, float alpha) {
               Sprite.setPosition(bound.getX(), bound.getY());
               Sprite.setSize(bound.getSize(),bound.getSize());
               Sprite.draw(batch);
    }
}