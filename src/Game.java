package mineclone;

import java.util.Random;

public class Game {
	
	public String[][] world;
	public long seed = 0;
	public int dimension;
	public int level1s;
	public int level2s;
	public int level_1s;
	public int level_2s;
	public int level0s;
	
	public Game(long seed, int dimension, int level1s, int level2s, int level_1s, int level_2s, int level0s) {
		this.seed = seed;
		this.dimension = dimension;
		this.level1s = level1s;
		this.level2s = level2s;
		this.level_1s = level_1s;
		this.level_2s = level_2s;
		this.level0s = level0s;
	}
	
	public static void main(String[] args) {
		long n = 9999; 
		Game game = new Game(n, 20, 5, 2, 1, 2, 5);
		int totalLevels = game.level2s + game.level1s + game.level0s + game.level_1s + game.level_2s;
		game.world = new String[totalLevels][game.dimension];
		
		// this is the list of pseudo-randomly generated numbers for the chunk
		double[] chunkNumbers = game.GetChunkNums(game.seed, game.dimension, totalLevels);
		
		game.GetWorld(totalLevels, chunkNumbers);
		
		// pass array into a print function.
		game.PrintWorld(game.world);
	}
	
	public void PrintWorld(String[][] world)
	{
		for(int x = 0; x < world.length; x++) {
			for(int y = 0; y < world[x].length; y++) {
				System.out.print(world[x][y]);
			}
			
			System.out.println();
		}
	}
	
	public double[] GetChunkNums(long seed, int dimension, int rows) {
		Random chunkGen = new Random(seed);
		double[] chunks = new double[dimension * rows];
		
		for (int x = 0; x < dimension * rows; x++)
		{
			chunks[x] = chunkGen.nextDouble();
		}
		
		return chunks;
	}
	
	// for now, build functions that process each level
	// the main array builder will choose the correct level
	// to write.
	public String[] GetLevel2Row(double[] d) {
		String[] row = new String[d.length];
		
		for (int i = 0; i < d.length; i++) {
			double bit = d[i];
			
			if (bit >= Level2.Grass[0] && bit <= Level2.Grass[1]) row[i] = Grass.sym;
			else if (bit >= Level2.Stone[0] && bit <= Level2.Stone[1]) row[i] = Stone.sym;
			else if (bit >= Level2.Dirt[0] && bit <= Level2.Dirt[1]) row[i] = Dirt.sym;
			else if (bit >= Level2.Air[0] && bit <= Level2.Air[1]) row[i] = Air.sym;
		}
		
		return row;
	}
	
	public String[] GetLevel1Row(double[] d){
		String[] row = new String[d.length];
		
		for (int i = 0; i < d.length; i++) {
			double bit = d[i];
			
			if (bit >= Level1.Grass[0] && bit <= Level1.Grass[1]) row[i] = Grass.sym;
			else if (bit >= Level1.Stone[0] && bit <= Level1.Stone[1]) row[i] = Stone.sym;
			else if (bit >= Level1.Dirt[0] && bit <= Level1.Dirt[1]) row[i] = Dirt.sym;
			else if (bit >= Level1.Air[0] && bit <= Level1.Air[1]) row[i] = Air.sym;
		}
		
		return row;
	}
	
	public String[] GetLevel0Row(double[] d){
		String[] row = new String[d.length];
		
		for (int i = 0; i < d.length; i++) {
			double bit = d[i];
			
			if (bit >= Level0.Grass[0] && bit <= Level0.Grass[1]) row[i] = Grass.sym;
			else if (bit >= Level0.Stone[0] && bit <= Level0.Stone[1]) row[i] = Stone.sym;
			else if (bit >= Level0.Dirt[0] && bit <= Level0.Dirt[1]) row[i] = Dirt.sym;
			else if (bit >= Level0.Air[0] && bit <= Level0.Air[1]) row[i] = Air.sym;
		}
		
		return row;
	}
	
