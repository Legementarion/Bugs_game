package com.bug.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import java.util.Random;

import javax.swing.CellEditor;

public class WorldCell extends Actor {

    private String Name="stones";  //Какому жуку пренадлежит
    private int CellID;
    private Bounds bound;   //Позиция на поле
    private Texture CellTexture;
    public Sprite Sprite;
    public TextureRegion region;




    WorldCell(String Name, int CellID, Bounds bound) {

        this.Name = Name;
        this.CellID = CellID;
        this.bound = bound;
        CellTexture = new Texture(Gdx.files.internal("stones.png"));
        int ElementWidth = CellTexture.getWidth()/5;
        int ElementHeight = CellTexture.getHeight()/2;
        int ElementX1 = ElementWidth * new Random().nextInt(5);
        int ElementY1 = ElementHeight * new Random().nextInt(2);
        region = new TextureRegion(CellTexture, ElementX1, ElementY1, ElementWidth, ElementHeight);
        Gdx.app.log("regionH: ", Float.toString(region.getRegionHeight()));
        Gdx.app.log("regionW: ", Float.toString(region.getRegionWidth()));
        Sprite = new Sprite(region);
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

           // CellTexture = new Texture(this.Name+".png");
           // Sprite = new Sprite(CellTexture);




    }


    @Override
    public void draw(Batch batch, float alpha) {
        if (Name == "red")   Sprite.setColor(Color.RED);
        if (Name == "blue")  Sprite.setColor(Color.BLUE);
        if (Name == "orange")   Sprite.setColor(Color.ORANGE);
        if (Name == "green")   Sprite.setColor(Color.GREEN);
        if (Name == "violet")   Sprite.setColor(Color.MAGENTA);
        if (Name == "indigo")   Sprite.setColor(Color.PURPLE);
        if (Name == "yellow")   Sprite.setColor(Color.YELLOW);
               Sprite.setOriginCenter();
               Sprite.setPosition(bound.getX(), bound.getY());
               Sprite.setSize(bound.getSize(),bound.getSize());
               Sprite.draw(batch);

    }
}