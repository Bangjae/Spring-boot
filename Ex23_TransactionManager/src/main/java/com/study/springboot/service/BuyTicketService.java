package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.study.springboot.dao.ITransaction1DAO;
import com.study.springboot.dao.ITransaction2DAO;

@Service
public class BuyTicketService implements IBuyTicketService
{
	// jdbc 연동을 위한 자동주입
	// new(생성자)는 사용하지않고 자동으로 대입
	@Autowired // 자동 주입을 받아 변수를 만든다.
	ITransaction1DAO transaction1;
	
	@Autowired
	ITransaction2DAO transaction2;
	
	// 트랜잭션 처리를 위한 자동 주입
	@Autowired  // 스프링안에 기본적으로 제공하는 것
	PlatformTransactionManager transactionManager;

	@Autowired
	TransactionDefinition definition;
	// definition : 기본설정값을 사용하겠다는 의미(?)  
	
	@Override
	public int buy(String consumerId, int amount, String error)
	{
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try 
		{
			transaction1.pay(consumerId, amount); // 현장 판매처 상황
			
			//의도적 에러 발생
			if(error.equals("1")) {int n = 10 / 0; }
			
			
			transaction2.pay(consumerId, amount); // 회계장부 상황
			/*
				에러1이 들어오면 이 테이블에는 저장이 안된다.
				이런일이 생기면 안되므로 트랜잭션이 필요함.
				
				transaction1부터 저장이 안되고 롤백을 해야한다.
				
			*/
			
			// 여기로 롤백을 함.
			transactionManager.commit(status);
			return 1;
		}
		catch(Exception e)
		{
			System.out.println("[PlatformTransactionManager] Rollback");
			//에러가 나면 transaction1안에 들어와있던 데이터도 롤백이 되어서
			//없어진다.
			transactionManager.rollback(status);
			return 0;
		}
	}
}
