package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

public class InimigoAtirador extends Inimigo {
    private float tempoUltimoTiro = 0;
    private float intervaloTiro = 1.8f;

    public InimigoAtirador(float posX, float posY, int tamX, int tamY, int hp, float velocidade) {
        super(posX, posY, tamX, tamY, hp, velocidade);
    }

    @Override
    public void atualizar(float delta) {
        posX = posX - velocidade * delta;
        atualizarCaixa();
    }

    @Override
    public void atirar(float delta, Array<Projetil> tiros) {
        tempoUltimoTiro = tempoUltimoTiro + delta;
        if (tempoUltimoTiro >= intervaloTiro) {
            tempoUltimoTiro = 0;
            Projetil tiro = new Projetil(posX, posY + tamY / 2f);
            tiro.setDirecao(-1);
            tiro.setVelocidade(1000);
            tiros.add(tiro);
        }
    }

    @Override
    public int getPontos() {
        return 200;
    }

    @Override
    public Color getCor() {
        return Color.PURPLE;
    }
}
