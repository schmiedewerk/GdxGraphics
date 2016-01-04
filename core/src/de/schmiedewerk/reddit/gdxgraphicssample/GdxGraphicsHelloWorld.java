package de.schmiedewerk.reddit.gdxgraphicssample;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.schmiedewerk.reddit.gdxgraphicssample.graphics.Graphics;
import de.schmiedewerk.reddit.gdxgraphicssample.graphics.Sprite;

public class GdxGraphicsHelloWorld extends ApplicationAdapter {

    private final float virtualWidth = 640;
    private final float virtualHeight = 480;
    private final int maxSpriteCount = 1000;
    private Graphics graphics;

    private Texture texture;
    private TextureRegion region;

    @Override
    public void create() {
        graphics = new Graphics(virtualWidth, virtualHeight, maxSpriteCount);

        texture = new Texture("badlogic.jpg");
        region = new TextureRegion(texture);

        Sprite logo = graphics.sprite();
        logo.region(region);
    }

    @Override
    public void render() {
        graphics.draw();
    }

    @Override
    public void resize(int width, int height) {
        graphics.onResize(width, height);
    }

    @Override
    public void dispose() {
        graphics.dispose();
    }

}
