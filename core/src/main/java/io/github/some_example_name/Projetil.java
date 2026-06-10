package io.github.some_example_name;

import com.badlogic.gdx.math.Rectangle;

public class Projetil {
    private float posX;
    private float posY;
    private int tamX = 20;
    private int tamY = 10;
    private float velocidade = 700;
    private int dano = 1;
    private Rectangle caixa;

    public Projetil(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        caixa = new Rectangle(posX, posY, tamX, tamY);

    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }

    public Rectangle getCaixa() {
        return caixa;
    }

    public void atualizar(float delta) {
        posX = posX + velocidade * delta;
        caixa.set(posX, posY, tamX, tamY);
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

    public boolean saiuDaTela(float larguraTela) {
        return posX > larguraTela;
    }


}
