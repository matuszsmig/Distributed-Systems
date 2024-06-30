package sr.ice.server;

import com.zeroc.Ice.Current;
import smarthouse.Coffee;
import smarthouse.ServerError;

import java.util.Optional;

public class CoffeeExpressI implements smarthouse.CoffeeExpress {
    private double waterLevel;
    private double milkLevel;
    private int coffeesMade;

    public CoffeeExpressI() {
        this.waterLevel = 100.0;
        this.milkLevel = 20.0;
        this.coffeesMade = 0;
    }

    @Override
    public String makeCoffee(Coffee coffee, Current current) throws ServerError {
        if (coffee.coffeePower > 1 || coffee.coffeePower < 0) {
            throw new ServerError("Incorrect power of coffee were given ");
        }
        if (this.coffeesMade >= 5 ) {
            throw new ServerError("Please clean up express");
        }
        if (coffee.withMilk) {
            if (this.milkLevel >= 2.0) {
                checkoutForGivenCoffee(coffee);
                this.milkLevel -= 2.0;
            } else {
                throw new ServerError("Please refill the milk");
            }
        } else {
            checkoutForGivenCoffee(coffee);
        }

        String coffeePower = howStrongCoffeeIs(coffee.coffeePower);
        String isCoffeeWithMilk = null;
        if (coffee.withMilk) {
            isCoffeeWithMilk = "with milk";
        } else {
            isCoffeeWithMilk = "without milk";
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        this.coffeesMade += 1;

        return "Your " + coffeePower + " coffee: " + coffee.coffeeType.name() + " " + isCoffeeWithMilk + " is ready!";
    }

    private String howStrongCoffeeIs(double power) {
        if (power > 0.5) {
            return "strong";
        } else {
            return "weak";
        }
    }

    private void checkoutForGivenCoffee(Coffee coffe) throws ServerError {
        switch (coffe.coffeeType){
            case LATTE -> {
                if (this.waterLevel >= 1.5) {
                    this.waterLevel -= 1.5;
                } else {
                    throw new ServerError("Please refill the water");
                }
            }
            case ESPRESSO -> {
                if (this.waterLevel >= 0.5) {
                    this.waterLevel -= 0.5;
                } else {
                    throw new ServerError("Please refill the water");
                }
            }
            case AMERICANO -> {
                if (this.waterLevel >= 1.0) {
                    this.waterLevel -= 1.0;
                } else {
                    throw new ServerError("Please refill the water");
                }
            }
            case CAPUCCINO -> {
                if (this.waterLevel >= 2.0) {
                    this.waterLevel -= 2.0;
                } else {
                    throw new ServerError("Please refill the water");
                }
            }
        }
    }

    @Override
    public String refillWater(Current current) throws ServerError {
        if (this.waterLevel >= 90.0) {
            throw new ServerError("Water is full or nearly full");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        this.waterLevel = 100.0;
        return "Water refilled";
    }

    @Override
    public String refillMilk(Current current) throws ServerError {
        if (this.milkLevel >= 17.5) {
            throw new ServerError("Milk is full or nearly full");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        this.waterLevel = 20.0;
        return "Milk refilled";
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
        if (this.coffeesMade == 0) {
            throw new ServerError("You don't have to clean express");
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        this.coffeesMade = 0;
        return "Coffee Express is now clean!";
    }
}
