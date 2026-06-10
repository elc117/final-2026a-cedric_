package io.github.some_example_name;

public class NaveAuxiliar extends Nave {
    private float offsetX;
    private float offsetY;

    public NaveAuxiliar(float offsetX, float offsetY, Nave lider) {
        super(0, 0, lider.getTamanhoX(), lider.getTamanhoY(), 100, 0);
        this.offsetX=offsetX;
        this.offsetY=offsetY;
    }

    public void seguir(Nave lider){
        posX=lider.getPosicaoX()+offsetX;
        posY=lider.getPosicaoY()+offsetY;
        atualizarCaixa();
    }


}
