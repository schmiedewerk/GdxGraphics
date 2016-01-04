package de.schmiedewerk.reddit.gdxgraphicssample.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Comparator;

/**
 * Created by /u/schmiedewerkgames @schmiedewerk on 04.01.2016.
 */
public class Graphics implements Disposable {

    private final SpriteBatch batch;
    private final Viewport viewport;
    private final OrthographicCamera camera;

    private final Pool<Sprite> spritePool;
    private final Array<Sprite> sprites;
    private final Array<Sprite> queue;

    private final Comparator<Sprite> layerComparator;

    private final Vector2 tmp = new Vector2();

    public Graphics(float virtualWidth, float virtualHeight, int maxSpriteCount) {
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(virtualWidth, virtualHeight, camera);

        this.spritePool = new SpritePool(maxSpriteCount);
        this.sprites = new Array<Sprite>(maxSpriteCount);
        this.queue = new Array<Sprite>(maxSpriteCount);

        this.layerComparator = new Comparator<Sprite>() {
            @Override
            public int compare(Sprite a, Sprite b) {
                // higher layer value: draw on-top
                return Integer.compare(a.layer, b.layer);
            }
        };

        fillSpritePool(maxSpriteCount);
    }

    public Sprite sprite() {
        Sprite sprite = spritePool.obtain();
        sprite.graphics = this;

        sprites.add(sprite);

        return sprite;
    }

    public void draw() {
        prepareQueue();
        commit();
    }

    public void onResize(int width, int height) {
        viewport.update(width, height);
    }

    /*default*/ void free(Sprite sprite) {
        sprites.removeValue(sprite, true);
        spritePool.free(sprite);
    }

    private void prepareQueue() {
        queue.clear();

        for (Sprite sprite : sprites) {
            if (sprite.region == null) {
                throw new IllegalStateException("Sprite leaked");
            }
            if (!sprite.visible) {
                continue;
            }
            if (sprite.a == 0) {
                continue;
            }

            queue.add(sprite);
        }

        queue.sort(layerComparator);
    }

    private void commit() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply(true);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for (Sprite sprite : queue) {
            batch.setColor(sprite.r, sprite.g, sprite.b, sprite.a);
            batch.draw(
                    sprite.region,
                    sprite.x, sprite.y,
                    sprite.ox, sprite.oy,
                    sprite.w, sprite.h,
                    sprite.sx, sprite.sy,
                    sprite.rotation
            );
        }
        batch.end();
    }

    private void fillSpritePool(int maxSpriteCount) {
        for (int i = 0; i < maxSpriteCount; i++) {
            Sprite sprite = spritePool.obtain();
            sprites.add(sprite);
        }
        for (Sprite sprite : sprites) {
            spritePool.free(sprite);
        }
        sprites.clear();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
