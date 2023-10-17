package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;

@Controller
public class MyController
{
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception
	{
		return "Valid Annotation (4)";
	}

	@RequestMapping("/insertForm")
	public String insert1()
	{
		return "createPage";
	}

	@RequestMapping("/create")
	public String insert2(@ModelAttribute("dto")@Valid ContentDto contentDto, BindingResult result)
	{
		String page = "createDonePage";
		
		if (result.hasErrors())
		{
			if (result.getFieldError("writer") != null)
			{
				System.out.println("1:" + result.getFieldError("writer").getDefaultMessage());
			}
			
			if (result.getFieldError("content") != null)
			{
				System.out.println("2:" + result.getFieldError("content").getDefaultMessage());
			}
			page = "createPage";
		}
		return page;
	}
//	
//	// 검증을 위한 클래스를 대체 한다.
//	@InitBinder
//	protected void initBinder(WebDataBinder binder)
//	{
//		binder.setValidator(new ContentValidator());
//	}
}
