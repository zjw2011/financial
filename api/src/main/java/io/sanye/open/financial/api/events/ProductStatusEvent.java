package io.sanye.open.financial.api.events;

import java.io.Serializable;

import io.sanye.open.financial.entity.enums.ProductStatus;

/**
 * 产品状态事件.
 *
 * @author jiawei zhang
 * 2018/9/1 下午11:31
 */
public class ProductStatusEvent implements Serializable {
    private static final long serialVersionUID = -4734984484308155445L;

    private String id;

    private ProductStatus productStatus;

    public ProductStatusEvent() {
    }

    public ProductStatusEvent(String id, ProductStatus productStatus) {
        this.id = id;
        this.productStatus = productStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return "ProductStatusEvent{" +
                "id='" + id + '\'' +
                ", productStatus=" + productStatus +
                '}';
    }
}
