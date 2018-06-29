package com.yunquanlai.admin.user.service.impl;

import com.yunquanlai.admin.user.dao.UserInfoDao;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.user.dao.UserWithdrawDepositDao;
import com.yunquanlai.admin.user.entity.UserWithdrawDepositEntity;
import com.yunquanlai.admin.user.service.UserWithdrawDepositService;
import org.springframework.transaction.annotation.Transactional;


@Service("userWithdrawDepositService")
@Transactional(rollbackFor = Exception.class)
public class UserWithdrawDepositServiceImpl implements UserWithdrawDepositService {
	@Autowired
	private UserWithdrawDepositDao userWithdrawDepositDao;

	@Autowired
    private UserInfoService userInfoService;
	
	@Override
	public UserWithdrawDepositEntity queryObject(Long id){
		return userWithdrawDepositDao.queryObject(id,false);
	}
	
	@Override
	public List<UserWithdrawDepositEntity> queryList(Map<String, Object> map){
		return userWithdrawDepositDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userWithdrawDepositDao.queryTotal(map);
	}
	
	@Override
	public void save(UserWithdrawDepositEntity userWithdrawDeposit){
		userWithdrawDepositDao.save(userWithdrawDeposit);
	}

    @Override
    @Transactional
    public void saveDepositoryWithdraw(UserWithdrawDepositEntity userWithdrawDeposit, UserInfoEntity userInfoEntity) throws RuntimeException {
	    BigDecimal depositAmount = userInfoEntity.getDepositAmount();
	    //提交押金提现申请时，冻结全部押金，可用押金设为0
	    userInfoEntity.setEnableDepositAmount(new BigDecimal(0));
	    userInfoEntity.setDisableDepositAmount(depositAmount);
	    userInfoService.update(userInfoEntity);
	    userWithdrawDepositDao.save(userWithdrawDeposit);
    }

    @Override
	public void update(UserWithdrawDepositEntity userWithdrawDeposit){
		userWithdrawDepositDao.update(userWithdrawDeposit);
	}
	
	@Override
	public void delete(Long id){
		userWithdrawDepositDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		userWithdrawDepositDao.deleteBatch(ids);
	}

	@Override
	public UserWithdrawDepositEntity queryObjectByUserId(Long id) {
		return userWithdrawDepositDao.queryObjectByUserId(id);
	}

    @Override
    @Transactional
    public void handleDepositoryWithdraw(UserWithdrawDepositEntity userWithdrawDepositEntity) throws RuntimeException {
        Long userInfoId = userWithdrawDepositEntity.getUserInfoId();
        UserInfoEntity userInfoEntity = userInfoService.queryObject(userInfoId);
        //处理押金提现申请时，全部押金/冻结押金设为0
        userInfoEntity.setDisableDepositAmount(new BigDecimal(0));
        userInfoEntity.setDepositAmount(new BigDecimal(0));
        userInfoService.update(userInfoEntity);
        userWithdrawDepositDao.update(userWithdrawDepositEntity);
    }

}
