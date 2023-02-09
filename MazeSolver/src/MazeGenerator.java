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
        //generates maze with all walls in place
    	Maze maze = new Maze(size);
    	//stack used to iterate through cells
        Stack<Cell> mazeStack = new Stack<Cell>();
        mazeStack.push(new Cell(0,0));
        //for every cell in the maze
        while(!mazeStack.isEmpty())
        {
        	//pops and visits the cell
        	Cell current = mazeStack.pop();
        	int x = current.getX();
        	int y = current.getY();
        	maze.visit(x, y);
        	
        	//possible directions to go & integer of how many directions are available
        	Direction[] directions = new Direction[4];
            int unvisited = 0;
            
        	//ensures a border wall is not removed then checks if the wall has already been visited
            //for all four cardinal directions
            if(x > 0)
        	{
        		if(!maze.isVisited(x-1, y))
        		{
        			directions[unvisited] = Direction.LEFT;
        			unvisited ++;
        		}
        	}
        	if(x < size-1)
        	{
        		if(!maze.isVisited(x+1, y))
        		{
        			directions[unvisited] = Direction.RIGHT;
        			unvisited ++;
        		}
        	}
        	if(y > 0)
        	{
        		if(!maze.isVisited(x, y-1))
        		{
        			directions[unvisited] = Direction.DOWN;
        			unvisited ++;
        		}
        	}
        	if(y < size-1)
        	{
        		if(!maze.isVisited(x, y+1))
        		{
        			directions[unvisited] = Direction.UP;
        			unvisited ++;
        		}
        	}
        	
        	//if there are any directions that haven't been visited, this randomly chooses one
        	//and removes the wall in that direction
        	if(unvisited > 0)
        	{
	        	Direction temp = directions[(int)(Math.random()*unvisited)];
	        	maze.removeWall(x, y, temp);
	        	mazeStack.push(current);
	        	//adjusts the x and y according to the random direction chosen
	        	if(temp == Direction.LEFT)
	        		x --;
	        	if(temp == Direction.RIGHT)
	        		x ++;
	        	if(temp == Direction.DOWN)
	        		y--;
	        	if(temp == Direction.UP)
	        		y++;
	        	//adds it to the stack
	        	mazeStack.push(new Cell(x, y));
        	}
        }
        
        //random start & stop points, repeating to make sure they are different
        int startX = (int)(Math.random() * size);
        int startY = (int)(Math.random() * size);
        int endX = (int)(Math.random() * size);
        int endY = (int)(Math.random() * size);
        while ((startX == endX) && (startY == endY))
        {
            startX = (int)(Math.random() * size);
            startY = (int)(Math.random() * size);
            endX = (int)(Math.random() * size);
            endY = (int)(Math.random() * size);
        } 
        
        //sets the start and stop to these coordinates
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
