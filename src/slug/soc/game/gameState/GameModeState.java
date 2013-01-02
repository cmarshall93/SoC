package slug.soc.game.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import slug.soc.game.FontProvider;
import slug.soc.game.Game;
import slug.soc.game.HouseSigilGenerator;
import slug.soc.game.RandomProvider;
import slug.soc.game.TerrianGenerator;
import slug.soc.game.gameObjects.Faction;
import slug.soc.game.gameObjects.GameObject;
import slug.soc.game.gameObjects.GameObjectPerson;
import slug.soc.game.gameObjects.GameObjectCursor;
import slug.soc.game.gameObjects.TerrainObject;
import slug.soc.game.gameObjects.TerrainObjectWater;

/**
 * Represents the current game.
 * @author slug
 *
 */
public class GameModeState implements IGameState, Runnable {

	private static final int MAP_UPDATE_RATE = 30;
	private static final int INFO_UPDATE_RATE = 120;
	private static GameModeState instance;

	private long lastFPS;
	private int currentFPS;
	private int frames;

	private boolean viewHoldings;

	private TerrainObject[][] map;
	private TerrianGenerator terrianGenerator;
	private GameObjectCursor cursor = new GameObjectCursor();
	private Integer currentXPos;
	private Integer currentYPos;
	private double[] zoomScales = {
			1.0, 0.5	
	};

	private boolean cursorActive = false;
	private boolean loadedWorld = false;

	private String[] loadingString = {".", "..", "..."};
	private int currentLoadingString = 0;

	private int currentZoomIndex = 0;

	private int mapFrameCounter;
	private int infoFrameCounter;

	public static GameModeState getInstance(){
		if(instance == null){
			instance = new GameModeState();
		}
		return instance;
	}

	private GameModeState(){
		terrianGenerator = new TerrianGenerator();
		lastFPS = System.currentTimeMillis();
	}

	public void run(){
		generateWorld();
	}

	public void generateWorld(){
		long start = System.nanoTime();

		map = terrianGenerator.testGenerateMapMultiCont(100, 100);
		currentXPos = 50;
		currentYPos = 50;

		//**************faction testing*******************
		Faction faction = new Faction();
		System.out.println(faction.getSigil());
		int x = 50;
		int y = 50;
		boolean testBol = false;
		while(!testBol){
			if(!map[y][x].isBuildable()){
				x = RandomProvider.getInstance().nextInt(map.length);
				y = RandomProvider.getInstance().nextInt(map.length);
			}
			else{
				testBol = true;
			}
		}
		for(GameObject g : faction.getHoldings()){
			map[y][x].addGameObject(g);
			for(TerrainObject t: map[y][x].getBiome().getContents()){
				if(t != null){
					t.setOwner(faction);
				}
			}
			y++;
		}
		//************************************************

		long end = System.nanoTime();
		System.out.println("GenTime: " + (end - start)/1000000);
		loadedWorld = true;
	}

	public TerrainObject[][] getMap(){
		return map;
	}

