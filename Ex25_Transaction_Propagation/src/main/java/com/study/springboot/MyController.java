package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.service.BuyAndLogService;

// 드랜잭션 미 적용시 에러 발생 상황 연출
@Controller
public class MyController {
	
//	@Autowired
//	IBuyTicketService buyTicket;
	@Autowired
	BuyAndLogService buyTicketLog;
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "Transaction Propagation (4)";
	}
	// 입력 폼이 있는 jsp 페이지 호출
	@RequestMapping("/buy_ticket")
	public String buy_ticket() {
		return "buy_ticket";
	}
	// 폼 액션으로 오는 데이터를 처리
	@RequestMapping("/buy_ticket_card")
	public String buy_ticket_card(@RequestParam("consumerId") String consumerId,
								@RequestParam("amount") String amount,
								@RequestParam("error") String error,
								Model model) {
		
//		int nResult = buyTicket.buy(consumerId, Integer.parseInt(amount), error);
		int nResult = buyTicketLog.buy(consumerId, Integer.parseInt(amount), error);
		model.addAttribute("consumerId", consumerId);
		model.addAttribute("amount", amount);
		// error 파라미터 값에 따라 에러를 내거나 안 내거나
		if (nResult == 1) {
			return "buy_ticket_end";
		} else {
			return "buy_ticket_error";
		}
	}
}
