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

class SliceOHeaven {
    public static final String DEF_ORDER_ID = "DEF-SOH-099";
    public static final double DEF_ORDER_TOTAL = 15.00;
    public static final double PIZZA_BASE_PRICE = 10.0;

    static final String STORE_NAME = "Slice-o-Heaven";
    static final String STORE_ADDRESS = "Pizza Streer";
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
                    StringBuilder customPizzaDetails = new StringBuilder("Custom Pizza with ");
                    for (String topping : chosenToppings) {
                        boolean found = false;
                        for (PizzaToppings t : toppings) {
                            if (t.getTopping().equals(topping)) {
                                customPizzaDetails.append(topping).append(", ");
                                customPizzaPrice += t.getToppingPrice();
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Invalid topping: " + topping);
                        }
                    }
                    if (customPizzaDetails.length() > 18) {
                        customPizzaDetails.setLength(customPizzaDetails.length() - 2);
                    }
                    customPizzaDetails.append(", for €").append(customPizzaPrice);
                    pizzasOrdered[pizzaIndex] = customPizzaDetails.toString();
                    orderTotal += customPizzaPrice;
                }
                pizzaIndex++;

                System.out.println("Available pizza sizes:");
                PizzaSize[] pizzaSizes = PizzaSize.values();
                for (PizzaSize size : pizzaSizes) {
                    System.out.println(size);
                }
                System.out.print("Please enter your choice: ");
                String sizeChoice;
                while (true) {
                    sizeChoice = scanner.next();
                    boolean found = false;
                    for (PizzaSize size : pizzaSizes) {
                        if (size.getPizzaSize().equals(sizeChoice)) {
                            pizzaSizesOrdered[pizzaIndex - 1] = size.toString();
                            orderTotal += size.getAddToPizzaPrice();
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    } else {
                        System.out.print("Invalid size. Please enter a valid size: ");
                    }
                }

                System.out.println("Available side dishes:");
                SideDish[] sideDishes = SideDish.values();
                for (SideDish sideDish : sideDishes) {
                    System.out.println(sideDish);
                }
                System.out.print("Please enter your choice: ");
                String sideDishChoice;
                while (true) {
                    sideDishChoice = scanner.next();
                    boolean found = false;
                    for (SideDish sideDish : sideDishes) {
                        if (sideDish.getSideDishName().equals(sideDishChoice)) {
                            sideDishesOrdered[sideDishIndex] = sideDish.toString();
                            orderTotal += sideDish.getAddToPizzaPrice();
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    } else {
                        System.out.print("Invalid side dish. Please enter a valid side dish: ");
                    }
                }
                sideDishIndex++;

                System.out.println("Available drinks:");
                Drinks[] drinks = Drinks.values();
                for (Drinks drink : drinks) {
                    System.out.println(drink);
                }
                System.out.print("Please enter your choice: ");
                String drinkChoice;
                while (true) {
                    drinkChoice = scanner.next();
                    boolean found = false;
                    for (Drinks drink : drinks) {
                        if (drink.getDrinkName().equals(drinkChoice)) {
                            drinksOrdered[drinkIndex] = drink.toString();
                            orderTotal += drink.getAddToPizzaPrice();
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    } else {
                        System.out.print("Invalid drink. Please enter a valid drink: ");
                    }
                }
                drinkIndex++;

                System.out.print("Do you want to order more? (Y/N): ");
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