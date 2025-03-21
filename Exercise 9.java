import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

enum PizzaSelection {
    PEPPERONI("Pepperoni", "Lots of pepperoni and extra cheese", 18),
    HAWAIIAN("Hawaiian", "Pineapple, ham, and extra cheese", 22),
    VEGGIE("Veggie", "Green pepper, onion, tomatoes, mushroom, and black olives", 25),
    BBQ_CHICKEN("BBQ Chicken", "Chicken in BBQ sauce, bacon, onion, green pepper, and cheddar cheese", 35),
    EXTRAVAGANZA("Extravaganza", "Pepperoni, ham, Italian sausage, beef, onions, green pepper, mushrooms, black olives, and extra cheese", 45);

    private final String pizzaName;
    private final String pizzaToppings;
    private final double price;

    PizzaSelection(String pizzaName, String pizzaToppings, double price) {
        this.pizzaName = pizzaName;
        this.pizzaToppings = pizzaToppings;
        this.price = price;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String getPizzaToppings() {
        return pizzaToppings;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return pizzaName + " Pizza with " + pizzaToppings + ", for €" + price;
    }
}

enum PizzaToppings {
    HAM("Ham", 2),
    PEPPERONI("Pepperoni", 2),
    BEEF("Beef", 2),
    CHICKEN("Chicken", 2),
    SAUSAGE("Sausage", 2),
    PINEAPPLE("Pineapple", 1),
    ONION("Onion", 0.5),
    TOMATOES("Tomatoes", 0.4),
    GREEN_PEPPER("Green Pepper", 0.5),
    BLACK_OLIVES("Black Olives", 0.5),
    SPINACH("Spinach", 0.5),
    CHEDDAR_CHEESE("Cheddar Cheese", 0.8),
    MOZZARELLA_CHEESE("Mozzarella Cheese", 0.8),
    FETA_CHEESE("Feta Cheese", 1),
    PARMESAN_CHEESE("Parmesan Cheese", 1);

    private final String topping;
    private final double toppingPrice;

    PizzaToppings(String topping, double toppingPrice) {
        this.topping = topping;
        this.toppingPrice = toppingPrice;
    }

    public String getTopping() {
        return topping;
    }

    public double getToppingPrice() {
        return toppingPrice;
    }

    @Override
    public String toString() {
        return topping + " - €" + toppingPrice;
    }
}

enum PizzaSize {
    LARGE("Large", 10),
    MEDIUM("Medium", 5),
    SMALL("Small", 0);

    private final String pizzaSize;
    private final double addToPizzaPrice;

    PizzaSize(String pizzaSize, double addToPizzaPrice) {
        this.pizzaSize = pizzaSize;
        this.addToPizzaPrice = addToPizzaPrice;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public double getAddToPizzaPrice() {
        return addToPizzaPrice;
    }

    @Override
    public String toString() {
        return pizzaSize + " - €" + addToPizzaPrice;
    }
}

enum SideDish {
    CALZONE("Calzone", 15),
    CHICKEN_PUFF("Chicken Puff", 20),
    MUFFIN("Muffin", 12),
    NOTHING("No side dish", 0);

    private final String sideDishName;
    private final double addToPizzaPrice;

    SideDish(String sideDishName, double addToPizzaPrice) {
        this.sideDishName = sideDishName;
        this.addToPizzaPrice = addToPizzaPrice;
    }

    public String getSideDishName() {
        return sideDishName;
    }

    public double getAddToPizzaPrice() {
        return addToPizzaPrice;
    }

    @Override
    public String toString() {
        return sideDishName + " - €" + addToPizzaPrice;
    }
}

enum Drinks {
    COCA_COLA("Coca Cola", 8),
    COCOA_DRINK("Cocoa Drink", 10),
    NOTHING("No drinks", 0);

    private final String drinkName;
    private final double addToPizzaPrice;

    Drinks(String drinkName, double addToPizzaPrice) {
        this.drinkName = drinkName;
        this.addToPizzaPrice = addToPizzaPrice;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public double getAddToPizzaPrice() {
        return addToPizzaPrice;
    }

    @Override
    public String toString() {
        return drinkName + " - €" + addToPizzaPrice;
    }
}

class CustomPizza {
    private String toppings;
    private double price;

    public CustomPizza(String toppings, double price) {
        this.toppings = toppings;
        this.price = price;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Custom Pizza with " + toppings + ", for €" + price;
    }
}

class OrderLogs {
    private ArrayDeque<String> orderLogs;

    public OrderLogs() {
        orderLogs = new ArrayDeque<>();
    }

    public void addOrderLog(String log) {
        orderLogs.push(log);
    }

