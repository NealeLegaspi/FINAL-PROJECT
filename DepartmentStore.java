package departmentstore;
import java.util.*;

public class DepartmentStore {
    public static void main(String[] args) {
        Scanner ndl = new Scanner(System.in);
        Administrator administrator = new Administrator();
        char choices;

        do {
            System.out.println("\nLogin as (C)ustomer or (A)dministrator: ");
            char userType = ndl.next().charAt(0);

            if (userType == 'C' || userType == 'c') {
                performShopping(ndl);
            } else if (userType == 'A' || userType == 'a') {
                performAdminTasks(ndl, administrator);
            } else {
                System.out.println("Invalid choice. Please enter 'C' or 'A'.");
            }

            System.out.print("Do you want to continue? (Y for Yes, N for No): ");
            choices = ndl.next().charAt(0);

        } while (choices == 'Y' || choices == 'y');

        System.out.println("Exiting the program. Thank you!");
        ndl.close();
    }

    private static void performShopping(Scanner scanner) {
        Scanner ndl = new Scanner(System.in);
        System.out.println("\n******************************************************************");
        System.out.println("*                WELCOME TO THE DEPARTMENT STORE!                *");
        System.out.println("******************************************************************");
        System.out.print("\nEnter your name: ");
        String customerName = ndl.nextLine();
        Customer customer = new Customer(customerName);

        char choices = 0;
        do {
            
        // Adding products to the store
        List<Product> storeInventory = new ArrayList<>();
        storeInventory.add(new Product("Shirt", 29.99, 5));
        storeInventory.add(new Product("Jeans", 39.99, 7));
        storeInventory.add(new Product("Sweater", 49.99, 4));
        storeInventory.add(new Product("Dress", 59.99, 5));
        storeInventory.add(new Product("Tie", 19.99, 10));
        storeInventory.add(new Product("Hat", 9.99, 15));
        storeInventory.add(new Product("Socks", 79.99, 18));
        storeInventory.add(new Product("Watch", 99.99, 3));
        storeInventory.add(new Product("Backpack", 39.99, 10));
        storeInventory.add(new Product("Gloves", 12.99, 20));
        storeInventory.add(new Product("Scarf", 18.99, 10));
        storeInventory.add(new Product("Jacket", 89.99, 5));
        storeInventory.add(new Product("Skirt", 34.99, 6));
        storeInventory.add(new Product("Belt", 15.99, 7));
        storeInventory.add(new Product("Umbrella", 24.99, 5));
        storeInventory.add(new Product("Sunglasses", 29.99, 10));
        storeInventory.add(new Product("Towel", 29.99, 17));
        storeInventory.add(new Product("Shoes", 22.99, 6));

        System.out.println("\nAvailable products:");
        for (int i = 0; i < storeInventory.size(); i++) {
            Product product = storeInventory.get(i);
            System.out.println((i + 1) + ". " + product.name + " - $" + product.price + ", Stocks: " + product.stock);
        }

        // Shopping
        int choice;
        do {
            System.out.print("Enter product number to add to cart (0 to finish, -1 to remove an item or check the cart): ");
            choice = ndl.nextInt();

            if (choice == 0 && customer.shoppingCart.isEmpty()) {
                System.out.println("Your cart is empty. Please add items before finishing.");
            } else if (choice == -1) {
                customer.removeFromCart();
            } else if (choice != 0) {
                int productIndex = choice - 1;

                if (productIndex >= 0 && productIndex < storeInventory.size()) {
                    Product selectedProduct = storeInventory.get(productIndex);

                    if (selectedProduct.stock > 0) {
                        customer.addToCart(selectedProduct);
                        selectedProduct.stock--; // Decrease the stock
                        System.out.println("Item added to the cart.");
                    } else {
                        System.out.println("Sorry, " + selectedProduct.name + " is out of stock. Please choose another product.");
                    }
                } else {
                    System.out.println("Invalid choice");
                }
            }
            
        } while (choice != 0 || customer.shoppingCart.isEmpty());

        // Cashier setup
        List<Cashier> cashiers = new ArrayList<>();
        cashiers.add(new Cashier(1));
        cashiers.add(new Cashier(2));
        cashiers.add(new Cashier(3));    

        // Payment
        String paymentMethod = customer.choosePaymentMethod();
        double cashPaid = 0.0;

        // Check if the chosen cashier is available
        int chosenCashierNumber;
        do {
            System.out.print("Enter the cashier number (1, 2, or 3): ");
            chosenCashierNumber = scanner.nextInt();

            if (chosenCashierNumber < 1 || chosenCashierNumber > 3) {
                System.out.println("Invalid cashier number. Please enter a valid cashier number.");
            } else {
                Cashier chosenCashier = cashiers.get(chosenCashierNumber - 1);
                if (chosenCashier.isOccupied()) {
                    System.out.println("Cashier " + chosenCashierNumber + " is occupied. Please choose another cashier.");
                } else {
                    chosenCashier.setOccupied(true);
                    break;
                }
            }
        } while (true);    

        if (!customer.shoppingCart.isEmpty()) {
                if (paymentMethod.equals("cash")) {
                    cashPaid = customer.processCashPayment(customer.calculateTotal());
                } else {
                    System.out.println("Credit card payment processed.");
                }

                // Display receipt
                System.out.println("\n\n----------------------------------------------------");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                System.out.println("------------------K.A.N.N. Store--------------------");
                System.out.println("           Sta. Rita Guiguinto, Bulacan                 ");
                System.out.println("                    0975488290                      ");
                System.out.println("\nReceipt for " + customer.name + ":");
                System.out.println("    Product                                 Price   ");
                for (Product product : customer.shoppingCart) {
                    System.out.println("    " + product.name + "                                   $" + product.price);
                }
                System.out.printf("\nTotal: $%.2f%n", customer.calculateTotal());

                if (paymentMethod.equals("cash")) {
                    System.out.printf("Cash paid: $%.2f%n", cashPaid);
                } else {
                    System.out.println("Payment: credit card");
                }
                
                System.out.println("\n----------------------------------------------------");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                System.out.println("----------------------------------------------------");

                // Reset cashier's status to available
                cashiers.get(chosenCashierNumber - 1).setOccupied(false);
            
                // Check if the user wants to make another purchase
                do {
                    System.out.print("\nDo you want to make another purchase? (Y for Yes, N for No): ");
                    choices = ndl.next().charAt(0);

                    if (choices == 'Y' || choices == 'y') {
                        // Clear the cart for a new purchase
                        customer.shoppingCart.clear();
                        break;
                    } else if (choices == 'N' || choices == 'n') {
                        System.out.println("THANK YOU FOR SHOPPING!");
                        break;
                    } else {
                        System.out.println("Invalid input! Please enter 'Y' or 'N'.");
                    }
                } while (true);

            } 
        } while (choices == 'Y' || choices == 'y');

        ndl.close();
    }
    private static void performAdminTasks(Scanner scanner, Administrator administrator) {
        char adminChoice;

        do {
            System.out.println("\nAdmin Tasks:");
            System.out.println("1. Display Inventory");
            System.out.println("2. Add Product");
            System.out.println("3. Remove Product");
            System.out.print("Enter your choice: ");
            int adminOption = scanner.nextInt();

            switch (adminOption) {
                case 1:
                    administrator.displayInventory();
                    break;
                case 2:
                    administrator.addProduct();
                    break;
                case 3:
                    administrator.removeProduct();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

            System.out.print("Do you want to perform another admin task? (Y for Yes, N for No): ");
            adminChoice = scanner.next().charAt(0);

        } while (adminChoice == 'Y' || adminChoice == 'y');
    }
}
