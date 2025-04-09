package com.example.cms.dto.requests;

import java.util.Date;

import com.example.cms.exceptions.AppErrorCodes;
import com.example.cms.exceptions.AppException;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ConversationFilter {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date[] dateRange;

	public Date[] getDateRange() {
		return dateRange;
	}

	public void setDateRange(Date[] dateRange) {
		this.dateRange = dateRange;
	}
	
	public void validate() {
		if(dateRange != null && dateRange.length != 2)
			throw new AppException(AppErrorCodes.INVALID_PARAMETER_VALUE, "dateRange");
	}
}
