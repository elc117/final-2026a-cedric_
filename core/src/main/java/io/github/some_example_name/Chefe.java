package io.github.some_example_name;

import com.badlogic.gdx.Gdx;

public class Chefe extends Inimigo {
    private float alvoX;
    private boolean chegou = false;
    private float velocidadeVertical = 200;
    private int direcaoVertical = 1;
    private float tempoUltimoTiro = 0;
    private float intervaloTiro = 1.2f;

    public Chefe(float posX, float posY, int tamX, int tamY, int hp, float velocidade) {
        super(posX, posY, tamX, tamY, hp, velocidade);
        alvoX = Gdx.graphics.getWidth() - tamX - 60;
    }

    @Override
    public void atualizar(float delta) {
        if (!chegou) {
            posX = posX - velocidade * delta;
            if (posX <= alvoX) {
                posX = alvoX;
                chegou = true;
            }
        } else {
            posY = posY + velocidadeVertical * direcaoVertical * delta;
            if (posY <= 0) {
                posY = 0;
                direcaoVertical = 1;
            } else if (posY + tamY >= Gdx.graphics.getHeight()) {
                posY = Gdx.graphics.getHeight() - tamY;
                direcaoVertical = -1;
            }
        }
        atualizarCaixa();
    }

    @Override
    public int getPontos() {
        return 1000;
    }

    @Override
    public Projetil atirar(float delta) {
        if (!chegou) {
            return null;
        }
        tempoUltimoTiro = tempoUltimoTiro + delta;
        if (tempoUltimoTiro >= intervaloTiro) {
            tempoUltimoTiro = 0;
            Projetil tiro = new Projetil(posX, posY + tamY / 2f);
            tiro.setDirecao(-1);
            tiro.setVelocidade(450);
            return tiro;
        }
        return null;
    }

    @Override
    public boolean morreNoToque() {
        return false;
    }
}
