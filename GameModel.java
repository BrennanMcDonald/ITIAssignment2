import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GameModel {
	// CONSTANTS
	public static final int AVAILABLE = 0;
	public static final int SELECTED = 1;
	public static final int DOT = 2;

	// VARIABLES
	private DotButton[][] gameStatus;
	private Point blueDot;
	private int steps;
	private int size;

	// CONSTRUCTOR
	public GameModel(int size){
		this.size = size;
		this.gameStatus = new DotButton[size][size];

		for(int x = 0; x < size; x++){
			for(int y = 0; y < size; y++){
				gameStatus[y][x] = new DotButton(x,y,AVAILABLE);
			}
		}
	}

	// METHODS
	public Point getCurrentDot(){
		return blueDot;
	}

	public int getCurrentStatus(int x, int y){
		return gameStatus[y][x].getType();
	}

	public DotButton getButtonAtPoint(int x, int y){
		return gameStatus[y][x];
	}

	public int getNumberOfSteps(){
		return steps;
	}

	public int getSize(){
		return size;
	}

	public void reset(){

	}

	public void select(int x, int y){
		gameStatus[y][x].setType(SELECTED);
	}

	public void setCurrentDot(int x, int y){
		gameStatus[y][x].setType(DOT);
		blueDot = new Point(x,y);
	}
}