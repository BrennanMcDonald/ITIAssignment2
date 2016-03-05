import java.util.*;

public class SearchController{
	private int size;
	private GameModel m;
	private Point originalPoint;

	private Point[][] finishdPath;
	private boolean[][] visited;
	private int[] yDirEven = {-1,-1,0,1,1,0};
	private int[] xDirEven = {-1,0,1,0,-1,-1};
	private int[] yDirOdd = {-1,-1,0,1,1,0};
	private int[] xDirOdd = {0,1,1,1,0,-1};

	public SearchController(Point p, int size, GameModel model){
		this.size = size;
		m = model;
		originalPoint = p;
		visited = new boolean[size][size];
		finishdPath = new Point[size][size];
		for(int i = 0; i < size; i++){
			for(int l = 0; l < size; l++){
				visited[i][l] = false;
			}
		}


	}

	public LinkedList<Point> BFS(Point p){
		LinkedList<Point> pathList = new LinkedList<Point>();
		Queue<Point> nodeQueue = new LinkedList<Point>();
		Map<String,Point> vertexMap = new HashMap<String,Point>();
		nodeQueue.add(p);
		int depth = 0;
		while(!nodeQueue.isEmpty()){
			depth++;

			Point tempPoint = nodeQueue.remove();
			for(int i = 0; i < 6; i++){
				int newX = 0;
				int newY = 0;
				// Refactor with 2d array indexed at n % 2
				if (tempPoint.getY() % 2 == 0){
					newX = tempPoint.getX() + xDirEven[i];
					newY = tempPoint.getY() + yDirEven[i];
				} else {
					newX = tempPoint.getX() + xDirOdd[i];
					newY = tempPoint.getY() + yDirOdd[i];
				}

				if (newX >= 0 && newX < size && newY >= 0 && newY < size){
					if (!visited[newY][newY] && m.getCurrentStatus(newX, newY) == 0){
						finishdPath[newY][newX] = tempPoint;
						nodeQueue.add(new Point(newX, newY));
						if (!vertexMap.containsKey(new Point(newX, newY).toString())){
							vertexMap.put((new Point(newX, newY)).toString(), tempPoint);
						}
					}
				} else {
					Point t = tempPoint;
					while(vertexMap.get(t.toString()).toString() != p.toString()){
						pathList.add(t);
						t = vertexMap.get(t.toString());
						if (t == p){
							return pathList;
						}
					}
					return pathList;
				}
			}
		}
		return pathList;

	}

}