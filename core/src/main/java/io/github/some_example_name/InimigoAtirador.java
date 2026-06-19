package io.github.some_example_name;

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
    public Projetil atirar(float delta) {
        tempoUltimoTiro = tempoUltimoTiro + delta;
        if (tempoUltimoTiro >= intervaloTiro) {
            tempoUltimoTiro = 0;
            Projetil tiro = new Projetil(posX, posY + tamY / 2f);
            tiro.setDirecao(-1);
            tiro.setVelocidade(400);
            return tiro;
        }
        return null;
    }

    @Override
    public int getPontos() {
        return 200;
    }
}
