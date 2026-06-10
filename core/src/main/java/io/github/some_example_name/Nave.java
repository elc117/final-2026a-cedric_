package io.github.some_example_name;
import com.badlogic.gdx.math.Rectangle;

abstract public class Nave {
    protected float posX;
    protected float posY;
    protected float tamX;
    protected float tamY;
    protected int hp;
    protected float velocidade;
    private Rectangle caixa;

    public Nave(float posX, float posY, float tamX, float tamY, int hp, float velocidade) {
        this.posX = posX;
        this.posY = posY;
        this.tamX = tamX;
        this.tamY = tamY;
        this.hp = hp;
        this.velocidade = velocidade;
        caixa = new Rectangle(posX, posY, tamX, tamY);
    }

    public float getPosicaoX() {
        return posX;
    }

    public float getPosicaoY() {
        return posY;
    }

    public float getTamanhoX() {
        return tamX;
    }

    public float getTamanhoY() {
        return tamY;
    }

    public Rectangle getCaixa() {
        return caixa;
    }

    public void receberDano(int dano) {
        hp = hp - dano;
    }

    public Boolean estaMorto() {
        if (hp <= 0) {
            return true;
        } else {
            return false;
        }
    }

    protected void atualizarCaixa() {
        caixa.set(posX, posY, tamX, tamY);
    }

}
