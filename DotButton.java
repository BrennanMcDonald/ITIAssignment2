import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.AbstractBorder;
import javax.swing.border.*;
import java.awt.Image;

public class DotButton extends JButton {
    private Border thickBorder = new LineBorder(Color.WHITE, 0);
    private int type;
    private int row, column;

    public DotButton(int row, int column, int type) {
    	ImageIcon img = new ImageIcon("data/ball-" + Integer.toString(type) + ".png");
    	setPreferredSize(new Dimension(40, 40));
    	setIcon(img);

        this.row = row;
        this.column = column;

    	setBackground(null);
    	//setBorderPainted(false); 
        setContentAreaFilled(false); 
        setFocusPainted(false); 
        setOpaque(false);
		setMargin(new Insets(0,0,0,0));
        

        setVerticalTextPosition(SwingConstants.TOP);
        setHorizontalTextPosition(SwingConstants.CENTER);

        this.type = type;
    }

    public void setType(int type) {
    	this.type = type;
    }

    public int getType(){
    	return type;
    }
    
    public int getRow() {
    	return this.row;
    }

    public int getColumn() {
    	return this.column;
    }
}