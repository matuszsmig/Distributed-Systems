import Ice
import sys
import smarthouse
import asyncio

def get_coffee_type(coffee_type):
    if coffee_type.lower() == "latte":
        return smarthouse.coffeeOptions.LATTE
    elif coffee_type.lower() == "espresso":
        return smarthouse.coffeeOptions.ESPRESSO
    elif coffee_type.lower() == "americano":
        return smarthouse.coffeeOptions.AMERICANO
    elif coffee_type.lower() == "capuccino":
        return smarthouse.coffeeOptions.CAPUCCINO
    else:
        raise smarthouse.ClientError("Incorrect type of coffee")
    
def coffee_expresses_comands(coffee_object, comand_options):
    print(comand_options)
    line = input("==> ")
    if line.lower() == "make coffee":
        coffee_options = """
Coffee options:
* LATTE
* ESPRESSO
* AMERICANO
* CAPUCCINO
"""
        print(coffee_options)
        coffeeType = input("Which type of coffee would you like to have? ")
        coffeePower = float(input("How strong coffee should be? (decimal) "))
        withMilk = input("Would you like to have milk? (yes/no) ").lower() == "yes"

        try:
            option = get_coffee_type(coffeeType)
            coffee = smarthouse.Coffee(option, coffeePower, withMilk)
            try:
                coffee_object.makeCoffeeAsync(coffee).add_done_callback(lambda response: print(response.result()))
            except smarthouse.ServerError as error:
                print(error.message)
        except smarthouse.ClientError as error:
            print(error.message)

    elif line.lower() == "refill water":
        try:
            coffee_object.refillWaterAsync().add_done_callback(lambda response: print(response.result()))
        except smarthouse.ServerError as error:
            print(error.message)

    elif line.lower() == "refill milk":
        try:
            coffee_object.refillMilkAsync().add_done_callback(lambda response: print(response.result()))
        except smarthouse.ServerError as error:
            print(error.message)

    elif line.lower() == "show level of water":
        response = coffee_object.checkWaterLevel()
        print(response)

    elif line.lower() == "show level of milk":
        response = coffee_object.checkMilkLevel()
        print(response)

    elif line.lower() == "clean up express":
        try:
            coffee_object.cleanExpressAsync().add_done_callback(lambda response: print(response.result()))
        except smarthouse.ServerError as error:
            print(error.message)
    else:
        print("???")

def main():
    status = 0
    ic = None

    try:
        ic = Ice.initialize(sys.argv)
        base = ic.propertyToProxy("CoffeeExpress.Proxy")
        base2 = ic.propertyToProxy("AutomaticCoffeeExpress.Proxy")
        base3 = ic.propertyToProxy("SmartFridge.Proxy")
        coffee_express_obj = smarthouse.CoffeeExpressPrx.checkedCast(base)
        automatic_coffee_express_obj = smarthouse.CoffeeExpressPrx.checkedCast(base2)
        smart_fridge_obj = smarthouse.SmartFridgePrx.checkedCast(base3)

        if not coffee_express_obj:
            raise RuntimeError("Invalid proxy")
        
        options_string = """
Available devices:
* Automatic Coffee Express
* Coffee Express
* Smart Fridge
"""

        while True:
            print(options_string)
            line = input("Chose device: ")
            if line.lower() == "coffee express":
                comand_options = """
Available commands for Coffee Express:
* Make Coffee
* Refill Water
* Refill Milk
* Show level of Water
* Show level of Milk
* Clean up Express
"""
                coffee_expresses_comands(coffee_express_obj, comand_options)
                
            elif line.lower() == "automatic coffee express":
                comand_options = """
Available commands for Automatic Coffee Express:
* Make Coffee
* Refill Water
* Refill Milk
* Show level of Water
* Show level of Milk
* Clean up Express
"""
                coffee_expresses_comands(automatic_coffee_express_obj, comand_options)
            elif line.lower() == "smart fridge":
                comand_options = """
Available commands for Smart Fridge:
* Put in Items
* Put out Items
* Change Temperature
* Check Free Space
"""
                print(comand_options)
                line = input("==> ")
                if line.lower() == "put in items":
                    items = input("Pass the items to put into the fridge: (separate by comma and space) ")
                    userItems = [item.strip() for item in items.split(', ')]
                    try:
                        smart_fridge_obj.putItemsAsync(userItems).add_done_callback(lambda response: print(response.result()))
                    except smarthouse.ServerError as error:
                        print(error.message)
                elif line.lower() == "put out items":
                    items = input("Pass the items to get from the fridge: (separate by comma and space) ")
                    userItems = [item.strip() for item in items.split(', ')]
                    try:
                        smart_fridge_obj.getItemsAsync(userItems).add_done_callback(lambda response: print(response.result()))
                    except smarthouse.ServerError as error:
                        print(error.message)
                elif line.lower() == "change temperature":
                    temperature = float(input("Pass the temperature (decimal) "))
                    try:
                        smart_fridge_obj.changeTemperatureAsync(temperature).add_done_callback(lambda response: print(response.result()))
                    except smarthouse.ServerError as error:
                        print(error.message)
                elif line.lower() == "check free space":
                    response = smart_fridge_obj.checkFreeSpace()
                    print(response)
                else:
                    print("???")

            elif not line:
                continue
            else:
                print("???")

    except Exception as e:
        print(e)
        status = 1

    if ic:
        try:
            ic.destroy()
        except Exception as e:
            print(e)
            status = 1

    sys.exit(status)

if __name__ == "__main__":
    asyncio.run(main())
