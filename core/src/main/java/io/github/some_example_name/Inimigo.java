package io.github.some_example_name;

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

    public Projetil atirar(float delta) {
        return null;
    }

    public boolean morreNoToque() {
        return true;
    }
}
