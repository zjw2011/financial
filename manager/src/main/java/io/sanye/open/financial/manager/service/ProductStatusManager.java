package io.sanye.open.financial.manager.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import io.sanye.open.financial.api.events.ProductStatusEvent;
import io.sanye.open.financial.entity.enums.ProductStatus;

/**
 * 产品状态管理.
 *
 * @author jiawei zhang
 * 2018/9/1 下午11:35
 */
@Component
public class ProductStatusManager {

    private static final String MQ_DESTINATION = "VirtualTopic.PRODUCT_STATUS";

    private static Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void changeStatus(String id, ProductStatus productStatus) {
        ProductStatusEvent event = new ProductStatusEvent(id, productStatus);
        LOG.info("send message: {}", event);
        jmsTemplate.convertAndSend(MQ_DESTINATION, event);
    }

    @PostConstruct
    public void init() {
        changeStatus("T001", ProductStatus.FINISHED);
    }
}
