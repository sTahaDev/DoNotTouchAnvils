package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainGame extends ApplicationAdapter{

	player player1 = new player("player.png");
	LinkedList<anvil> anvils = new LinkedList<anvil>();
	float sayac = 0;
	Timer myTimer = new Timer();
	float anvilAralik = 1;
	int skor = 0;
	int skorAralik = 5;
	boolean gameOver = false;
	int speedAralik = 40;
	boolean mainMenu = true;
	//BackGround
	Texture bgImg;
	SpriteBatch batch;

	BitmapFont font;

	Texture playButtonImg;
	Rectangle playButtonRec;

	//Mouse
	Rectangle mouseRec;



	@Override
	public void create () {

		font = new BitmapFont(Gdx.files.internal("ab.fnt"),Gdx.files.internal("ab.png"),false);
		bgImg = new Texture("bg.png");
		batch = new SpriteBatch();
		playButtonImg = new Texture("play1.png");
		player1.create();
		TimerTask gorev = new TimerTask() {
			@Override
			public void run() {
				sayac++;
				skor += 1;
				System.out.println(skor);

			}
		};
		myTimer.schedule(gorev,0,1000);

	}

	@Override
	public void render () {
		ScreenUtils.clear(0.25f, 0.25f, 0.25f, 1);
		batch.begin();
		bgDraw();
		batch.end();

		if (!gameOver && !mainMenu){
			//Oyun
			batch.begin();
			font.setColor(new Color(Color.BLACK));
			font.draw(batch,"YOUR SCORE: " + skor, 0, 600-10);
			batch.end();
			player1.render();
			anvilDus();
			collision();
		}else if (!gameOver && mainMenu){
			//Main Menu Açık Sadece
			mouseRec = new Rectangle(Gdx.input.getX()-1,Gdx.input.getY(),1,1);
			batch.begin();
			font.setColor(new Color(Color.BLACK));
			font.draw(batch,"DoNotTouchAnvils", 380, 450);
			batch.draw(playButtonImg,500,250);
			batch.end();
			playButtonRec = new Rectangle(500,250,playButtonImg.getWidth(),(playButtonImg.getHeight()+30));
			if (Gdx.input.isTouched()){
				if (playButtonRec.overlaps(mouseRec)){
					mainMenu = false;
					skor = 0;
				}

			}


		}else if (gameOver && !mainMenu){
			//Game OVer Bölümü
			mouseRec = new Rectangle(Gdx.input.getX()-1,Gdx.input.getY(),1,1);
			batch.begin();
			font.setColor(new Color(Color.BLACK));
			font.draw(batch,"Game Over !", 380, 450);
			batch.draw(playButtonImg,500,250);
			batch.end();
			playButtonRec = new Rectangle(500,250,playButtonImg.getWidth(),(playButtonImg.getHeight()+30));
			if (Gdx.input.isTouched()){
				if (playButtonRec.overlaps(mouseRec)){
					gameOver = false;
					skor = 0;
					anvils.clear();
					anvilAralik = 1;
					skorAralik = 5;
					speedAralik = 40;
				}

			}
		}


	}
	public void bgDraw(){
		batch.draw(bgImg,0,0);
	}

	private void collision() {
		for (int i = 0; i < anvils.size(); i++) {

			if (player1.playerRec.overlaps(anvils.get(i).getBounds())){
				gameOver = true;
			}
		}
	}

	@Override
	public void dispose () {
		myTimer.cancel();
	}
	public void anvilDus(){

		if (anvilAralik < 0.3){

			for (int i = 0; i < anvils.size(); i++) {
				anvils.get(i).speed = 15;
			}
			if (skor > speedAralik){
				for (int i = 0; i < anvils.size(); i++) {
					anvils.get(i).speed += 5;
				}
				speedAralik += 10;
			}
			anvilAralik = 0.01f;
		}else {
			if (skor > skorAralik){
				skorAralik += 5;
				anvilAralik -= 0.1f;
			}
		}
		if (sayac > anvilAralik){
			sayac  =0;

			Random rand = new Random();
			int randInt = rand.nextInt(1200);
			anvils.addLast(new anvil("anvil.png",randInt));
			anvils.getLast().create();
		}
		for (int i = 0; i < anvils.size(); i++) {
			anvils.get(i).render();
		}


	}
}
