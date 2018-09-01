package io.sanye.open.financial.seller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import io.sanye.open.financial.api.ProductRpc;
import io.sanye.open.financial.api.domain.ProductRpcReq;
import io.sanye.open.financial.entity.Product;

/**
 * desc.
 *
 * @author jiawei zhang
 * 2018/9/1 下午10:41
 */
@Component
public class ProductCache {
    private static final String CACHE_NAME = "imooc_product";

    private static Logger LOG = LoggerFactory.getLogger(ProductRpcService.class);

    @Autowired
    private ProductRpc productRpc;

    @Autowired
    private HazelcastInstance hazelcastInstance;


    @Cacheable(cacheNames = CACHE_NAME)
    public Product readCache(String id) {
        LOG.info("rpc查询单一产品，请求ID:{}", id);
        Product result = productRpc.findOne(id);
        LOG.info("rpc查询单一产品，结果:{}", result);

        return result;
    }

    @CachePut(cacheNames = CACHE_NAME, key = "#product.id")
    public Product putCache(Product product) {
        return product;
    }

    @CacheEvict(cacheNames = CACHE_NAME)
    public void removeCache(String id) {

    }

    public List<Product> readAllCache() {
        Map map = hazelcastInstance.getMap(CACHE_NAME);
        List<Product> products = null;
        if (map.size() > 0) {
            products = new ArrayList<>();
            products.addAll(map.values());
        } else {
            products = findAll();
        }
        return products;
    }

    public List<Product> findAll() {
        ProductRpcReq req = new ProductRpcReq();
//        List<String> status = new ArrayList<>();
//        status.add(ProductStatus.IN_SELL.name());
//        req.setStatusList(status);

        LOG.info("rpc查询全部产品，请求:{}", req);
        List<Product> result = productRpc.query(req);
        LOG.info("rpc查询全部产品，结果:{}", result);

        return result;
    }
}
