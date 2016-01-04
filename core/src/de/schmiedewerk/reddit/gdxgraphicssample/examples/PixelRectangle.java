package de.schmiedewerk.reddit.gdxgraphicssample.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import de.schmiedewerk.reddit.gdxgraphicssample.GdxGraphicsHelloWorld;
import de.schmiedewerk.reddit.gdxgraphicssample.graphics.Graphics;
import de.schmiedewerk.reddit.gdxgraphicssample.graphics.Sprite;
import de.schmiedewerk.reddit.gdxgraphicssample.utility.GameAdapter;

/**
 * Created by /u/schmiedewerkgames @schmiedewerk on 04.01.2016.
 *
 * A Sprite made out of a 1x1 white pixel allows for very quick
 * placeholder art. This is the core use of the graphics extension.
 */
public class PixelRectangle extends GameAdapter {

    private Graphics graphics;

    private Texture texture;
    private TextureRegion pixel;
    private Sprite rectangle;

    private final int layerChessBoard = 1;
    private final int layerRectangle = 2;

    @Override
    public void create() {
        graphics = new Graphics(
                GdxGraphicsHelloWorld.virtualWidth,
                GdxGraphicsHelloWorld.virtualHeight,
                GdxGraphicsHelloWorld.maxSpriteCount
        );

        texture = new Texture("pixel.png");
        pixel = new TextureRegion(texture);

        spawnChessBoard();

        Gdx.input.setInputProcessor(this);
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT)
            replaceRectangle(graphics.unproject(screenX, screenY));

        if (button == Input.Buttons.RIGHT)
            disposeRectangle();

        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        if (rectangle != null)
            rotateRectangle(amount);

        return true;
    }

    private void replaceRectangle(Vector2 position) {
        if (rectangle != null)
            rectangle.dispose();

        rectangle = graphics.sprite();
        rectangle.layer(layerRectangle);
        rectangle.region(pixel);

        int width = MathUtils.random(10, 20);
        int height = MathUtils.random(10, 20);
        rectangle.size(width, height);
        rectangle.origin(width / 2f, height / 2f);

        rectangle.color(
                MathUtils.random(.2f, .8f),
                MathUtils.random(.2f, .8f),
                MathUtils.random(.2f, .8f),
                1
        );

        rectangle.position(
                position.x - width / 2f,
                position.y - height / 2f
        );
    }

    private void rotateRectangle(int direction) {
        float rotation = 10f * Math.signum(direction);
        rectangle.rotate(rotation);
    }

    private void disposeRectangle() {
        if (rectangle != null) {
            rectangle.dispose();
            rectangle = null;
        }
    }

    private void spawnChessBoard() {
        final int tileSize = 30;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Sprite sprite = graphics.sprite();
                sprite.layer(layerChessBoard);
                sprite.region(pixel);

                sprite.position(x * tileSize, y * tileSize);
                sprite.size(tileSize, tileSize);

                boolean even = ((x + y) % 2 == 0);
                sprite.color(even ? Color.LIGHT_GRAY : Color.BLUE);
            }
        }
    }
}
