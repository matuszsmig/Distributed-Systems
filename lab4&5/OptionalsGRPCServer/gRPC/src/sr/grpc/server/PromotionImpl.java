package sr.grpc.server;

import io.grpc.stub.StreamObserver;
import sr.grpc.gen.Delivery;
import sr.grpc.gen.DeliveryTip;
import sr.grpc.gen.PromotionGrpc;

import java.util.List;

public class PromotionImpl extends PromotionGrpc.PromotionImplBase {
    @Override
    public void countTips(Delivery request,
                          io.grpc.stub.StreamObserver<sr.grpc.gen.DeliveryTip> responseObserver){
        List<Double> tipsList = request.getDeliveryTips().getTipList();

        double averageTip;
        if (tipsList.isEmpty()) {
            averageTip = Double.NaN;
        } else {
            double sum = 0;
            for (Double tip : tipsList) {
                sum += tip;
            }
            averageTip = sum / tipsList.size();
        }

        DeliveryTip deliveryTipResponse = DeliveryTip.newBuilder()
                .setDeliveryAverageTips(averageTip)
                .build();

        responseObserver.onNext(deliveryTipResponse);
        responseObserver.onCompleted();
    }
}
