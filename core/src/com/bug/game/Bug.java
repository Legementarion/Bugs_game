package com.bug.game;

import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Bug extends Actor {
    private String Name;   //Bugs Name (blue/red)
    private int durability = 100;  //Bugs health
    private int power;  //Power of bugs attack
    private int CurrentPossition;  // Curren CellID
    private ArrayList<WorldCell> CapturedCells = new ArrayList<WorldCell>();
    private Texture bug_texture;
    private Sprite Sprite;
    private Bounds bound;   //Позиция на поле
    World world;

    public Bug(String Name, int CellID, Bounds bound) {
        this.bound = bound;
        this.Name = Name;
        this.CurrentPossition = CellID;

        if (Name == "red") {
            bug_texture = new Texture("bug_red.bmp");
        }
         else{
            bug_texture = new Texture("bug_blue.bmp");
        }
        System.out.println("name - " + Name);
        System.out.println("position - " + CurrentPossition);


        Sprite = new Sprite(bug_texture);

    }



    Bug () {

        if (Name == "red") {
            bug_texture = new Texture("bug_red.bmp");
        }
        else{
            bug_texture = new Texture("bug_red.bmp");
        }

        this.Name = "red";
        this.CurrentPossition = 0;
    }

    public void setCurrentPosition (int NewPosition){
      this.CurrentPossition = NewPosition;
      this.bound = world.getCell(NewPosition).GetCellCords();
    }

    public String getName() {
        return this.Name;
    }

    /**
     *
     * @param Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    public int getDurability() {
        return this.durability;
    }

    /**
     *
     * @param durability
     */
    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getPower() {
        return this.power;
    }

    /**
     *
     * @param power
     */
    public void setPower(int power) {
        this.power = power;
    }
    /**
     * Getting damaged from oposit bug  -> this.health-=enemy.power;
     * @param power
     */
    public void getDamaged(int power) {
        this.durability-=power;
    }

    public void setCapturedCell(WorldCell Cell) {

        CapturedCells.add(Cell);

    }

    public ArrayList<WorldCell> getCapturedCells() {

        return this.CapturedCells;

    }

    public int getCurrentPosition () {
        return this.CurrentPossition;
    }




    @Override
    public void draw(Batch batch, float alpha) {
        Sprite.setPosition(bound.getX(), bound.getY());
        Sprite.draw(batch);
    }

}