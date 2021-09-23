/* name of package */
package app;

/* all necessary imports */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.Test;

/* app code */
public class Trattoria
{
	/* app start */
	private static void welcome(PrintWriter out, Scanner scan) {
		System.out.println("Welcome to Trattoria.");
		System.out.println("Enter your username:");
		String userName = scan.nextLine(); // get username from console
		out.println(userName); // add username into file
	}
	
	/* order one pizza or more */
	@Test // test of value
	private static boolean order(PrintWriter out, Scanner scan, boolean continueOrder) {
		System.out.println("Choose your pizza (Margherita, Capriciosa or Calzone):");
		String pizza = scan.nextLine(); // get pizza type from console
		if (pizza.equals("Margherita") || pizza.equals("Capriciosa") || pizza.equals("Calzone"))
		{
			out.println(pizza); // add pizza type into file if is one of three supported types
			System.out.println("Pizza is added to your order.");
			/* because Marghertia is base pizza, this section append only to Capriciosa and Calzone choices */
			/* both Capriciosa and Calzone have mushrooms and ham by default, only form is different */
			if (pizza.equals("Capriciosa") || pizza.equals("Calzone"))
			{
				System.out.println("If you want to add extra mushrooms to your pizza, type + and click Enter.");
				System.out.println("If you want to remove mushrooms from your pizza, type - and click Enter.");
				String mushrooms = scan.nextLine();
				if (mushrooms.equals("+")) // if + is written
				{
					out.println("+mushrooms");
					System.out.println("Extra mushrooms are added to your pizza.");
				}
				if (mushrooms.equals("-")) // if - is written
				{
					out.println("-mushrooms");
					System.out.println("Mushrooms are removed from your pizza.");
				}
				System.out.println("If you want to add extra ham to your pizza, type + and click Enter.");
				System.out.println("If you want to remove ham from your pizza, type - and click Enter.");
				String ham = scan.nextLine();
				if (ham.equals("+")) // if + is written
				{
					out.println("+ham");
					System.out.println("Extra ham are added to your pizza.");
				}
				if (ham.equals("-")) // if - is written
				{
					out.println("-ham");
					System.out.println("Ham are removed from your pizza.");
				}
			}
			System.out.println("Enter your additions to pizza:"); // if no additions, leave blank
			String additions = scan.nextLine();
			out.println(additions);
			System.out.println("Additions are added to your pizza.");
		}
		else // block other types of pizza
		{
			System.out.println("Sorry, this type of pizza is not supported.");
		}
		/* continue or stop order */
		System.out.println("If you want to add another pizza to your order, type y and click Enter.");
		String anotherPizza = scan.nextLine();
		if (anotherPizza.equals("y")) // if y is written
		{
			assertTrue(continueOrder); // value should be true
		}
		else // if something else is written
		{
			continueOrder = false;
			assertFalse(continueOrder); // value should be false
		}
		return continueOrder; // if true, continue order, if false, stop order and exit from app
	}
	
	/* exit from app */
	private static void close(PrintWriter out, Scanner scan) {
		System.out.println("Order completed. Goodbye.");
		out.println("-----"); // separator of orders
		/* close streams to prevent memory leak */
		scan.close();
		out.close();
	}
	
	/* main class */
	public static void main(String[] args)
	{
		/* app run */
		try
		{
			FileWriter fw = new FileWriter("Trattoria.txt", true); // stream for writing data into existing file
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			Scanner scan = new Scanner(System.in); // stream for getting written text from console
			boolean continueOrder = true;
			welcome(out, scan);
			while (continueOrder) // continue order until exit from app
			{
				continueOrder = order(out, scan, continueOrder);
			}
			close(out, scan);
		}
		/* IOException handling */
		catch (IOException e)
		{
			System.out.println("Error - IO exception occured.");
		}
	}
}
