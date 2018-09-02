package io.sanye.open.financial.seller.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import io.sanye.open.financial.entity.Order;
import io.sanye.open.financial.entity.Product;
import io.sanye.open.financial.entity.enums.OrderStatus;
import io.sanye.open.financial.entity.enums.OrderType;
import io.sanye.open.financial.seller.repositories.OrderRepository;

/**
 * 订单服务.
 *
 * @author jiawei zhang
 * 2018/9/2 下午6:47
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRpcService productRpcService;

    /**
     * 申购订单.
     * @param order 原始订单数据
     * @return
     */
    public Order apply(Order order) {

        //数据校验
        checkOrder(order);

        //完善订单数据
        completeOrder(order);

        return orderRepository.saveAndFlush(order);
    }

    /**
     * 完善订单数据.
     */
    private void completeOrder(Order order) {
        order.setOrderId(UUID.randomUUID().toString().replace("-", ""));
        order.setOrderType(OrderType.APPLY.name());
        order.setOrderStatus(OrderStatus.SUCCESS.name());
        order.setUpdateAt(new Date());
    }

    /**
     * 校验数据.
     */
    private void checkOrder(Order order) {
        //必填字段
        Assert.notNull(order.getOuterOrderId(), "需要外部订单编号");
        Assert.notNull(order.getChanId(), "需要渠道编号");
        Assert.notNull(order.getChanUserId(), "需要用户编号");
        Assert.notNull(order.getProductId(), "需要产品编号");
        Assert.notNull(order.getAmount(), "需要购买金额");
        Assert.notNull(order.getCreateAt(), "需要订单时间");
        //产品是否存在及金额是否符合要求
        Product product = productRpcService.findOne(order.getProductId());
        Assert.notNull(product, "产品不存在");
        //金额要满足如果有起投金额，要大于等于起投金额，如果有投资步长时，超过起投部分要是投资步长的整数倍
        if (product.getThresholdAmount() != null) {
            Assert.isTrue(order.getAmount()
                    .compareTo(product.getThresholdAmount()) >= 0, "购买金额小于起投金额");
        } else if (product.getStepAmount() != null) {
            Assert.isTrue(order.getAmount()
                    .subtract(product.getThresholdAmount())
                    .divide(product.getStepAmount())
                    .intValue() == 0, "超过起投的金额不是投资步长的整数倍");
        }
    }

}
