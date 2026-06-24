package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class GerenciadorOndas {
    private static final float INTERVALO_CHEFE = 45f;
    private static final float BONUS_VELOCIDADE_POR_ONDA = 30f;
    private static final float BONUS_VELOCIDADE_MAX = 200f;
    private int onda = 1;
    private int inimigosPorOnda = 30;
    private int spawnadosNaOnda = 0;
    private float tempoDesdeSpawn = 0;
    private float intervaloSpawn = 0.9f;
    private float tempoDesdeChefe = 0;
    private float bonusVelocidade = 0;

    public void atualizar(float delta, Array<Inimigo> inimigos) {
        tempoDesdeChefe = tempoDesdeChefe + delta;
        if (tempoDesdeChefe >= INTERVALO_CHEFE) {
            tempoDesdeChefe = 0;
            if (!temChefe(inimigos)) {
                spawnarChefe(inimigos);
            }
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

    private boolean temChefe(Array<Inimigo> inimigos) {
        for (Inimigo ini : inimigos) {
            if (ini.ehChefe()) {
                return true;
            }
        }
        return false;
    }

    private void spawnar(Array<Inimigo> inimigos) {
        float y = MathUtils.random(0, Gdx.graphics.getHeight() - 50);
        if (MathUtils.randomBoolean(0.7f)) {
            inimigos.add(new InimigoAtirador(Gdx.graphics.getWidth(), y, 50, 50, 5, 250 + bonusVelocidade));
        } else {
            inimigos.add(new InimigoComum(Gdx.graphics.getWidth(), y, 50, 50, 3, 500 + bonusVelocidade));
        }
    }

    private void spawnarChefe(Array<Inimigo> inimigos) {
        float y = Gdx.graphics.getHeight() / 2f - 60;
        inimigos.add(new Chefe(Gdx.graphics.getWidth(), y, 120, 120, 50, 120));
    }

    private void avancarOnda() {
        onda = onda + 1;
        spawnadosNaOnda = 0;
        inimigosPorOnda = inimigosPorOnda + 6;
        intervaloSpawn = Math.max(0.5f, intervaloSpawn - 0.15f);
        bonusVelocidade = Math.min(BONUS_VELOCIDADE_MAX, bonusVelocidade + BONUS_VELOCIDADE_POR_ONDA);
    }

    public int getOnda() {
        return onda;
    }
}
