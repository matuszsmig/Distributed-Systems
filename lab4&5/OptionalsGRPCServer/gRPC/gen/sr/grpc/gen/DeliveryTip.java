// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: deliveries.proto

// Protobuf Java Version: 4.26.1
package sr.grpc.gen;

/**
 * Protobuf type {@code DeliveryTip}
 */
public final class DeliveryTip extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:DeliveryTip)
    DeliveryTipOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 1,
      /* suffix= */ "",
      DeliveryTip.class.getName());
  }
  // Use DeliveryTip.newBuilder() to construct.
  private DeliveryTip(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private DeliveryTip() {
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sr.grpc.gen.Deliveries.internal_static_DeliveryTip_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sr.grpc.gen.Deliveries.internal_static_DeliveryTip_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sr.grpc.gen.DeliveryTip.class, sr.grpc.gen.DeliveryTip.Builder.class);
  }

  public static final int DELIVERYAVERAGETIPS_FIELD_NUMBER = 1;
  private double deliveryAverageTips_ = 0D;
  /**
   * <code>double deliveryAverageTips = 1;</code>
   * @return The deliveryAverageTips.
   */
  @java.lang.Override
  public double getDeliveryAverageTips() {
    return deliveryAverageTips_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (java.lang.Double.doubleToRawLongBits(deliveryAverageTips_) != 0) {
      output.writeDouble(1, deliveryAverageTips_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (java.lang.Double.doubleToRawLongBits(deliveryAverageTips_) != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(1, deliveryAverageTips_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof sr.grpc.gen.DeliveryTip)) {
      return super.equals(obj);
    }
    sr.grpc.gen.DeliveryTip other = (sr.grpc.gen.DeliveryTip) obj;

    if (java.lang.Double.doubleToLongBits(getDeliveryAverageTips())
        != java.lang.Double.doubleToLongBits(
            other.getDeliveryAverageTips())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + DELIVERYAVERAGETIPS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getDeliveryAverageTips()));
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sr.grpc.gen.DeliveryTip parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.DeliveryTip parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.DeliveryTip parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.DeliveryTip parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.DeliveryTip parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.DeliveryTip parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.DeliveryTip parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.DeliveryTip parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static sr.grpc.gen.DeliveryTip parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static sr.grpc.gen.DeliveryTip parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sr.grpc.gen.DeliveryTip parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.DeliveryTip parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(sr.grpc.gen.DeliveryTip prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code DeliveryTip}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:DeliveryTip)
      sr.grpc.gen.DeliveryTipOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sr.grpc.gen.Deliveries.internal_static_DeliveryTip_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sr.grpc.gen.Deliveries.internal_static_DeliveryTip_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sr.grpc.gen.DeliveryTip.class, sr.grpc.gen.DeliveryTip.Builder.class);
    }

    // Construct using sr.grpc.gen.DeliveryTip.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      deliveryAverageTips_ = 0D;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sr.grpc.gen.Deliveries.internal_static_DeliveryTip_descriptor;
    }

    @java.lang.Override
    public sr.grpc.gen.DeliveryTip getDefaultInstanceForType() {
      return sr.grpc.gen.DeliveryTip.getDefaultInstance();
    }

    @java.lang.Override
    public sr.grpc.gen.DeliveryTip build() {
      sr.grpc.gen.DeliveryTip result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sr.grpc.gen.DeliveryTip buildPartial() {
      sr.grpc.gen.DeliveryTip result = new sr.grpc.gen.DeliveryTip(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(sr.grpc.gen.DeliveryTip result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.deliveryAverageTips_ = deliveryAverageTips_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof sr.grpc.gen.DeliveryTip) {
        return mergeFrom((sr.grpc.gen.DeliveryTip)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sr.grpc.gen.DeliveryTip other) {
      if (other == sr.grpc.gen.DeliveryTip.getDefaultInstance()) return this;
      if (other.getDeliveryAverageTips() != 0D) {
        setDeliveryAverageTips(other.getDeliveryAverageTips());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 9: {
              deliveryAverageTips_ = input.readDouble();
              bitField0_ |= 0x00000001;
              break;
            } // case 9
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private double deliveryAverageTips_ ;
    /**
     * <code>double deliveryAverageTips = 1;</code>
     * @return The deliveryAverageTips.
     */
    @java.lang.Override
    public double getDeliveryAverageTips() {
      return deliveryAverageTips_;
    }
    /**
     * <code>double deliveryAverageTips = 1;</code>
     * @param value The deliveryAverageTips to set.
     * @return This builder for chaining.
     */
    public Builder setDeliveryAverageTips(double value) {

      deliveryAverageTips_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>double deliveryAverageTips = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearDeliveryAverageTips() {
      bitField0_ = (bitField0_ & ~0x00000001);
      deliveryAverageTips_ = 0D;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:DeliveryTip)
  }

  // @@protoc_insertion_point(class_scope:DeliveryTip)
  private static final sr.grpc.gen.DeliveryTip DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sr.grpc.gen.DeliveryTip();
  }

  public static sr.grpc.gen.DeliveryTip getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DeliveryTip>
      PARSER = new com.google.protobuf.AbstractParser<DeliveryTip>() {
    @java.lang.Override
    public DeliveryTip parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<DeliveryTip> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DeliveryTip> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sr.grpc.gen.DeliveryTip getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
