package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bug.game.Bug;
import com.bug.game.MyGdxGame;
import com.bug.game.World;

/**
 * Created by Lego on 08.04.2015.
 */
public class PlayScreen implements Screen {
    MyGdxGame game;
    Stage stage;
    BackgroundActor background;

    Group group_bug = new Group();
    Group group_stage = new Group();

    public PlayScreen(final MyGdxGame gam) {
        game = gam;
        stage = new Stage(new ScreenViewport());
        stage.addActor(game.background);


    }

    class BugListener extends InputListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            Gdx.input.vibrate(25);
            System.out.println("succses");
            game.world.MakeStep();
            return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            Gdx.input.vibrate(80);
            System.out.println("fail");
        }
    }



    @Override
    public void render(float delta) {

        Bug bug = new Bug("red",20, game.world.getCell(20).GetCellCords());

        bug.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.vibrate(25);
                System.out.println("succses");
                Gdx.app.exit();
            };
        });



        group_bug.addActor(bug);

        stage.addActor(group_bug);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {
        background = new BackgroundActor();
        background.setPosition(0, 0);


        for (int i=0; i<100; i++) {
            group_stage.addActor(game.world.getCell(i));
        }

        for (int j=0; j<game.world.Bugs.size(); j++) {

            game.world.getBug(j).addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.input.vibrate(25);
                    System.out.println("succses");
                    Gdx.app.exit();
                } ;
            });
            group_bug.addActor(game.world.getBug(j));

        }

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

