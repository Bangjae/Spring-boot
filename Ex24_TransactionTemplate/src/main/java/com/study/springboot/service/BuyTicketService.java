package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

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
	
//	// 트랜잭션 처리를 위한 자동 주입
//	@Autowired  // 스프링안에 기본적으로 제공하는 것
//	PlatformTransactionManager transactionManager;
//	@Autowired
//	TransactionDefinition definition;
//	// definition : 기본설정값을 사용하겠다는 의미(?)  
	
	@Autowired
	TransactionTemplate transactionTemplate;
	
	@Override
	public int buy(String consumerId, int amount, String error)
	{
		// 너무 뒤에 커밋과 롤백이 있음
//		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try 
		{
		/*
			트랜잭션 템플릿을 통해 DB처리와 비지니스 로직을 실행한다.
		*/
		transactionTemplate.execute(new TransactionCallbackWithoutResult()
		{
			// 익명 클래스의 오버라이딩 된 메서드 안으로 모든 로직을 옮겨준다.
			// 또한 템플릿을 사용하면 commit, rollback이 자동으로 처리된다.
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) 
			{
			
				/*
					비지니스 로직 : 두개 다 성공하면 커밋, 1개라도 에러가나면 롤백을 한다.
					이렇게 붙어있어서 자동으로 커밋,롤백이 되어서 코딩관리하기가 쉽다.
					그래서 이 방법을 더 선호한다.
				*/
				transaction1.pay(consumerId, amount); // 현장 판매처 상황
			
				//의도적 에러 발생
				if(error.equals("1")) {int n = 10 / 0; }
			
			
				transaction2.pay(consumerId, amount); // 회계장부 상황
		
			}
		});
			return 1;
		}
		catch(Exception e)
		{
			System.out.println("[PlatformTransactionManager] Rollback");

			return 0;
		}
	}
}
