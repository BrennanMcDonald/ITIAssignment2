import java.awt.*;
import java.awt.AWTEvent;
import java.awt.event.*;
import java.util.Random;
import java.util.*;

class GameController implements ActionListener{
	public static GameView v;
	private GameModel m;
	private int h;
	public void start(){

	}

	public void reset(){

	}

	public static int randInt(int min, int max) {

	    // NOTE: This will (intentionally) not run as written so that folks
	    // copy-pasting have to think about how to initialize their
	    // Random instance.  Initialization of the Random instance is outside
	    // the main scope of the question, but some decent options are to have
	    // a field that is initialized once and then re-used as needed or to
	    // use ThreadLocalRandom (if using at least Java 1.7).
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	public GameController(int size) {
		m = new GameModel(size);
		v = new GameView(m, this);

		/* 	Generates 4 random ints between (0 and h)/2 */

		/* 	Adds the two numbers which gives a weight 
				closer to the center of the board 		*/
		int x = randInt(size/4, (size*3)/4);
		int y = randInt(size/4, (size*3)/4);

		int x1;
		int y1;

		m.setCurrentDot(x,y);
		int amountOfDots = (int)(Math.floor((size * size) * 0.1));

		for(int i = 0; i < amountOfDots+1; i++){
			do {
				x1 = ((int)(Math.random() * size));
				y1 = ((int)(Math.random() * size));
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
			m,
			v);
		LinkedList<Point> path = c.BFS(new Point(m.getCurrentDot().getX(), m.getCurrentDot().getY()));
		System.out.println(path.get(path.size() - 1));
		m.setCurrentDot((path.get(path.size() - 1)).getX(), (path.get(path.size() - 1)).getY());
	}	

}