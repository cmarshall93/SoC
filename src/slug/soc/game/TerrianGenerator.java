package slug.soc.game;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import slug.soc.game.gameObjects.*;


public class TerrianGenerator {

	private TerrainObject[] temperateTerrain = {
			new TerrainObjectWater(),
			new TerrainObjectGrassPlain(),
			new TerrainObjectGrassPlain(),
			new TerrainObjectForest(),
			new TerrainObjectGrassPlain(),
			new TerrainObjectGrassHill()
	};
	private TerrainObject[] articTerrain = {
			new TerrainObjectWater(),
			new TerrainObjectSnowPlain(),
			new TerrainObjectSnowHill()
	};

	private LinkedList<Point> rivers = new LinkedList<Point>();

	public TerrianGenerator(){
	}

	private TerrainObject[][] constructTerrainMap(int[][] intMap){

		TerrainObject[][] map = new TerrainObject[intMap.length][intMap.length];

		for(int y = 0; y < map.length; y++){
			for(int x = 0; x < map.length; x++){
				if(y < map.length/101 || y > map.length - (map.length/101)){
					try {
						if(intMap[y][x] < articTerrain.length){
							map[y][x] = articTerrain[intMap[y][x]].getClass().newInstance();
						}
						else{
							map[y][x] = articTerrain[articTerrain.length - (1 + RandomProvider.getInstance().nextInt(2))].getClass().newInstance();
						}
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				else{
					try {
						if(intMap[y][x] < temperateTerrain.length){
							map[y][x] = temperateTerrain[intMap[y][x]].getClass().newInstance();
						}
						else{
							if(intMap[y][x] > 10){
								map[y][x] = new TerrainObjectMountain();
							}
							else{
								map[y][x] = temperateTerrain[temperateTerrain.length - (1 + RandomProvider.getInstance().nextInt(3))].getClass().newInstance();
							}
						}
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		setupRiver(map);
		generateBiomes(map);
		return map;
	}


	private void setupRiver(TerrainObject[][] map){

		for(ListIterator<Point> itr = rivers.listIterator(); itr.hasNext();itr.next()) {

			Point p = (Point) itr.next();
			itr.previous();//return to p
			Point next = null;
			Point prev = null;
			
			if(itr.hasPrevious()){
				prev = itr.previous();//previous river part;
				itr.next();//return to p
				System.out.println("prev : "+prev.toString());
			}
			
			itr.next();
			if(itr.hasNext()){
				next = itr.next();//next river part
				itr.previous();
				System.out.println("next : "+next.toString());
			}
			System.out.println();
			itr.previous();

			if(prev != null && next != null){
				if(prev.getY() != p.getY() && next.getY() != p.getY()){	//if next and previous are above and below.
					map[(int) p.getY()][(int) p.getX()] = new TerrainObjectRiverVertical();
				}
				if(prev.getY() < p.getY() && next.getY() == p.getY() && next.getX() > p.getX()){//if previous is lower and next is to the left
					map[(int) p.getY()][(int) p.getX()] = new TerrainObjectRiverTopRightCorner();
				}
				if(prev.getY() < p.getY() && next.getY() == p.getY() && next.getX() < p.getX()){//if previous is lower and next is to the right
					map[(int) p.getY()][(int) p.getX()] = new TerrainObjectRiverTopLeftCorner();
				}
				if(prev.getY() > p.getY() && next.getY() == p.getY() && next.getX() > p.getX()){//if previous is higher and next is to the left
					map[(int) p.getY()][(int) p.getX()] = new TerrainObjectRiverBottomLeftCorner();
				}
				if(prev.getY() > p.getY() && next.getY() == p.getY() && next.getX() < p.getX()){//if previous is higher and next is to the right
					map[(int) p.getY()][(int) p.getX()] = new TerrainObjectRiverBottomRightCorner();
				}
				else{
					map[(int) p.getY()][(int) p.getX()] = new TerrainObjectRiverHorizontal();
				}
			}
		}
	}

	private int[][] simulateAnt(int[][] intMap, int cx, int cy, int l){
		for(int i = 0, c = l;i < c ; i++){
			if(cy < 0 || cx < 0 || cy > intMap.length -1 || cx > intMap.length -1){
				cy = RandomProvider.getInstance().nextInt(intMap.length);
				cx = RandomProvider.getInstance().nextInt(intMap.length);
			}
			intMap[cy][cx] += 1;
			cy += RandomProvider.getInstance().nextInt(3) - 1;
			cx += RandomProvider.getInstance().nextInt(3) - 1;
		}
		return intMap;
	}

	private int[][] generateIntMap(int w, int h){
		int[][] intMap = new int[w][h];

		for(int y = 0; y < intMap.length; y++){
			for(int x = 0; x < intMap.length; x++){
				intMap[y][x] = 0;
			}
		}
		return intMap;
	}

	private TerrainObject[][] generateBiomes(TerrainObject[][] map){
		for(int y = 0; y < map.length; y++){
			for(int x = 0; x < map.length; x++){
				if(!map[y][x].isBiome() && (!(map[y][x] instanceof TerrainObjectWater)) ){
					createBiome(x, y, map,WordGenerator.getInstance().getRandomAdjective());
				}
			}
		}
		return map;
	}

	private void smoothTerrain(int[][] intMap){
		for(int i = 0; i < 2; i++){	
			for(int y = 0; y < intMap.length; y++){
				for(int x = 0; x < intMap.length; x++){
					if(intMap[y][x] == 0){
						if(x > 0 && y > 0 && x < intMap.length - 1 && y < intMap.length - 1){
							if((intMap[y + 1][x] > 0 && intMap[y -1][x] > 0) || (intMap[y][x + 1] > 0 && intMap[y][x - 1] > 0)){
								intMap[y][x] = 1;
							}
						}
					}
				}
			}
		}
	}

	private void generateRivers(int[][] intMap){
		int sY = 39, sX = 60;

		int[][] hMap = new int[intMap.length][intMap.length];

		for(int y = 0; y < intMap.length; y++){
			for(int x = 0; x < intMap.length; x++){
				hMap[y][x] = intMap[y][x];
				if(intMap[y][x] > temperateTerrain.length){
					hMap[y][x] = temperateTerrain.length -1;
				}
				if(hMap[y][x] == 2 || hMap[y][x] ==  4){
					hMap[y][x] = 1;
				}
			}
		}
		LinkedList<Point> river = new LinkedList<Point>();

		river.add(new Point(sX, sY));

		int cY = sY, cX = sX;

		boolean finishedBuilding = false;

		int c = 0;

		while(finishedBuilding == false){

			System.out.println(cX +" : " +  cY);

			ArrayList<Point> possiblePath = new ArrayList<Point>();

			//check for lower spots to move to
			if(cY + 1 < hMap.length){
				if(hMap[cY + 1][cX] <= hMap[cY][cX] && hMap[cY + 1][cX] != 0){
					possiblePath.add(new Point(cX , cY + 1));
				}
			}
			if(cY - 1 > -1){
				if(hMap[cY - 1][cX] <= hMap[cY][cX] && hMap[cY - 1][cX] != 0){
					possiblePath.add(new Point(cX, cY - 1));
				}
			}
			if(cX + 1 < hMap.length){
				if(hMap[cY][cX + 1] <= hMap[cY][cX]  && hMap[cY][cX + 1] != 0){
					possiblePath.add(new Point(cX + 1, cY));
				}
			}
			if(cX - 1 > -1){
				if(hMap[cY][cX - 1] <= hMap[cY][cX]  && hMap[cY][cX - 1] != 0){
					possiblePath.add(new Point(cX - 1, cY));
				}
			}

			//check there are places to go
			if(possiblePath.isEmpty()){
				finishedBuilding = true;
			}

			ArrayList<Point> updatedPath = new ArrayList<Point>();

			//check possible places aren't already in the river, this is currently broken.
			for(Point p : possiblePath){
				if(!river.contains(p)){
					updatedPath.add(p);
				}
			}

			possiblePath = updatedPath;

			//check there are still places to go
			if(possiblePath.isEmpty()){
				finishedBuilding = true;
			}

			if(c == 10000){
				finishedBuilding = true;
			}

			if(river.size() > 8){
				finishedBuilding = true;
			}

			//add one point from possible path
			if(!finishedBuilding){
				int r = RandomProvider.getInstance().nextInt(possiblePath.size());
				river.add(possiblePath.get(r));
				cY = (int) possiblePath.get(r).getY();
				cX = (int) possiblePath.get(r).getX();
			}

			c++;

		}

		rivers = river;

	}

	private void createBiome(int x, int y, TerrainObject[][] map, String n){
		map[y][x].setBiomeString(n + " ");
		if(y + 1 < map.length -1){
			if(!map[y + 1][x].isBiome() && map[y + 1][x].getClass() == (map[y][x]).getClass()){
				createBiome(x, y + 1, map, n);
			}
		}
		if(y - 1 > 0){
			if(!map[y - 1][x].isBiome() && (map[y - 1][x].getClass() == (map[y][x]).getClass())){
				createBiome(x, y - 1, map, n);
			}
		}
		if(x + 1 < map.length -1){
			if(!map[y][x + 1].isBiome() && map[y][x + 1].getClass() == (map[y][x]).getClass()){
				createBiome(x + 1, y, map, n);
			}
		}
		if(x - 1 > 0){
			if(!map[y][x - 1].isBiome() && map[y][x - 1].getClass() == (map[y][x]).getClass()){
				createBiome(x - 1, y, map, n);
			}
		}
	}

	public TerrainObject[][] testGenerateMapAnt(int w, int h){

		int[][] intMap = generateIntMap(w, h);


		intMap = simulateAnt(intMap, intMap.length/2, intMap.length/2, 1001);

		return constructTerrainMap(intMap);
	}

	public TerrainObject[][] testGenerateMapMultiCont(int w, int h){

		int[][] intMap = generateIntMap(w, h);

		for(int n = 0, cy = RandomProvider.getInstance().nextInt(intMap.length), cx = RandomProvider.getInstance().nextInt(intMap.length); n < 4; n++){
			intMap = simulateAnt(intMap, cx, cy, 2001);
		}

		smoothTerrain(intMap);

		int avg = 0;
		int q = 0;
		int c = 0;
		for(int y = 0; y < intMap.length; y++){
			for(int x = 0;x < intMap.length; x++){				//calcualte average tile value.
				if(intMap[y][x] > 0){
					avg += intMap[y][x];
					c++;
					if(intMap[y][x] > q){
						q = intMap[y][x];
					}
				}
			}
		}
		System.out.println(avg/c);
		System.out.println(q);

		generateRivers(intMap);

		return constructTerrainMap(intMap);
	}

}
