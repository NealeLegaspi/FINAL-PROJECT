package package1;


class Product {
	 String name;
	    double price;
	    int stock;

	    Product(String name, double price, int stock) {
	        this.name = name;
	        this.price = price;
	        this.stock = stock;
	    }	    	
	 
	    private int productId;
	    private String productName;
	    private double pricee;
	    private int quantity;

	    public Product(int productId, String productName, double price, int quantity) {
	        this.productId = productId;
	        this.productName = productName;
	        this.price = price;
	        this.quantity = quantity;
	    }

	    // Getters
	    public int getProductId() {
	        return productId;
	    }

	    public String getProductName() {
	        return productName;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public int getQuantity() {
	        return quantity;
	    }
	}

	



