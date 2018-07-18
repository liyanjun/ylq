package com.yunquanlai.admin.product.service.impl;

import com.yunquanlai.admin.product.dao.ProductTicketDao;
import com.yunquanlai.admin.product.entity.ProductTicketEntity;
import com.yunquanlai.admin.product.service.ProductTicketService;
import com.yunquanlai.api.comsumer.vo.ProductTicketVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("productTicketService")
@Transactional(rollbackFor = Exception.class)
public class ProductTicketServiceImpl implements ProductTicketService {
	@Autowired
	private ProductTicketDao productTicketDao;
	
	@Override
	public ProductTicketEntity queryObject(Long id){
		return productTicketDao.queryObject(id,false);
	}

    @Override
    public ProductTicketEntity queryObjectByProductTicketNum(String productTicketNum) {
        return productTicketDao.queryObjectByProductTicketNum(productTicketNum);
    }

    @Override
	public List<ProductTicketEntity> queryList(Map<String, Object> map){
		return productTicketDao.queryList(map);
	}

	@Override
	public List<ProductTicketEntity> queryListByProductId(Long productId) {
		return productTicketDao.queryListByProductId(productId);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return productTicketDao.queryTotal(map);
	}
	
	@Override
	public void save(ProductTicketEntity productTicket){
		productTicketDao.save(productTicket);
	}
	
	@Override
	public void update(ProductTicketEntity productTicket){
		productTicketDao.update(productTicket);
	}
	
	@Override
	public void delete(Long id){
		productTicketDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		productTicketDao.deleteBatch(ids);
	}

	@Override
	public List<ProductTicketVO> queryListForClient(Map map) {
		return productTicketDao.queryListForClient(map);
	}

}
