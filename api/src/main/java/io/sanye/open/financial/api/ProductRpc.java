package io.sanye.open.financial.api;

import java.util.List;

import com.googlecode.jsonrpc4j.JsonRpcService;
import io.sanye.open.financial.api.domain.ProductRpcReq;
import io.sanye.open.financial.entity.Product;

/**
 * 产品相关的rpc服务.
 *
 * @author jiawei zhang
 */
@JsonRpcService("rpc/products")
public interface ProductRpc {

    /**
     * 查询多个产品.
     */
    List<Product> query(ProductRpcReq req);

    /**
     * 查询单个产品.
     * @param id 产品ID
     * @return
     */
    Product findOne(String id);
}
