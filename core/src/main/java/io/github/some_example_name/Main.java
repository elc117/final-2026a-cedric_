package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class Main extends ApplicationAdapter {
    private ShapeRenderer shape;
    private NaveJogador nave;
    private Array<Projetil> projeteis;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        nave = new NaveJogador(100, 360, 100, 50, 100, 250);
        projeteis = new Array<Projetil>();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();

        Projetil novo = nave.atirar(delta);
        if (novo != null) {
            projeteis.add(novo);
        }

        for (int i = projeteis.size - 1; i >= 0; i--) {
            Projetil p = projeteis.get(i);
            p.atualizar(delta);
            if (p.saiuDaTela(Gdx.graphics.getWidth())) {
                projeteis.removeIndex(i);
            }
        }

        nave.mover(delta);

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.RED);
        for (Projetil p : projeteis) {
            shape.rect(p.getPosicaoX(), p.getPosicaoY(), p.getTamanhoX(), p.getTamanhoY());
        }
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
