# -*- coding: utf-8 -*-
#
# Copyright (c) ZeroC, Inc. All rights reserved.
#
#
# Ice version 3.7.10
#
# <auto-generated>
#
# Generated from file `deliveries.ice'
#
# Warning: do not edit this file.
#
# </auto-generated>
#

from sys import version_info as _version_info_
import Ice, IcePy

# Start of module DeliveryService
_M_DeliveryService = Ice.openModule('DeliveryService')
__name__ = 'DeliveryService'

if '_t_DeliveryTips' not in _M_DeliveryService.__dict__:
    _M_DeliveryService._t_DeliveryTips = IcePy.defineSequence('::DeliveryService::DeliveryTips', (), IcePy._t_double)

if 'Delivery' not in _M_DeliveryService.__dict__:
    _M_DeliveryService.Delivery = Ice.createTempClass()
    class Delivery(Ice.Value):
        def __init__(self, phoneNumber='', streetName='', houseNumber=0, apartmentNumber=Ice.Unset, deliveryTips=Ice.Unset):
            self.phoneNumber = phoneNumber
            self.streetName = streetName
            self.houseNumber = houseNumber
            self.apartmentNumber = apartmentNumber
            self.deliveryTips = deliveryTips

        def ice_id(self):
            return '::DeliveryService::Delivery'

        @staticmethod
        def ice_staticId():
            return '::DeliveryService::Delivery'

        def __str__(self):
            return IcePy.stringify(self, _M_DeliveryService._t_Delivery)

        __repr__ = __str__

    _M_DeliveryService._t_Delivery = IcePy.defineValue('::DeliveryService::Delivery', Delivery, -1, (), False, False, None, (
        ('phoneNumber', (), IcePy._t_string, False, 0),
        ('streetName', (), IcePy._t_string, False, 0),
        ('houseNumber', (), IcePy._t_int, False, 0),
        ('apartmentNumber', (), IcePy._t_int, True, 1),
        ('deliveryTips', (), _M_DeliveryService._t_DeliveryTips, True, 2)
    ))
    Delivery._ice_type = _M_DeliveryService._t_Delivery

    _M_DeliveryService.Delivery = Delivery
    del Delivery

_M_DeliveryService._t_Promotion = IcePy.defineValue('::DeliveryService::Promotion', Ice.Value, -1, (), False, True, None, ())

if 'PromotionPrx' not in _M_DeliveryService.__dict__:
    _M_DeliveryService.PromotionPrx = Ice.createTempClass()
    class PromotionPrx(Ice.ObjectPrx):

        def CountTips(self, delivery, context=None):
            return _M_DeliveryService.Promotion._op_CountTips.invoke(self, ((delivery, ), context))

        def CountTipsAsync(self, delivery, context=None):
            return _M_DeliveryService.Promotion._op_CountTips.invokeAsync(self, ((delivery, ), context))

        def begin_CountTips(self, delivery, _response=None, _ex=None, _sent=None, context=None):
            return _M_DeliveryService.Promotion._op_CountTips.begin(self, ((delivery, ), _response, _ex, _sent, context))

        def end_CountTips(self, _r):
            return _M_DeliveryService.Promotion._op_CountTips.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_DeliveryService.PromotionPrx.ice_checkedCast(proxy, '::DeliveryService::Promotion', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_DeliveryService.PromotionPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::DeliveryService::Promotion'
    _M_DeliveryService._t_PromotionPrx = IcePy.defineProxy('::DeliveryService::Promotion', PromotionPrx)

    _M_DeliveryService.PromotionPrx = PromotionPrx
    del PromotionPrx

    _M_DeliveryService.Promotion = Ice.createTempClass()
    class Promotion(Ice.Object):

        def ice_ids(self, current=None):
            return ('::DeliveryService::Promotion', '::Ice::Object')

        def ice_id(self, current=None):
            return '::DeliveryService::Promotion'

        @staticmethod
        def ice_staticId():
            return '::DeliveryService::Promotion'

        def CountTips(self, delivery, current=None):
            raise NotImplementedError("servant method 'CountTips' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_DeliveryService._t_PromotionDisp)

        __repr__ = __str__

    _M_DeliveryService._t_PromotionDisp = IcePy.defineClass('::DeliveryService::Promotion', Promotion, (), None, ())
    Promotion._ice_type = _M_DeliveryService._t_PromotionDisp

    Promotion._op_CountTips = IcePy.Operation('CountTips', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), _M_DeliveryService._t_Delivery, False, 0),), (), ((), IcePy._t_double, False, 0), ())

    _M_DeliveryService.Promotion = Promotion
    del Promotion

# End of module DeliveryService
