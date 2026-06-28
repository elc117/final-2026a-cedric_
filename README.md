
# Relatório Projeto

## Identificação
Cedric Marques Rocha - Sistemas de Informação

## Proposta

A proposta de projeto consiste em um jogo 2D do gênero shoot'em up de rolagem lateral, desenvolvido em Java com o framework libGDX. A escolha do gênero veio do tema obrigatório do trabalho, "grupo". O objetivo estabelecido foi fazer com que esse tema fizesse parte da jogabilidade, e não fosse só um detalhe. Por isso, em vez de o jogador controlar uma única nave, como na maioria dos jogos do tipo, a ideia central é que ele comande um esquadrão que vai crescendo ao longo da partida. O tema aparece, portanto, na mecânica principal: o jogador controla uma nave central e, ao longo do jogo, pode recrutar naves auxiliares que se posicionam em formação fixa ao seu redor, formando um esquadrão de até 5 naves que atiram em conjunto. Quanto maior o grupo, maior o dano do jogador. Ao ser atingido, o esquadrão perde uma nave auxiliar, e quando sobra apenas a líder sozinha o jogo termina.
A estrutura do jogo é de rolagem lateral: o esquadrão avança pela tela enquanto surgem inimigos em ondas, que vão ficando mais numerosos e mais rápidos com o tempo. Os inimigos têm padrões de ataque variados, alguns apenas avançam em direção ao jogador, outros disparam projéteis, e o chefe se movimenta em patrulha e atira em triplos.
Entre as funcionalidades estão presentes: movimentação da nave pelo teclado, coleta de power-ups que aumentam o esquadrão, inimigos que surgem com padrões de ataque variados, detecção de colisões entre projéteis e naves inimigas, um sistema de pontuação e um chefe ao final da fase. Além dessas, o projeto também ganhou telas de início e de fim de jogo, um fundo estelar com efeito de parallax e sprites em pixel art para dar mais identidade visual.

## Processo de desenvolvimento

O processo de desenvolvimento se deu de forma bastante iterativa e incremental. O primeiro contato com o framework e escopo (desenvolvimento de jogo) foi lento por minha falta de experiência, porém, consegui uma evolução gradual até chegar no produto final. O primeiro problema encontrado se deu nas dependências do projeto, que foi rapidamente solucionado com ajuda de IA, o que estava acontecendo era incopatibilidade numa das versões de uma das dependências, isso foi solucionado ao remover a dependência, já que ao realizar uma breve pesquisa, constatatou-se que ela não seria necessária. Então eu parti para o desenvolvimento, inicialmente eu comecei a procurar entender como funcionava a estrutura do projeto, depois eu fui para a pasta core, na main, lá estudei e fiz teste nos métodos create e render, que são toda base do jogo no libGDX. Após imprimir um quadrado na tela (que representava a nave do jogador e posteriormete sua hitbox), procurei entender como poderia se dar a movimentação dos objetos na tela e depois o dano (sobreposição de objetos). E então continuei adicionando objeto por objeto, definindo seus métodos, classes, e suas relações de forma evolutiva (sem um planejamento prévio). Buscando sempre aplicar o conteúdo visto em sala, e ter coerência com genêro do jogo. Na proposta incial eu havia estipulado que as naves poderiam tiros em direções diferentes, após testes, achei melhor a mira fixa e remover esse requisito, já que ele não era essencial para os objetivos do trabalho. Para mim o mais complexo foi a lógica de jogo em si, por ter que lidar com um código caracterizado por uma  execução/renderização constante. Em suma, acho que consegui desenvolver um trabalho que atendeu os requistos e finalidade proposta.


## Diagrama de classes

Classes do jogo e as relações entre elas:

![Diagrama de classes](imagens/diagrama-classes.png)


## Orientações para execução

### Pré-requisitos

- JDK 11 ou superior instalado.

### Jogar online

A versão web está publicada no itch.io: https://cedric2026.itch.io/jogo-nave

### Rodar no desktop

Na raiz do projeto, execute:

```
./gradlew lwjgl3:run
```

## Resultado final

Vídeo curto demonstrando uma partida (esquadrão crescendo, ondas de inimigos e o chefe):

[https://github.com/elc117/final-2026a-cedric_/raw/main/imagens/gameplay.mp4](https://github.com/user-attachments/assets/75cdbd99-75c6-4209-a92f-578a41797ffe
)

