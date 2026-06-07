package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends ApplicationAdapter {
    private ShapeRenderer shape;
    private NaveJogador nave;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        nave = new NaveJogador(100,360,50,50,100,250);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        nave.Mover(Gdx.graphics.getDeltaTime());

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.YELLOW);
        shape.rect(nave.getPosicaoX(), nave.getPosicaoY(), nave.getTamanhoX(), nave.getTamanhoY());
        shape.end();
    }

    @Override
    public void dispose() {
        shape.dispose();
    }

    @Override
    public void resize(int width, int height) {
        shape.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }
}
