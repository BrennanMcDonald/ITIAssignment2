import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import javax.swing.border.*;
import javax.swing.border.EmptyBorder;
public class BoardView extends JPanel {

	GameModel model;
	GameController controller;
	private int b = 1;

	public BoardView(GameModel gameModel, GameController gameController) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		model = gameModel;
		controller = gameController;
		setPreferredSize(new Dimension(gameModel.getSize() * 40, gameModel.getSize() * 40));
		update();
	}

	public void update(){
		removeAll(); 
			System.out.println();

		for(int y = 0; y < model.getSize(); y++){
			JPanel panel = new JPanel();

			if (y % 2 == 0){
				panel.setBorder(new EmptyBorder(0, 0, 0, 40));
			}

			for(int x = 0; x < model.getSize(); x++){
				System.out.print(model.getCurrentStatus(x,y));
				DotButton tempButton = new DotButton(x, y, model.getCurrentStatus(x,y));
				tempButton.addActionListener(controller);
				tempButton.setBorder(null);
				tempButton.setPreferredSize(new Dimension(40,40));
				panel.add(tempButton);
			}
			System.out.println();
			add(panel);
		}

		updateUI();

	}	
}