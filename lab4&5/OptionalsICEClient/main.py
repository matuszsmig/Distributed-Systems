import Ice
import sys
import DeliveryService

def main():
    status = 0
    ic = None

    try:
        ic = Ice.initialize(sys.argv)
        base = ic.propertyToProxy("Promotion.Proxy")
        obj = DeliveryService.PromotionPrx.checkedCast(base)

        if not obj:
            raise RuntimeError("Invalid proxy")
        
        while True:
            choice = input("==> ")
            if choice == "basic":
                response = obj.CountTips(DeliveryService.Delivery(phoneNumber="+48 555 555 555", streetName="Budryka", houseNumber=2))
                print(response)
            elif choice == "with apartment":
                response = obj.CountTips(DeliveryService.Delivery(phoneNumber="+48 555 555 555", streetName="Budryka", houseNumber=2, apartmentNumber=501))
                print(response)
            elif choice == "with tip":
                response = obj.CountTips(DeliveryService.Delivery(phoneNumber="+48 555 555 555", streetName="Budryka",
                                                                   houseNumber=2, apartmentNumber=501, deliveryTips=[5.30, 2.00, 7.30]))
                print(response)


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
    main()
