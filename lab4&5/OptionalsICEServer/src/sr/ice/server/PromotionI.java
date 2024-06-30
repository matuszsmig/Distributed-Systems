package sr.ice.server;

import DeliveryService.Delivery;
import DeliveryService.Promotion;
import com.zeroc.Ice.Current;

import java.util.List;

public class PromotionI implements Promotion {
    @Override
    public double CountTips(Delivery delivery, Current current) {
        try {
            double[] tipsArray = delivery.getDeliveryTips();

            if (tipsArray != null && tipsArray.length > 0) {
                double sum = 0;
                for (double tip : tipsArray) {
                    sum += tip;
                }
                return sum / tipsArray.length;
            } else {
                return 0.0;
            }
        } catch (java.util.NoSuchElementException e) {
            System.err.println("Lista deliveryTips nie została podana.");
            e.printStackTrace();
            return 0.0;
        }
    }
}
