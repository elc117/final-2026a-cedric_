package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class Main extends ApplicationAdapter {
    private ShapeRenderer shape;
    private NaveJogador nave;
    private Array<Projetil> projeteis;
    private Array<Inimigo> inimigos;
    private Array<PowerUp> powerups;
    private int pontuacao=0;
    private Esquadrao esquadrao;
    private GerenciadorOndas gerenciador;
    private GerenciadorPowerUps gerenciadorPowerUps;
    private SpriteBatch batch;
    private BitmapFont font;
    private boolean gameOver;
    private float tempo;
    private Array<Projetil> projeteisInimigos;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2f);
        iniciar();
    }

    private void iniciar() {
        nave = new NaveJogador(100, 360, 40, 15, 100, 400);
        projeteis = new Array<Projetil>();
        inimigos = new Array<Inimigo>();
        powerups = new Array<PowerUp>();
        projeteisInimigos = new Array<Projetil>();
        esquadrao = new Esquadrao(nave);
        gerenciador = new GerenciadorOndas();
        gerenciadorPowerUps = new GerenciadorPowerUps();
        pontuacao = 0;
        gameOver = false;
        tempo = 0;
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();

        if (gameOver) {
            int largura = Gdx.graphics.getWidth();
            int altura = Gdx.graphics.getHeight();
            batch.begin();
            font.draw(batch, "GAME OVER", largura / 2f - 90, altura / 2f + 60);
            font.draw(batch, "Pontuacao: " + pontuacao, largura / 2f - 90, altura / 2f);
            font.draw(batch, "ENTER para recomecar", largura / 2f - 130, altura / 2f - 60);
            batch.end();
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                iniciar();
            }
            return;
        }

        tempo += delta;

        esquadrao.atirar(delta, projeteis);

        for (int i = projeteis.size - 1; i >= 0; i--) {
            Projetil p = projeteis.get(i);
            p.atualizar(delta);
            if (p.saiuDaTela(Gdx.graphics.getWidth())) {
                projeteis.removeIndex(i);
            }
        }

        gerenciador.atualizar(delta, inimigos);

        for (int i = inimigos.size - 1; i >= 0; i--) {
            Inimigo ini = inimigos.get(i);
            ini.atualizar(delta);
            Projetil tiro = ini.atirar(delta);
            if (tiro != null) {
                projeteisInimigos.add(tiro);
            }
            if (ini.saiuDaTela()) {
                inimigos.removeIndex(i);
            }
        }

        for(int i = projeteis.size -1; i >=0; i--){
            Projetil p = projeteis.get(i);
            for (int j = inimigos.size - 1; j >= 0; j--){
                Inimigo ini = inimigos.get(j);
                if (p.getCaixa().overlaps(ini.getCaixa())) {
                    ini.receberDano(p.getDano());
                    projeteis.removeIndex(i);
                    if (ini.estaMorto()) {
                        pontuacao += ini.getPontos();
                        inimigos.removeIndex(j);
                    }
                    break;
                }
            }
        }

        esquadrao.mover(delta);

        for (int i = inimigos.size - 1; i >= 0; i--) {
            Inimigo ini = inimigos.get(i);
            if (esquadrao.colideCom(ini.getCaixa())) {
                if (ini.morreNoToque()) {
                    inimigos.removeIndex(i);
                }
                if (esquadrao.levarDano()) {
                    gameOver = true;
                }
            }
        }

        for (int i = projeteisInimigos.size - 1; i >= 0; i--) {
            Projetil p = projeteisInimigos.get(i);
            p.atualizar(delta);
            if (p.saiuDaTela(Gdx.graphics.getWidth())) {
                projeteisInimigos.removeIndex(i);
            } else if (esquadrao.colideCom(p.getCaixa())) {
                projeteisInimigos.removeIndex(i);
                if (esquadrao.levarDano()) {
                    gameOver = true;
                }
            }
        }

        if (!esquadrao.estaCheio()) {
            gerenciadorPowerUps.atualizar(delta, powerups);
        }

        for (int i = powerups.size - 1; i >= 0; i--) {
            PowerUp pu = powerups.get(i);
            pu.atualizar(delta);
            if (pu.saiuDaTela()) {
                powerups.removeIndex(i);
            } else if (nave.getCaixa().overlaps(pu.getCaixa())) {
                esquadrao.adicionarAuxiliar();
                powerups.removeIndex(i);
            }
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
        shape.setColor(Color.MAGENTA);
        for (Projetil p : projeteisInimigos) {
            shape.rect(p.getPosicaoX(), p.getPosicaoY(), p.getTamanhoX(), p.getTamanhoY());
        }
        shape.setColor(Color.YELLOW);
        shape.rect(nave.getPosicaoX(), nave.getPosicaoY(), nave.getTamanhoX(), nave.getTamanhoY());

        shape.setColor(Color.ORANGE);

        for(NaveAuxiliar aux : esquadrao.getAuxiliares()){
            shape.rect(aux.getPosicaoX(), aux.getPosicaoY(), aux.getTamanhoX(), aux.getTamanhoY());
        }

        shape.setColor(Color.CYAN);
        for (PowerUp pu : powerups) {
            shape.rect(pu.getPosicaoX(), pu.getPosicaoY(), pu.getTamanhoX(), pu.getTamanhoY());
        }
        shape.end();

        int altura = Gdx.graphics.getHeight();
        batch.begin();
        font.draw(batch, "Pontuacao: " + pontuacao, 20, altura - 20);
        font.draw(batch, "Tempo: " + (int) tempo + "s", 20, altura - 60);
        batch.end();
    }

    @Override
    public void dispose() {
        shape.dispose();
        batch.dispose();
        font.dispose();
    }

    @Override
    public void resize(int width, int height) {
        shape.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }
}
