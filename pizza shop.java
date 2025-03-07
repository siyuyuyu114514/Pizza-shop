import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

class SliceOHeaven {
    public static final String DEF_ORDER_ID = "DEF-SOH-099";
    public static final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    public static final double DEF_ORDER_TOTAL = 15.00;
    
    static final String STORE_NAME = "Slice-o-Heaven";
    static final String STORE_ADDRESS = "Pizza Streer";
    static final String STORE_EMAIL = "sliceoheaven@qq.com";
    static final String STORE_PHONE = "114514";

    static final Map<String, List<String>> PIZZA_INGREDIENTS = new HashMap<>();
    static final Map<String, Double> PIZZA_PRICE = new HashMap<>();
    static final Map<String, Double> SIDES = new HashMap<>();
    static final Map<String, Double> DRINKS = new HashMap<>();

    static{
        PIZZA_INGREDIENTS.put("Margherita", Arrays.asList("Tomato sauce", "Mozzarella", "Basil"));
        PIZZA_INGREDIENTS.put("Pepperoni", Arrays.asList("Tomato sauce", "Mozzarella", "Pepperoni"));
        PIZZA_INGREDIENTS.put("Pepperoni", Arrays.asList("Tomato sauce", "Mozzarella", "Mushroom", "Bell peppers"));
        
        PIZZA_PRICE.put("Margherita", 10.00);
        PIZZA_PRICE.put("Pepperoni", 12.00);
        PIZZA_PRICE.put("Vegetarian", 11.00);

        SIDES.put("Garlic Bread", 5.00);
        SIDES.put("Chicken Wings", 7.00);
        SIDES.put("Cheese Sticks", 6.00);
    
        DRINKS.put("Cola", 3.00);
        DRINKS.put("Lemonade", 3.50);
    }
    
    private final String orderId;
    private double orderTotal;
    private String pizzaIngredients;
    private final List<String> orderItems = new ArrayList<>();
    
    public SliceOHeaven(){
        this.orderId = DEF_ORDER_ID;
        this.pizzaIngredients = DEF_PIZZA_INGREDIENTS;
        this.orderTotal = DEF_ORDER_TOTAL;
    }

    public SliceOHeaven(String orderId, String pizzaIngredients, double orderTotal){
        this.orderId = orderId;
        this.pizzaIngredients = pizzaIngredients;
        this.orderTotal = orderTotal;
    }

    public String getOrderId(){
        return orderId;
    } 

    public double getOrderTotal(){
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal){
        this.orderTotal = orderTotal;
    }

    public String getPizzaIngredients(){
        return pizzaIngredients;
    }
    
    public void setPizzaIngredients(String pizzaIngredients){
        this.pizzaIngredients = pizzaIngredients;
    }
    
