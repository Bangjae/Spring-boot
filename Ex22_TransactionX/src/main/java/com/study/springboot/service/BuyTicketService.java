package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.dao.ITransaction1DAO;
import com.study.springboot.dao.ITransaction2DAO;

@Service
public class BuyTicketService implements IBuyTicketService
{
	// new(생성자)는 사용하지않고 자동으로 대입
	@Autowired // 자동 주입을 받아 변수를 만든다.
	ITransaction1DAO transaction1;
	
	@Autowired
	ITransaction2DAO transaction2;
	
	@Override
	public int buy(String consumerId, int amount, String error)
	{
		/*
		  상황 : 현장에서는 표가 발행 되었는데 전산에 등록이 
		  		안될 때...
		*/
		try 
		{
			transaction1.pay(consumerId, amount); // 현장 판매처 상황
			
			//의도적 에러 발생
			if(error.equals("1")) 
			{int n = 10 / 0; }
			
			
			transaction2.pay(consumerId, amount); // 회계장부 상황
			/*
				에러1이 들어오면 이 테이블에는 저장이 안된다.
				이런일이 생기면 안되므로 트랜잭션이 필요함.
				
				transaction1부터 저장이 안되고 롤백을 해야한다.
				
			*/
			return 1;
		}
		catch(Exception e)
		{
			return 0;
		}
	}
}