	public String[] GetLevel_1Row(double[] d){
		String[] row = new String[d.length];
		
		for (int i = 0; i < d.length; i++) {
			double bit = d[i];
			
			if (bit >= Level_1.Grass[0] && bit <= Level_1.Grass[1]) row[i] = Grass.sym;
			else if (bit >= Level_1.Stone[0] && bit <= Level_1.Stone[1]) row[i] = Stone.sym;
			else if (bit >= Level_1.Dirt[0] && bit <= Level_1.Dirt[1]) row[i] = Dirt.sym;
			else if (bit >= Level_1.Air[0] && bit <= Level_1.Air[1]) row[i] = Air.sym;
		}
		
		return row;
	}
	
	public String[] GetLevel_2Row(double[] d){
		String[] row = new String[d.length];
		
		for (int i = 0; i < d.length; i++) {
			double bit = d[i];
			
			if (bit >= Level_2.Grass[0] && bit <= Level_2.Grass[1]) row[i] = Grass.sym;
			else if (bit >= Level_2.Stone[0] && bit <= Level_2.Stone[1]) row[i] = Stone.sym;
			else if (bit >= Level_2.Dirt[0] && bit <= Level_2.Dirt[1]) row[i] = Dirt.sym;
			else if (bit >= Level_2.Air[0] && bit <= Level_2.Air[1]) row[i] = Air.sym;
		}
		
		return row;
	}
	
	public void GetWorld(int totalLevels, double[] chunkNumbers) {
		int chunkPos = 0;
		int level = 0;
		
		// Populate Level 2s
		for (int x = 0; x < this.level2s; x++) {
			double[] rowSeeds = new double[this.dimension];
			for (int y = 0; y < this.dimension; y++) {
				rowSeeds[y] = chunkNumbers[chunkPos];
				chunkPos++;
			}
			
			String[] row = this.GetLevel2Row(rowSeeds);
			
			for (int z = 0; z < this.dimension; z++) {
				this.world[level][z] = row[z];
			}
			
			level++;
		}
		
		// Populate Level 1s
		for (int x = 0; x < this.level1s; x++) {
			double[] rowSeeds = new double[this.dimension];
			for (int y = 0; y < this.dimension; y++) {
				rowSeeds[y] = chunkNumbers[chunkPos];
				chunkPos++;
			}
			
			String[] row = this.GetLevel1Row(rowSeeds);
			
			for (int z = 0; z < this.dimension; z++) {
				this.world[level][z] = row[z];
			}
			
			level++;
		}
		
		// Populate Level 0s
		for (int x = 0; x < this.level0s; x++) {
			double[] rowSeeds = new double[this.dimension];
			for (int y = 0; y < this.dimension; y++) {
				rowSeeds[y] = chunkNumbers[chunkPos];
				chunkPos++;
			}
			
			String[] row = this.GetLevel0Row(rowSeeds);
			
			for (int z = 0; z < this.dimension; z++) {
				this.world[level][z] = row[z];
			}
			
			level++;
		}
			
		// Populate Level_1s
		for (int x = 0; x < this.level_1s; x++) {
			double[] rowSeeds = new double[this.dimension];
			for (int y = 0; y < this.dimension; y++) {
				rowSeeds[y] = chunkNumbers[chunkPos];
				chunkPos++;
			}
			
			String[] row = this.GetLevel_1Row(rowSeeds);
			
			for (int z = 0; z < this.dimension; z++) {
				this.world[level][z] = row[z];
			}
			
			level++;
		}
		
		// Populate Level_2s
		for (int x = 0; x < this.level_2s; x++) {
			double[] rowSeeds = new double[this.dimension];
			for (int y = 0; y < this.dimension; y++) {
				rowSeeds[y] = chunkNumbers[chunkPos];
				chunkPos++;
			}
			
			String[] row = this.GetLevel_2Row(rowSeeds);
			
			for (int z = 0; z < this.dimension; z++) {
				this.world[level][z] = row[z];
			}
			
			level++;
		}
	}
}