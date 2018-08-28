package io.sanye.open.financial.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.sanye.open.financial.entity.Product;

/**
 * 产品管理.
 *
 * @author jiawei zhang
 * 2018/8/27 下午11:28
 */
public interface ProductRepository extends JpaRepository<Product, String> {
}
