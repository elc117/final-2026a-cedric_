package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

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
    private GlyphLayout layout;
    private CampoDeEstrelas estrelas;
    private ObjectMap<String, Texture> texturas;
    private boolean gameOver;
    private boolean iniciado;
    private float tempo;
    private Array<Projetil> projeteisInimigos;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2f);
        layout = new GlyphLayout();
        estrelas = new CampoDeEstrelas(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        texturas = new ObjectMap<String, Texture>();
        carregarTextura("navejogador.png");
        carregarTextura("naveauxiliar.png");
        carregarTextura("inimigocomum.png");
        carregarTextura("inimigoatirador.png");
        carregarTextura("chefeinimigo.png");
        carregarTextura("tiros.png");
        iniciar();
        iniciado = false;
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

        estrelas.atualizar(delta, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shape.begin(ShapeRenderer.ShapeType.Filled);
        for (Estrela e : estrelas.getEstrelas()) {
            shape.setColor(e.getBrilho(), e.getBrilho(), e.getBrilho(), 1);
            shape.rect(e.getPosicaoX(), e.getPosicaoY(), e.getTamanho(), e.getTamanho());
        }
        shape.end();

        if (!iniciado) {
            float altura = Gdx.graphics.getHeight();
            batch.begin();
            desenharCentralizado("Mover: WASD / Setas", altura / 2f + 60);
            desenharCentralizado("Atirar: ESPAÇO", altura / 2f);
            desenharCentralizado("ENTER para começar", altura / 2f - 60);
            batch.end();
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                iniciar();
                iniciado = true;
            }
            return;
        }

        if (gameOver) {
            float altura = Gdx.graphics.getHeight();
            batch.begin();
            desenharCentralizado("FIM DE JOGO", altura / 2f + 90);
            desenharCentralizado("Pontuação: " + pontuacao, altura / 2f + 30);
            desenharCentralizado("Tempo: " + (int) tempo + "s", altura / 2f - 30);
            desenharCentralizado("ENTER para recomeçar", altura / 2f - 90);
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
            ini.atirar(delta, projeteisInimigos);
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
        shape.setColor(Color.CYAN);
        for (PowerUp pu : powerups) {
            shape.rect(pu.getPosicaoX(), pu.getPosicaoY(), pu.getTamanhoX(), pu.getTamanhoY());
        }
        shape.end();

        batch.begin();
        for (Inimigo ini : inimigos) {
            Texture t = texturas.get(ini.getSprite());
            batch.draw(t, ini.getPosicaoX(), ini.getPosicaoY(), ini.getTamanhoX(), ini.getTamanhoY(),
                0, 0, t.getWidth(), t.getHeight(), true, false);
        }
        Texture tiro = texturas.get("tiros.png");
        for (Projetil p : projeteis) {
            batch.draw(tiro, p.getPosicaoX(), p.getPosicaoY(), p.getTamanhoX(), p.getTamanhoY());
        }
        for (Projetil p : projeteisInimigos) {
            batch.draw(tiro, p.getPosicaoX(), p.getPosicaoY(), p.getTamanhoX(), p.getTamanhoY(),
                0, 0, tiro.getWidth(), tiro.getHeight(), true, false);
        }
        boolean esquadraoVisivel = !(esquadrao.estaInvencivel() && (int) (tempo * 10) % 2 == 0);
        if (esquadraoVisivel) {
            batch.draw(texturas.get("navejogador.png"), nave.getPosicaoX(), nave.getPosicaoY(),
                nave.getTamanhoX(), nave.getTamanhoY());
            Texture tAux = texturas.get("naveauxiliar.png");
            for (NaveAuxiliar aux : esquadrao.getAuxiliares()) {
                batch.draw(tAux, aux.getPosicaoX(), aux.getPosicaoY(), aux.getTamanhoX(), aux.getTamanhoY());
            }
        }
        int altura = Gdx.graphics.getHeight();
        font.draw(batch, "Pontuação: " + pontuacao, 20, altura - 20);
        font.draw(batch, "Tempo: " + (int) tempo + "s", 20, altura - 60);
        batch.end();
    }

    private void carregarTextura(String nome) {
        Texture t = new Texture(Gdx.files.internal(nome));
        t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        texturas.put(nome, t);
    }

    private void desenharCentralizado(String texto, float y) {
        layout.setText(font, texto);
        font.draw(batch, layout, (Gdx.graphics.getWidth() - layout.width) / 2f, y);
    }

    @Override
    public void dispose() {
        shape.dispose();
        batch.dispose();
        font.dispose();
        for (Texture t : texturas.values()) {
            t.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        shape.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }
}
