package com.bug.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lego on 30.05.2015.
 */
public class StatusBar extends Actor {

    Texture CellTexture = new Texture(Gdx.files.internal("Numbers.png"));
    int TextureElementWidth = CellTexture.getWidth()/10;
    int TextureElementHeight = CellTexture.getHeight();

    private Sprite BackGround = new Sprite(new Texture("StatusBar_BG.png"));
    private Sprite SpeedIcon = new Sprite(new Texture("Srpeed.png"));
    private Sprite HealthIcon = new Sprite(new Texture("Health.png"));
    private Sprite PowerIcon = new Sprite(new Texture("Attack.png"));
    private int StartX;
    private int StartY;
    private int Width;
    private int Height;
    private float IconSize;
    private float StartIconsPositionY;
    private float StartIconsPositionX;
    Bug bug;
    ArrayList<Sprite> LableHelth = new ArrayList<Sprite>();
    ArrayList<Sprite> LableSpeed = new ArrayList<Sprite>();
    ArrayList<Sprite> LablePower = new ArrayList<Sprite>();
    BTextSymbol Label = new BTextSymbol();


    public void BuildStatusBar(int StartX, int StartY, int Width, int Height) {
        this.StartX = StartX;
        this.StartY = StartY;
        this.Width = Width;
        this.Height = Height;
        this.IconSize = Height/8;
        this.StartIconsPositionY = Height-IconSize*4;
        this.StartIconsPositionX = this.StartX+(Width/6);

        setBackGround();



            setHelth();
            setSpeed();
            setPower();
        }





    private void setBackGround () {
        BackGround.setPosition(StartX + 10, StartY);
        BackGround.setSize(Width, Height);
    }

    private void setHelth () {
        HealthIcon.setPosition(StartIconsPositionX, StartIconsPositionY);
        HealthIcon.setSize(IconSize, IconSize);
    }

    private void setSpeed () {
        SpeedIcon.setPosition(HealthIcon.getX(), HealthIcon.getY() - IconSize - (IconSize / 2));
        SpeedIcon.setSize(IconSize, IconSize);
    }

    private void setPower () {
        PowerIcon.setPosition(SpeedIcon.getX(),SpeedIcon.getY()-IconSize-(IconSize/2));
        PowerIcon.setSize(IconSize, IconSize);
    }

    public void setBug (Bug bug) {
        this.bug = bug;
    }

    private ArrayList<Sprite> getLabel (String String, int X, int Y) {
        ArrayList<Sprite> LabelSprites = new ArrayList<Sprite>();
        for (int i = 0; i<String.length(); i++) {
            Label.setTextureNumber(Character.getNumericValue(String.charAt(i)));
            LabelSprites.add(Label.getSprite());
            LabelSprites.get(i).setPosition(X+((IconSize/2)*i),Y);
            LabelSprites.get(i).setSize(IconSize,IconSize);

        }
        return LabelSprites;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        BackGround.draw(batch, parentAlpha);
        if (bug != null) {
            Color LableColor = Color.BLACK;
            HealthIcon.draw(batch, parentAlpha);
            LableColor = setValueColor(bug.getDurability());
            for (Sprite Sprite : getLabel(String.valueOf(bug.getDurability()),(int)(HealthIcon.getX()+IconSize),(int)HealthIcon.getY())) {
                Sprite.setColor(LableColor);
                Sprite.draw(batch, parentAlpha);

            }

            SpeedIcon.draw(batch, parentAlpha);
            LableColor = setValueColor(bug.CruisingRange);
            for (Sprite Sprite : getLabel(String.valueOf(bug.CruisingRange),(int)(SpeedIcon.getX()+IconSize),(int)SpeedIcon.getY())) {
                Sprite.setColor(LableColor);
                Sprite.draw(batch, parentAlpha);
            }

            PowerIcon.draw(batch, parentAlpha);
            LableColor = setValueColor(bug.getPower());
            for (Sprite Sprite : getLabel(String.valueOf(bug.getPower()),(int)(PowerIcon.getX()+IconSize),(int)PowerIcon.getY())) {
                Sprite.setColor(LableColor);
                Sprite.draw(batch, parentAlpha);

            }
        }

    }

    private Color setValueColor (int num) {
        if (num >= 80) {return Color.GREEN;}
        else if (num < 80 && num >= 30) { return Color.YELLOW;}
        else if (num < 30) {return Color.RED;}
        return null;
    }
}
