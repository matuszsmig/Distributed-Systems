#ifndef SMARTHOUSE_ICE
#define SMARTHOUSE_ICE

module smarthouse
{
    exception ServerError { string message;};
    exception ClientError { string message;};

    enum coffeeOptions { LATTE, ESPRESSO, AMERICANO, CAPUCCINO };

    sequence<string> StringSequene;

    struct Coffee
    {
        coffeeOptions coffeeType;
        double coffeePower;
        bool withMilk;
    };

    interface SmartFridge
    {
        string putItems(StringSequene userItems) throws ServerError;
        string getItems(StringSequene userItems) throws ServerError;
        string changeTemperature(double temperature) throws ServerError;
        int checkFreeSpace();
    }

    interface CoffeeExpress
    {
        string makeCoffee(Coffee coffe) throws ServerError;
        string refillWater() throws ServerError;
        string refillMilk() throws ServerError;
        double checkWaterLevel();
        double checkMilkLevel();
        string cleanExpress() throws ServerError;
    };
};

#endif


