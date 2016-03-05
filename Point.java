import javax.swing.*;

class Point {
	private int x;
	private int y;
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void reset(int x, int y){
		this.x = x;
		this.y = y;
	}

	public String toString(){
		return "[" + String.valueOf(x) + "," + String.valueOf(y) + "]";
	}
}