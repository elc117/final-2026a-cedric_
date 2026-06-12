package io.github.some_example_name;

import com.badlogic.gdx.math.Rectangle;

public class PowerUp {
    private float posX;
    private float posY;
    private int tamX = 25;
    private int tamY = 25;
    private float velocidade = 150;
    private Rectangle caixa;

    public PowerUp(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        caixa = new Rectangle(posX, posY, tamX, tamY);
    }

    public void atualizar(float delta) {
        posX = posX - velocidade * delta;
        caixa.set(posX, posY, tamX, tamY);
    }

    public boolean saiuDaTela() {
        return posX + tamX < 0;
    }

    public Rectangle getCaixa() {
        return caixa;
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
