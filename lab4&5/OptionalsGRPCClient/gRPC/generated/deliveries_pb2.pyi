from google.protobuf.internal import containers as _containers
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Iterable as _Iterable, Mapping as _Mapping, Optional as _Optional, Union as _Union

DESCRIPTOR: _descriptor.FileDescriptor

class Delivery(_message.Message):
    __slots__ = ("phoneNumber", "streetName", "houseNumber", "apartmentNumber", "deliveryTips")
    class DeliveryTips(_message.Message):
        __slots__ = ("tip",)
        TIP_FIELD_NUMBER: _ClassVar[int]
        tip: _containers.RepeatedScalarFieldContainer[float]
        def __init__(self, tip: _Optional[_Iterable[float]] = ...) -> None: ...
    PHONENUMBER_FIELD_NUMBER: _ClassVar[int]
    STREETNAME_FIELD_NUMBER: _ClassVar[int]
    HOUSENUMBER_FIELD_NUMBER: _ClassVar[int]
    APARTMENTNUMBER_FIELD_NUMBER: _ClassVar[int]
    DELIVERYTIPS_FIELD_NUMBER: _ClassVar[int]
    phoneNumber: str
    streetName: str
    houseNumber: int
    apartmentNumber: int
    deliveryTips: Delivery.DeliveryTips
    def __init__(self, phoneNumber: _Optional[str] = ..., streetName: _Optional[str] = ..., houseNumber: _Optional[int] = ..., apartmentNumber: _Optional[int] = ..., deliveryTips: _Optional[_Union[Delivery.DeliveryTips, _Mapping]] = ...) -> None: ...

class DeliveryTip(_message.Message):
    __slots__ = ("deliveryAverageTips",)
    DELIVERYAVERAGETIPS_FIELD_NUMBER: _ClassVar[int]
    deliveryAverageTips: float
    def __init__(self, deliveryAverageTips: _Optional[float] = ...) -> None: ...
