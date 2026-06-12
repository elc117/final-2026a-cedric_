package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class NaveJogador extends Nave {
    private float tempoUltimoTiro;
    private float intervaloTiro=0.22f;

    public NaveJogador(float posX, float posY, int tamX, int tamY, int hp, float velocidade) {
        super(posX, posY, tamX, tamY, hp, velocidade);
    }

    public void mover(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            posX = posX - velocidade * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            posX = posX + velocidade * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            posY = posY + velocidade * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            posY = posY - velocidade * delta;
        }

        posX = Math.max(0, Math.min(posX, Gdx.graphics.getWidth() - tamX));
        posY = Math.max(0, Math.min(posY, Gdx.graphics.getHeight() - tamY));
    }

    public Projetil atirar(float delta) {
        tempoUltimoTiro = tempoUltimoTiro + delta;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && tempoUltimoTiro >= intervaloTiro) {
            tempoUltimoTiro=0;
            Projetil p = new Projetil((posX+tamX),(posY+tamY/2));
            return p;
        }else{
            return null;
        }

    }

}
