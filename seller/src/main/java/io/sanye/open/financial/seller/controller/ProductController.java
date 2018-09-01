package io.sanye.open.financial.seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sanye.open.financial.entity.Product;
import io.sanye.open.financial.seller.service.ProductRpcService;

/**
 * 产品相关.
 *
 * @author jiawei zhang
 * 2018/9/1 下午10:33
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRpcService productRpcService;

    @RequestMapping("/{id}")
    public Product findOne(@PathVariable String id) {
        return productRpcService.findOne(id);
    }


}
