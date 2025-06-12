import java.util.*;

class Stock {
    String symbol;
    String name;
    double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return symbol + " - " + name + " | ₹" + price;
    }
}

class Holding {
    Stock stock;
    int quantity;

    public Holding(Stock stock, int quantity) {
        this.stock = stock;
        this.quantity = quantity;
    }

    public double getValue() {
        return stock.price * quantity;
    }

    @Override
    public String toString() {
        return stock.symbol + " | Qty: " + quantity + " | Value: ₹" + getValue();
    }
}

public class StockTradingPlatform {
    static Scanner scanner = new Scanner(System.in);
    static List<Stock> marketStocks = new ArrayList<>();
    static Map<String, Holding> portfolio = new HashMap<>();
    static double cashBalance = 100000; // Start with ₹100,000

    // Simulated market data
    static void initializeMarket() {
        marketStocks.add(new Stock("TCS", "Tata Consultancy", 3200));
        marketStocks.add(new Stock("INFY", "Infosys", 1400));
        marketStocks.add(new Stock("RELI", "Reliance", 2500));
        marketStocks.add(new Stock("HDFC", "HDFC Bank", 1600));
    }

    static void displayMarket() {
        System.out.println("\n--- Market Data ---");
        for (Stock stock : marketStocks) {
            System.out.println(stock);
        }
    }

    static Stock findStock(String symbol) {
        for (Stock s : marketStocks) {
            if (s.symbol.equalsIgnoreCase(symbol)) return s;
        }
        return null;
    }

    static void buyStock() {
        System.out.print("Enter stock symbol to buy: ");
        String symbol = scanner.nextLine();
        Stock stock = findStock(symbol);

        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity to buy: ");
        int qty = scanner.nextInt();
        scanner.nextLine(); // consume newline

        double cost = qty * stock.price;
        if (cost > cashBalance) {
            System.out.println("Not enough balance to buy.");
            return;
        }

        cashBalance -= cost;
        portfolio.put(symbol, new Holding(stock, portfolio.getOrDefault(symbol, new Holding(stock, 0)).quantity + qty));
        System.out.println("Bought " + qty + " shares of " + symbol);
    }

    static void sellStock() {
        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.nextLine();
        Holding holding = portfolio.get(symbol);

        if (holding == null || holding.quantity == 0) {
            System.out.println("You don’t own this stock.");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int qty = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (qty > holding.quantity) {
            System.out.println("Not enough quantity.");
            return;
        }

        double revenue = qty * holding.stock.price;
        cashBalance += revenue;
        holding.quantity -= qty;

        if (holding.quantity == 0) portfolio.remove(symbol);

        System.out.println("Sold " + qty + " shares of " + symbol + " for ₹" + revenue);
    }

    static void viewPortfolio() {
        System.out.println("\n--- Portfolio ---");
        double totalValue = 0;
        for (Holding h : portfolio.values()) {
            System.out.println(h);
            totalValue += h.getValue();
        }
        System.out.println("Cash Balance: ₹" + cashBalance);
        System.out.println("Total Portfolio Value: ₹" + (cashBalance + totalValue));
    }

    static void updateMarketPrices() {
        Random rand = new Random();
        for (Stock stock : marketStocks) {
            double change = (rand.nextDouble() * 0.1 - 0.05) * stock.price; // -5% to +5%
            stock.price = Math.round((stock.price + change) * 100.0) / 100.0;
        }
        System.out.println("📈 Market prices updated!");
    }

    public static void main(String[] args) {
        initializeMarket();

        int choice;
        do {
            System.out.println("\n====== Stock Trading Menu ======");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Update Market Prices");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayMarket();
                    break;
                case 2:
                    buyStock();
                    break;
                case 3:
                    sellStock();
                    break;
                case 4:
                    viewPortfolio();
                    break;
                case 5:
                    updateMarketPrices();
                    break;
                case 6:
                    System.out.println("Thank you for using the Trading Platform!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 6);
    }
}