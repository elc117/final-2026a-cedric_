package io.github.some_example_name;

public abstract class Inimigo extends Nave {

    public Inimigo(float posX, float posY, int tamX, int tamY, int hp, float velocidade){
        super(posX, posY, tamX, tamY, hp, velocidade);
    }

    public abstract void atualizar(float delta);
}
