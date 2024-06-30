import grpc
from generated.deliveries_pb2 import *
from generated.deliveries_pb2_grpc import *

def main():
    with grpc.insecure_channel('127.0.0.5:50051') as channel:
        stub = PromotionStub(channel)
        while True:
            choice = input("==> ")
            match choice:
                case "basic":
                    response = stub.CountTips(Delivery(phoneNumber="+48 555 555 555", streetName="Budryka", houseNumber=2))
                    print(response)
                case "with apartment":
                    response = stub.CountTips(Delivery(phoneNumber="+48 555 555 555", streetName="Budryka", houseNumber=2, apartmentNumber=501))
                    print(response)
                case "with tip":
                    response = stub.CountTips(Delivery(phoneNumber="+48 555 555 555", streetName="Budryka",
                                                       houseNumber=2, apartmentNumber=501, deliveryTips=Delivery.DeliveryTips(tip=[2.50, 5.50, 3.50])))
                    print(response)

if __name__ == "__main__":
    main()
