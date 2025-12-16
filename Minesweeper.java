import java.util.Scanner;

public class Minesweeper
{   
    static int nummines;
    
    
    final static int MINE = 1;
    final static int EMPTY = 0;
    
    final static int EASY = 10;
    final static int MEDIUM = 20;
    final static int HARD = 40;
    
    static int diff;
    static int[][] grid;
    static boolean[][] revealed;
    
    public static void main(String[] args)
    {
        diff = EASY;
        grid = new int[diff][diff];
        revealed = new boolean[diff][diff];
        Scanner in = new Scanner(System.in);
        nummines = diff*diff*15/100;
        
        int input = in.nextInt();
        placeMines();
        hideGrid();
        printGrid();
        
        //game loop
        while (true)
        {
            input = in.nextInt();
            System.out.println("\f");
            
            if (floodClear(input/10, input%10));
            
            printGrid();
        }
    }
    
    public static boolean floodClear(int x, int y)
    {
        if (x > -1 && y > -1
        && x < grid.length && y < grid[0].length
        && grid[x][y] != MINE)
        {
            revealed[x][y] = true;
            return true;
        }
        
        if (floodClear(x+1, y) == false) return false;
        if (floodClear(x-1, y) == false) return false;
        if (floodClear(x, y+1) == false) return false;
        if (floodClear(x, y-1) == false) return false;
        return true;
    }
    
    public static void printGrid()
    {
        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[0].length; c++)
            {
                if (revealed[r][c])
                {
                    if (grid[r][c] == 0)
                    {
                        System.out.print(countSurrounding(r,c) + " "); //print number of mines aroud it
                    }
                    else System.out.print("@ "); //priunt a BIG BOMB :0
                } else System.out.print("? ");
            }
            System.out.println(""); //new line
        }
    }
    
    public static void hideGrid()
    {
        for (int r = 0; r < revealed.length; r++)
        {
            for (int c = 0; c < revealed[0].length; c++)
            {
                revealed[r][c] = false;
            }
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
    
    public static int countSurrounding(int r, int c)
    {
        int count = 0;
                    
        //loop thru surrounding squares
        for (int r2 = r-1; r2 < r+2; r2++)
        {
            for (int c2 = c-1; c2 < c+2; c2++)
            {
                if (r2 > -1 && c2 > -1
                && r2 < grid.length&& c2 < grid[0].length
                && grid[r2][c2] == MINE) count++; //incremenbt surrounding mine count
            }
        }
        
        return count;        
    }
}


