package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bug.game.MyGdxGame;

import static com.badlogic.gdx.Gdx.graphics;


public class MainMenuScreen implements Screen {
    MyGdxGame game;
    Stage  stage = new Stage(new ScreenViewport());
    Table table = new Table();
    Sound sound_button = Gdx.audio.newSound(Gdx.files.internal("sounds/Click_sounds.wav"));
    Sound sound_BG = Gdx.audio.newSound(Gdx.files.internal("sounds/GameBG.wav"));



    Skin skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas(Gdx.files.internal("MainMenu.pack")));

    TextButton buttonPlay = new TextButton("Play", skin, "Play");
    TextButton buttonExit = new TextButton("Exit", skin, "Exit");
    Button Logo = new Button(skin);

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
        long soundId = sound_BG.loop();
        sound_BG.setVolume(soundId,((float) (0.2)));

        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound_button.play();
                sound_button.dispose();
                sound_BG.stop();
                sound_BG.dispose();
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
        double Width = graphics.getWidth()/1.5;
        table.add(Logo).size( (int)(graphics.getWidth()/1.25) ,(int)(graphics.getHeight()/2.35)).padBottom(10).row();
        table.add(buttonPlay).size(graphics.getWidth()/3 ,graphics.getHeight() /7).padBottom(50).row();
        table.add(buttonExit).size(graphics.getWidth()/3,graphics.getHeight()/7).padBottom(50).row();

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