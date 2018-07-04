package com.yunquanlai.admin.product.entity;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 商品信息表表
 *
 * @author liyanjun
 * @email
 * @date 2018-05-30 22:26:24
 */
public class ProductInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private ProductInfoEntity productInfoEntity;

    private ProductDetailEntity productDetailEntity;

    private String banner1;

    private String banner2;

    private String banner3;

    private String banner4;

    /**
     * 商品库存
     */
    private Integer stock;

    public ProductInfoVO() {
        productInfoEntity = new ProductInfoEntity();
        productDetailEntity = new ProductDetailEntity();
    }

    public ProductInfoVO(ProductInfoEntity productInfoEntity, ProductDetailEntity productDetailEntity) {
        this.productInfoEntity = productInfoEntity;
        this.productDetailEntity = productDetailEntity;
        String[] banners = productDetailEntity.getBanner().split(",");
        if (banners.length > 0) {
            banner1 = "null".equals(banners[0]) ? "" : banners[0];
        }
        if (banners.length > 1) {
            banner2 = "null".equals(banners[1]) ? "" : banners[1];
        }
        if (banners.length > 2) {
            banner3 = "null".equals(banners[2]) ? "" : banners[2];
        }
        if (banners.length > 3) {
            banner4 = "null".equals(banners[3]) ? "" : banners[3];
        }
    }

    /**
     * 设置：主键ID
     */
    public void setId(Long id) {
        productInfoEntity.setId(id);
    }

    /**
     * 获取：主键ID
     */
    public Long getId() {
        return productInfoEntity.getId();
    }

    /**
     * 设置：商品名称
     */
    public void setName(String name) {
        productInfoEntity.setName(name);
    }

    /**
     * 获取：商品名称
     */
    public String getName() {
        return productInfoEntity.getName();
    }

    /**
     * 商品主图
     *
     * @return
     */
    public String getImg() {
        return productInfoEntity.getImg();
    }

    /**
     * 商品主图
     *
     * @return
     */
    public void setImg(String img) {
        productInfoEntity.setImg(img);
    }

    /**
     * 设置：标价
     */
    public void setAmountShow(BigDecimal amountShow) {
        productInfoEntity.setAmountShow(amountShow);
    }

    /**
     * 获取：标价
     */
    public BigDecimal getAmountShow() {
        return productInfoEntity.getAmountShow();
    }

    /**
     * 设置：实价
     */
    public void setAmount(BigDecimal amount) {
        productInfoEntity.setAmount(amount);
    }

    /**
     * 获取：实价
     */
    public BigDecimal getAmount() {
        return productInfoEntity.getAmount();
    }

    /**
     * 设置：商品品牌 ID
     */
    public void setBrandId(Long brandId) {
        productInfoEntity.setBrandId(brandId);
    }

    /**
     * 获取：商品品牌 ID
     */
    public Long getBrandId() {
        return productInfoEntity.getBrandId();
    }

    /**
     * 设置：商品品牌
     */
    public void setBrandName(String brandName) {
        productInfoEntity.setBrandName(brandName);
    }

    /**
     * 获取：商品品牌
     */
    public String getBrandName() {
        return productInfoEntity.getBrandName();
    }

    /**
     * 设置：桶类型，10：一次性桶，20：可回收桶
     */
    public void setBucketType(Integer bucketType) {
        productInfoEntity.setBucketType(bucketType);
    }

    /**
     * 获取：桶类型，10：一次性桶，20：可回收桶
     */
    public Integer getBucketType() {
        return productInfoEntity.getBucketType();
    }

    /**
     * 设置：配送费
     */
    public void setDeliveryFee(BigDecimal deliveryFee) {
        productInfoEntity.setDeliveryFee(deliveryFee);
    }

    /**
     * 获取：配送费
     */
    public BigDecimal getDeliveryFee() {
        return productInfoEntity.getDeliveryFee();
    }

    public String getContent() {
        return productDetailEntity.getContent();
    }

    public void setContent(String content) {
        productDetailEntity.setContent(content);
    }

    public ProductInfoEntity getProductInfoEntity() {
        return productInfoEntity;
    }

    public void setProductInfoEntity(ProductInfoEntity productInfoEntity) {
        this.productInfoEntity = productInfoEntity;
    }

    public ProductDetailEntity getProductDetailEntity() {
        productDetailEntity.setBanner(banner1 + "," + banner2 + "," + banner3 + "," + banner4);
        return productDetailEntity;
    }

//    public void setProductDetailEntity(ProductDetailEntity productDetailEntity) {
//        String[] banners = productDetailEntity.getBanner().split(",");
//        banner1 = banners[0];
//        banner2 = banners[1];
//        banner3 = banners[2];
//        banner4 = banners[3];
//        this.productDetailEntity = productDetailEntity;
//    }

    public String getBanner1() {
        return banner1;
    }

    public void setBanner1(String banner1) {
        this.banner1 = banner1;
    }

    public String getBanner2() {
        return banner2;
    }

    public void setBanner2(String banner2) {
        this.banner2 = banner2;
    }

    public String getBanner3() {
        return banner3;
    }

    public void setBanner3(String banner3) {
        this.banner3 = banner3;
    }

    public String getBanner4() {
        return banner4;
    }

    public void setBanner4(String banner4) {
        this.banner4 = banner4;
    }

    public Integer getIsQuick() {
        return productInfoEntity.getIsQuick();
    }

    public void setIsQuick(Integer isQuick) {
        productInfoEntity.setIsQuick(isQuick);
    }

    public Integer getSort() {
        return productInfoEntity.getSort();
    }

    public void setSort(Integer sort) {
        productInfoEntity.setSort(sort);
    }

    public String getProductNum() {
        return productInfoEntity.getProductNum();
    }

    public void setProductNum(String productNum) {
        productInfoEntity.setProductNum(productNum);
    }

    public BigDecimal getAverageLevel() {
        return productDetailEntity.getAverageLevel();
    }

    public void setAverageLevel(BigDecimal averageLevel) {
        productDetailEntity.setAverageLevel(averageLevel);
    }

    public String getProductSpecifications() {
        return productInfoEntity.getProductSpecifications();
    }

    public void setProductSpecifications(String productSpecifications) {
        productInfoEntity.setProductSpecifications(productSpecifications);
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getDrinkingWaterType() {
        return productInfoEntity.getDrinkingWaterType();
    }

    public void setDrinkingWaterType(Integer drinkingWaterType) {
        productInfoEntity.setDrinkingWaterType(drinkingWaterType);
    }
}
