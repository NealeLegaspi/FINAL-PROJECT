package departmentstore;
import java.util.*;

public class DepartmentStore {
    public static void main(String[] args) {
        Scanner ndl = new Scanner(System.in);
        Administrator administrator = new Administrator("administrator@gmail.com", "admin123");
        Customer customer = new Customer("Juan Dela Cruz", "juandelacruz@gmail.com", "juan1234");
        char choices;

        do {
            System.out.print("\nLogin as (C)ustomer or (A)dministrator: ");
            char userType = ndl.next().charAt(0);

            if (userType == 'C' || userType == 'c') {
                if (performCustomerLogin(customer, ndl)) {
                    performShopping(ndl, customer, administrator);
                } else {
                    System.out.println("Invalid credentials. Please try again.");
                }
            } else if (userType == 'A' || userType == 'a') {
                if (performAdminLogin(administrator, ndl)) {
                    performAdminTasks(ndl, administrator);
                } else {
                    System.out.println("Invalid credentials. Please try again.");
                }
            } else {
                System.out.println("Invalid choice. Please enter 'C' or 'A'.");
            }

            while (true) {
                System.out.print("\nDo you want to continue? (Y for Yes, N for No): ");
                choices = ndl.next().charAt(0);

                if (choices == 'N') {
                    System.out.println("Exiting the program....");
                    break;  
                } else if (choices == 'Y') {
                    System.out.println("Continuing the program....");
                    break;  
                } else {
                    System.out.println("Invalid input! Please enter 'Y' or 'N'.");
                }
            }

        } while (choices == 'Y' || choices == 'y');

    }
    
    private static boolean performCustomerLogin(Customer customer, Scanner scanner) {
        System.out.print("\nEnter your email: ");
        String email = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        return customer.authenticate(email, password);
    }

    private static boolean performAdminLogin(Administrator administrator, Scanner scanner) {
        System.out.print("\nEnter your email: ");
        String email = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        return administrator.authenticate(email, password);
    }

    private static void performShopping(Scanner scanner, Customer customer, Administrator administrator) {
        System.out.println("\n******************************************************************");
        System.out.println("*                WELCOME TO THE DEPARTMENT STORE!                *");
        System.out.println("******************************************************************");
        System.out.println("\nCustomer: " + customer.name);
        
        char choices = 0;
        do {
            List<Product> storeInventory = administrator.getStoreInventory();

            System.out.println("\nAvailable products:");
            for (int i = 0; i < storeInventory.size(); i++) {
                Product product = storeInventory.get(i);
                System.out.println((i + 1) + ". " + product.name + " - $" + product.price + ", Stocks: " + product.stock);
            }

            customer.setStoreInventory(storeInventory);

            int choice;
            do {
                System.out.print("\nEnter product number to add to cart (0 to finish, -1 to remove an item or check the cart, -2 to cancel): ");
                choice = scanner.nextInt();

                if (choice == 0 && customer.shoppingCart.isEmpty()) {
                    System.out.println("Your cart is empty. Please add items before finishing.");
                } else if (choice == -1) {
                    System.out.println("\nYour shopping cart: ");
                    customer.removeFromCart();
                } else if (choice == -2) {                  
                    System.out.println("SHOPPING CANCELLED.");
                    System.exit(0);
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

            List<Cashier> cashiers = new ArrayList<>();
            cashiers.add(new Cashier(1));
            cashiers.add(new Cashier(2));
            cashiers.add(new Cashier(3));    

            String paymentMethod = customer.choosePaymentMethod();
            double cashPaid = 0.0;

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
                    
                    double change = cashPaid - customer.calculateTotal();
                    System.out.printf("Change: $%.2f%n", change);
                    
                } else {
                    System.out.println("Payment: credit card");
                }
                
                System.out.println("\n----------------------------------------------------");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                System.out.println("----------------------------------------------------");

                cashiers.get(chosenCashierNumber - 1).setOccupied(false);
            
                do {
                    System.out.print("\nDo you want to make another purchase? (Y for Yes, N for No): ");
                    choices = scanner.next().charAt(0);

                    if (choices == 'Y' || choices == 'y') {
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
        
            administrator.setStoreInventory(customer.getStoreInventory());
            
        } while (choices == 'Y' || choices == 'y');
    }
    
   private static void performAdminTasks(Scanner scanner, Administrator administrator) {
    System.out.println("\n******************************************************************");
    System.out.println("*                         ADMINISTRATOR                          *");
    System.out.println("******************************************************************");   
    
    char adminChoice;

    do {
        System.out.println("\nAdmin Tasks:");
        System.out.println("1. Display Inventory");
        System.out.println("2. Add Product");
        System.out.println("3. Remove Product");
        System.out.println("4. Edit Stock");
        System.out.println("5. Edit Price");
        System.out.print("Enter your choice (Enter 0 to cancel): ");
        int adminOption = scanner.nextInt();

        if (adminOption == 0) {
            System.out.println("Operation canceled.");
            return;
        }

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
            case 4:
                // Edit Stock
                editStock(scanner, administrator);
                break;
            case 5:
                // Edit Price
                editPrice(scanner, administrator);
                break;
            default:
                System.out.println("Invalid choice!");
        }

        System.out.print("Do you want to perform another admin task? (Y for Yes, N for No): ");
        adminChoice = scanner.next().charAt(0);

    } while (adminChoice == 'Y' || adminChoice == 'y');
}


    private static void editStock(Scanner scanner, Administrator administrator) {
        administrator.displayInventory();
        System.out.print("Enter the product index to edit stock (Enter 0 to cancel): ");

        try {
            int productIndex = scanner.nextInt();

            if (productIndex == 0) {
                System.out.println("Operation canceled.");
                return;
            }

            System.out.print("Enter the new stock: ");
            int newStock = scanner.nextInt();

            administrator.editStock(productIndex - 1, newStock); // Adjusting for 0-based index
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for the product index and stock.");
            scanner.nextLine(); // Consume the invalid input
        }
    }

    private static void editPrice(Scanner scanner, Administrator administrator) {
        administrator.displayInventory();
        System.out.print("Enter the product index to edit price (Enter 0 to cancel): ");

        try {
            int productIndex = scanner.nextInt();

            if (productIndex == 0) {
                System.out.println("Operation canceled.");
                return;
            }

            System.out.print("Enter the new price: ");
            double newPrice = scanner.nextDouble();

            administrator.editPrice(productIndex - 1, newPrice); // Adjusting for 0-based index
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for the product index and a valid double for the price.");
            scanner.nextLine(); // Consume the invalid input
        }
    }
}
