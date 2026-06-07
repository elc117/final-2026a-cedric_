package io.github.some_example_name.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import io.github.some_example_name.Main;

/** Launches the GWT application. */
public class GwtLauncher extends GwtApplication {
        @Override
        public GwtApplicationConfiguration getConfig () {
            // Tamanho fixo, igual ao desktop (1280x720), para a nave aparecer
            // na mesma posicao e o jogo ter o mesmo sistema de coordenadas.
            return new GwtApplicationConfiguration(1280, 720);
        }
        @Override
        public ApplicationListener createApplicationListener () {
            return new Main();
        }
}