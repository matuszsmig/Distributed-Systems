package sr.ice.server;

import com.zeroc.Ice.Current;
import smarthouse.Coffee;
import smarthouse.ServerError;

public class AutomaticCoffeeExpressI implements smarthouse.CoffeeExpress{
    private double waterLevel;
    private double milkLevel;
    private int coffeesMade;

    public AutomaticCoffeeExpressI() {
        this.waterLevel = 50.0;
        this.milkLevel = 30.0;
        this.coffeesMade = 0;
    }
    @Override
    public String makeCoffee(Coffee coffee, Current current) throws ServerError {
        if (coffee.coffeePower > 1 || coffee.coffeePower < 0) {
            throw new ServerError("Incorrect power of coffee were given ");
        }
        if (this.coffeesMade >= 3 ) {
            this.cleanExpress(current);
        }
        if (this.waterLevel < 30.0) {
            this.refillWater(current);
        }
        if (this.milkLevel < 20.0) {
            this.refillMilk(current);
        }

        if (coffee.withMilk) {
            this.milkLevel -= 2.0;
        }
        checkoutForGivenCoffee(coffee);

        String coffeePower = howStrongCoffeeIs(coffee.coffeePower);
        String isCoffeeWithMilk = null;
        if (coffee.withMilk) {
            isCoffeeWithMilk = "with milk";
        } else {
            isCoffeeWithMilk = "without milk";
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        this.coffeesMade += 1;

        return "Your " + coffeePower + " coffee: " + coffee.coffeeType.name() + " " + isCoffeeWithMilk + " made by automatic express is ready!";
    }

    private String howStrongCoffeeIs(double power) {
        if (power > 0.5) {
            return "strong";
        } else {
            return "weak";
        }
    }

    private void checkoutForGivenCoffee(Coffee coffee){
        switch (coffee.coffeeType){
            case LATTE -> {
                this.waterLevel -= 1.5;
            }
            case ESPRESSO -> {
                this.waterLevel -= 0.5;
            }
            case AMERICANO -> {
                this.waterLevel -= 1.0;
            }
            case CAPUCCINO -> {
                this.waterLevel -= 2.0;
            }
        }
    }

    @Override
    public String refillWater(Current current) throws ServerError {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        this.waterLevel = 50.0;
        return "You don't have to do it but Water is refilled :)";
    }

    @Override
    public String refillMilk(Current current) throws ServerError {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        this.waterLevel = 30.0;
        return "You don't have to do it but Milk is refilled :)";
    }

    @Override
    public double checkWaterLevel(Current current) {
        return this.waterLevel;
    }

    @Override
    public double checkMilkLevel(Current current) {
        return this.milkLevel;
    }

    @Override
    public String cleanExpress(Current current) throws ServerError {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        this.coffeesMade = 0;
        return "You don't have to do it but Coffee Express is now clean! :)";
    }
}
