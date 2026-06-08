package io.github.some_example_name;

public class InimigoComum extends Inimigo{

    public InimigoComum(float posX, float posY, int tamX, int tamY, int hp, float velocidade){
        super(posX, posY, tamX, tamY, hp, velocidade);
    }

    @Override
    public void atualizar(float delta){
        posX=posX-velocidade*delta;

    }
}
