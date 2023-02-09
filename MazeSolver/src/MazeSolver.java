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
    public Path solve(Maze maze)
    {
        Path path = new Path();
        //queue used to iterate through cells
        Queue<Cell> pathStack = new Queue<Cell>();
        pathStack.enqueue(maze.getStart());
        //for every cell in the maze
        while(!pathStack.isEmpty())
        {
        	//pops and visits the cell
        	Cell current = pathStack.dequeue();
        	int x = current.getX();
        	int y = current.getY();
        	maze.visit(x, y);
        	
        	if(current.equals(maze.getEnd()))
        	{
        		throw new UnsupportedOperationException("implement this later");
        	}
        	
        	//checks if a direction is open and unvisited for all cardinal directions
            if(x > 0)
        	{
        		if(!maze.isVisited(x-1, y) && maze.isOpen(x, y, Direction.LEFT))
        		{
        			pathStack.enqueue(new Cell(x-1, y));
        		}
        	}
        	if(x < maze.size()-1)
        	{
        		if(!maze.isVisited(x+1, y) && maze.isOpen(x, y, Direction.RIGHT))
        		{
        			pathStack.enqueue(new Cell(x+1, y));
        		}
        	}
        	if(y > 0)
        	{
        		if(!maze.isVisited(x, y-1) && maze.isOpen(x, y, Direction.DOWN))
        		{
        			pathStack.enqueue(new Cell(x, y-1));
        		}
        	}
        	if(y < maze.size()-1)
        	{
        		if(!maze.isVisited(x, y+1) && maze.isOpen(x, y, Direction.UP))
        		{
        			pathStack.enqueue(new Cell(x, y+1));
        		}
        	}
        	
        }
        return path;
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
