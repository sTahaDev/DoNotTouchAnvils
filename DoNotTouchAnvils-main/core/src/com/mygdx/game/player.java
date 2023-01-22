package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.security.Key;
import java.util.Vector;


public class player {
    SpriteBatch batch;

    String url;
    Texture img;
    public Vector2 playerPos = new Vector2();
    int speed = 10;
    int gravity = -5;
    int velocity;
    public Rectangle playerRec;

    player(String url){
        this.url = url;

    }


    public void create(){
        batch = new SpriteBatch();
        img = new Texture(url);

    }
    public void render(){


        batch.begin();
        batch.draw(img,playerPos.x,playerPos.y);
        batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            playerPos.x += -speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            playerPos.x += speed;

        }

        //Gravity
        playerPos.y += velocity;
        if (playerPos.y > 0){
            velocity += gravity;
        }
        else if (playerPos.y == 0){
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
               velocity += 50;
            }


        } else if (playerPos.y < 0) {

            velocity = 0;
            playerPos.y = 0;
        }
        playerRec = getBounds();


    }
    public Rectangle getBounds(){
        return new Rectangle(playerPos.x,playerPos.y,img.getWidth(),img.getHeight());
    }

}