    private void mostRecentLogEntry() {
        if (!orderLogs.isEmpty()) {
            System.out.println(orderLogs.peek());
        } else {
            System.out.println("The log is empty.");
        }
    }

    private String getOrderLog() {
        if (!orderLogs.isEmpty()) {
            return orderLogs.pop();
        } else {
            System.out.println("The log is empty.");
            return null;
        }
    }

    private void removeAllLogEntries() {
        orderLogs.clear();
        System.out.println("All log entries have been removed.");
    }

    private boolean orderLogsEmpty() {
        return orderLogs.isEmpty();
    }

    public void handleLogs() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose what you want to do with order logs:");
            System.out.println("1. Display all the logs");
            System.out.println("2. Display the most recent logs");
            System.out.println("3. Remove a log entry");
            System.out.println("4. Remove all log entries");
            System.out.println("5. Check if the log is completely empty");
            System.out.print("Enter your choice (1 – 5): ");

            int choice;
            while (true) {
                try {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 5) {
                        break;
                    } else {
                        System.out.print("Invalid choice. Please enter a number between 1 and 5: ");
                    }
                } catch (Exception e) {
                    System.out.print("Invalid input. Please enter a valid number: ");
                    scanner.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    System.out.println(orderLogs);
                    break;
                case 2:
                    mostRecentLogEntry();
                    break;
                case 3:
                    getOrderLog();
                    break;
                case 4:
                    removeAllLogEntries();
                    break;
                case 5:
                    if (orderLogsEmpty()) {
                        System.out.println("The log is completely empty");
                    } else {
                        System.out.println("The log is not completely empty");
                    }
                    break;
            }

            System.out.print("Do you want to perform another log operation? (Y/N): ");
            String continueOperation;
            while (true) {
                continueOperation = scanner.next();
                if (continueOperation.equalsIgnoreCase("Y") || continueOperation.equalsIgnoreCase("N")) {
                    break;
                } else {
                    System.out.print("Invalid input. Please enter Y or N: ");
                }
            }
            if (continueOperation.equalsIgnoreCase("N")) {
                break;
            }
        }
    }
}

class PizzaOrderQueue {
    private Queue<String> pizzaOrderQueue;

    public PizzaOrderQueue() {
        pizzaOrderQueue = new ArrayDeque<>();
    }

    public void addOrder(String order) {
        pizzaOrderQueue.add(order);
    }

    public void displayNextOrder() {
        if (!pizzaOrderQueue.isEmpty()) {
            System.out.println("Next order: " + pizzaOrderQueue.poll());
        } else {
            System.out.println("No orders in the queue.");
        }
    }

    public void displayAllOrders() {
        if (!pizzaOrderQueue.isEmpty()) {
            System.out.println("Orders in the queue: " + pizzaOrderQueue);
        } else {
            System.out.println("No orders in the queue.");
        }
    }

    public void clearQueue() {
        pizzaOrderQueue.clear();
        System.out.println("All orders have been removed from the queue.");
    }

    public boolean isQueueEmpty() {
        return pizzaOrderQueue.isEmpty();
    }

    public void handleQueueOperations() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose what you want to do with the pizza order queue:");
            System.out.println("1. Display the next order");
            System.out.println("2. Display all orders");
            System.out.println("3. Remove all orders");
            System.out.println("4. Check if the queue is empty");
            System.out.print("Enter your choice (1 – 4): ");

            int choice;
            while (true) {
                try {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 4) {
                        break;
                    } else {
                        System.out.print("Invalid choice. Please enter a number between 1 and 4: ");
                    }
                } catch (Exception e) {
                    System.out.print("Invalid input. Please enter a valid number: ");
                    scanner.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    displayNextOrder();
                    break;
                case 2:
                    displayAllOrders();
                    break;
                case 3:
                    clearQueue();
                    break;
                case 4:
                    if (isQueueEmpty()) {
                        System.out.println("The queue is empty");
                    } else {
                        System.out.println("The queue is not empty");
                    }
                    break;
            }

            System.out.print("Do you want to perform another queue operation? (Y/N): ");
            String continueOperation;
            while (true) {
                continueOperation = scanner.next();
                if (continueOperation.equalsIgnoreCase("Y") || continueOperation.equalsIgnoreCase("N")) {
                    break;
                } else {
                    System.out.print("Invalid input. Please enter Y or N: ");
                }
            }
            if (continueOperation.equalsIgnoreCase("N")) {
                break;
            }
        }
    }
}

// 处理订单类
class HandleOrders {
    private ArrayList<CustomPizza> customPizzas;

