package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class anvil {
    Texture img;
    SpriteBatch batch;
    String url;
    Vector2 anvilPos = new Vector2();
    int speed = 8;
    Rectangle anvilRec;
    anvil(String url,float x){
        this.url = url;
        this.anvilPos.x = x;

    }
    public void create(){
        img = new Texture(url);
        anvilPos.y = 600+img.getHeight();

        batch = new SpriteBatch();
    }
    public void render(){
        batch.begin();
        batch.draw(img,anvilPos.x,anvilPos.y);
        anvilPos.y -= speed;
        batch.end();
        anvilRec = getBounds();

    }
    public Rectangle getBounds(){
        return new Rectangle(anvilPos.x,anvilPos.y,img.getWidth(),img.getHeight());
    }
}
