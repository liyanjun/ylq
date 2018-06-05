package com.yunquanlai.admin.user.service;


import com.yunquanlai.admin.user.entity.UserAddressEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户地址信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 15:44:08
 */
public interface UserAddressService {
	
	UserAddressEntity queryObject(Long id);
	
	List<UserAddressEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserAddressEntity userAddress);
	
	void update(UserAddressEntity userAddress);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

    List<UserAddressEntity> queryByUserId(Long id);
}
