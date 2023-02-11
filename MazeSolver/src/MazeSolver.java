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
	//new object storing the path taken to get to every cell
    public class StoredCell
    {
    	 private Cell current;
         private Path stored;
         
         //stores the current cell and generates the path to get there
         public StoredCell(Cell current, Path path)
         {
             this.current = current;
             stored = new Path();
             for(Cell cell : path)
             {
                 stored.addLast(cell);
             }
             stored.addLast(current);
         }

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
        Path path = Path.NO_PATH;
        //queue used to iterate through cells
        Queue<StoredCell> pathStack = new Queue<StoredCell>();
        pathStack.enqueue(new StoredCell(maze.getStart(), path));
        
        //for every cell in the maze
        while(!pathStack.isEmpty())
        {
        	//pops and visits the cell
        	StoredCell current = pathStack.dequeue();
        	int x = current.getCell().getX();
        	int y = current.getCell().getY();
        	maze.visit(x, y);
        	
        	if(current.getCell().equals(maze.getEnd()))
        	{
        		return current.getPath();
        	}
        	
        	//checks if a direction is open and unvisited for all cardinal directions
            if(x > 0)
        	{
        		if(!maze.isVisited(x-1, y) && maze.isOpen(x, y, Direction.LEFT))
        		{
        			StoredCell tempC = new StoredCell(new Cell(x-1, y), Path.NO_PATH);
        			pathStack.enqueue(tempC);
        		}
        	}
        	if(x < maze.size()-1)
        	{
        		if(!maze.isVisited(x+1, y) && maze.isOpen(x, y, Direction.RIGHT))
        		{
        			StoredCell tempC = new StoredCell(new Cell(x+1, y), Path.NO_PATH);
        			pathStack.enqueue(tempC);
        		}
        	}
        	if(y > 0)
        	{
        		if(!maze.isVisited(x, y+1) && maze.isOpen(x, y, Direction.DOWN))
        		{
        			StoredCell tempC = new StoredCell(new Cell(x, y-1), Path.NO_PATH);
        			pathStack.enqueue(tempC);
        		}
        	}
        	if(y < maze.size()-1)
        	{
        		if(!maze.isVisited(x, y+1) && maze.isOpen(x, y, Direction.UP))
        		{
        			StoredCell tempC = new StoredCell(new Cell(x, y+1), Path.NO_PATH);
        			pathStack.enqueue(tempC);
        		}
        	}
        	
        }
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
