package com.yunquanlai.admin.product.service;

import com.yunquanlai.admin.product.entity.ProductBrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品品牌信息表
 *
 * @author liyanjun
 * @email
 * @date 2018-05-30 23:09:11
 */
public interface ProductBrandService {

    ProductBrandEntity queryObject(Long id);

    List<ProductBrandEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(ProductBrandEntity productBrand);

    void update(ProductBrandEntity productBrand);

    void delete(Long id);

    void deleteBatch(Long[] ids);

    /**
     * 查询所有品牌信息
     *
     * @return 所有品牌信息
     */
    List<ProductBrandEntity> queryAll();
}
