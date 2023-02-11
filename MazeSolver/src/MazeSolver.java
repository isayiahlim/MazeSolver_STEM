/**
 * Isayiah Lim
 * 2/7/2023
 * Data Structures Period 1
 * Mrs. Kankleborg
 * Solves mazes using Breadth-First Search.
 */
public class MazeSolver
{
    /**
     * Provides a solution for a given maze, if possible. A solution is a path from the start cell
     * to the finish cell (inclusive). If there is no solution to the maze then returns the static
     * instance {@link Path#NO_PATH}. If the maze is perfect then there must be only one solution.
     *
     * @param maze the maze to solve
     * @return a solution for the maze or {@link Path#NO_PATH} if there is no solution
     */
	//object storing a path taken to get to every cell
    public class StoredCell
    {
    	//variables for current cell and the path to that cell 
    	private Cell current;
        private Path stored;
         
        public StoredCell(Cell current, Path path)
        {
            this.current = current;
            //copies the path to the parameter cell & adds the new cell to the end
            stored = new Path();
            for(Cell cell : path)
            {
                stored.addLast(cell);
            }
            stored.addLast(current);
        }
        //methods to access vars
        public Cell getCell() 
        { 
        	return current; 
        }
        public Path getPath() 
        { 
        	return stored; 
        }
    }
	
	public Path solve(Maze maze)
    {
        //queue storing all cells & their paths
        Queue<StoredCell> pathStack = new Queue<StoredCell>();
        pathStack.enqueue(new StoredCell(maze.getStart(), Path.NO_PATH));
        
        //for every storedCell (cell + path) in the maze
        while(!pathStack.isEmpty())
        {
        	//pops and visits the cell
        	StoredCell current = pathStack.dequeue();
        	int x = current.getCell().getX();
        	int y = current.getCell().getY();
        	maze.visit(x, y);
        	
        	//if the cell reaches the end, returns the path stored for whatever cell is found
        	if(current.getCell().equals(maze.getEnd()))
        	{
        		return current.getPath();
        	}
        	
        	//checks if a direction is open and unvisited for all cardinal directions
        	//if it is, enqueues both the cell and the path to get to that cell
            if(x > 0)
        	{
        		if(!maze.isVisited(x-1, y) && maze.isOpen(x, y, Direction.LEFT))
        		{
        			pathStack.enqueue(new StoredCell(new Cell(x-1, y), current.getPath()));
        		}
        	}
        	if(x < maze.size()-1)
        	{
        		if(!maze.isVisited(x+1, y) && maze.isOpen(x, y, Direction.RIGHT))
        		{
        			pathStack.enqueue(new StoredCell(new Cell(x+1, y), current.getPath()));
        		}
        	}
        	if(y > 0)
        	{
        		if(!maze.isVisited(x, y-1) && maze.isOpen(x, y, Direction.DOWN))
        		{
        			pathStack.enqueue(new StoredCell(new Cell(x, y-1), current.getPath()));
        		}
        	}
        	if(y < maze.size()-1)
        	{
        		if(!maze.isVisited(x, y+1) && maze.isOpen(x, y, Direction.UP))
        		{
        			pathStack.enqueue(new StoredCell(new Cell(x, y+1), current.getPath()));
        		}
        	}
        	
        }
        //if no path is returned, there is no solution
        return Path.NO_PATH;
    }

    /**
     * Creates, solves, and draws a sample maze. Try solving mazes with different sizes!
     *
     * @param args unused
     */
    public static void main(String[] args)
    {
        // First, generate a new maze.
        int size = 10; // Setting above 200 is not recommended!
        MazeGenerator generator = new MazeGenerator();
        Maze maze = generator.generate(size);
        maze.freeze();

        // Next, solve it!
        MazeSolver solver = new MazeSolver();
        maze.resetVisited();
        Path solutionPath = solver.solve(maze);
        maze.setSolution(solutionPath);

        // This is so we can see which cells were explored and in what order.
        maze.setDrawVisited(true);

        maze.draw();
    }
}
