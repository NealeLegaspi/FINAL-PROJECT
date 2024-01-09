package package1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {
	
	private static List<Product> productList = new ArrayList<>();
    private static int productIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Welcome to the Department Store System");
            System.out.println("Select user type:");
            System.out.println("(1) Admin");
            System.out.println("(2) User");
            System.out.println("(-1) Exit");

            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Admin, enter your email: ");
                    String adminEmail = scanner.nextLine();

                    System.out.print("Admin, enter your password: ");
                    String adminPassword = scanner.nextLine();


                    System.out.println("Admin email: " + adminEmail);
                    System.out.println("Admin password: " + adminPassword);

                    adminMenu();
                    break;

                case 2:
                    System.out.print("User, enter your email: ");
                    String userEmail = scanner.nextLine();

                    System.out.print("User, enter your password: ");
                    String userPassword = scanner.nextLine();


                    System.out.println("User email: " + userEmail);
                    System.out.println("User password: " + userPassword);

                    showProductList();
                    break;

                case -1:
                    System.out.println("Exiting the Department Store System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or -1.");
            }

        } while (choice != -1);

        scanner.close();
    }

    private static void adminMenu() {
        Scanner scanner = new Scanner(System.in);

        int adminChoice;
        do {
        	System.out.println("***************************************************");
        	System.out.println("------------DEPARTMENT STORE SYSTEM.---------------");
            System.out.println("\nAdmin Menu -> ");  
            System.out.println("---------------------------------------------------");
            System.out.println("(1) Add Product");
            System.out.println("(2) Show Product List");
            System.out.println("(-1) Back to Main Menu");

            adminChoice = scanner.nextInt();
            scanner.nextLine();

            switch (adminChoice) {
                case 1:
                    addProduct();
                    break;

                case 2:
                    showProductList();
                    System.out.println();
                    break;

                case -1:
                    System.out.println("Returning to the main menu.");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or -1.");
            }

        } while (adminChoice != -1);
    }

    private static void addProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter the product price: ");
        double productPrice = scanner.nextDouble();

        System.out.print("Enter the product quantity: ");
        int productQuantity = scanner.nextInt();

        Product newProduct = new Product(productIdCounter++, productName, productPrice, productQuantity);
        productList.add(newProduct);

        System.out.println("Product added successfully: " + newProduct.getProductName());
    }

    private static void showProductList() {
        System.out.println("\nProduct List:");
        for (Product product : productList) {
            System.out.println(product.getProductId() + ". " + product.getProductName() +
                    " - $" + product.getPrice() + " - Quantity: " + product.getQuantity());
        }
    }
} 

  


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
