package io.sanye.open.financial.api.domain;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Sort;

/**
 * desc.
 *
 * @author jiawei zhang
 * 2018/8/31 下午1:09
 */
public class ProductRpcReq {
    private List<String> ids;

    private BigDecimal minRewrdRate;

    private BigDecimal maxRewrdRate;

    private List<String> statusList;


    @Override
    public String toString() {
        return "ProductRpcReq{" +
                "ids=" + ids +
                ", minRewrdRate=" + minRewrdRate +
                ", maxRewrdRate=" + maxRewrdRate +
                ", statusList=" + statusList +
                '}';
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public BigDecimal getMinRewrdRate() {
        return minRewrdRate;
    }

    public void setMinRewrdRate(BigDecimal minRewrdRate) {
        this.minRewrdRate = minRewrdRate;
    }

    public BigDecimal getMaxRewrdRate() {
        return maxRewrdRate;
    }

    public void setMaxRewrdRate(BigDecimal maxRewrdRate) {
        this.maxRewrdRate = maxRewrdRate;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
