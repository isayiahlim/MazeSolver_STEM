/**
 * Isayiah Lim
 * 2/7/2023
 * Data Structures Period 1
 * Mrs. Kankleborg
 * Creates new mazes using Depth-First Search
 */
public class MazeGenerator
{
    /**
     * Randomly generates a perfect maze of {@param size}.
     *
     * @param size the size of the maze to generate
     * @return the generated maze
     */
    public Maze generate(int size)
    {
        Maze maze = new Maze(size);
        Stack<Cell> mazeStack = new Stack<Cell>();
        mazeStack.push(new Cell(0,0));
        int unvisited = 0;
        while(!mazeStack.isEmpty())
        {
        	
        	Cell current = mazeStack.pop();
        	int x = current.getX();
        	int y = current.getY();
        	maze.visit(x, y);
        	//possible directions to go
        	Direction[] directions = new Direction[4];
        	if(x > 0)
        	{
        		if(maze.isVisited(x-1, y))
        		{
        			directions[unvisited] = Direction.LEFT;
        			unvisited ++;
        		}
        	}
        	if(x < size-1)
        	{
        		if(maze.isVisited(x+1, y))
        		{
        			directions[unvisited] = Direction.RIGHT;
        			unvisited ++;
        		}
        	}
        	if(y > 0)
        	{
        		if(maze.isVisited(x, y-1))
        		{
        			directions[unvisited] = Direction.DOWN;
        			unvisited ++;
        		}
        	}
        	if(y < size-1)
        	{
        		if(maze.isVisited(x, y+1))
        		{
        			directions[unvisited] = Direction.UP;
        			unvisited ++;
        		}
        	}
        	if(unvisited > 0)
        	{
	        	Direction temp = directions[(int)(Math.random()*unvisited)];
	        	maze.removeWall(x, y, temp);
	        	mazeStack.push(current);
	        	if(temp == Direction.LEFT)
	        		x --;
	        	if(temp == Direction.RIGHT)
	        		x ++;
	        	if(temp == Direction.DOWN)
	        		y--;
	        	if(temp == Direction.UP)
	        		y++;
	        	mazeStack.push(new Cell(x, y));
        	}
        }
        
        //random start stop points
        int startX = (int)(Math.random() * size);
        int startY = (int)(Math.random() * size);
        int endX = (int)(Math.random() * size);
        int endY = (int)(Math.random() * size);
        do 
        {
            startX = (int)(Math.random() * size);
            startY = (int)(Math.random() * size);
            endX = (int)(Math.random() * size);
            endY = (int)(Math.random() * size);
        } while ((startX == endX) && (startY == endY));

        maze.setStart(startX, startY);
        maze.setEnd(endX, endY);
        return maze;
    }
    /**
     * Creates and draws a sample maze. Try generating mazes with different sizes!
     *
     * @param args unused
     */
    public static void main(String[] args)
    {
        StdRandom.setSeed(34);
        int size = 10; // Setting above 200 is not recommended!
        MazeGenerator generator = new MazeGenerator();
        Maze maze = generator.generate(size);
        maze.draw();
    }
}
