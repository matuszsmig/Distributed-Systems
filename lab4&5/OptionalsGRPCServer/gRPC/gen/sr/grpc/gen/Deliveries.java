// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: deliveries.proto
// Protobuf Java Version: 4.26.1

package sr.grpc.gen;

public final class Deliveries {
  private Deliveries() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 1,
      /* suffix= */ "",
      Deliveries.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Delivery_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_Delivery_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Delivery_DeliveryTips_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_Delivery_DeliveryTips_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DeliveryTip_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_DeliveryTip_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020deliveries.proto\"\333\001\n\010Delivery\022\023\n\013phone" +
      "Number\030\001 \001(\t\022\022\n\nstreetName\030\002 \001(\t\022\023\n\013hous" +
      "eNumber\030\003 \001(\005\022\034\n\017apartmentNumber\030\004 \001(\005H\000" +
      "\210\001\001\0221\n\014deliveryTips\030\005 \001(\0132\026.Delivery.Del" +
      "iveryTipsH\001\210\001\001\032\033\n\014DeliveryTips\022\013\n\003tip\030\001 " +
      "\003(\001B\022\n\020_apartmentNumberB\017\n\r_deliveryTips" +
      "\"*\n\013DeliveryTip\022\033\n\023deliveryAverageTips\030\001" +
      " \001(\00123\n\tPromotion\022&\n\tCountTips\022\t.Deliver" +
      "y\032\014.DeliveryTip\"\000B\033\n\013sr.grpc.genB\nDelive" +
      "riesP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Delivery_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Delivery_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_Delivery_descriptor,
        new java.lang.String[] { "PhoneNumber", "StreetName", "HouseNumber", "ApartmentNumber", "DeliveryTips", });
    internal_static_Delivery_DeliveryTips_descriptor =
      internal_static_Delivery_descriptor.getNestedTypes().get(0);
    internal_static_Delivery_DeliveryTips_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_Delivery_DeliveryTips_descriptor,
        new java.lang.String[] { "Tip", });
    internal_static_DeliveryTip_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_DeliveryTip_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_DeliveryTip_descriptor,
        new java.lang.String[] { "DeliveryAverageTips", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
