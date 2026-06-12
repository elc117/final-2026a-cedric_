package io.github.some_example_name;

import com.badlogic.gdx.utils.Array;

public class Esquadrao {
    private NaveJogador lider;
    private Array<NaveAuxiliar> auxiliares;
    private float[] offsetsX = {-30, -30, -60, -60};
    private float[] offsetsY = {25, -25, 50, -50};
    private static final int MAX_AUXILIARES = 4;

    public Esquadrao(NaveJogador lider) {
        this.lider = lider;
        auxiliares = new Array<NaveAuxiliar>();
    }

    public void mover(float delta) {
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

    public boolean adicionarAuxiliar() {
        if (auxiliares.size >= MAX_AUXILIARES) {
            return false;
        }
        NaveAuxiliar nova = new NaveAuxiliar(offsetsX[auxiliares.size], offsetsY[auxiliares.size], lider);
        nova.seguir(lider);
        auxiliares.add(nova);
        return true;
    }

    public NaveJogador getLider() {
        return lider;
    }

    public Array<NaveAuxiliar> getAuxiliares() {
        return auxiliares;
    }
}
