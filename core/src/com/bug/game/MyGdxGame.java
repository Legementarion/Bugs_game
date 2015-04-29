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


    class BugListener implements InputProcessor {

      /*  @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            Gdx.input.vibrate(25);
            System.out.println("succses");
            world.MakeStep();
            return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            Gdx.input.vibrate(80);
            System.out.println("fail");
        }
*/
        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            Gdx.input.vibrate(25);
            System.out.println("succses");
            world.MakeStep();
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            Gdx.input.vibrate(80);
            System.out.println("fail");
            return true;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }

	@Override
	public void create () {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        background = new BackgroundActor();
        background.setPosition(0, 0);

        world = new World();
		batch = new SpriteBatch();
        Gdx.input.setInputProcessor(new BugListener());

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
