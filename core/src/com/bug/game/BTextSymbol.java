package com.bug.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

/**
 * Created by Lego on 30.05.2015.
 */
public class BTextSymbol extends Actor {
    Sprite Number;
    TextureRegion [] region = new TextureRegion[10];


    BTextSymbol () {

        Texture  NumberTexture = new Texture("Numbers.png");
        int TextureElementWidth = NumberTexture.getWidth()/10;
        int TextureElementHeight = NumberTexture.getHeight();
        int TextureElementY = 0;
        for (int i = 0; i<10; i++) {
            int TextureElementX = TextureElementWidth * i;
            region[i] = new TextureRegion(NumberTexture,
                    TextureElementX,
                    TextureElementY,
                    TextureElementWidth,
                    TextureElementHeight);

        }
     //   this.Number = new Sprite (new Texture("Health.png"));

    }

    public void setTextureNumber (int Number) {
        this.Number = new Sprite(region[Number]);
    }



    public Sprite getSprite() {
        return this.Number;
    }

}
