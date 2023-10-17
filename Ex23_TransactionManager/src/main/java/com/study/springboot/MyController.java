package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.service.IBuyTicketService;

@Controller
public class MyController
{
	@Autowired
	IBuyTicketService buyTicket;
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception
	{
		return "Transaction X (1)"; 	
	}

	// 입력 폼이있는 jsp호출
	@RequestMapping("/buy_ticket") 
	public String buy_ticket()
	{
		return "buy_ticket";
	}
	
	// 폼 액션으로 오는 데이터를 처리
	@RequestMapping("/buy_ticket_card")
	public String buy_ticket_card(@RequestParam("consumerId") String consumerId,
								  @RequestParam("amount") String amount,
								  @RequestParam("error") String error,
								  Model model)
	{
		int nResult = buyTicket.buy(consumerId, Integer.parseInt(amount), error);
		
		model.addAttribute("consumerId", consumerId);
		model.addAttribute("amount", amount);
		
		// error 파라미터 값에 따라 에러를 내거나 안내거나~ 
		if(nResult ==1)
		{
			return "buy_ticket_end";
		}
		else
		{
			return "buy_ticket_error";
		}
	}
}
