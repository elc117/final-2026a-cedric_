package io.github.some_example_name;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class CampoDeEstrelas {
    private static final int QUANTIDADE = 120;
    private Array<Estrela> estrelas;

    public CampoDeEstrelas(float largura, float altura) {
        estrelas = new Array<Estrela>();
        for (int i = 0; i < QUANTIDADE; i++) {
            float velocidade = MathUtils.random(20f, 200f);
            float tamanho = 1f + velocidade / 100f;
            float brilho = 0.3f + velocidade / 285f;
            float x = MathUtils.random(0, largura);
            float y = MathUtils.random(0, altura);
            estrelas.add(new Estrela(x, y, velocidade, tamanho, brilho));
        }
    }

    public void atualizar(float delta, float largura, float altura) {
        for (Estrela e : estrelas) {
            e.atualizar(delta, largura, altura);
        }
    }

    public Array<Estrela> getEstrelas() {
        return estrelas;
    }
}
