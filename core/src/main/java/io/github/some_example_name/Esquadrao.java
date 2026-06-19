package io.github.some_example_name;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Esquadrao {
    private NaveJogador lider;
    private Array<NaveAuxiliar> auxiliares;
    private float[] offsetsX = {-30, -30, -60, -60};
    private float[] offsetsY = {25, -25, 50, -50};
    private static final int MAX_AUXILIARES = 4;
    private static final float DURACAO_INVENCIVEL = 1f;
    private float tempoInvencivel = 0;

    public Esquadrao(NaveJogador lider) {
        this.lider = lider;
        auxiliares = new Array<NaveAuxiliar>();
    }

    public void mover(float delta) {
        if (tempoInvencivel > 0) {
            tempoInvencivel = tempoInvencivel - delta;
        }
        lider.mover(delta);
        for (NaveAuxiliar aux : auxiliares) {
            aux.seguir(lider);
        }
    }

    public void atirar(float delta, Array<Projetil> projeteis) {
        Projetil p = lider.atirar(delta);
        if (p != null) {
            projeteis.add(p);
            for (NaveAuxiliar aux : auxiliares) {
                projeteis.add(aux.atirar());
            }
        }
    }

    public boolean estaCheio() {
        return auxiliares.size >= MAX_AUXILIARES;
    }

    public boolean adicionarAuxiliar() {
        if (auxiliares.size >= MAX_AUXILIARES) {
            return false;
        }
        NaveAuxiliar nova = new NaveAuxiliar(offsetsX[auxiliares.size], offsetsY[auxiliares.size], lider);
        nova.seguir(lider);
        auxiliares.add(nova);
        return true;
    }

    public boolean colideCom(Rectangle caixa) {
        if (lider.getCaixa().overlaps(caixa)) {
            return true;
        }
        for (NaveAuxiliar aux : auxiliares) {
            if (aux.getCaixa().overlaps(caixa)) {
                return true;
            }
        }
        return false;
    }

    public boolean removerAuxiliar() {
        if (auxiliares.size > 0) {
            auxiliares.removeIndex(auxiliares.size - 1);
            return true;
        }
        return false;
    }

    public boolean levarDano() {
        if (tempoInvencivel > 0) {
            return false;
        }
        tempoInvencivel = DURACAO_INVENCIVEL;
        return !removerAuxiliar();
    }

    public NaveJogador getLider() {
        return lider;
    }

    public Array<NaveAuxiliar> getAuxiliares() {
        return auxiliares;
    }
}
