package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class GerenciadorPowerUps {
    private float tempoDesdeSpawn = 0;
    private float intervaloSpawn = 60f;

    public void atualizar(float delta, Array<PowerUp> powerups) {
        tempoDesdeSpawn = tempoDesdeSpawn + delta;
        if (tempoDesdeSpawn >= intervaloSpawn) {
            tempoDesdeSpawn = 0;
            float y = MathUtils.random(0, Gdx.graphics.getHeight() - 25);
            powerups.add(new PowerUp(Gdx.graphics.getWidth(), y));
        }
    }
}
