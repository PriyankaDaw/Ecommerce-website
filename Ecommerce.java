package Internship;
import java.util.*;
class Product{
    int id;
    String name;
    double price;
    int stock;

    Product(int id, String name, double price, int stock){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
class CartItem{
    Product product;
    int quantity;

    CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }
    public double Subtotal(){
        return product.price * quantity;
    }
}

public class Ecommerce {
    public static List<Product>Productslist = new ArrayList<>();
    public static List<CartItem>cart = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Productslist.add(new Product(1, "Mouse",599.00, 5));          // Fillup the Productslist.
        Productslist.add(new Product(2, "Java Book",699.00, 5));       // Fillup the Productslist.
        Productslist.add(new Product(3, "Keyboard",899.00, 5));       // Fillup the Productslist.

        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   🛍️ Welcome to JavaShop – Console Store   ║");            // Introduction.
        System.out.println("╚════════════════════════════════════════════╝");
        int input;
        do{
            System.out.println("Please select an option: ");
            System.out.println("1️⃣  View Products");
            System.out.println("2️⃣  Add Product to Cart");
            System.out.println("3️⃣  View Cart");
            System.out.println("4️⃣  Checkout");
            System.out.println("5️⃣  Exit");
            System.out.print("Enter a option: ");
            input = sc.nextInt();

            switch (input){
                case 1 -> ShowProducts();
                case 2 -> AddtoCart();
                case 3 -> display();
                case 4 -> checkout();
                case 5 -> System.out.println("\uD83D\uDC4B Exiting... Thank you for using JavaShop!");
                default -> System.out.println("❌ Invalid choice. Try again!");
            }
        } while (input != 5);
    }
    static void ShowProducts(){
        System.out.println("\n🛍️  Available Products:");
        System.out.printf("%-5s %-20s %-10s %-10s\n", "ID", "Product", "Price", "Stock");
        System.out.println("----------------------------------------------------");
        for(Product p : Productslist){
            System.out.printf("%-5d %-20s ₹%-10.2f %-10d\n", p.id, p.name, p.price, p.stock);
        }
        System.out.println("----------------------------------------------------");
    }

    static void AddtoCart(){
        System.out.print("\nEnter product ID to add to cart: ");
        int id = sc.nextInt();
        Product selected = null;

        for(Product p: Productslist){
            if(p.id == id){
                selected = p;
                break;
            }
        }

        if(selected == null){
            System.out.println("❌ Product not found.");
            return;
        }

        System.out.print("Enter quantity:  ");
        int qty = sc.nextInt();
        if(qty > selected.stock){
            System.out.println("⚠️ Not enough stock.");
            return;
        }
        for(CartItem items: cart){
            if(items.product.id == id){
                items.quantity += qty;
                selected.stock -= qty;
                System.out.println("✅ Product quantity updated in cart.");
                return;
            }
        }
        cart.add(new CartItem(selected, qty));
        selected.stock -= qty;
        System.out.println("✅ Product added to cart.");
    }

    static void display(){
        if(cart.isEmpty()){
            System.out.println("\uD83D\uDED2 Your cart is empty.");
             return;
        }

        System.out.println("-------------------------------------------------------------");
        System.out.println("🧺 Your Cart:");
        System.out.printf("%-5s %-20s %-10s %-10s %-10s\n", "Id", "Products", "Price", "Quantity", "Subtotal");
        System.out.println("-------------------------------------------------------------");
        double total=0;
        for (CartItem items : cart) {
            System.out.printf("%-5d %-20s ₹%-10.2f %-10d ₹%-10.2f\n", items.product.id, items.product.name, items.product.price, items.quantity, items.Subtotal() );
            total+=items.Subtotal();
        }
        System.out.println("---------------------------------------------------------------");
        System.out.printf(" Total : %-10.2f\n",total);
        System.out.println("--------------------------------------------------------------");
    }

    static void checkout(){
        if (cart.isEmpty()) {
            System.out.println("🛒 Your cart is empty.");
            return;
        }
        System.out.println("-------------------------------------------");
        System.out.println("🧾 Generating Invoice...");
        display();
        System.out.println("Transaction is being process:");
        System.out.println("✅ Payment Successful! Thank you for shopping!");
        System.out.println("-------------------------------------------");
        cart.clear();
    }
}
