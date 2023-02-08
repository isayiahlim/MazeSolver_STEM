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
        while(mazeStack != null)
        {
        	
        	Cell current = mazeStack.pop();
        	int x = current.getX();
        	int y = current.getY();
        	maze.visit(x, y);
        	//possible directions to go & int to put directions into it
        	Direction[] directions = new Direction[4];
        	int unvisited = 0;
        	if(x > 0)
        	{
        		if()
        	}
        }
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
