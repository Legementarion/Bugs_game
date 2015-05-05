package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bug.game.MyGdxGame;




public class MainMenuScreen implements Screen {
    MyGdxGame game;
    Stage  stage = new Stage(new ScreenViewport());
    Table table = new Table();
    Sound sound_button = Gdx.audio.newSound(Gdx.files.internal("sounds/Click_sounds.wav"));



    Skin skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas(Gdx.files.internal("MainMenu.pack")));


    TextButton buttonPlay = new TextButton("Play", skin, "Play");

    TextButton buttonExit = new TextButton("Exit", skin, "Exit");
    Label title = new Label("Game Title", skin);

    public MainMenuScreen(final MyGdxGame gam) {
    game = gam;



    }

        @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound_button.play();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game));
            }
        });

        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound_button.play();
                Gdx.app.exit();
            }
        });

        stage.addActor(game.background);

        table.add(title).size(800,150).padBottom(40).row();
        table.add(buttonPlay).size(800,150).padBottom(50).row();
        table.add(buttonExit).size(800,150).padBottom(50).row();

        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}