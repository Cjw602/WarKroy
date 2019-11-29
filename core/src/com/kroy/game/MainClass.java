package com.kroy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.FileNotFoundException;
import java.io.IOException;


public class MainClass extends ApplicationAdapter {


	Map mapData;
	SpriteBatch batch;
	Human humanData;
	Enemy enemyData;




	@Override
	public void create () {

		initSetting();
		loadTextures();



		mapData = new Map(Constants.getMapFileName());
		humanData = new Human("humanName",true);
		enemyData = new Enemy("EnemyName",false);




		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		if (Constants.getManager().update()){
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			Gdx.gl.glClearColor(0, 0, 0, 1);

			batch.begin();


			renderMap(batch);
            batch.draw(Constants.getManager().get("borderArt.png", Texture.class), 0, 0, Constants.getResolutionWidth(), Constants.getResolutionHeight(),0,0,1280,720,false,false);


            batch.end();

		}
		else{
			System.out.println("--LOADING-- " + Constants.getManager().getProgress() + " --LOADING--");
		}


	}



	@Override
	public void dispose () {
		batch.dispose();
	}

	public void loadTextures(){
		Constants.getManager().load("grassTile.png", Texture.class);
		Constants.getManager().load("roadTile.png", Texture.class);
		Constants.getManager().load("borderArt.png", Texture.class);
		Constants.getManager().load("stationTile.png", Texture.class);
		Constants.getManager().load("fortressTile.png", Texture.class);

	}

	public void initSetting(){
		Gdx.graphics.setWindowedMode(Constants.getResolutionWidth(),Constants.getResolutionHeight());
	}




	public void renderMap(SpriteBatch batch)
	{
		for(int width = 0; width < mapData.getMapWidth(); width++)
		{
			for(int height = 0; height < mapData.getMapHeight(); height++)
			{
				batch.draw(Constants.getManager().get(mapData.getMapData()[width][height].getTexName(), Texture.class),(width * Constants.getTileSize()) + mapData.getShiftX(),(height*Constants.getTileSize())+ mapData.getShiftY(), Constants.getTileSize(),Constants.getTileSize(),0,0,32,32,false,false);
			}
		}
	}







}
