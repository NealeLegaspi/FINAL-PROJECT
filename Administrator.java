class Administrator {
    List<Product> storeInventory;

    Administrator() {
        storeInventory = new ArrayList<>();
        initializeInventory();
    }
    
    private void initializeInventory() {
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
        
    }
    
    void displayInventory() {
        System.out.println("\nCurrent inventory:");
        for (int i = 0; i < storeInventory.size(); i++) {
            Product product = storeInventory.get(i);
            System.out.println((i + 1) + ". " + product.name + " - $" + product.price + ", Stocks: " + product.stock);
        }
    }
    
    void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: $");
        double price = scanner.nextDouble();
        System.out.print("Enter initial stock: ");
        int stock = scanner.nextInt();

        Product newProduct = new Product(name, price, stock);
        storeInventory.add(newProduct);
        System.out.println("Product added to the inventory.");
    }
    
    void removeProduct() {
        Scanner scanner = new Scanner(System.in);
        displayInventory();
        System.out.print("Enter the number of the product to remove: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= storeInventory.size()) {
            Product removedProduct = storeInventory.remove(choice - 1);
            System.out.println(removedProduct.name + " removed from the inventory.");
        } else {
            System.out.println("Invalid choice. No product removed.");
        }
    }
}