package com.yunquanlai.admin.user.service.impl;

import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.product.entity.ProductTicketEntity;
import com.yunquanlai.admin.user.dao.UserProductTicketDao;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.entity.UserProductTicketEntity;
import com.yunquanlai.admin.user.service.UserProductTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("userProductTicketService")
@Transactional(rollbackFor = Exception.class)
public class UserProductTicketServiceImpl implements UserProductTicketService {
	@Autowired
	private UserProductTicketDao userProductTicketDao;
	
	@Override
	public UserProductTicketEntity queryObject(Long id){
		return userProductTicketDao.queryObject(id,false);
	}
	
	@Override
	public List<UserProductTicketEntity> queryList(Map<String, Object> map){
		return userProductTicketDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userProductTicketDao.queryTotal(map);
	}
	
	@Override
	public void save(UserProductTicketEntity userProductTicket){
		userProductTicketDao.save(userProductTicket);
	}
	
	@Override
	public void update(UserProductTicketEntity userProductTicket){
		userProductTicketDao.update(userProductTicket);
	}
	
	@Override
	public void delete(Long id){
		userProductTicketDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		userProductTicketDao.deleteBatch(ids);
	}

	@Override
	public void buyTicket(UserInfoEntity user, ProductTicketEntity productTicket) {
		UserProductTicketEntity userProductTicket = new UserProductTicketEntity();
		userProductTicket.setUserName(user.getUsername());
		userProductTicket.setProductTicketId(productTicket.getId());
		userProductTicket.setProductId(productTicket.getProductInfoId());
		userProductTicket.setProductName(productTicket.getProductInfoName());
		userProductTicket.setProductTicketTitle(productTicket.getTitle());
		userProductTicket.setProductTicketSubtitle(productTicket.getSubtitle());
		userProductTicket.setProductTicketNum(productTicket.getProductTicketNum());
		userProductTicket.setTotalCount(productTicket.getCout());
		userProductTicket.setUseCount(0);
		userProductTicket.setRemainderCount(productTicket.getCout());
		userProductTicket.setAmount(productTicket.getAmout());
		userProductTicket.setStatus(UserProductTicketEntity.STATUS_NEW);
		userProductTicket.setFinishTime(productTicket.getDeadline());
		userProductTicket.setEndTime(productTicket.getDeadline());
		userProductTicket.setCreationTime(new Date());
		userProductTicket.setBenifitUserId(user.getRecommenderID());
		userProductTicket.setBenifitUsername(user.getRecommenderName());
		userProductTicketDao.save(userProductTicket);
	}

	@Override
	public void pay(String outTradeNo, Integer totalFee) {
		String[] ids = outTradeNo.split("-");
		Integer id = Integer.parseInt(ids[0]);
		UserProductTicketEntity userProductTicketEntity = userProductTicketDao.queryObject(id, true);
		if (userProductTicketEntity == null) {
			throw new RuntimeException("找不到有效的订单");
		}
		// 订单不是可支付状态,幂等性返回(已关闭的订单，如果收到支付完成通知，把它拉起来到已支付，然后配送，已关闭指的是不能发起支付)
		if (userProductTicketEntity.getStatus() != UserProductTicketEntity.STATUS_NEW && userProductTicketEntity.getStatus() != UserProductTicketEntity.STATUS_CLOSE) {
			return;
		}
//         TODO 上线打开，校验金额，微信返回的金额单位是分，我们先除以100
//        BigDecimal wechatBackFee = new BigDecimal(totalFee.toString()).divide(BigDecimal.TEN).divide(BigDecimal.TEN);
			// 订单不包含押金
//            if (!userProductTicketEntity.getAmount().equals(wechatBackFee)) {
//                throw new RuntimeException("支付金额不等于订单金额");
//            }
		userProductTicketEntity.setStatus(UserProductTicketEntity.STATUS_PAID);
		//TODO 支付时间，及各种结束时间
//		userProductTicketEntity.setPaidTime(new Date());
		userProductTicketDao.update(userProductTicketEntity);
	}

}
