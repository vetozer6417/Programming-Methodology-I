package logic;

import java.util.ArrayList;
import java.util.Scanner;

public class GroceryStore {

	public static Scanner in;
	public static ShoppingCart cart;

	public static void main(String[] args) {
		String command;
		in = new Scanner(System.in);
		cart = new ShoppingCart();

		viewPromotion();
		
		while (true) {
			showMenu();
			command = in.nextLine();

			switch (command) {
			case "U":
				addUnitItemMenu();
				break;
			case "W":
				addWeightItemMenu();
				break;
			case "V":
				viewAllItemMenu();
				break;
			case "":
				checkOutMenu();
				System.exit(0);
				break;
			default:
				System.out.println("Invalid command.");
				break;
			}
			
			/*
			if (command.equals("U"))
			{
				addUnitItemMenu();
			}
			else if (command.equals("W"))
			{
				addWeightItemMenu();
			}
			else if (command.equals("V"))
			{
				viewAllItemMenu();
			}
			else if (command.equals(""))
			{
				checkOutMenu();
				System.exit(0);
			}
			else
			{
				System.out.println("Invalid command.");
			}
			*/
		}
	}

	public static void showMenu() {
		// TODO Auto-generated method stub
		System.out.println("----------------------------------------");
		System.out.println("Grocery Store Menu");
		System.out.println("----------------------------------------");
		System.out.println("U)\tAdd Unit item to shopping cart");
		System.out.println("W)\tAdd Weight item to shopping cart");
		System.out.println("V)\tView all items in shopping cart");
		System.out.println("Enter)\tCheck out your shopping cart");
		System.out.println("----------------------------------------");
		System.out.print("Enter input command : ");
	}

	public static void addWeightItemMenu() {
		// TODO Auto-generated method stub
		String name;
		double unitPrice;

		System.out.println("----------------------------------------");
		System.out.println("Add Weight item to shopping cart");
		System.out.println("----------------------------------------");

		System.out.print("Enter name : ");
		name = in.nextLine();
		
		int itemIndex = isInShoppingCart(name, 2);
		if (isInShoppingCart(name, 2) >= 0)
		{
			WeightItem weightItem = (WeightItem)cart.getItems().get(itemIndex);
			
			double addedWeight = weightItem.scale();
			System.out.printf("Weight is %.2f \n", addedWeight);
			
			double previousWeight = weightItem.getWeight();
			weightItem.setWeight(weightItem.getWeight() + addedWeight);
			System.out.printf("Total weight of %s is %.2f (Previous is %.2f)\n",weightItem.getName(),weightItem.getWeight(),previousWeight);
			System.out.printf("Cost is $%.2f (Price per kilogram is $%.2f)\n",weightItem.cost(),weightItem.getUnitPrice());
		}
		else
		{
			System.out.print("Enter price per kilogram : ");
			unitPrice = in.nextDouble();

			WeightItem w_item = new WeightItem(name, unitPrice);
			System.out.printf("Weight is %.2f \n", w_item.getWeight());
			
			cart.addItem(w_item);

			in.nextLine(); // For "\n" from pressing enter
		}
	}

	public static void addUnitItemMenu() {
		// TODO Auto-generated method stub
		String name;
		int amount;
		double unitPrice;

		System.out.println("----------------------------------------");
		System.out.println("Add Unit item to shopping cart");
		System.out.println("----------------------------------------");

		System.out.print("Enter name : ");
		name = in.nextLine();
		System.out.print("Number of Units : ");
		amount = in.nextInt();
		
		int itemIndex = isInShoppingCart(name, 1);
		if (isInShoppingCart(name, 1) >= 0)
		{
			UnitItem unitItem = (UnitItem)cart.getItems().get(itemIndex);
			
			int previousAmount = unitItem.getAmount();
			unitItem.setAmount(previousAmount + amount);
			System.out.println("Total number of " + unitItem.getName() + " is " + unitItem.getAmount() + " (Previous is " + previousAmount + ")");
			System.out.printf("Cost is $%.2f (Price per unit is $%.2f)\n",unitItem.cost(),unitItem.getUnitPrice());
		}
		else
		{
			System.out.print("Enter price per unit : ");
			unitPrice = in.nextDouble();

			cart.addItem(new UnitItem(name, unitPrice, amount));
		}

		in.nextLine(); // For "\n" from pressing enter
	}

	public static void viewAllItemMenu() {
		// TODO Auto-generated method stub
		System.out.println("----------------------------------------");
		System.out.println("View all items in shopping cart");
		System.out.println("----------------------------------------");

		ArrayList<Item> items = cart.getItems();

		if (items.size() == 0)
		{
			System.out.println("There isn't any item in shopping cart!!!");
			return;
		}
		for (int i = 0; i < items.size(); i++) {
			System.out.println("#" + (i+1) + "\t" + items.get(i).toString());
		}
	}
	
	public static void checkOutMenu(){
		System.out.println("----------------------------------------");
		System.out.println("Check out your shopping cart");
		System.out.println("----------------------------------------");
		
		boolean isDiscount = cart.isDiscounted();
		double totalCost = cart.checkOut();
		
		if (isDiscount)
		{
			System.out.println("Congratulation! You got a " + cart.getDiscount() + "% discount");
			System.out.printf("Grand total cost : $%.2f (Save $%.2f)\n",totalCost * ((100 - cart.getDiscount()) / 100.00),totalCost * (cart.getDiscount() / 100.00));
		}
		else
		{
			System.out.printf("Grand total cost : $%.2f\n",totalCost);
		}
	}
	
	public static void viewPromotion(){
		System.out.println("**************************************************");
		System.out.println("Today Promotion");
		System.out.println("Buy at least " + cart.getRequiredUnitItems() +  " UnitItem(s) & " + cart.getRequiredWeightItems() + " WeightItem(s),Get " + cart.getDiscount() + "% off");
		System.out.println("**************************************************");
	}
	
	public static int isInShoppingCart(String itemName, int itemType) {
		// if itemType is 1,checks unit items
		// if itemType is 2,checks weight items

		ArrayList<Item> items = cart.getItems();

		if (itemType == 1) 
		{
			for (int i = 0; i < items.size(); i++) 
			{
				if (items.get(i) instanceof UnitItem && items.get(i).name.equals(itemName))
				{
					return i;
				}
			}
		}
		else
		{
			for (int i = 0; i < items.size(); i++) 
			{
				if (items.get(i) instanceof WeightItem && items.get(i).name.equals(itemName))
				{
					return i;
				}
			}
		}

		return -1;
	}
}