    public HandleOrders() {
        customPizzas = new ArrayList<>();
    }

    public void addCustomPizza(String toppings, double price) {
        CustomPizza customPizza = new CustomPizza(toppings, price);
        customPizzas.add(customPizza);
    }

    public void displayCustomPizzas() {
        for (CustomPizza customPizza : customPizzas) {
            System.out.println(customPizza);
        }
    }
}

// 披萨店类
class SliceOHeaven {
    public static final String DEF_ORDER_ID = "DEF-SOH-099";
    public static final double DEF_ORDER_TOTAL = 15.00;
    public static final double PIZZA_BASE_PRICE = 10.0;

    static final String STORE_NAME = "Slice-o-Heaven";
    static final String STORE_ADDRESS = "Pizza Street";
    static final String STORE_EMAIL = "sliceoheaven@qq.com";
    static final String STORE_PHONE = "114514";

    private final String orderId;
    private double orderTotal;
    private String pizzaDetails;
    private final String[] pizzasOrdered = new String[10];
    private final String[] pizzaSizesOrdered = new String[10];
    private final String[] sideDishesOrdered = new String[20];
    private final String[] drinksOrdered = new String[20];
    private int pizzaIndex = 0;
    private int sideDishIndex = 0;
    private int drinkIndex = 0;

    public SliceOHeaven() {
        this.orderId = DEF_ORDER_ID;
        this.orderTotal = DEF_ORDER_TOTAL;
    }

