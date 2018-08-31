package io.sanye.open.financial.seller.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.sanye.open.financial.api.ProductRpc;
import io.sanye.open.financial.api.domain.ProductRpcReq;
import io.sanye.open.financial.entity.Product;
import io.sanye.open.financial.entity.enums.ProductStatus;
import springfox.documentation.builders.RequestHandlerSelectors;

/**
 * desc.
 *
 * @author jiawei zhang
 * 2018/8/31 上午11:26
 */
@Service
public class ProductRpcService {
    private static Logger LOG = LoggerFactory.getLogger(ProductRpcService.class);


    @Autowired
    private ProductRpc productRpc;

    /**
     * 查询全部产品.
     *
     * @return
     */
    public List<Product> findAll() {

        ProductRpcReq req = new ProductRpcReq();
//        List<String> status = new ArrayList<>();
//        status.add(ProductStatus.IN_SELL.name());
//        req.setStatusList(status);

        LOG.info("rpc查询全部产品，请求:{}", req);
        List<Product> result = productRpc.query(req);
        LOG.info("rpc查询全部产品，结果:{}", result);

        return null;
    }

    @PostConstruct
    public void testFindAll() {
        findAll();
    }

}
