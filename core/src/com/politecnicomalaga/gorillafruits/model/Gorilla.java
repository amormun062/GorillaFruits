package com.politecnicomalaga.gorillafruits.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.politecnicomalaga.gorillafruits.managers.AssetsManager;
import com.politecnicomalaga.gorillafruits.managers.SettingsManager;

public class Gorilla extends Actor {

    private Stage stage;
    private int velX;
    private Texture gorillaTextura;
    public Circle body;

    public Gorilla (Stage stage){
        super();
        this.stage = stage;
        gorillaTextura = new Texture(Gdx.files.internal(AssetsManager.IMG_GORILLA));
        body = new Circle(getX(),getY(),getWidth());
        setSize(64,64);
        setX(Gdx.graphics.getWidth()/2f);
        setY(0);
        velX = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
        batch.draw(gorillaTextura,getX(),getY(), SettingsManager.DEFAULT_SIZE,SettingsManager.DEFAULT_SIZE);
    }


    @Override
    public void act(float delta) {
        super.act(delta);

        //EVENTO. Cambio de posicion del JUGADOR segun la posicion del raton
        stage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.input.getX() <= Gdx.graphics.getWidth()/3f){
                    velX = -SettingsManager.VEL_GORILLA;

                }else if (Gdx.input.getX() > Gdx.graphics.getWidth()/3f && Gdx.input.getX() <= Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/3f){
                    velX = 0;

                } else if (Gdx.input.getX() >= Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/3f){
                    velX = SettingsManager.VEL_GORILLA;
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        setX(getX()+velX);

        //Evitamos que se salga de los limites del SCREEN
        if (getX() <= 0 || getX() >= Gdx.graphics.getWidth()-getWidth()){
            velX = 0;
        }

        //Actualizamos la posicion del area de colicion
        body.setX(getX());
        body.setY(getY());
    }

    public void dispose() {
        gorillaTextura.dispose();
    }
}
