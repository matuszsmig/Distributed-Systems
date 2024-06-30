#ifndef SMARTHOUSE_ICE
#define SMARTHOUSE_ICE

module DeliveryService {
    sequence<double> DeliveryTips;

    class Delivery {
        string phoneNumber;
        string streetName;
        int houseNumber;
        optional(1) int apartmentNumber;
        optional(2) DeliveryTips deliveryTips;
    };

    interface Promotion {
        double CountTips(Delivery delivery);
    };
};

#endif


