
package departmentstore;
import java.util.*;

public class DepartmentStore {
    public static void main(String[] args) {
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
        Product product1 = new Product("Shirt", 29.99, 5);
        Product product2 = new Product("Jeans", 39.99, 7);
        Product product3 = new Product("Sweater", 49.99, 4);
        Product product4 = new Product("Dress", 59.99, 5);
        Product product5 = new Product("Tie", 19.99, 10);
        Product product6 = new Product("Hat", 9.99, 15);
        Product product7 = new Product("Socks", 79.99, 18);
        Product product8 = new Product("Watch", 99.99, 3);
        Product product9 = new Product("Backpack", 39.99, 10);
        Product product10 = new Product("Gloves", 12.99, 20);
        Product product11 = new Product("Scarf", 18.99, 10);
        Product product12 = new Product("Jacket", 89.99, 5);
        Product product13 = new Product("Skirt", 34.99, 6);
        Product product14 = new Product("Belt", 15.99, 7);
        Product product15 = new Product("Umbrella", 24.99, 5);
        Product product16 = new Product("Sunglasses", 29.99, 10);
        Product product17 = new Product("Towel", 29.99, 17);
        Product product18 = new Product("Shoes", 22.99, 6);

        System.out.println("\nAvailable products:");
        System.out.println("\n1. " + product1.name + " - $" + product1.price + ", Stocks: " + product1.stock);
        System.out.println("\n2. " + product2.name + " - $" + product2.price + ", Stocks: " + product2.stock);
        System.out.println("\n3. " + product3.name + " - $" + product3.price + ", Stocks: " + product3.stock);
        System.out.println("\n4. " + product4.name + " - $" + product4.price + ", Stocks: " + product4.stock);
        System.out.println("\n5. " + product5.name + " - $" + product5.price + ", Stocks: " + product5.stock);
        System.out.println("\n6. " + product6.name + " - $" + product6.price + ", Stocks: " + product6.stock);
        System.out.println("\n7. " + product7.name + " - $" + product7.price + ", Stocks: " + product7.stock);
        System.out.println("\n8. " + product8.name + " - $" + product8.price + ", Stocks: " + product8.stock);
        System.out.println("\n9. " + product9.name + " - $" + product9.price + ", Stocks: " + product9.stock);
        System.out.println("\n10. " + product10.name + " - $" + product10.price + ", Stocks: " + product10.stock);
        System.out.println("\n11. " + product11.name + " - $" + product11.price + ", Stocks: " + product11.stock);
        System.out.println("\n12. " + product12.name + " - $" + product12.price + ", Stocks: " + product12.stock);
        System.out.println("\n13. " + product13.name + " - $" + product13.price + ", Stocks: " + product13.stock);
        System.out.println("\n14. " + product14.name + " - $" + product14.price + ", Stocks: " + product14.stock);
        System.out.println("\n15. " + product15.name + " - $" + product15.price + ", Stocks: " + product15.stock);
        System.out.println("\n16. " + product16.name + " - $" + product16.price + ", Stocks: " + product16.stock);
        System.out.println("\n17. " + product17.name + " - $" + product17.price + ", Stocks: " + product17.stock);
        System.out.println("\n18. " + product18.name + " - $" + product18.price + ", Stocks: " + product18.stock);

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
                Product selectedProduct = null;
                
                switch (choice) {
                    case 1:
                        selectedProduct = product1;
                        break;
                    case 2:
                        selectedProduct = product2;
                        break;
                    case 3:
                        selectedProduct = product3;
                        break;
                    case 4:
                        selectedProduct = product4;
                        break;
                    case 5:
                        selectedProduct = product5;
                        break;
                    case 6:
                        selectedProduct = product6;
                        break;
                    case 7:
                        selectedProduct = product7;
                        break;
                    case 8:
                        selectedProduct = product8;
                        break;
                    case 9:
                        selectedProduct = product9;
                        break;
                    case 10:
                        selectedProduct = product10;
                        break;
                    case 11:
                        selectedProduct = product11;
                        break;
                    case 12:
                        selectedProduct = product12;
                        break;
                    case 13:
                        selectedProduct = product13;
                        break;
                    case 14:
                        selectedProduct = product14;
                        break;
                    case 15:
                        selectedProduct = product15;
                        break;
                    case 16:
                        selectedProduct = product16;
                        break;
                    case 17:
                        selectedProduct = product17;
                        break;
                    case 18:
                        selectedProduct = product18;
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
                
                if (selectedProduct != null && selectedProduct.stock > 0) {
                    customer.addToCart(selectedProduct);
                    selectedProduct.stock--; // Decrease the stock
                    System.out.println("Item added to the cart.");
                } else if (selectedProduct != null) {
                    System.out.println("Sorry, " + selectedProduct.name + " is out of stock. Please choose another product.");
                }
                
            }
            
        } while (choice != 0 || customer.shoppingCart.isEmpty());

        // Payment
        String paymentMethod = customer.choosePaymentMethod();
        double cashPaid = 0.0;

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
                } else {
                    System.out.println("Payment: credit card");
                }
                
                System.out.println("\n----------------------------------------------------");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                System.out.println("----------------------------------------------------");

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
}