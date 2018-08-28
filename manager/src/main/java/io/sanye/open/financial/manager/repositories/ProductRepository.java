package io.sanye.open.financial.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.sanye.open.financial.entity.Product;

/**
 * 产品管理.
 *
 * @author jiawei zhang
 */
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
}
