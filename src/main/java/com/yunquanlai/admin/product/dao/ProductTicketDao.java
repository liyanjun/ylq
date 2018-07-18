package com.yunquanlai.admin.product.dao;

import com.yunquanlai.admin.product.entity.ProductTicketEntity;
import com.yunquanlai.admin.system.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * 商品水票信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-07-17 12:11:07
 */
public interface ProductTicketDao extends BaseDao<ProductTicketEntity> {
    ProductTicketEntity queryObjectByProductTicketNum(String productTicketNum);
    List<ProductTicketEntity> queryListByProductId(Map<String, Object> map);

}
