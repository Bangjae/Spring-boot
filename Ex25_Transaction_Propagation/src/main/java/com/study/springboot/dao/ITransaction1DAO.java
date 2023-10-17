package com.study.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ITransaction1DAO {
	public void pay(String consumerId, int amount);
}
