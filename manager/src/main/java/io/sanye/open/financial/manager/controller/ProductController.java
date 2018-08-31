package io.sanye.open.financial.manager.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.sanye.open.financial.entity.Product;
import io.sanye.open.financial.manager.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * desc.
 *
 * @author jiawei zhang
 * 2018/8/28 下午3:20
 */
@RestController
@RequestMapping("/products")
@Api(tags = "产品",description = "增删改产品")
public class ProductController {

    private static Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "创建产品", notes = "根据对应业务规则添加响应的产品")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        LOG.info("创建产品, 参数:{}", product);

        Product result = productService.addProduct(product);

        LOG.info("创建产品, 结果:{}", result);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findOne(@PathVariable String id) {
        LOG.info("查询单个产品, id={}", id);
        Product product = productService.findOne(id);
        LOG.info("查询单个产品, 结果={}", product);
        return product;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Product> query(String ids,
                               BigDecimal minRewardRate, BigDecimal maxRewardRate,
                               String status,
                               @RequestParam(defaultValue = "0") int pageNum,
                               @RequestParam(defaultValue = "20") int pageSize) {
        LOG.info("查询产品,ids={},minRewardRate={},maxRewardRate={},status={},pageNum={},pageSize={}",
                ids, minRewardRate, maxRewardRate, status, pageNum, pageSize);
        List<String> idList = null, statusList = null;
        if (!StringUtils.isEmpty(ids)) {
            idList = Arrays.asList(ids.split(","));
        }
        if (!StringUtils.isEmpty(status)) {
            statusList = Arrays.asList(status.split(","));
        }

        Pageable pageable = new PageRequest(pageNum, pageSize);

        Page<Product> page = productService.query(idList, minRewardRate, maxRewardRate, statusList, pageable);

        LOG.info("查询产品,结果={}", page.getTotalElements());
        return page.getContent();
    }

}
