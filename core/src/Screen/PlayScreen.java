package Screen;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bug.game.Bounds;
import com.bug.game.Bug;
import com.bug.game.MyGdxGame;
import com.bug.game.World;

/**
 * Created by Lego on 08.04.2015.
 */
public class PlayScreen implements Screen {
    MyGdxGame game;
    Stage stage;
    private Bounds bound;
    Group group_bug = new Group();
    Group group_stage = new Group();




    public PlayScreen(final MyGdxGame gam) {
        game = gam;
        stage = new Stage(new ScreenViewport());
        stage.addActor(game.background);

        for (int j=0; j<game.world.Bugs.size(); j++) {
            game.world.getBug(j).addListener(new BugListener());
            group_bug.addActor(game.world.getBug(j));
            if (game.world.getBug(j).isTouchable()){
                System.out.println("bug succses");
            }
        }

        for (int i=0; i<100; i++) {
            group_stage.addActor(game.world.getCell(i));
        }

    }

    class BugListener extends InputListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//            Gdx.input.vibrate(25);
            System.out.println("succses");
            event.getListenerActor().setSize(200, 100);
            return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
     //     Gdx.input.vibrate(80);
            System.out.println("fail");
            event.getListenerActor().setSize(100, 50);
        }
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Очистка экрана
        game.camera.update();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {

        group_bug.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("succses 44");
            }
        });


        group_bug.setTouchable(Touchable.enabled);
        stage.addActor(group_stage);
        stage.addActor(group_bug);

       Gdx.input.setInputProcessor(stage);

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

