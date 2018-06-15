package com.yunquanlai.admin.product.service.impl;

import com.yunquanlai.admin.product.dao.ProductBrandDao;
import com.yunquanlai.admin.product.dao.ProductInfoDao;
import com.yunquanlai.admin.product.entity.ProductBrandEntity;
import com.yunquanlai.admin.product.entity.ProductInfoEntity;
import com.yunquanlai.admin.product.service.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("productBrandService")
public class ProductBrandServiceImpl implements ProductBrandService {
    @Autowired
    private ProductBrandDao productBrandDao;
    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    @Cacheable(value = "productBrand",key = "#id")
    public ProductBrandEntity queryObject(Long id) {
        return productBrandDao.queryObject(id, false);
    }

    @Override
    public List<ProductBrandEntity> queryList(Map<String, Object> map) {
        return productBrandDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return productBrandDao.queryTotal(map);
    }

    @Override
    @CacheEvict(value = "productBrand")
    public void save(ProductBrandEntity productBrand) {
        productBrandDao.save(productBrand);
    }

    @Override
    @CacheEvict(value = "productBrand")
    public void update(ProductBrandEntity productBrand) {
        ProductInfoEntity productInfoEntity = new ProductInfoEntity();
        productInfoEntity.setBrandName(productBrand.getName());
        productInfoEntity.setBrandId(productBrand.getId());
        productInfoDao.updateBrandName(productInfoEntity);
        productBrandDao.update(productBrand);
    }

    @Override
    @CacheEvict(value = "productBrand")
    public void delete(Long id) {
        productBrandDao.delete(id);
    }

    @Override
    @CacheEvict(value = "productBrand")
    public void deleteBatch(Long[] ids) {
        productBrandDao.deleteBatch(ids);
    }

    @Override
    @Cacheable(value = "productBrand")
    public List<ProductBrandEntity> queryAll() {
        return productBrandDao.queryList(null);
    }

}
