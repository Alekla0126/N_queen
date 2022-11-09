// -----------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------
import java.util.EmptyStackException;
// ---------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
/**
 *
 * @author Alejandro Lagunes
 */
// ---------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
public class Solver
{
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    /*
     *  Discovers both the number of and coordinates of each solution based on the given game-board size.
     *
     * @param int gameSize
     *     the size of the chess board
     * @return none
     * @precondition
     *     "gameSize" is valid (an integer > 3)
     */
    // -----------------------------------------------------------------------------------------------------------------
    public static void findSolutions(int gameSize)
    {
        // -------------------------------------------------------------------------------------------------------------
        // The "gameBoard" is from here forward referred to as a "LinkedStack" object of "Queen" object type.
        LinkedStack<Queen> gameBoard = new LinkedStack<Queen>();
        int stackSize = 0;
        int solCount = 0;
        Queen topQueen = new Queen();
        Queen testQueen = new Queen();
        Queen tempQueen = new Queen();
        // lastWasPopped is used to reassign variables correctly.
        boolean lastWasPopped = false;
        // the position of "topQueen" is paramount, and used as an.
        // indicator throughout the algorithm.
        gameBoard.push(topQueen);
        stackSize++;
        // While "topQueen" does not exceed the end of the board.
        // ---------------------------------------------------------------------------------------------------------------
        while(topQueen.getCol() <= gameSize)
        {
            // ------------------------------------------------------------------------------------------------------------
            // While the board isn't filled with one specific solution
            // (this would be a stackSize of 4 for a 4 x 4 gamebaord).
            // ------------------------------------------------------------------------------------------------------------
            while(stackSize < gameSize)
            {
                // ---------------------------------------------------------------------------------------------------------
                // "tempQueen" is the last Queen placed.
                try
                {
                    tempQueen = gameBoard.itemAt(0);
                }
                catch (EmptyStackException e)
                {
                    System.out.println(e);
                }
                // -----------------------------------------------------------------------------------------------------
                if(lastWasPopped)
                {
                    testQueen = new Queen(testQueen.getRow(), testQueen.getCol() + 1);
                    lastWasPopped = false;
                }
                else
                {
                    testQueen = new Queen(tempQueen.getRow() + 1, 1);
                }
                // -----------------------------------------------------------------------------------------------------
                // Loop through all existing queens, starting at the bottom of LinkedStack/top of "gameBoard".
                // For loop is re-initialized if conflicts are found, causing this to process an indefinite amount of
                // times.
                int i = stackSize -1;
                while(i >= 0)
                {
                    // -------------------------------------------------------------------------------------------------
                    // Temp initialized for use in while loop, but value should end up being "itemAt(i)".
                    Queen temp = new Queen();
                    try
                    {
                        temp = gameBoard.itemAt(i);
                    }
                    catch (EmptyStackException e)
                    {
                        System.out.println(e);
                    }
                    // -------------------------------------------------------------------------------------------------
                    while(temp.conflict(testQueen) && testQueen.getCol() <= gameSize)
                    {
                        //if "testQueen" needs to be incremented, all other existing Queens must
                        //be checked for conflict again
                        testQueen.setCol(testQueen.getCol() + 1);
                        i = stackSize;
                    }
                    // -------------------------------------------------------------------------------------------------
                    //if you've reached the limit of "testQueen" and there are still conflicts
                    //AND "topQueen" is still the only Queen on the stack
                    if(testQueen.getCol() > gameSize && stackSize == 1)
                    {
                        topQueen = gameBoard.pop();
                        topQueen.setCol(topQueen.getCol() + 1);
                        testQueen = new Queen(topQueen.getRow() + 1, 1);
                        gameBoard.push(topQueen);
                    }
                    // -------------------------------------------------------------------------------------------------
                    else if(testQueen.getCol() > gameSize)
                    {
                        testQueen = gameBoard.pop();
                        testQueen.setCol(testQueen.getCol() + 1);
                        if(i == stackSize)
                        {
                            i--;
                        }
                        stackSize--;
                    }
                    i --;
                }
                // -----------------------------------------------------------------------------------------------------
                // If the for loop finished and there wasn't a conflict and the top queen wasn't adjusted.
                if(testQueen != topQueen)
                {
                    gameBoard.push(testQueen);
                    stackSize++;
                }
                // -----------------------------------------------------------------------------------------------------
            } // End of individual solutions.
            // ---------------------------------------------------------------------------------------------------------
            if(topQueen.getCol() <= gameSize)
            {
                solCount++;
                System.out.print("\n\nSolution #" + solCount + ": ");
                // Printing the coordinates of all Queens in the LinkedStack.
                int i = stackSize - 1;
                while(i >= 0)
                {
                    Queen index = gameBoard.itemAt(i);
                    if(i == 0)
                    {
                        System.out.print("[row: " + index.getRow() + " column: " + index.getCol() + "] ");
                    }
                    else
                    {
                        System.out.print("[row: " + index.getRow() + " column: " + index.getCol() + "], ");
                    }
                    i--;
                }
            }
            // ---------------------------------------------------------------------------------------------------------
            // Popping the last Queen placed and starting over.
            gameBoard.pop();
            stackSize--;
            lastWasPopped = true;
            // ---------------------------------------------------------------------------------------------------------
        }  // End of all the solutions.
        System.out.println("\n\nTotal solutions for game size " + gameSize + " is " + solCount);
        // -------------------------------------------------------------------------------------------------------------
    }  // End of findSolutions()
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
}
