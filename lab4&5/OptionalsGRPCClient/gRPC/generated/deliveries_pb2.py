# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: deliveries.proto
# Protobuf Python Version: 5.26.1
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import symbol_database as _symbol_database
from google.protobuf.internal import builder as _builder
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x10\x64\x65liveries.proto\"\xdb\x01\n\x08\x44\x65livery\x12\x13\n\x0bphoneNumber\x18\x01 \x01(\t\x12\x12\n\nstreetName\x18\x02 \x01(\t\x12\x13\n\x0bhouseNumber\x18\x03 \x01(\x05\x12\x1c\n\x0f\x61partmentNumber\x18\x04 \x01(\x05H\x00\x88\x01\x01\x12\x31\n\x0c\x64\x65liveryTips\x18\x05 \x01(\x0b\x32\x16.Delivery.DeliveryTipsH\x01\x88\x01\x01\x1a\x1b\n\x0c\x44\x65liveryTips\x12\x0b\n\x03tip\x18\x01 \x03(\x01\x42\x12\n\x10_apartmentNumberB\x0f\n\r_deliveryTips\"*\n\x0b\x44\x65liveryTip\x12\x1b\n\x13\x64\x65liveryAverageTips\x18\x01 \x01(\x01\x32\x33\n\tPromotion\x12&\n\tCountTips\x12\t.Delivery\x1a\x0c.DeliveryTip\"\x00\x42\x1b\n\x0bsr.grpc.genB\nDeliveriesP\x01\x62\x06proto3')

_globals = globals()
_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, _globals)
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'deliveries_pb2', _globals)
if not _descriptor._USE_C_DESCRIPTORS:
  _globals['DESCRIPTOR']._loaded_options = None
  _globals['DESCRIPTOR']._serialized_options = b'\n\013sr.grpc.genB\nDeliveriesP\001'
  _globals['_DELIVERY']._serialized_start=21
  _globals['_DELIVERY']._serialized_end=240
  _globals['_DELIVERY_DELIVERYTIPS']._serialized_start=176
  _globals['_DELIVERY_DELIVERYTIPS']._serialized_end=203
  _globals['_DELIVERYTIP']._serialized_start=242
  _globals['_DELIVERYTIP']._serialized_end=284
  _globals['_PROMOTION']._serialized_start=286
  _globals['_PROMOTION']._serialized_end=337
# @@protoc_insertion_point(module_scope)
