package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

public abstract class Inimigo extends Nave {

    public Inimigo(float posX, float posY, int tamX, int tamY, int hp, float velocidade){
        super(posX, posY, tamX, tamY, hp, velocidade);
    }

    public abstract void atualizar(float delta);

    public boolean saiuDaTela() {
        return posX + tamX < 0;
    }

    public int getPontos() {
        return 100;
    }

    public void atirar(float delta, Array<Projetil> tiros) {
    }

    public boolean morreNoToque() {
        return true;
    }

    public Color getCor() {
        return Color.GREEN;
    }

    public boolean ehChefe() {
        return false;
    }
}
