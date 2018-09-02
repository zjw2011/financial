package io.sanye.open.financial.seller.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.sanye.open.financial.entity.Order;
import io.sanye.open.financial.seller.params.OrderParam;
import io.sanye.open.financial.seller.service.OrderService;
import io.sanye.open.financial.seller.service.ProductRpcService;

/**
 * 订单相关.
 *
 * @author jiawei zhang
 * 2018/9/2 下午7:12
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static Logger LOG = LoggerFactory.getLogger(ProductRpcService.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public Order apply(@RequestHeader String authId,@RequestHeader String sign, @RequestBody OrderParam param) {
        LOG.info("申购请求:{}", param);
        Order order = new Order();
        BeanUtils.copyProperties(param, order);
        Order result = orderService.apply(order);
        LOG.info("申购结果:{}", result);
        return result;
    }
}