## Referências e créditos

- [libGDX](https://libgdx.com/) 1.14.1: framework do jogo (renderização, entrada, ciclo de vida). Projeto gerado a partir do template do [https://libgdx.com/wiki/start/project-generation](https://libgdx.com/wiki/start/project-generation).
- [gdx-ai](https://github.com/libgdx/gdx-ai) 1.8.2 e [gdx-controllers](https://github.com/libgdx/gdx-controllers) 2.2.4: dependências incluídas pelo template, mas não utilizadas no jogo.
- [documentação do libGDX](https://libgdx.com/wiki/): ciclo de vida, SpriteBatch, OrthographicCamera e carregamento de assets.
- [playlist de vídeos sobre desenvolvimento libGDX](https://www.youtube.com/watch?v=a8MPxzkwBwo&list=PLZm85UZQLd2SXQzsF-a0-pPF6IWDDdrXt) me auxiliou nas etapas inciais.
- Sprites das naves, inimigos e tiros foram gerados com ChatGPT. Prompt utilizado: "Crie um conjunto de sprites em pixel art para os seguintes objetos: Nave do jogador 40×15, Nave auxiliar 40×15, Inimigo comum 50×50, Inimigo atirador 50×50, Chefe 120×120 e Tiro do jogador 20×10."
- Diagramas de classes gerados com [Mermaid](https://mermaid.ai/).
- As classes puramente estéticas (Estrela e CampoDeEstrelas) foram geradas com Claude sonnet 4.6 (alto pensamento).
- Para auxílio no desenvolvimento, auxílio conceitual e revisão de código foi utilizado o Claude Sonnet 4.6. Exemplos:

**1. Erro de build apenas no módulo html**

> Estou tendo um erro de build, o desktop lwjgl3 roda normalmente, o erro só acontece no módulo html. O que está causando isso?
>
> ```
> [ERROR] Errors in '...box2dlights-1.5-sources.jar!/box2dLight/Spinor.java'
> [ERROR] Line 4: The import com.badlogic.gdx.utils.StringBuilder cannot be resolved
> [ERROR] Aborting compile due to errors in some input files
> ```

Resposta (resumida): o módulo html usa GWT para traduzir Java em JavaScript, e o box2dlights não foi feito para ser compatível com GWT — por isso só o build web falha e o desktop roda normal. Solução: remover a dependência do box2dlights (e do Box2D, se também estiver presente) nos `build.gradle` (raiz, core, html) e no `GdxDefinition.gwt.xml`. Como o jogo usa colisão por `Rectangle`, não precisa de física, então remover não afeta o gameplay.

**2. Fixar o chefe no canto direito da tela**

> A classe Chefe possui o método `atualizar` herdado de Nave; as outras classes de inimigos já possuem uma movimentação da direita para a esquerda, porém eu gostaria que a nave chefe ficasse fixa no canto direito da tela até ser destruída. Como posso alterar o método `atualizar` para que faça isso? [código InimigoAtirador], [código Inimigo], [código InimigoComum].

Resposta (resumida): a forma mais simples é sobrescrever `atualizar` na Chefe para que ela não se mova — basta fixar `posX` no canto direito e só atualizar a caixa de colisão. Diferente do InimigoComum (que faz `posX = posX - velocidade * delta`), o chefe não decrementa o `posX`:

```java
@Override
public void atualizar(float delta) {
    posX = Gdx.graphics.getWidth() - tamX - 60;
    atualizarCaixa();
}
```

**3. Nave principal não aparece na tela**

> Está ocorrendo um erro na main, não estou conseguindo desenhar na tela a nave principal. A que se deve isso?

Resposta (resumida): o problema é desenhar fora do bloco `batch.begin()`/`batch.end()` — ou com o `ShapeRenderer` ainda aberto. No libGDX você não pode ter `shape.begin()` e `batch.begin()` ativos ao mesmo tempo. Se a nave for desenhada antes de fechar o `shape.end()` (das estrelas/powerups), o draw é descartado e nada aparece. A nave precisa estar entre `batch.begin()` e `batch.end()`, depois que todo `ShapeRenderer` já fez `end()`:

```text
shape.end();        // fecha as estrelas
batch.begin();
batch.draw(texturas.get("navejogador.png"), nave.getPosicaoX(), nave.getPosicaoY(),
        nave.getTamanhoX(), nave.getTamanhoY());
batch.end();
```
