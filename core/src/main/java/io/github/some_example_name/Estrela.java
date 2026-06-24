package io.github.some_example_name;

import com.badlogic.gdx.math.MathUtils;

public class Estrela {
    private float posX;
    private float posY;
    private float velocidade;
    private float tamanho;
    private float brilho;

    public Estrela(float posX, float posY, float velocidade, float tamanho, float brilho) {
        this.posX = posX;
        this.posY = posY;
        this.velocidade = velocidade;
        this.tamanho = tamanho;
        this.brilho = brilho;
    }

    public void atualizar(float delta, float largura, float altura) {
        posX = posX - velocidade * delta;
        if (posX < 0) {
            posX = largura;
            posY = MathUtils.random(0, altura);
        }
    }

    public float getPosicaoX() {
        return posX;
    }

    public float getPosicaoY() {
        return posY;
    }

    public float getTamanho() {
        return tamanho;
    }

    public float getBrilho() {
        return brilho;
    }
}
