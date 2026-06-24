package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class InimigoComum extends Inimigo{
    private boolean diagonal = MathUtils.randomBoolean();
    private int direcaoVertical = MathUtils.randomBoolean() ? 1 : -1;

    public InimigoComum(float posX, float posY, int tamX, int tamY, int hp, float velocidade){
        super(posX, posY, tamX, tamY, hp, velocidade);
    }

    @Override
    public void atualizar(float delta){
        posX = posX - velocidade * delta;
        if (diagonal) {
            posY = posY + velocidade * direcaoVertical * delta;
            if (posY <= 0) {
                posY = 0;
                direcaoVertical = 1;
            } else if (posY + tamY >= Gdx.graphics.getHeight()) {
                posY = Gdx.graphics.getHeight() - tamY;
                direcaoVertical = -1;
            }
        }
        atualizarCaixa();
    }
}
