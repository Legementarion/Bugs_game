package com.bug.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import Screen.BackgroundActor;
import Screen.MainMenuScreen;


public class MyGdxGame  extends Game {
    public BackgroundActor background;
    public World world ;
    SpriteBatch batch;
    public OrthographicCamera camera;




	@Override
	public void create () {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        background = new BackgroundActor();
        background.setPosition(0, 0);

        world = new World();
		batch = new SpriteBatch();

        this.setScreen(new MainMenuScreen(this));


    }

    @Override
    public void render() {
        super.render(); // важно!
    }


    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
