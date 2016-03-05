import java.awt.*;
import java.awt.AWTEvent;
import java.awt.event.*;
import java.util.*;

class GameController implements ActionListener{
	public static GameView v;
	private GameModel m;
	private int h;
	public void start(){

	}

	public void reset(){

	}

	public GameController(int size) {
		h = 9;
		m = new GameModel(h);
		v = new GameView(m, this);

		/* 	Generates 4 random ints between (0 and h)/2 */
		int randX1 = ((int)(Math.random() * h))/2;
		int randX2 = ((int)(Math.random() * h))/2;
		int randY1 = ((int)(Math.random() * h))/2;
		int randY2 = ((int)(Math.random() * h))/2;

		/* 	Adds the two numbers which gives a weight 
				closer to the center of the board 		*/
		int x = randX1 + randX2;
		int y = randY1 + randY2;

		int x1;
		int y1;

		m.setCurrentDot(x,y);
		int amountOfDots = (int)(Math.floor((size * size) * 0.1));

		for(int i = 0; i < amountOfDots+1; i++){
			System.out.println(amountOfDots);
			do {
				x1 = ((int)(Math.random() * h));
				y1 = ((int)(Math.random() * h));
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
		System.out.println(path.get(path.size() - 1));
		m.setCurrentDot((path.get(path.size() - 1)).getX(), (path.get(path.size() - 1)).getY());
		v.getBoardView().update();

	}	

}