package sr.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: deliveries.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class PromotionGrpc {

  private PromotionGrpc() {}

  public static final java.lang.String SERVICE_NAME = "Promotion";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.Delivery,
      sr.grpc.gen.DeliveryTip> getCountTipsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CountTips",
      requestType = sr.grpc.gen.Delivery.class,
      responseType = sr.grpc.gen.DeliveryTip.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.Delivery,
      sr.grpc.gen.DeliveryTip> getCountTipsMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.Delivery, sr.grpc.gen.DeliveryTip> getCountTipsMethod;
    if ((getCountTipsMethod = PromotionGrpc.getCountTipsMethod) == null) {
      synchronized (PromotionGrpc.class) {
        if ((getCountTipsMethod = PromotionGrpc.getCountTipsMethod) == null) {
          PromotionGrpc.getCountTipsMethod = getCountTipsMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.Delivery, sr.grpc.gen.DeliveryTip>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CountTips"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Delivery.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.DeliveryTip.getDefaultInstance()))
              .setSchemaDescriptor(new PromotionMethodDescriptorSupplier("CountTips"))
              .build();
        }
      }
    }
    return getCountTipsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PromotionStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PromotionStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PromotionStub>() {
        @java.lang.Override
        public PromotionStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PromotionStub(channel, callOptions);
        }
      };
    return PromotionStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PromotionBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PromotionBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PromotionBlockingStub>() {
        @java.lang.Override
        public PromotionBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PromotionBlockingStub(channel, callOptions);
        }
      };
    return PromotionBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PromotionFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PromotionFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PromotionFutureStub>() {
        @java.lang.Override
        public PromotionFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PromotionFutureStub(channel, callOptions);
        }
      };
    return PromotionFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void countTips(sr.grpc.gen.Delivery request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.DeliveryTip> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCountTipsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Promotion.
   */
  public static abstract class PromotionImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return PromotionGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Promotion.
   */
  public static final class PromotionStub
      extends io.grpc.stub.AbstractAsyncStub<PromotionStub> {
    private PromotionStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PromotionStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PromotionStub(channel, callOptions);
    }

    /**
     */
    public void countTips(sr.grpc.gen.Delivery request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.DeliveryTip> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCountTipsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Promotion.
   */
  public static final class PromotionBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<PromotionBlockingStub> {
    private PromotionBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PromotionBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PromotionBlockingStub(channel, callOptions);
    }

    /**
     */
    public sr.grpc.gen.DeliveryTip countTips(sr.grpc.gen.Delivery request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCountTipsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Promotion.
   */
  public static final class PromotionFutureStub
      extends io.grpc.stub.AbstractFutureStub<PromotionFutureStub> {
    private PromotionFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PromotionFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PromotionFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.DeliveryTip> countTips(
        sr.grpc.gen.Delivery request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCountTipsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_COUNT_TIPS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_COUNT_TIPS:
          serviceImpl.countTips((sr.grpc.gen.Delivery) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.DeliveryTip>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCountTipsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sr.grpc.gen.Delivery,
              sr.grpc.gen.DeliveryTip>(
                service, METHODID_COUNT_TIPS)))
        .build();
  }

  private static abstract class PromotionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PromotionBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.grpc.gen.Deliveries.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Promotion");
    }
  }

  private static final class PromotionFileDescriptorSupplier
      extends PromotionBaseDescriptorSupplier {
    PromotionFileDescriptorSupplier() {}
  }

  private static final class PromotionMethodDescriptorSupplier
      extends PromotionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    PromotionMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PromotionGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PromotionFileDescriptorSupplier())
              .addMethod(getCountTipsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
