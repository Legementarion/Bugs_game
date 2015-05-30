package Screen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bug.game.Bounds;
import com.bug.game.Buffs;
import com.bug.game.Bug;
import com.bug.game.MyGdxGame;
import com.bug.game.WorldCell;

/**
 * Created by Lego on 08.04.2015.
 */
public class PlayScreen implements Screen {
    public MyGdxGame game;
    Stage stage;
    Group group_bug = new Group();
    Group group_buff = new Group();
    Group group_stage = new Group();
    Sound sound_BG = Gdx.audio.newSound(Gdx.files.internal("sounds/GameBG.wav"));



    public PlayScreen(final MyGdxGame gam) {
        game = gam;
        stage = new Stage(new ScreenViewport());
        game.background.SetBackgroundPlay();
        stage.addActor(game.background);
        stage.addActor(game.world.StatusBar);

        for (final Bug bug : game.world.Bugs) {
            bug.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.input.vibrate(25);
                    bug.Sprite.setScale(1.2f);
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.input.vibrate(80);
                    float[] Cord = {bug.getX(), bug.getY(), bug.getHeight()};
                    if (game.world.SelectedBug == null) {
                        game.world.SelectedBug = bug;
                        game.world.StatusBar.setBug(bug);
                        game.world.SelectPossibleSteps(game.world.SelectedBug);

                    } else if (game.world.SelectedBug == bug) {
                        game.world.SelectedBug.Sprite.setScale(1.0f);
                        game.world.UnSelectPossibleSteps(game.world.SelectedBug);
                        game.world.StatusBar.setBug(null);
                        game.world.SelectedBug = null;
                    }
                    else {
                        game.world.UnSelectPossibleSteps(game.world.SelectedBug);
                        game.world.SelectedBug.Sprite.setScale(1.0f);
                        game.world.SelectedBug = bug;
                        game.world.StatusBar.setBug(bug);
                        game.world.SelectPossibleSteps(game.world.SelectedBug);
                    }
                }
        });
            group_bug.addActor(bug);
        }
    for (int key : game.world.WorldMap.keySet()) {
        final WorldCell Cell = game.world.WorldMap.get(key);
        Cell.addListener(new InputListener() {
               public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                   if (game.world.SelectedBug != null) {
                       if (game.world.CheckNextStep(Cell)) {
                           Cell.Sprite.setScale(1.2f);
                       }
                   }
                   return true;
               }

               public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                   if (game.world.SelectedBug != null) {
                       game.world.MakeStep(Cell, stage);
                       Cell.Sprite.setScale(1.0f);
                   }
               }
           });
            group_stage.addActor(game.world.WorldMap.get(key));
        }
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Очистка экрана
        game.camera.update();
        stage.addActor(group_buff);
        stage.addActor(group_bug);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


    }



    @Override
    public void resize(int width, int height) {}

    public void removeActor(Actor ActorrBuffer){

            for (Actor actor: stage.getActors()) {
                if (actor == ActorrBuffer) {
                    actor.remove();
                }
            }
        }


    @Override
    public void show() {
        long soundId = sound_BG.loop();
        sound_BG.setVolume(soundId,((float) (0.2)));
        for (final Buffs buff : game.world.Buff) {
            group_buff.addActor(buff);
        }
        group_bug.setTouchable(Touchable.enabled);
        group_stage.setTouchable(Touchable.enabled);
        stage.addActor(group_stage);


       Gdx.input.setInputProcessor(stage);
       Gdx.input.setCatchBackKey(true); // Это нужно для того, чтобы пользователь возвращался назад, в случае нажатия на кнопку Назад на своем устройстве
    }

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        game.dispose();
    }
}

