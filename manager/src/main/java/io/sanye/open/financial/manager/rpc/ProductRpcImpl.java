package io.sanye.open.financial.manager.rpc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import io.sanye.open.financial.api.ProductRpc;
import io.sanye.open.financial.api.domain.ProductRpcReq;
import io.sanye.open.financial.entity.Product;
import io.sanye.open.financial.manager.service.ProductService;

/**
 * desc.
 *
 * @author jiawei zhang
 * 2018/8/31 上午11:11
 */
@AutoJsonRpcServiceImpl
@Service
public class ProductRpcImpl implements ProductRpc {

    private static Logger LOG = LoggerFactory.getLogger(ProductRpcImpl.class);

    @Autowired
    private ProductService productService;

    @Override
    public List<Product> query(ProductRpcReq req) {
        LOG.info("查询多个产品,请求:", req);

        Pageable pageable = new PageRequest(0, 1000, Sort.Direction.DESC, "rewardRate");

        Page<Product> result = productService.query(req.getIds(), req.getMinRewrdRate(), req.getMaxRewrdRate(), req.getStatusList(), pageable);

        LOG.info("查询多个产品,结果:{}", result);
        return result.getContent();
    }
}
