package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class GerenciadorOndas {
    private static final float TEMPO_DO_CHEFE = 40f;
    private int onda = 1;
    private int inimigosPorOnda = 5;
    private int spawnadosNaOnda = 0;
    private float tempoDesdeSpawn = 0;
    private float intervaloSpawn = 1.5f;
    private float tempoTotal = 0;
    private boolean chefeSpawnado = false;

    public void atualizar(float delta, Array<Inimigo> inimigos) {
        tempoTotal = tempoTotal + delta;

        if (!chefeSpawnado && tempoTotal >= TEMPO_DO_CHEFE) {
            spawnarChefe(inimigos);
            chefeSpawnado = true;
        }

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
        if (MathUtils.randomBoolean(0.3f)) {
            inimigos.add(new InimigoAtirador(Gdx.graphics.getWidth(), y, 50, 50, 5, 120));
        } else {
            inimigos.add(new InimigoComum(Gdx.graphics.getWidth(), y, 50, 50, 3, 150));
        }
    }

    private void spawnarChefe(Array<Inimigo> inimigos) {
        float y = Gdx.graphics.getHeight() / 2f - 60;
        inimigos.add(new Chefe(Gdx.graphics.getWidth(), y, 120, 120, 50, 120));
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
