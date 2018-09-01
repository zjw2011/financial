package io.sanye.open.financial.seller.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import io.sanye.open.financial.api.ProductRpc;
import io.sanye.open.financial.api.domain.ProductRpcReq;
import io.sanye.open.financial.entity.Product;

/**
 * desc.
 *
 * @author jiawei zhang
 * 2018/8/31 上午11:26
 */
@Service
public class ProductRpcService implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger LOG = LoggerFactory.getLogger(ProductRpcService.class);


    @Autowired
    private ProductCache productCache;

    /**
     * 查询全部产品.
     *
     * @return
     */
    public List<Product> findAll() {

        return productCache.readAllCache();
    }

    //@PostConstruct
    public void testFindAll() {
        findAll();
    }

    public Product findOne(String id) {
        Product product = productCache.readCache(id);
        if (product == null) {
            productCache.removeCache(id);
        }
        return product;
    }

    //@PostConstruct
    public void testFindOne() {
        findOne("T001");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Product> products = findAll();
        products.forEach(product -> {
            productCache.putCache(product);
        });
    }
}
