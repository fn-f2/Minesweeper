import java.util.Scanner;

public class Minesweeper
{   
    static int nummines;

    final static int MINE = -1;
    final static int EMPTY = 0;

    static int gridwidth = 10;
    static int[][] grid;
    static boolean[][] revealed;

    public static void main(String[] args)
    {
        System.out.print("\f");

        //initialize stuff
        grid = new int[gridwidth][gridwidth];
        revealed = new boolean[gridwidth][gridwidth];
        Scanner in = new Scanner(System.in);
        nummines = gridwidth*gridwidth*15/100;
        printGrid();
        
        String input = in.nextLine();
        
        String[] inps = input.split(",");
        
        int y = Integer.parseInt(inps[0]);
        int x = Integer.parseInt(inps[1]);
        
        initializeGrid(y, x);

        //game loop
        do
        {
            System.out.print("\f");
            if (grid[y][x] == 0) floodClear(y, x);
            else if (grid[y][x] > 0) revealed[y][x] = true;
            printGrid();

            input = in.nextLine();
            inps = input.split(",");
        

            
            x = Integer.parseInt(inps[1]);
            y = Integer.parseInt(inps[0]);
        } while (!hasWon());
    }

    public static boolean hasWon()
    {
        for (int r = 0; r < gridwidth; r++)
        {
            for (int c = 0; c < gridwidth; c++)
            {
                if (grid[r][c] != MINE && !revealed[r][c])return false;
            }
        }
        
        System.out.print("\f");
        System.out.print("UYOU WIN YEAH WOOOO YEAH YEAH");
        return true;
    }
    
    public static void floodClear(int x, int y)
    {
        if (x  < gridwidth && x > -1 && y < gridwidth && y > -1 && grid[x][y] == 0 && !revealed[x][y])
        {
            revealed[x][y] = true;
            floodClear(x+1, y);
            floodClear(x-1, y);
            floodClear(x, y+1);
            floodClear(x, y-1);
            //if current square is zero, reveal adjacent squares greater than zero
            for (int r = x-1; r < x+2; r++)
            {
                for (int c = y-1; c < y+2; c++)
                {
                    if (r > -1 && c > -1 && r < gridwidth && c < gridwidth //ensure within bounds of board
                    && grid[r][c] > 0) revealed[r][c] = true;
                }
            }
        }
    }

    public static void printGrid()
    {
        //coords and upper/side border
        System.out.print("     ");
        for (int i = 0; i < gridwidth; i++)
        {
            System.out.print("(");
            System.out.print(i + ")");
        }
        System.out.print("\n     ");
        
        for (int i = 0; i < gridwidth; i++)
        {
        System.out.print("▁▁▁");
        }
        System.out.println();
        

        //print grid
        boolean dark = false;
        for (int r = 0; r < gridwidth; r++)
        {
            System.out.print("(");
            System.out.print(r + ") ▕");
            for (int c = 0; c < gridwidth; c++)
            {
                if (revealed[r][c])
                {
                    if (grid[r][c] != MINE)
                    {
                        System.out.print(" ");
                        System.out.print(grid[r][c] + " ");
                    } else System.out.print(" M "); //priunt MINE :0
                }
                else if (dark) System.out.print("[ ]"); //▓▓▓
                else System.out.print("[ ]");//▒▒▒
                dark = !dark;
            }
            System.out.println("▏"); //new line
            dark = !dark;
        }

        //bottom border
        System.out.print("     ");
        for (int i = 0; i < gridwidth; i++)
        {
        System.out.print("▔▔▔");
        }
    }

    public static void initializeGrid(int movex, int movey)
    {
        placeMines(movex, movey);
        for (int r = 0; r < gridwidth; r++)
        {
            for (int c = 0; c < gridwidth; c++)
            {
                revealed[r][c] = false;
                if (grid[r][c] != MINE) grid[r][c] = countSurrounding(r, c);
            }
        }

    }

    public static void placeMines(int movex, int movey)
    {
        System.out.println("first move");
        while (nummines > 0)
        {
            int randomx = (int) (Math.random() * gridwidth);
            int randomy = (int) (Math.random() * gridwidth);
            while (grid[randomx][randomy] == MINE
            || (randomx > movex-2 && randomx < movex+2
                && randomy > movey-2 && randomy < movey+2))
            {
                randomx = (int) (Math.random() * gridwidth);
                randomy = (int) (Math.random() * gridwidth);
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
                if (r2 > -1 && c2 > -1 && r2 < gridwidth && c2 < gridwidth //ensure within bounds of board
                && grid[r2][c2] == MINE) count++; //incremenbt surrounding mine count
            }
        }

        return count;        
    }
}

