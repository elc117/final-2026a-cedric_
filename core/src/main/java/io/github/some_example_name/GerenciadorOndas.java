package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class GerenciadorOndas {
    private int onda = 1;
    private int inimigosPorOnda = 5;
    private int spawnadosNaOnda = 0;
    private float tempoDesdeSpawn = 0;
    private float intervaloSpawn = 1.5f;

    public void atualizar(float delta, Array<Inimigo> inimigos) {
        tempoDesdeSpawn = tempoDesdeSpawn + delta;

        if (spawnadosNaOnda < inimigosPorOnda) {
            if (tempoDesdeSpawn >= intervaloSpawn) {
                tempoDesdeSpawn = 0;
                spawnar(inimigos);
                spawnadosNaOnda = spawnadosNaOnda + 1;
            }
        } else if (inimigos.size == 0) {
            avancarOnda();
        }
    }

    private void spawnar(Array<Inimigo> inimigos) {
        float y = MathUtils.random(0, Gdx.graphics.getHeight() - 50);
        inimigos.add(new InimigoComum(Gdx.graphics.getWidth(), y, 50, 50, 3, 150));
    }

    private void avancarOnda() {
        onda = onda + 1;
        spawnadosNaOnda = 0;
        inimigosPorOnda = inimigosPorOnda + 2;
        intervaloSpawn = Math.max(0.5f, intervaloSpawn - 0.15f);
    }

    public int getOnda() {
        return onda;
    }
}
