package com.yunquanlai.admin.system.service.impl;

import com.yunquanlai.admin.system.dao.TokenDao;
import com.yunquanlai.admin.system.entity.TokenEntity;
import com.yunquanlai.admin.system.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service("tokenService")
public class TokenServiceImpl implements TokenService {
	@Autowired
	private TokenDao tokenDao;


	@Override
	public TokenEntity queryByUserId(Long userId) {
		return tokenDao.queryByUserId(userId);
	}

	@Override
	@Cacheable(value="tokens", key="#token")
	public TokenEntity queryByToken(String token) {
		return tokenDao.queryByToken(token);
	}

	@Override
	public void save(TokenEntity token){
		tokenDao.save(token);
	}
	
	@Override
	@CacheEvict(value = "tokens", key = "#oldToken")
	public void update(TokenEntity token,String oldToken){
		tokenDao.update(token);
	}

}
