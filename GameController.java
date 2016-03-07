import java.awt.*;
import java.awt.AWTEvent;
import java.awt.event.*;
import java.util.*;

class GameController implements ActionListener{

	private static GameView v;
	private GameModel m;
	private int size;
	private double probability = 0.2;

	public void start(){

	}

	public void reset(){
		m = new GameModel(size);
		v = new GameView(m, this);

		/* 	Generates 4 random ints between (0 and h)/2 */
		int randX1 = ((int)(Math.random() * size))/2;
		int randX2 = ((int)(Math.random() * size))/2;
		int randY1 = ((int)(Math.random() * size))/2;
		int randY2 = ((int)(Math.random() * size))/2;

		/* 	Adds the two numbers which gives a weight 
				closer to the center of the board 		*/
		int x = randX1 + randX2;
		int y = randY1 + randY2;

		int x1;
		int y1;

		m.setCurrentDot(x,y);
		int amountOfDots = (int)(Math.floor((size * size) * probability));

		for(int i = 0; i < amountOfDots+1; i++){
			System.out.println(amountOfDots);
			do {
				x1 = ((int)(Math.random() * size));
				y1 = ((int)(Math.random() * size));
				System.out.println(x1);
				System.out.println(y1);
			} while( m.getCurrentStatus(x1,y1) != 0 );
			m.select(x1,y1);
		}

		v.getBoardView().update();
	}

	public GameController(int size) {
		this.size = size;
		m = new GameModel(size);
		v = new GameView(m, this);

		/* 	Generates 4 random ints between (0 and h)/2 */
		int randX1 = ((int)(Math.random() * size))/2;
		int randX2 = ((int)(Math.random() * size))/2;
		int randY1 = ((int)(Math.random() * size))/2;
		int randY2 = ((int)(Math.random() * size))/2;

		/* 	Adds the two numbers which gives a weight 
				closer to the center of the board 		*/
		int x = randX1 + randX2;
		int y = randY1 + randY2;

		int x1;
		int y1;

		m.setCurrentDot(x,y);
		int amountOfDots = (int)(Math.floor((size * size) * probability));

		for(int i = 0; i < amountOfDots+1; i++){
			System.out.println(amountOfDots);
			do {
				x1 = ((int)(Math.random() * size));
				y1 = ((int)(Math.random() * size));
				System.out.println(x1);
				System.out.println(y1);
			} while( m.getCurrentStatus(x1,y1) != 0 );
			m.select(x1,y1);
		}

		v.getBoardView().update();
	}

	public void actionPerformed(ActionEvent e) {
		int i = ((DotButton)e.getSource()).getType() + 1,
			row = ((DotButton)e.getSource()).getRow(),
			column = ((DotButton)e.getSource()).getColumn();

		m.select(row,column);
		

		SearchController c = new SearchController(
			new Point(
				m.getCurrentDot().getX(), 
				m.getCurrentDot().getY()
				), 
			m.getSize(),
			m);
		LinkedList<Point> path = c.BFS(new Point(m.getCurrentDot().getX(), m.getCurrentDot().getY()));
		if (path == null){
			System.out.println("AI Won");
			m.setCurrentDot(-1,-1);
			v.getBoardView().update();
		} else {
			if (path.size() == 0){
				System.out.println("You won!");
				v.getBoardView().update();

			} else {
				m.setCurrentDot((path.get(path.size() - 1)).getX(), (path.get(path.size() - 1)).getY());
				v.getBoardView().update();
			}
		}

	}	

}