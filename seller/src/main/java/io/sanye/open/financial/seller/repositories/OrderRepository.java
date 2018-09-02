package io.sanye.open.financial.seller.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.sanye.open.financial.entity.Order;

/**
 * 产品管理.
 *
 * @author jiawei zhang
 */
public interface OrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {
}