	public void processKey(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_UP){
			if(currentYPos > 0){
				if(cursorActive){
					map[currentYPos][currentXPos].removeGameObject(cursor);
					currentYPos--;
					map[currentYPos][currentXPos].addGameObject(cursor);
				}
				else{
					currentYPos--;
				}
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			if(currentYPos < getMap().length - 1){
				if(cursorActive){
					map[currentYPos][currentXPos].removeGameObject(cursor);
					currentYPos++;
					map[currentYPos][currentXPos].addGameObject(cursor);
				}
				else{
					currentYPos++;
				}
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(currentXPos < getMap().length -1){ 
				if(cursorActive){
					map[currentYPos][currentXPos].removeGameObject(cursor);
					currentXPos++;
					map[currentYPos][currentXPos].addGameObject(cursor);
				}
				else{
					currentXPos++;
				}
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(currentXPos > 0){
				if(cursorActive){
					map[currentYPos][currentXPos].removeGameObject(cursor);
					currentXPos--;
					map[currentYPos][currentXPos].addGameObject(cursor);
				}
				else{
					currentXPos--;
				}
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_OPEN_BRACKET){
			if(currentZoomIndex - 1 > -1){
				currentZoomIndex--;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET){
			if(currentZoomIndex + 1 < zoomScales.length){
				currentZoomIndex++;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_C){
			if(!cursorActive){
				map[currentYPos][currentXPos].addGameObject(cursor);
				cursorActive = true;
			}
			else{
				cursorActive = false;
				map[currentYPos][currentXPos].removeGameObject(cursor);
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			Game.getInstance().setCurrentGameState(PauseGameState.getInstance());
		}
		else if(e.getKeyCode() ==  KeyEvent.VK_H){
			viewHoldings = !viewHoldings;
		}
		else if(e.getKeyCode() == KeyEvent.VK_I){
			if(getMap()[currentYPos][currentXPos].getOwner() != null){
				FactionInformationState.getInstance().setFactionToDispaly(getMap()[currentYPos][currentXPos].getOwner());
				Game.getInstance().setCurrentGameState(FactionInformationState.getInstance());
			}
		}
	}

	public Image createImage(){
		mapFrameCounter++;
		infoFrameCounter++;
		Image gameImage = new BufferedImage(1000,500, BufferedImage.TYPE_INT_RGB);
		Graphics g = gameImage.getGraphics();
		if(loadedWorld){
			drawMap(g);
			g.setColor(Color.WHITE);
			g.drawLine(500, 0, 500, 500);
			drawInfo(g);
		}
		else{
			drawLoading(g);
		}

		if(mapFrameCounter >= MAP_UPDATE_RATE ){
			mapFrameCounter = 0;
		}
		if(infoFrameCounter >= INFO_UPDATE_RATE){
			infoFrameCounter = 0;
		}

		return gameImage;
	}	

	private void drawMap(Graphics g){
		int gy = 30;
		int gx;
		g.setFont(FontProvider.getInstance().getFont());
		g.setFont(FontProvider.getInstance().getFont().deriveFont((float)Math.floor(19 * zoomScales[currentZoomIndex])));
		for(int y = currentYPos - 12 * (int) (1/zoomScales[currentZoomIndex]), my = 0; my < (25 * 1/zoomScales[currentZoomIndex]); y++,my++){
			gx = 15;
			for(int x = currentXPos - 12 * (int) (1/zoomScales[currentZoomIndex]), mx = 0; mx < (25 * 1/zoomScales[currentZoomIndex]) ; x++, mx++){
				if(x < 0 || y < 0 || x >= getMap().length || y >= getMap().length ){
					g.setColor(Color.BLACK);
					g.drawString(" ", gx, gy);
				}
				else{
					if(mapFrameCounter >= MAP_UPDATE_RATE){
						getMap()[y][x].nextTile();
					}
					if(viewHoldings && map[y][x].getOwner() != null){ //check if player wants to see owners of tiles
						g.setColor(map[y][x].getOwner().getFactionColor().getColor());
					}
					else{//default viewing
						g.setColor(getMap()[y][x].getTile().getColor());
					}
					g.drawString(getMap()[y][x].getTile().getSymbol().toString(), gx, gy);
				}
				gx += g.getFont().getSize();
			}
			gy += g.getFont().getSize();
		}
	}

	private void drawInfo(Graphics g){

		int gx = 510;
		int gy = 30;

		g.setFont(FontProvider.getInstance().getFont().deriveFont(10f));
		if(currentYPos > 0 && currentXPos > 0 && currentYPos < map.length && currentXPos < map.length){
			g.drawString(getMap()[currentYPos][currentXPos].toString(),gx, gy);
		}
		gy += 11;
		g.drawString("X: " + currentXPos.toString(), gx, gy);
		gx += 50;
		g.drawString("Y: " + currentYPos.toString(), gx, gy);
		gx -= 50;
		gy += 20;
		if(getMap()[currentYPos][currentXPos].getOwner() != null){
			String propString = "Property of the " + getMap()[currentYPos][currentXPos].getOwner().toString() + " family";
			g.drawString(propString, gx, gy);
			g.setColor(Color.RED);
			g.drawString("(i)", gx + (propString.length() * g.getFont().getSize()), gy);
			g.setColor(Color.WHITE);
		}
		else{
			g.drawString("Unclaimed land", gx, gy);
		}

		gy += 40;
		if(infoFrameCounter >= INFO_UPDATE_RATE && getMap()[currentYPos][currentXPos].getGameObjects().size() > 0){
			getMap()[currentYPos][currentXPos].getNextGameObject();
			if(getMap()[currentYPos][currentXPos].getCurrentGameObject() instanceof GameObjectCursor){
				getMap()[currentYPos][currentXPos].getNextGameObject();
			}
		}
		if(getMap()[currentYPos][currentXPos].getGameObjects().size() > 0){
			g.drawString(getMap()[currentYPos][currentXPos].getCurrentGameObject().toString() + " 1 of " 
						+ getMap()[currentYPos][currentXPos].getGameObjects().size() +" objects on this tile", gx, gy);
			String [] desc = getMap()[currentYPos][currentXPos].getCurrentGameObject().getStringDesc();
			for(int i = 0; i < desc.length; i++){
				gy += 20;
				g.drawString(desc[i], gx, gy);
			}
		}
		if(viewHoldings){
			gy = 480;
			gx = 520;
			g.drawString("Holdings View", gx, gy);
		}

		gy = 480;
		gx = 980;
		if(System.currentTimeMillis() - lastFPS > 1000){
			currentFPS = frames;
			frames = 0;
			lastFPS += 1000;
		}
		Integer f = currentFPS;
		g.drawString(f.toString(), gx, gy);
		frames++;
	}

	private void drawLoading(Graphics g){
		int gy = 30;
		int gx = 30;
		g.setFont(FontProvider.getInstance().getFont());
		g.drawString("Generating world", gx, gy);
		gy += 20;
		for(String s: getGenStatus()){
			g.drawString(s, gx, gy);
			gy += 20;
		}
		gy = 480;
		gx = 500;
		if(mapFrameCounter >= MAP_UPDATE_RATE){
			if(currentLoadingString + 1 < loadingString.length){
				currentLoadingString++  ;
			}
			else{
				currentLoadingString = 0;
			}
		}
		g.drawString(loadingString[currentLoadingString], gx, gy);
	}

	private String[] getGenStatus(){
		String[] status = new String[4];
		status[0] = "Terrain Generation......" + terrianGenerator.getGenStatus().toString() + "%";
		status[1] = "test";
		status[2] = "test";
		status[3] = "test";
		return status;
	}
}
