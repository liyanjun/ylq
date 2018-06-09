package com.yunquanlai.admin.product.service.impl;

import com.yunquanlai.admin.product.dao.ProductBrandDao;
import com.yunquanlai.admin.product.dao.ProductInfoDao;
import com.yunquanlai.admin.product.entity.ProductBrandEntity;
import com.yunquanlai.admin.product.entity.ProductInfoEntity;
import com.yunquanlai.admin.product.service.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void save(ProductBrandEntity productBrand) {
        productBrandDao.save(productBrand);
    }

    @Override
    public void update(ProductBrandEntity productBrand) {
        ProductInfoEntity productInfoEntity = new ProductInfoEntity();
        productInfoEntity.setBrandName(productBrand.getName());
        productInfoEntity.setBrandId(productBrand.getId());
        productInfoDao.updateBrandName(productInfoEntity);
        productBrandDao.update(productBrand);
    }

    @Override
    public void delete(Long id) {
        productBrandDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        productBrandDao.deleteBatch(ids);
    }

    @Override
    public List<ProductBrandEntity> queryAll() {
        return productBrandDao.queryList(null);
    }

}
