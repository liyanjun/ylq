package com.yunquanlai.admin.user.service.impl;

import com.yunquanlai.admin.user.dao.UserInfoDao;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.service.UserInfoService;
import com.yunquanlai.utils.RRException;
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
    public UserWithdrawDepositEntity queryObject(Long id) {
        return userWithdrawDepositDao.queryObject(id, false);
    }

    @Override
    public List<UserWithdrawDepositEntity> queryList(Map<String, Object> map) {
        return userWithdrawDepositDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userWithdrawDepositDao.queryTotal(map);
    }

    @Override
    public void save(UserWithdrawDepositEntity userWithdrawDeposit) {
        userWithdrawDepositDao.save(userWithdrawDeposit);
    }

    @Override
    public void saveDepositoryWithdraw(UserWithdrawDepositEntity userWithdrawDeposit, UserInfoEntity userInfoEntity) {
        BigDecimal disableDepositAmount = userInfoEntity.getDisableDepositAmount();
        BigDecimal enableDepositAmount = userInfoEntity.getEnableDepositAmount();
        //可用押金金额>0
        if (enableDepositAmount.compareTo(new BigDecimal(0)) == 1) {
            //提交押金提现申请时，可用押金设为提取金额
            userWithdrawDeposit.setWithdrawAmount(enableDepositAmount);
            //冻结全部押金
            disableDepositAmount = disableDepositAmount.add(enableDepositAmount);
            enableDepositAmount = enableDepositAmount.subtract(enableDepositAmount);
            userInfoEntity.setDisableDepositAmount(disableDepositAmount);
            userInfoEntity.setEnableDepositAmount(enableDepositAmount);
            userInfoService.update(userInfoEntity);
            userWithdrawDepositDao.save(userWithdrawDeposit);
        } else {
            throw new RRException("您当前无可提现押金");
        }

    }

    @Override
    public void update(UserWithdrawDepositEntity userWithdrawDeposit) {
        userWithdrawDepositDao.update(userWithdrawDeposit);
    }

    @Override
    public void delete(Long id) {
        userWithdrawDepositDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        userWithdrawDepositDao.deleteBatch(ids);
    }

    @Override
    public UserWithdrawDepositEntity queryObjectByUserId(Long id) {
        return userWithdrawDepositDao.queryObjectByUserId(id);
    }

    @Override
    public void handleDepositoryWithdraw(UserWithdrawDepositEntity userWithdrawDepositEntity) {
        Long userInfoId = userWithdrawDepositEntity.getUserInfoId();
        UserInfoEntity userInfoEntity = userInfoService.queryObject(userInfoId);
        BigDecimal depositAmount = userInfoEntity.getDepositAmount();
        BigDecimal disableDepositAmount = userInfoEntity.getDisableDepositAmount();
        BigDecimal withdrawAmount = userWithdrawDepositEntity.getWithdrawAmount();
        //处理押金提现申请时，全部押金/冻结押金减去提现金额
        userInfoEntity.setDisableDepositAmount(disableDepositAmount.subtract(withdrawAmount));
        userInfoEntity.setDepositAmount(depositAmount.subtract(withdrawAmount));
        userWithdrawDepositEntity.setWithdrawAmount(withdrawAmount.subtract(withdrawAmount));
        userInfoService.update(userInfoEntity);
        userWithdrawDepositDao.update(userWithdrawDepositEntity);
    }

}
