// ---------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
//        ███╗   ██╗       ██████╗ ██╗   ██╗███████╗███████╗███╗   ██╗███████╗
//        ████╗  ██║      ██╔═══██╗██║   ██║██╔════╝██╔════╝████╗  ██║██╔════╝
//        ██╔██╗ ██║█████╗██║   ██║██║   ██║█████╗  █████╗  ██╔██╗ ██║███████╗
//        ██║╚██╗██║╚════╝██║▄▄ ██║██║   ██║██╔══╝  ██╔══╝  ██║╚██╗██║╚════██║
//        ██║ ╚████║      ╚██████╔╝╚██████╔╝███████╗███████╗██║ ╚████║███████║
//        ╚═╝  ╚═══╝       ╚══▀▀═╝  ╚═════╝ ╚══════╝╚══════╝╚═╝  ╚═══╝╚══════╝
// ---------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
import java.util.InputMismatchException;
import java.util.Scanner;
// ---------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
/**
 *
 * @author Alejandro Lagunes
 */
// ---------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
class Main
{
   // ------------------------------------------------------------------------------------------------------------------
   private static Scanner in = new Scanner(System.in);
   // ------------------------------------------------------------------------------------------------------------------
   public static void main(String[] args)
   {
      // ---------------------------------------------------------------------------------------------------------------
      String findMore = "Y";
      // ---------------------------------------------------------------------------------------------------------------
      while(!findMore.equalsIgnoreCase("N"))
      {
         int gameSize = 3;
         // ------------------------------------------------------------------------------------------------------------
         //Exception is handled if given a non integer, but gameSize must also be greater than three.
         while (gameSize <= 3)
         {
            printMenu();
            System.out.print("\nPlease enter a valid game size (4 or greater) : ");
            try
            {
               gameSize = in.nextInt();
            }
            catch(InputMismatchException e)
            {
               System.out.println(e);
               in.next();
            }
            
         }
         // ------------------------------------------------------------------------------------------------------------
         long startTime = System.nanoTime();
         Solver s = new Solver();
         s.findSolutions(gameSize);
         long stopTime = System.nanoTime();
         System.out.println((stopTime - startTime)/1000000);
         System.out.print("\nWould you like to compute another solution (Y/N)? ");
         findMore = in.next();
         // ------------------------------------------------------------------------------------------------------------
      }
      // ---------------------------------------------------------------------------------------------------------------
      System.out.println("\nExiting...\nThank you for using the N queen solver!");
      // ---------------------------------------------------------------------------------------------------------------
   }
   static void printMenu()
   {
      // ---------------------------------------------------------------------------------------------------------------
      System.out.println();
      System.out.println("        ███╗   ██╗       ██████╗ ██╗   ██╗███████╗███████╗███╗   ██╗███████╗\n" +
                         "        ████╗  ██║      ██╔═══██╗██║   ██║██╔════╝██╔════╝████╗  ██║██╔════╝\n" +
                         "        ██╔██╗ ██║█████╗██║   ██║██║   ██║█████╗  █████╗  ██╔██╗ ██║███████╗\n" +
                         "        ██║╚██╗██║╚════╝██║▄▄ ██║██║   ██║██╔══╝  ██╔══╝  ██║╚██╗██║╚════██║\n" +
                         "        ██║ ╚████║      ╚██████╔╝╚██████╔╝███████╗███████╗██║ ╚████║███████║\n" +
                         "        ╚═╝  ╚═══╝       ╚══▀▀═╝  ╚═════╝ ╚══════╝╚══════╝╚═╝  ╚═══╝╚══════╝ ");
      System.out.println("\nN must be at least 4, as there are no solutions for gameboards");
      System.out.println("smaller than that.");
      // ---------------------------------------------------------------------------------------------------------------
   }
}

