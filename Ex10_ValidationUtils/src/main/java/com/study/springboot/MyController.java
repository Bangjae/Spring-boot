package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController
{
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception
	{
		return "ValidationUtils (2)";
	}

	@RequestMapping("/insertForm")
	public String insert1()
	{
		return "createPage";
	}

	@RequestMapping("/create")
	public String insert2(@ModelAttribute("dto") ContentDto contentDto, BindingResult result)
	{
		// 폼값 검증이 환료된 후 포워드할 View의 경로를 설정한다.
		String page = "createDonePage";
		System.out.println(contentDto);

		// 폼값 검증을 위해 객체를 생성한다.
		ContentValidator validator = new ContentValidator();
		// 폼값 검증을 위해 메서드를 호출한다.
		validator.validate(contentDto, result); // 검증
		// 폼값 검증에 실패했는지 확인한다.
		if (result.hasErrors())
		{
			System.out.println("getAllErrors : " + result.getAllErrors());

			// 제목 검증에 실패한 경우 우리가 생성한 에러코드를 출력한다.
			if (result.getFieldError("writer") != null)
			{
				System.out.println("1:" + result.getFieldError("writer").getCode());
			}
			
			// 내용 검증을 실패한 경우 디폴트 에러코드를 출력한다.
			if (result.getFieldError("content") != null)
			{
				System.out.println("2:" + result.getFieldError("content").getCode());
			}
			page = "createPage";
		}
		return page;
	}
}
