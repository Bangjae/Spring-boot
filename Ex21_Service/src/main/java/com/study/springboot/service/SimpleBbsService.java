package com.study.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.dao.ISimpleBbsDao;
import com.study.springboot.dto.SimpleBbsDto;

@Service
public class SimpleBbsService implements ISimpleBbsService
{
	@Autowired
	ISimpleBbsDao dao;
	
	// 원래 여러개가 있어야함. 지금은 한개만,  서비스의 역할은 없음
	@Override
	public List<SimpleBbsDto> list()
	{
		return dao.listDao();
	}
	
	// 원래 여러개가 있어야함. 지금은 한개만,  서비스의 역할은 없음
	@Override
	public SimpleBbsDto view(String id)
	{
		return dao.viewDao(id);
	}
			
	@Override
	public int write(Map<String, String> map)
	{
		int nResult = dao.writeDao(map);
		return nResult;
	}
	
	@Override
	public int delete(String id)
	{
		int nResult = dao.deleteDao(id);
		return nResult;
	}
	
	@Override
	public int count()
	{
		int nTotalCount = dao.articleCount();
		return nTotalCount;
	}

}