    public SliceOHeaven(String orderId, double orderTotal) {
        this.orderId = orderId;
        this.orderTotal = orderTotal;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public void takeOrder() {
        HandleOrders handleOrders = new HandleOrders();
        OrderLogs orderLogs = new OrderLogs();
        PizzaOrderQueue pizzaOrderQueue = new PizzaOrderQueue();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Welcome to Slice-o-Heaven Pizzeria. Here’s what we serve:");
                PizzaSelection[] pizzaSelections = PizzaSelection.values();
                for (int i = 0; i < pizzaSelections.length; i++) {
                    System.out.println((i + 1) + ". " + pizzaSelections[i]);
                }
                System.out.println((pizzaSelections.length + 1) + ". Custom Pizza with a maximum of 10 toppings that you choose");
                System.out.print("Please enter your choice (1 - " + (pizzaSelections.length + 1) + "): ");

                int pizzaChoice;
                while (true) {
                    try {
                        pizzaChoice = scanner.nextInt();
                        if (pizzaChoice >= 1 && pizzaChoice <= pizzaSelections.length + 1) {
                            break;
                        } else {
                            System.out.print("Invalid choice. Please enter a number between 1 and " + (pizzaSelections.length + 1) + ": ");
                        }
                    } catch (Exception e) {
                        System.out.print("Invalid input. Please enter a valid number: ");
                        scanner.nextLine();
                    }
                }

                if (pizzaChoice <= pizzaSelections.length) {
                    PizzaSelection selectedPizza = pizzaSelections[pizzaChoice - 1];
                    pizzasOrdered[pizzaIndex] = selectedPizza.toString();
                    orderTotal += selectedPizza.getPrice();
                    orderLogs.addOrderLog("Ordered " + selectedPizza.getPizzaName() + " pizza");
                    pizzaOrderQueue.addOrder("Ordered " + selectedPizza.getPizzaName() + " pizza");
                } else {
                    System.out.println("Available toppings:");
                    PizzaToppings[] toppings = PizzaToppings.values();
                    for (PizzaToppings topping : toppings) {
                        System.out.println(topping);
                    }
                    System.out.print("Enter a maximum of 10 toppings (separated by spaces): ");
                    scanner.nextLine();
                    String[] chosenToppings = scanner.nextLine().split(" ");
                    double customPizzaPrice = PIZZA_BASE_PRICE;
                    StringBuilder customPizzaToppings = new StringBuilder();
                    for (String topping : chosenToppings) {
                        boolean found = false;
                        for (PizzaToppings t : toppings) {
                            if (t.getTopping().equals(topping)) {
                                customPizzaToppings.append(topping).append(", ");
                                customPizzaPrice += t.getToppingPrice();
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Invalid topping: " + topping);
                        }
                    }
                    if (customPizzaToppings.length() > 0) {
                        customPizzaToppings.setLength(customPizzaToppings.length() - 2);
                    }
                    String toppingsStr = customPizzaToppings.toString();
                    handleOrders.addCustomPizza(toppingsStr, customPizzaPrice);
                    pizzasOrdered[pizzaIndex] = "Custom Pizza with " + toppingsStr + ", for €" + customPizzaPrice;
                    orderTotal += customPizzaPrice;
                    orderLogs.addOrderLog("Ordered Custom Pizza with " + toppingsStr + " toppings");
                    pizzaOrderQueue.addOrder("Ordered Custom Pizza with " + toppingsStr + " toppings");
                }
                pizzaIndex++;

                System.out.println("Choose a pizza size:");
                PizzaSize[] pizzaSizes = PizzaSize.values();
                for (int i = 0; i < pizzaSizes.length; i++) {
                    System.out.println((i + 1) + ". " + pizzaSizes[i]);
                }
                System.out.print("Enter your choice (1 - " + pizzaSizes.length + "): ");
                int sizeChoice;
                while (true) {
                    try {
                        sizeChoice = scanner.nextInt();
                        if (sizeChoice >= 1 && sizeChoice <= pizzaSizes.length) {
                            break;
                        } else {
                            System.out.print("Invalid choice. Please enter a number between 1 and " + pizzaSizes.length + ": ");
                        }
                    } catch (Exception e) {
                        System.out.print("Invalid input. Please enter a valid number: ");
                        scanner.nextLine();
                    }
                }
                PizzaSize selectedSize = pizzaSizes[sizeChoice - 1];
                pizzaSizesOrdered[pizzaIndex - 1] = selectedSize.toString();
                orderTotal += selectedSize.getAddToPizzaPrice();

                System.out.println("Choose a side dish:");
                SideDish[] sideDishes = SideDish.values();
                for (int i = 0; i < sideDishes.length; i++) {
                    System.out.println((i + 1) + ". " + sideDishes[i]);
                }
                System.out.print("Enter your choice (1 - " + sideDishes.length + "): ");
                int sideDishChoice;
                while (true) {
                    try {
                        sideDishChoice = scanner.nextInt();
                        if (sideDishChoice >= 1 && sideDishChoice <= sideDishes.length) {
                            break;
                        } else {
                            System.out.print("Invalid choice. Please enter a number between 1 and " + sideDishes.length + ": ");
                        }
                    } catch (Exception e) {
                        System.out.print("Invalid input. Please enter a valid number: ");
                        scanner.nextLine();
                    }
                }
                SideDish selectedSideDish = sideDishes[sideDishChoice - 1];
                sideDishesOrdered[sideDishIndex++] = selectedSideDish.toString();
                orderTotal += selectedSideDish.getAddToPizzaPrice();

                System.out.println("Choose a drink:");
                Drinks[] drinks = Drinks.values();
                for (int i = 0; i < drinks.length; i++) {
                    System.out.println((i + 1) + ". " + drinks[i]);
                }
                System.out.print("Enter your choice (1 - " + drinks.length + "): ");
                int drinkChoice;
                while (true) {
                    try {
                        drinkChoice = scanner.nextInt();
                        if (drinkChoice >= 1 && drinkChoice <= drinks.length) {
                            break;
                        } else {
                            System.out.print("Invalid choice. Please enter a number between 1 and " + drinks.length + ": ");
                        }
                    } catch (Exception e) {
                        System.out.print("Invalid input. Please enter a valid number: ");
                        scanner.nextLine();
                    }
                }
                Drinks selectedDrink = drinks[drinkChoice - 1];
                drinksOrdered[drinkIndex++] = selectedDrink.toString();
                orderTotal += selectedDrink.getAddToPizzaPrice();

                System.out.print("Do you want to order another pizza? (Y/N): ");
                String continueOrder;
                while (true) {
                    continueOrder = scanner.next();
                    if (continueOrder.equalsIgnoreCase("Y") || continueOrder.equalsIgnoreCase("N")) {
                        break;
                    } else {
                        System.out.print("Invalid input. Please enter Y or N: ");
                    }
                }
                if (continueOrder.equalsIgnoreCase("N")) {
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== ").append(STORE_NAME).append(" Receipt ===\n");
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Items: \n");
        for (int i = 0; i < pizzaIndex; i++) {
            sb.append("- ").append(pizzasOrdered[i]).append("\n");
            if (i < pizzaSizesOrdered.length && pizzaSizesOrdered[i] != null) {
                sb.append("  ").append(pizzaSizesOrdered[i]).append("\n");
            }
            if (i < sideDishesOrdered.length && sideDishesOrdered[i] != null) {
                sb.append("  ").append(sideDishesOrdered[i]).append("\n");
            }
            if (i < drinksOrdered.length && drinksOrdered[i] != null) {
                sb.append("  ").append(drinksOrdered[i]).append("\n");
            }
        }
        sb.append(String.format("Total: €%.2f\n", orderTotal));
        sb.append("==============================\n");
        return sb.toString();
    }
}

