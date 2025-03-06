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
        Scanner scanner = new Scanner(System.in);
        try{
        System.out.println("Enter three ingredients for your pizza(use spaces to separate ingredients):");
        String ing1 = scanner.next();
        String ing2 = scanner.next();
        String ing3 = scanner.next();
        orderItems.add("Pizza Ingredients: " + ing1 + ", " + ing2 + ", " + ing3);

        System.out.println("Enter size of pizza(Small, Medium, Large):");
        String pizzaSize = scanner.next();
        orderItems.add("Pizza Size: " + pizzaSize);


        System.out.println("Do you want extra cheese (Y/N):");
        String extraCheese = scanner.next();
        orderItems.add("Extra Cheese: " + extraCheese);

        System.out.println("Enter one side dish (Calzone, Garlic bread, None):");
        String sideDish = scanner.next();
        orderItems.add("Side Dish: " + sideDish);

        System.out.println("Enter drinks(Cold Coffee, Cocoa drink, Coke, None):");
        String drinks = scanner.next();
        orderItems.add("Drinks: " + drinks);

        System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
        String wantDiscount = scanner.next();

        if (wantDiscount.equalsIgnoreCase("Y")){
            isItYourBirthday();
        }else {
            makeCardPayment();
        }
    }finally {
        scanner.close();
    }
}   

    public void isItYourBirthday(){
        Scanner scanner = new Scanner(System.in);
        try{
        System.out.println("Enter your birthday (yyyy-MM-dd):");
        String birthdateStr = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate birthdate = LocalDate.parse(birthdateStr, formatter);
        LocalDate today = LocalDate.now();

        int age = Period.between(birthdate, today).getYears();
        boolean isBirthday = birthdate.getMonth() == today.getMonth() && birthdate.getDayOfMonth() == today.getDayOfMonth();

        if (age < 18 && isBirthday){
            System.out.println("Congratulations! You pay only half the price for your order");
            orderTotal /= 2;
        }else{
            System.out.println("Too bad! You do not meet the conditions to get our 50% discount"); 
        }
    }finally {
        scanner.close();
    }
}
    public void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);
        try{
        System.out.println("Enter your card number:");
        long cardNumber = scanner.nextLong();
        System.out.println("Enter the card's expiry date (YYYY-MM):");
        String expiryDate = scanner.next();
        System.out.println("Enter the card's cvv number:");
        int cvv = scanner.nextInt();

        processCardPayment(cardNumber, expiryDate, cvv);
    }finally {
        scanner.close();
    }
}
    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardNumberStr = Long.toString(cardNumber);
        int cardLength = cardNumberStr.length();
        if (cardLength == 14) {
            System.out.println("Card accepted");
        } else {
            System.out.println("Invalid card");
            return;
        }

        int firstCardDigit = Integer.parseInt(cardNumberStr.substring(0, 1));
        System.out.println("First card digit: " + firstCardDigit);

        long blacklistedNumber = 12345678901234L; 
        if (cardNumber == blacklistedNumber) {
            System.out.println("Card is blacklisted. Please use another card");
            return;
        }

        int lastFourDigits = Integer.parseInt(cardNumberStr.substring(cardNumberStr.length() - 4));
        System.out.println("Last four digits: " + lastFourDigits);
        
        String cardNumberToDisplay = cardNumberStr.charAt(0) + cardNumberStr.substring(1, cardNumberStr.length() - 4).replaceAll(".", "*") + cardNumberStr.substring(cardNumberStr.length() - 4);
        System.out.println("Card number to display: " + cardNumberToDisplay);

        printReceipt();
    }

    public void printReceipt(){
        System.out.println("\n=== " + STORE_NAME + " Receipt ===");
        System.out.println("Order ID: " + orderId);
        System.out.println("Items: ");
        for (String item : orderItems){
            System.out.println("- " + item);
        }
        System.out.printf("Total: $%.2f\n", orderTotal);
        System.out.println("==============================\n");
        
    }

    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice){
        System.out.println("Pizza of the day: " + pizzaOfTheDay);
        System.out.println("Side of the day: " + sideOfTheDay);
        System.out.println("Special price: " + specialPrice);
    }
}

