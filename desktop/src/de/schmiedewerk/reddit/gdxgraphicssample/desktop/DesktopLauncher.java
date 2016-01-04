package de.schmiedewerk.reddit.gdxgraphicssample.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.schmiedewerk.reddit.gdxgraphicssample.GdxGraphicsHelloWorld;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GdxGraphicsHelloWorld(), config);
	}
}
