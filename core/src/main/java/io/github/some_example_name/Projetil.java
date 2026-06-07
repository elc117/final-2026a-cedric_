package io.github.some_example_name;

public class Projetil {
    private float posX;
    private float posY;
    private int tamX = 20;
    private int tamY = 10;
    private float velocidade = 600;
    private int dano = 1;

    public Projetil(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public void atualizar(float delta) {
        posX = posX + velocidade * delta;
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

    public boolean saiuDaTela(float larguraTela){
        return posX>larguraTela;
    }

}
