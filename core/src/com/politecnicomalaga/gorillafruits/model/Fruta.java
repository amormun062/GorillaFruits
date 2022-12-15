package com.politecnicomalaga.gorillafruits.model;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.politecnicomalaga.gorillafruits.managers.AssetsManager;
import com.politecnicomalaga.gorillafruits.managers.SettingsManager;

public class Fruta extends Actor {

    private Stage stage;
    private Texture frutaTextura;
    public Circle body;

    public Fruta(Stage stage){
        this.stage = stage;

        switch (random(0,3)){
            case 0:
                frutaTextura = new Texture(Gdx.files.internal(AssetsManager.IMG_BANANA));
                break;
            case 1:
                frutaTextura = new Texture(Gdx.files.internal(AssetsManager.IMG_CIRUELA));
                break;
            case 2:
                frutaTextura = new Texture(Gdx.files.internal(AssetsManager.IMG_MANZANA));
                break;
            case 3:
                frutaTextura = new Texture(Gdx.files.internal(AssetsManager.IMG_NARANJA));
                break;
        }
        setSize(SettingsManager.DEFAULT_SIZE,SettingsManager.DEFAULT_SIZE);
        body = new Circle(getX(),getY(),getWidth());
        setX(random(20,Gdx.graphics.getWidth()-80));
        setY(Gdx.graphics.getHeight()+getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(frutaTextura, this.getX(), this.getY(), SettingsManager.DEFAULT_SIZE,SettingsManager.DEFAULT_SIZE);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setY(getY()-4);

        body.setX(getX());
        body.setY(getY());

        if (getY() < -getHeight()){
            remove();
        }
    }

    public void dispose() {
        frutaTextura.dispose();
    }

}
