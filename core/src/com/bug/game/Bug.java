package com.bug.game;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;


public class Bug extends Actor {
    private String Name;   //Bugs Name (blue/red)
    private int BugID = 0;
    public int cruising_range = 1; //Maximum step for bug
    private int durability = 100;  //Bugs health
    private int power;  //Power of bugs attack
    private int CurrentPossition;  // Curren CellID
    private WorldCell CurrentCell;  // Curren CellID
    private ArrayList<WorldCell> CapturedCells = new ArrayList<WorldCell>();
    private Texture bug_texture;
    public Sprite Sprite;
    private Bounds bound;   //Позиция на поле
    World world;
    MyGdxGame game;


    public Bug(String Name, int CellID, final Bounds bound) {
        this.bound = bound;
        this.Name = Name;
        this.CurrentPossition = CellID;
        this.BugID++;

     //   setCapturedCell(CurrentPossition, this.Name);

        if (Name == "red") {
            bug_texture = new Texture("Bug_red_1dmg.png");
        }
         else{
            bug_texture = new Texture("Bug_blue_1dmg.png");
        }

        Sprite = new Sprite(bug_texture);

        setHeight(bound.getSize());
       // setHeight(bug_texture.getHeight());
        setWidth(bound.getSize());
        //  setWidth(bug_texture.getWidth());
        setPosition(bound.getX(), bound.getY());

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.input.vibrate(25);
                Sprite.setScale(1.2f);
                System.out.println("succses");
                event.getListenerActor().setSize(200, 100);
                Gdx.app.log("Example", "touch started at (" + x + ", " + y + ")");
                return true;
            }
/*
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {

                if (event.getStageX() > game.world.getBug(1).bound.getX()) {
                    System.out.println("N ");
                          game.world.MakeStep("N", game.world.getBug(1).getID());
                }
                if (event.getStageX() < game.world.getBug(1).bound.getX()) {
                    System.out.println("S ");
                         game.world.MakeStep("S", game.world.getBug(0).getID());
                }
                if (event.getStageY() > game.world.getBug(1).bound.getY()) {
                    System.out.println("E ");
                         game.world.MakeStep("E", game.world.getBug(0).getID());
                }
                if (event.getStageY() < game.world.getBug(1).bound.getY()) {
                    System.out.println("W ");
                          game.world.MakeStep("W", game.world.getBug(0).getID());
                }

            }
*/

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.input.vibrate(80);
                Sprite.setScale(1.0f);
                System.out.println("fail");
                event.getListenerActor().setSize(100, 50);
                Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
            }
        });


        setTouchable(Touchable.enabled);



    }



    Bug () {

        if (Name == "red") {
            bug_texture = new Texture("bug_red.jpg");
        }
        else{
            bug_texture = new Texture("bug_red.jpg");
        }

        this.Name = "red";
        this.CurrentPossition = 0;
    }

    public void setCurrentPosition (int NewPosition){
      this.CurrentPossition = NewPosition;
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

    public Bounds getBound() {
        return this.bound;
    }

    public void setBound(Bounds bound) {
        this.bound = bound;
        setPosition(bound.getX(), bound.getY());
    }

    public int getID() {
        return this.BugID;
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


    public void setCapturedCell(WorldCell Cell, String Name) {

        System.out.println("Captured start! ");
       // game.world.World_hm.get(Cell).setName(Name);
        System.out.println("Name is set! ");
      //  world.World_hm.put(Cell.getCellID(), Cell);
        System.out.println("cell changed ");
        CapturedCells.add(world.World_hm.get(Cell));
     //   world.World_hm.put(Cell, CapturedCells.get(CurrentPossition));
        System.out.println("cell added to captured ");

    }

    public ArrayList<WorldCell> getCapturedCells() {

        return this.CapturedCells;

    }

    public int getCurrentPosition () {
        return this.CurrentPossition;
    }


    @Override
    public void draw(Batch batch, float alpha) {

        Sprite.setSize(bound.getSize(),bound.getSize());
        Sprite.setPosition(bound.getX(), bound.getY());
        Sprite.draw(batch);
    }

}