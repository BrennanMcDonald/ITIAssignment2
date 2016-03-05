import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;

class GameView extends JFrame {
	private BoardView BV;
	private GridLayout gameGrid;
	private JPanel buttonLayout; 
	private JButton close_btn, reset_btn;


	public GameView(GameModel model, GameController gameController) {
		super("Board View");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BV = new BoardView(model, gameController);
		gameGrid = new GridLayout(1,2);
		add(BV,BorderLayout.CENTER);

		buttonLayout = new JPanel();
		close_btn = new JButton("Close");
		reset_btn = new JButton("Reset");

		buttonLayout.setLayout(new FlowLayout());
		buttonLayout.add(reset_btn, BorderLayout.WEST);
		buttonLayout.add(close_btn, BorderLayout.EAST);
		buttonLayout.setPreferredSize(new Dimension((model.getSize() * 40) + 110 , 40));
		setPreferredSize(new Dimension((model.getSize() * 40) + 110, (model.getSize() * 40) + 240 ));
		add(buttonLayout, BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}

	public BoardView getBoardView(){
		return BV;
	}

}
