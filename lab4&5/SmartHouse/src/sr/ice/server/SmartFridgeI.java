package sr.ice.server;

import com.zeroc.Ice.Current;
import smarthouse.ServerError;
import smarthouse.SmartFridge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmartFridgeI implements SmartFridge {
    private int fridgeSpace;
    private double temperature;
    private List<String> fridgeItems;

    public SmartFridgeI() {
        this.temperature = 4.5;
        this.fridgeSpace = 20;
        this.fridgeItems = new ArrayList<>();
    }

    @Override
    public String putItems(String[] userItems, Current current) throws ServerError {
        if (userItems.length == 0) {
            throw new ServerError("You didn't pass any item");
        }
        List<String> itemsNotInserted = new ArrayList<>();
        for (String item : userItems) {
            if (fridgeItems.size() < fridgeSpace) {
                fridgeItems.add(item);
            } else {
                itemsNotInserted.add(item);
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        if (itemsNotInserted.size() > 0) {
            return "Unable to insert items into the fridge: " + itemsNotInserted;
        } else {
            return "All items have been successfully inserted into the fridge.";
        }
    }

    @Override
    public String getItems(String[] userItems, Current current) throws ServerError {
        if (userItems.length == 0) {
            throw new ServerError("You didn't pass any item");
        }
        List<String> notFoundItems = new ArrayList<>();
        for (String item : userItems) {
            if (fridgeItems.contains(item)) {
                fridgeItems.remove(item);
            } else {
                notFoundItems.add(item);
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        if (notFoundItems.size() > 0) {
            return "Items not found in the fridge: " + Arrays.toString(notFoundItems.toArray());
        } else {
            return "Every item were removed from the fridge";
        }
    }

    @Override
    public String changeTemperature(double temperature, Current current) throws ServerError {
        if (temperature >= 12.0 || temperature <= 0.0){
            throw new ServerError("Please provide a reasonable temperature");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        this.temperature = temperature;
        return "Temperature has been changed to: " + temperature + " celsius.";
    }

    @Override
    public int checkFreeSpace(Current current) {
        return this.fridgeSpace - this.fridgeItems.size();
    }
}
