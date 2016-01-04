package de.schmiedewerk.reddit.gdxgraphicssample.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by /u/schmiedewerkgames @schmiedewerk on 04.01.2016.
 */
public class Sprite implements Pool.Poolable {

    /*default*/ Graphics graphics;

    /*default*/ int layer;
    /*default*/ TextureRegion region;
    /*default*/ boolean visible = true;

    /*default*/ float x, y;
    /*default*/ float ox, oy;
    /*default*/ float w, h;
    /*default*/ float sx = 1, sy = 1;
    /*default*/ float rotation;
    /*default*/ float r = 1, g = 1, b = 1, a = 1;

    public void dispose() {
        graphics.free(this);
    }

    public void layer(int layer) {
        this.layer = layer;
    }

    public void region(TextureRegion region) {
        this.region = region;
        size(region.getRegionWidth(), region.getRegionHeight());
        origin(region.getRegionWidth() / 2f, region.getRegionHeight() / 2f);
    }

    public void visible(boolean visible) {
        this.visible = visible;
    }

    public void position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void origin(float x, float y) {
        this.ox = x;
        this.oy = y;
    }

    public void size(float width, float height) {
        this.w = width;
        this.h = height;
    }

    public void scale(float x, float y) {
        this.sx = x;
        this.sy = y;
    }

    public void rotation(float rotation) {
        this.rotation = rotation;
    }

    public void color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    @Override
    public void reset() {
        graphics = null;

        layer = 0;
        region = null;
        visible = true;

        x = 0;
        y = 0;
        ox = 0;
        oy = 0;
        w = 0;
        h = 0;
        sx = 1;
        sy = 1;
        rotation = 0;
        r = 1;
        g = 1;
        b = 1;
        a = 1;
    }
}
