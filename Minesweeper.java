import java.util.Scanner;

public class Minesweeper
{   
    static int nummines = 20;
    
    
    final static int MINE = 1;
    final static int EMPTY = 0;
    
    final static int EASY = 10;
    final static int MEDIUM = 20;
    final static int HARD = 40;
    
    static int[][] grid;
    static boolean[][] revealed;
    
    public static void main(String[] args)
    {
        grid = new int[EASY][EASY];
        
        printGrid();
    }
    
    public static void printGrid()
    {   
        placeMines();
        
        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[0].length; c++)
            {
                if (grid[r][c] == 0)
                {
                    System.out.print(grid[r][c] + " ");
                }
                else System.out.print("@ ");
            }
            System.out.println("");
        }
    }
    
    public static void placeMines()
    {
        while (nummines > 0)
        {
            int randomx = (int) (Math.random() * 10);
            int randomy = (int) (Math.random() * 10);
            while (grid[randomx][randomy] == MINE)
            {
                randomx = (int) (Math.random() * 10);
                randomy = (int) (Math.random() * 10);
            }
            grid[randomx][randomy] = MINE;
            
            nummines--;
        }
    }
}


