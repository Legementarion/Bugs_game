package Screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
/**
 * Created by Lego on 08.04.2015.
 */
    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.Batch;
    import com.badlogic.gdx.graphics.g2d.Sprite;

    public class BackgroundActor extends Actor {
        private Texture backgroundTexture;
        private Sprite backgroundSprite;

        public BackgroundActor() {
            backgroundTexture = new Texture("BG1.png");
            backgroundSprite = new Sprite(backgroundTexture);
            backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        public void SetBackgroundMenu(){
            backgroundTexture = new Texture("BG1.png");
            backgroundSprite = new Sprite(backgroundTexture);
            backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
        public void SetBackgroundPlay(){
            backgroundTexture = new Texture("BG.png");
            backgroundSprite = new Sprite(backgroundTexture);
            backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        @Override
        public void draw(Batch batch, float alpha) {
            backgroundSprite.draw(batch);
        }
}
