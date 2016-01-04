package de.schmiedewerk.reddit.gdxgraphicssample.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.schmiedewerk.reddit.gdxgraphicssample.GdxGraphicsHelloWorld;
import de.schmiedewerk.reddit.gdxgraphicssample.examples.PixelRectangle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new PixelRectangle(), config);
	}
}
