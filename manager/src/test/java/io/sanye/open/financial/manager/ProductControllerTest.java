package io.sanye.open.financial.manager;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import io.sanye.open.financial.entity.Product;
import io.sanye.open.financial.entity.enums.ProductStatus;
import io.sanye.open.financial.util.RestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 产品控制层测试.
 *
 * @author jiawei zhang
 * 2018/8/28 下午3:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Value("http://localhost:${local.server.port}/manager")
    private String baseUrl;

    private static RestTemplate rest = new RestTemplate();

    //正常产品数据
    private static List<Product> normals = new ArrayList<>();

    //异常产品数据
    private static List<Product> exceptions = new ArrayList<>();

    @BeforeClass
    public static void init() {
        Product p1 = new Product("T001", "灵活宝1号", ProductStatus.AUDITING.name(),
                new BigDecimal(10), new BigDecimal(1), new BigDecimal(3.42));
        Product p2 = new Product("T002", "活期盈-金色人生", ProductStatus.AUDITING.name(),
                new BigDecimal(10), new BigDecimal(0), new BigDecimal(3.28));
        Product p3 = new Product("T003", "朝朝盈-聚财", ProductStatus.AUDITING.name(),
                new BigDecimal(100), new BigDecimal(10), new BigDecimal(3.86));
        normals.add(p1);
        normals.add(p2);
        normals.add(p3);

        Product e1 = new Product(null, "编号不可为空", ProductStatus.AUDITING.name(),
                BigDecimal.valueOf(10), BigDecimal.valueOf(1), BigDecimal.valueOf(2.34));
        Product e2 = new Product("E002", "收益率范围错误", ProductStatus.AUDITING.name(),
                BigDecimal.ZERO, BigDecimal.valueOf(1), BigDecimal.valueOf(31));
        Product e3 = new Product("E003", "投资步长需为整数", ProductStatus.AUDITING.name(),
                BigDecimal.ZERO, BigDecimal.valueOf(1.01), BigDecimal.valueOf(3.44));

        exceptions.add(e1);
        exceptions.add(e2);
        exceptions.add(e3);

        ResponseErrorHandler errorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {

            }
        };
        rest.setErrorHandler(errorHandler);
    }

    @Test
    public void create() {
        normals.forEach(product -> {
            Product result = RestUtil.postJSON(rest, baseUrl + "/products", product, Product.class);
            Assert.assertNotNull("插入失败", product);
        });
    }

    @Test
    public void createException() {
        exceptions.forEach(product -> {
            Map<String, String> result = RestUtil.postJSON(rest, baseUrl + "/products", product, HashMap.class);
            Assert.assertTrue("插入成功", result.get("message").equals(product.getName()));
        });
    }

    @Test
    public void findOne() {
        normals.forEach(product -> {
            Product result = rest.getForObject(baseUrl + "/products/" + product.getId(), Product.class);
            Assert.assertTrue("查询失败", product.getId().equals(result.getId()));
        });

        exceptions.forEach(product -> {
            Product result = rest.getForObject(baseUrl + "/products/" + product.getId(), Product.class);
            Assert.assertTrue("查询失败", result == null);
        });
    }

    @Test
    public void query() {
        List<Product> products= rest.
                getForObject(baseUrl + "/products?ids={ids}&minRewardRate={minRewardRate}&maxRewardRate={maxRewardRate}&status={status}",
                        List.class, "", 1, 100, "");
        //数组的解析 JSON
        Assert.assertTrue("查询失败", products.size() == 3);

    }

}
