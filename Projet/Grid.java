package Projet;

public class Grid {
	int[][] bool;
	int size;

	
	public Grid(int squarelength) { //divido la escena en una grille de puntos y a los bordes les doy valor 1 y al resto 0
		size = squarelength;
		bool = new int[Math.round(400/size)][Math.round(800/size)];
		for (int i = 0; i<Math.round(400/size); i++) {
			for (int j = 0; j<Math.round(800/size); j++) {
				bool[i][j] = 0;
			}
		}
		for (int i = 0; i<Math.round(400/size); i++) {
			bool[i][0] = 1;
			bool[i][Math.round(800/size) - 1] = 1;
		}
		for (int j = 0; j<Math.round(800/size); j++) {
			bool[0][j] = 1;
			bool[Math.round(400/size) - 1][j] = 1;
		}
	}
	
	public int get(int row, int column) {
		if (row < Math.round(400/size) && column < Math.round(800/size)) {
			return bool[row][column];
		}
		else {
			return -1;
		}
	}
	
	public void add(int row, int column) {
		if (row < Math.round(400/size) && column < Math.round(800/size)) {
			bool[row][column] = 1;
		}
	}
	
	public void remove(int row, int column) {
		if (row < Math.round(400/size) && column < Math.round(800/size)) {
			bool[row][column] = 0;
		}
	}
	
	public void addVwall(int rowbeginning, int columnbeginning, int length, int direction) {
		for (int i = 0; i < length; i++) {
			if (direction >= 1 && ((rowbeginning - i) >=  0)) {
				bool[rowbeginning - i][columnbeginning] = 1;
			}
			else if (direction <= -1 && ((rowbeginning + i) < Math.round(400/size) - 1)){
				bool[rowbeginning + i][columnbeginning] = 1;
			}
		}
	}
	
	public void addHwall(int rowbeginning, int columnbeginning, int length, int direction) {
		for (int i = 0; i < length; i++) {
			if (direction >= 1 && ((columnbeginning + i) < Math.round(800/size) - 1)) {
				//la segunda condicion del if es para que si llega a un limite del muro deje de "construir" muro
				bool[rowbeginning][columnbeginning + i] = 1;
			}
			else if (direction == 0 && (columnbeginning - i) >= 0){
				bool[rowbeginning][columnbeginning - i] = 1;
			}
		}
	}
}