    public void takeOrder(){
       try(Scanner scanner = new Scanner(System.in)){
        System.out.println("Available pizza types and their ingredients:");
        for (Map.Entry<String, List<String>> entry : PIZZA_INGREDIENTS.entrySet()) {
            System.out.println(entry.getKey() + ": " + String.join(", ", entry.getValue()));
        }
        System.out.println("Enter the number of the pizza type (1 - Margherita, 2 - Pepperoni, 3 - Vegetarian):");
        int pizzaTypeChoice;
        while (true) {
            try {
                pizzaTypeChoice = scanner.nextInt();
                if (pizzaTypeChoice >= 1 && pizzaTypeChoice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); 
            }
        }
        String pizzaType;
        switch (pizzaTypeChoice) {
            case 1:
                pizzaType = "Margherita";
                break;
            case 2:
                pizzaType = "Pepperoni";
                break;
            case 3:
                pizzaType = "Vegetarian";
                break;
            default:
                pizzaType = "Margherita"; 
        }
        pizzaIngredients = String.join(", ", PIZZA_INGREDIENTS.get(pizzaType));
        orderItems.add("Pizza: " + pizzaType + " (" + pizzaIngredients + ")");
        orderTotal += PIZZA_PRICE.get(pizzaType);

        System.out.println("Enter size of pizza (Small, Medium, Large):");
        String pizzaSize;
        while (true) {
            pizzaSize = scanner.next();
            if (Arrays.asList("Small", "Medium", "Large").contains(pizzaSize)) {
                break;
            } else {
                System.out.println("Invalid size. Please enter Small, Medium, or Large.");
            }
        }
        orderItems.add("Pizza Size: " + pizzaSize);

        System.out.println("Do you want extra cheese (Y/N):");
        String extraCheese;
        while (true) {
            extraCheese = scanner.next();
            if (extraCheese.equalsIgnoreCase("Y") || extraCheese.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }
        orderItems.add("Extra Cheese: " + extraCheese);
        if (extraCheese.equalsIgnoreCase("Y")) {
            orderTotal += 2; 
        }

        System.out.println("Available sides: Garlic Bread, Chicken Wings, Cheese Sticks, None");
        System.out.println("Enter one side dish:");
        String sideDish;
        while (true) {
            sideDish = scanner.next();
            if (SIDES.containsKey(sideDish) || sideDish.equals("None")) {
                break;
            } else {
                System.out.println("Invalid side dish. Please choose from the available options.");
            }
        }
        if (!sideDish.equals("None")) {
            orderItems.add("Side Dish: " + sideDish);
            orderTotal += SIDES.get(sideDish);
        }

        System.out.println("Available drinks: Cola, Lemonade, None");
        System.out.println("Enter drinks:");
        String drinks;
        while (true) {
            drinks = scanner.next();
            if (DRINKS.containsKey(drinks) || drinks.equals("None")) {
                break;
            } else {
                System.out.println("Invalid drink. Please choose from the available options.");
            }
        }
        if (!drinks.equals("None")) {
            orderItems.add("Drink: " + drinks);
            orderTotal += DRINKS.get(drinks);
        }

        System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
        String wantDiscount;
        while (true) {
            wantDiscount = scanner.next();
            if (wantDiscount.equalsIgnoreCase("Y") || wantDiscount.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }

        if (wantDiscount.equalsIgnoreCase("Y")) {
            isItYourBirthday();
        } else {
            makeCardPayment();
        }
    }
}
    public void isItYourBirthday(){
        try(Scanner scanner = new Scanner(System.in)){
        LocalDate minDate = LocalDate.now().minusYears(120);
        LocalDate maxDate = LocalDate.now().minusYears(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthdate;
        while (true) {
            System.out.println("Enter your birthday (YYYY-MM-DD):");
            String birthdateStr = scanner.next();
            try {
                birthdate = LocalDate.parse(birthdateStr, formatter);
                if (birthdate.isAfter(minDate) && birthdate.isBefore(maxDate)) {
                    break;
                } else {
                    System.out.println("Invalid date. Please enter a date between 5 and 120 years ago.");
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
        LocalDate today = LocalDate.now();

        int age = Period.between(birthdate, today).getYears();
        boolean isBirthday = birthdate.getMonth() == today.getMonth() && birthdate.getDayOfMonth() == today.getDayOfMonth();

        if (age < 18 && isBirthday) {
            System.out.println("Congratulations! You pay only half the price for your order");
            orderTotal /= 2;
        } else {
            System.out.println("Too bad! You do not meet the conditions to get our 50% discount");
        }
        makeCardPayment();
    }
}    
    public void makeCardPayment() {
        try(Scanner scanner = new Scanner(System.in)){
        long cardNumber;
        String expiryDate;
        int cvv;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        while (true) {
            System.out.println("Enter your card number:");
            try {
                cardNumber = scanner.nextLong();
                if (Long.toString(cardNumber).length() == 14) {
                    break;
                } else {
                    System.out.println("Invalid card number. Please enter a 14 - digit number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); 
            }
        }

        LocalDate today = LocalDate.now();
        LocalDate expDate;
        while (true) {
            System.out.println("Enter the card's expiry date (YYYY-MM):");
            expiryDate = scanner.next();
            try {
                expDate = LocalDate.parse(expiryDate + "-01", formatter);
                if (expDate.isAfter(today)) {
                    break;
                } else {
                    System.out.println("Invalid expiry date. Please enter a future date.");
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use YYYY-MM.");
            }
        }

        while (true) {
            System.out.println("Enter the card's cvv number:");
            try {
                cvv = scanner.nextInt();
                if (String.valueOf(cvv).length() == 3) {
                    break;
                } else {
                    System.out.println("Invalid CVV number. Please enter a 3 - digit number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); 
            }
        }

        processCardPayment(cardNumber, expiryDate, cvv);
    }
}
    private static final long BLACKLISTED_NUMBER = 12345678901234L;
    
    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardNumberStr = Long.toString(cardNumber);
        while (cardNumberStr.length() != 14 || cardNumber == BLACKLISTED_NUMBER) {
            try(Scanner scanner = new Scanner(System.in)){
            System.out.println("Invalid card number. Please enter a valid 14 - digit non - blacklisted card number:");
            try {
                cardNumber = scanner.nextLong();
                cardNumberStr = Long.toString(cardNumber);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); 
            }
        }
    }
        int firstCardDigit = Integer.parseInt(cardNumberStr.substring(0, 1));
        int lastFourDigits = Integer.parseInt(cardNumberStr.substring(cardNumberStr.length() - 4));

        String cardBrand;
        switch (firstCardDigit) {
            case 3:
                cardBrand = "American Express or Diner's Club";
                break;
            case 4:
                cardBrand = "Visa";
                break;
            case 5:
                cardBrand = "MasterCard";
                break;
            default:
                cardBrand = "Unknown";
        }
        System.out.println("Card brand: " + cardBrand);
    
        if (lastFourDigits == 0) {
            System.out.println("Invalid last four digits. They cannot be all zero.");
            return;
        }
        
        String cardNumberToDisplay = cardNumberStr.charAt(0) + cardNumberStr.substring(1, cardNumberStr.length() - 4).replaceAll(".", "*") + cardNumberStr.substring(cardNumberStr.length() - 4);
        System.out.println("Card number to display: " + cardNumberToDisplay);

        System.out.println("Payment successful!");
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== ").append(STORE_NAME).append(" Receipt ===\n");
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Items: \n");
        for (String item : orderItems) {
            sb.append("- ").append(item).append("\n");
        }
        sb.append(String.format("Total: $%.2f\n", orderTotal));
        sb.append("==============================\n");
        return sb.toString();
    }

    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice){
        System.out.println("Pizza of the day: " + pizzaOfTheDay);
        System.out.println("Side of the day: " + sideOfTheDay);
        System.out.println("Special price: " + specialPrice);
    }
}

