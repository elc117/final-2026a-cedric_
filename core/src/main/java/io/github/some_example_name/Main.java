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
    private Array<Inimigo> inimigos;
    private int pontuacao=0;
    private Array<NaveAuxiliar> auxiliares;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        nave = new NaveJogador(100, 360, 60, 30, 100, 400);
        projeteis = new Array<Projetil>();
        inimigos = new Array<Inimigo>();
        inimigos.add(new InimigoComum(1280, 360, 50, 50, 3, 150));
        auxiliares= new Array<NaveAuxiliar>();
        auxiliares.add(new NaveAuxiliar(-30,45, nave));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();

        Projetil novo = nave.atirar(delta);
        if (novo != null) {
            projeteis.add(novo);
            for (NaveAuxiliar aux : auxiliares) {
                projeteis.add(aux.atirar());
            }
        }

        for (int i = projeteis.size - 1; i >= 0; i--) {
            Projetil p = projeteis.get(i);
            p.atualizar(delta);
            if (p.saiuDaTela(Gdx.graphics.getWidth())) {
                projeteis.removeIndex(i);
            }
        }

        for (int i = inimigos.size - 1; i >= 0; i--) {
            Inimigo ini = inimigos.get(i);
            ini.atualizar(delta);
        }

        for(int i = projeteis.size -1; i >=0; i--){
            Projetil p = projeteis.get(i);
            for (int j = inimigos.size - 1; j >= 0; j--){
                Inimigo ini = inimigos.get(j);
                if (p.getCaixa().overlaps(ini.getCaixa())) {
                    ini.receberDano(p.getDano());
                    projeteis.removeIndex(i);
                    if (ini.estaMorto()) {
                        inimigos.removeIndex(j);
                        pontuacao += 100;
                    }
                    break;
                }
            }
        }

        nave.mover(delta);

        for(NaveAuxiliar aux : auxiliares){
            aux.seguir(nave);
        }

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.GREEN);
        for (Inimigo ini : inimigos) {
            shape.rect(ini.getPosicaoX(), ini.getPosicaoY(), ini.getTamanhoX(), ini.getTamanhoY());
        }
        shape.setColor(Color.RED);
        for (Projetil p : projeteis) {
            shape.rect(p.getPosicaoX(), p.getPosicaoY(), p.getTamanhoX(), p.getTamanhoY());
        }
        shape.setColor(Color.YELLOW);
        shape.rect(nave.getPosicaoX(), nave.getPosicaoY(), nave.getTamanhoX(), nave.getTamanhoY());

        shape.setColor(Color.ORANGE);

        for(NaveAuxiliar aux : auxiliares){
            shape.rect(aux.getPosicaoX(), aux.getPosicaoY(), aux.getTamanhoX(), aux.getTamanhoY());
        }
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
