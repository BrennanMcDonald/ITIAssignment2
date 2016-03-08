import java.util.*;

public class SearchController{
	private int size;
	private GameModel m;

	private boolean[][] visited;
	private int[] yDirEven = {-1,-1,0,1,1,0};
	private int[] xDirEven = {-1,0,1,0,-1,-1};

	private int[] yDirOdd = {-1,-1,0,1,1,0};
	private int[] xDirOdd = {0,1,1,1,0,-1};

	private LinkedList<Point> pathList;
	private Queue<Point> nodeQueue;
	private Map<String,Point> vertexMap;

	public SearchController(Point p, int size, GameModel model){
		this.size = size;
		m = model;
		visited = new boolean[size][size];

	}

	public LinkedList<Point> BFS(Point p){
		// Reset the visited nodes
		for(int i = 0; i < size; i++){
			for(int l = 0; l < size; l++){
				visited[i][l] = false;
			}
		}

		// Reset all maps and Queues
		pathList = new LinkedList<Point>();
		nodeQueue = new LinkedList<Point>();
		vertexMap = new HashMap<String,Point>();

		nodeQueue.add(p);
		
		// Breadth First Implimentation
		while(!nodeQueue.isEmpty()){
			if (nodeQueue.size() > (size * size)){
				return (LinkedList<Point>)nodeQueue;
			}

			Point tempPoint = nodeQueue.remove();
			for(int i = 0; i < 6; i++){
				int newX = 0;
				int newY = 0;
				if (tempPoint.getY() % 2 == 0) {
					newX = tempPoint.getX() + xDirEven[i];
					newY = tempPoint.getY() + yDirEven[i];
				} else {
					newX = tempPoint.getX() + xDirOdd[i];
					newY = tempPoint.getY() + yDirOdd[i];
				}
				if (newX >= 0 && newX < size && newY >= 0 && newY < size) {
					if (!visited[newY][newX] && m.getCurrentStatus(newX, newY) == 0){

						nodeQueue.add(new Point(newX, newY));
						visited[newY][newX] = true;

						if (!vertexMap.containsKey(new Point(newX, newY).toString())){
							vertexMap.put((new Point(newX, newY)).toString(), tempPoint);
						}
					} else {
					}
				} else {
					Point t = tempPoint;

					if (vertexMap.get(t.toString()) == null){
						return null;
					}

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