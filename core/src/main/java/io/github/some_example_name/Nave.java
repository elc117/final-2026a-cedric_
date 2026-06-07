package io.github.some_example_name;

abstract public class Nave {
    protected float posX;
    protected float posY;
    protected int tamX;
    protected int tamY;
    protected int hp;
    protected float velocidade;

    public Nave(float posX, float posY, int tamX, int tamY, int hp, float velocidade) {
        this.posX = posX;
        this.posY = posY;
        this.tamX = tamX;
        this.tamY = tamY;
        this.hp = hp;
        this.velocidade = velocidade;
    }

    public float getPosicaoX() {
        return posX;
    }

    public float getPosicaoY() {
        return posY;
    }

    public int getTamanhoX() {
        return tamX;
    }

    public int getTamanhoY() {
        return tamY;
    }
}
