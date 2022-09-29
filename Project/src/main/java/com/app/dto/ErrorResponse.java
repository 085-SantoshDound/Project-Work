package com.app.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ErrorResponse {
	private String message;
	private LocalDateTime timeDate;
	public ErrorResponse(String msg)
	{
		super();
		this.message=msg;
		
		this.timeDate=LocalDateTime.now();
	}
}
