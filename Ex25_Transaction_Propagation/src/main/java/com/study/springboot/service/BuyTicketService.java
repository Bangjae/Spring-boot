package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.study.springboot.dao.ITransaction1DAO;
import com.study.springboot.dao.ITransaction2DAO;

// @Service 어노테이션을 지정하면 이 클래스를 빈으로 사용한다 라는 의미
@Service
public class BuyTicketService implements IBuyTicketService {
	@Autowired	// 자동주입을 받아 변수로 만든다.
	ITransaction1DAO transaction1;
	@Autowired
	ITransaction2DAO transaction2;
	
	@Autowired
	TransactionTemplate transactionTemplate;
	
	/*
	 	전파속성
	 	REQUIRES_NEW : 각각의 트랜잭션을 처리한다. 즉 포함시킨 메서드에서 에러가
	 		발생하더라도 포함된 메서드에서는 정상처리된다.
	 	REQUIRED : 기존 트랜잭션에 의존한다. 즉 포함된 메서드나 포함시킨
	 		메서드 어느쪽이든 오류가 발생하면 모든 작업이 rollback 된다.
	 		별도의 설정이 없으면 해당값이 디폴트로 지정된다.
	 */
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Transactional(propagation = Propagation.REQUIRED)
	
	public int buy(String consumerId, int amount, String error) {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					transaction1.pay(consumerId, amount);	// 현장 판매처 상황
					// 의도적 에러 발생
					if (error.equals("1")) {int n = 10 / 0;}
					transaction2.pay(consumerId, amount);	// 회계장부 상황
				}
			});
			
			return 1;
		} catch (Exception e) {
			System.out.println("[Transaction Propagation #2] Rollback");
			return 0;
		}
	}
}
