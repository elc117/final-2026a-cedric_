package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class NaveJogador extends Nave {
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

    }
}
