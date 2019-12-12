package com.yunquanlai.admin.product.dao;

import com.yunquanlai.admin.product.entity.ProductTicketEntity;
import com.yunquanlai.admin.system.dao.BaseDao;
import com.yunquanlai.api.comsumer.vo.ProductTicketVO;

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

    /**
     * 根据产品 ID 查找水票
     *
     * @param queryListByProductId
     * @return
     */
    List<ProductTicketEntity> queryListByProductId(Long queryListByProductId);

    /**
     * 为小程序查找水漂列表
     *
     * @param map
     * @return
     */
    List<ProductTicketVO> queryListForClient(Map map);
}
