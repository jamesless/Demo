package com.sfpay.airscape.server.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sfpay.airscape.server.service.UserService;
import com.sfpay.airscape.server.vo.Page;
import com.sfpay.airscape.server.vo.User;

/**
 * 
 * @author sfhq1588
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	@Override
	public List<User> queryByPage(Page page) {
		return userDao.queryByPage(page);
	}

	@Override
	public Integer count() {
		return userDao.count();
	}
	

}
