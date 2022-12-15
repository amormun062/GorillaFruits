package com.politecnicomalaga.gorillafruits.view;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.politecnicomalaga.gorillafruits.managers.AssetsManager;
import com.politecnicomalaga.gorillafruits.model.Fruta;
import com.politecnicomalaga.gorillafruits.model.Gorilla;

public class GameScreen implements Screen {
    private Game game;
    private Stage stage;

    private Skin skin;
    private Gorilla gorilla;
    private SpriteBatch batch;
    private Label puntuacion;

    private float tiempoFruta;
    private int numPuntos;

    Array<Fruta> frutas;

    public GameScreen(Game game) {
        batch = new SpriteBatch();
        this.game = game;
        tiempoFruta = 0;
        numPuntos = 0;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        frutas = new Array<>();

        stage.act();

        //OBJETOS
        gorilla = new Gorilla(stage);
        stage.addActor(gorilla);

        frutas.add(new Fruta(stage));
        stage.addActor(frutas.get(0));

        //PUNTUACION
        skin = new Skin(Gdx.files.internal(AssetsManager.SKIN));
        puntuacion = new Label("Puntos: " + numPuntos, skin);
        puntuacion.setPosition(20,Gdx.graphics.getHeight()-35);

        stage.addActor(puntuacion);
    }


    @Override
    public void show() {
        Gdx.app.log("GameScreen","show");
    }


    //Renderiza el escenario
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Pintamos el fondo de pantalla
        batch.begin();
        //Pintamos los actores
        stage.draw();
        batch.end();
        tiempoFruta += delta;

        stage.act(delta);

        if (tiempoFruta > random(1,5)){
            Fruta fruta = new Fruta(stage);
            frutas.add(fruta);
            stage.addActor(fruta);
            tiempoFruta = 0;
        }
        comprobarFruta();
    }


    //Metodo encargado de gestionar la EXISTENCIA de la FRUTA
    public void comprobarFruta(){
        for (Fruta fruta : frutas){
            if (Intersector.overlaps(gorilla.body,fruta.body)){
                fruta.dispose();
                frutas.removeValue(fruta,true);
                stage.getActors().removeValue(fruta,true);
                numPuntos++;
                puntuacion.setText("Puntos: " + numPuntos);
            }

            if (fruta.getY() < -fruta.getHeight()){
                fruta.dispose();
                frutas.removeValue(fruta,true);
                stage.getActors().removeValue(fruta,true);
            }
        }
    }


    //Ajusta el tamaño del escenario
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
    }


    @Override
    public void pause() {

    }


    @Override
    public void resume() {

    }


    @Override
    public void hide() {

    }


    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        stage.dispose();
    }

}
