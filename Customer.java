package departmentstore;
import java.util.*;

class Customer {
    String name;
    ArrayList<Product> shoppingCart;

    Customer(String name) {
        this.name = name;
        this.shoppingCart = new ArrayList<>();
    }

    void addToCart(Product product) {
        shoppingCart.add(product);
    }

    double calculateTotal() {
        double total = 0;
        for (Product product : shoppingCart) {
            total += product.price;
        }
        return total;
    }

    void removeFromCart() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
            return;
        }

        System.out.println("Your current cart:");
        for (int i = 0; i < shoppingCart.size(); i++) {
            Product product = shoppingCart.get(i);
            System.out.println((i + 1) + ". " + product.name + " - $" + product.price);
        }

        Scanner ndl = new Scanner(System.in);
        System.out.print("Enter the number of the item to remove (0 to cancel): ");
        int choice = ndl.nextInt();

        if (choice > 0 && choice <= shoppingCart.size()) {
            Product removedProduct = shoppingCart.remove(choice - 1);
            removedProduct.stock++;
            System.out.println("Item removed from the cart.");
        } else if (choice != 0) {
            System.out.println("Invalid choice. No item removed.");
        }
    }

    String choosePaymentMethod() {
        List<String> validPaymentMethods = Arrays.asList("cash", "credit card");

        Scanner ndl = new Scanner(System.in);
        String paymentMethod;

        do {
            System.out.print("Choose payment method (cash or credit card): ");
            paymentMethod = ndl.nextLine().toLowerCase();

            if (!validPaymentMethods.contains(paymentMethod)) {
                System.out.println("Invalid payment method. Please choose a valid payment method.");
            }

        } while (!validPaymentMethods.contains(paymentMethod));

        return paymentMethod;
    }
    

    double processCashPayment(double total) {
        Scanner ndl = new Scanner(System.in);
        System.out.print("Enter the amount of cash you have: $");
        double cashAmount = ndl.nextDouble();

        if (cashAmount < total) {
            System.out.println("Insufficient funds. Payment canceled.");
            return 0.0;
        } else {
            double change = cashAmount - total;
            System.out.printf("Change: $%.2f%n", change);
            return cashAmount;
        }
    }
}