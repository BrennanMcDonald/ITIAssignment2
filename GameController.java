import java.awt.*;
import java.awt.AWTEvent;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

class GameController implements ActionListener{

	private static GameView v;
	private GameModel model;
	private int size;
	private double probability = 0.2;

	// Generate a random int in a range
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}


	public void start(){

		int x,y,selectedX, selectedY;

		model = new GameModel(size);
		v = new GameView(model, this);

		x = randInt(size/4, (3 * size)/4);
		y = randInt(size/4, (3 * size)/4);

		model.setCurrentDot(x,y);
		int amountOfDots = (int)(Math.floor((size * size) * probability));

		for(int i = 0; i < amountOfDots+1; i++){
			do {
				selectedX = ((int)(Math.random() * size));
				selectedY = ((int)(Math.random() * size));
			} while( model.getCurrentStatus(selectedX,selectedY) != 0 );
			model.select(selectedX,selectedY);
		}

		v.getBoardView().update();
	}

	public void reset(){
		start();
	}

	public GameController(int size) {
		this.size = size;

	}

	public void actionPerformed(ActionEvent e) {
		int i = ((DotButton)e.getSource()).getType() + 1,
			row = ((DotButton)e.getSource()).getRow(),
			column = ((DotButton)e.getSource()).getColumn();

		model.select(row,column);
		

		SearchController c = new SearchController(
			new Point(
				model.getCurrentDot().getX(), 
				model.getCurrentDot().getY()
				), 
			model.getSize(),
			model);

		LinkedList<Point> path = c.BFS(new Point(model.getCurrentDot().getX(), model.getCurrentDot().getY()));
		if (path == null){
			JOptionPane.showMessageDialog(null, "AI Won!");
			v.setVisible(false);
			v.dispose();
			reset();
		} else {
			if (path.size() == 0){
				JOptionPane.showMessageDialog(null, "You Won!");
				v.setVisible(false);
				v.dispose();
				reset();

			} else {
				Point nextBlueDot = (path.get(path.size() - 1));
				model.setCurrentDot(nextBlueDot.getX(), nextBlueDot.getY());
				v.getBoardView().update();
			}
		}

	}	

}