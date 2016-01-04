package de.schmiedewerk.reddit.gdxgraphicssample.graphics;

import com.badlogic.gdx.utils.Pool;

/**
 * Created by /u/schmiedewerkgames @schmiedewerk on 04.01.2016.
 */
public class SpritePool extends Pool<Sprite> {

    public SpritePool(int size) {
        super(size, size);
    }

    @Override
    protected Sprite newObject() {
        return new Sprite();
    }
}
