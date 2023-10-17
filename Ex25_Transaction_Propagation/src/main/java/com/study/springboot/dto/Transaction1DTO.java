package com.study.springboot.dto;

import lombok.Data;

// 현장 매표소
@Data
public class Transaction1DTO {
	private String consumerId;
	private int amount;
}
