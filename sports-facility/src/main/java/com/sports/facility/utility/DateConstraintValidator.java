package com.sports.facility.utility;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;





public class DateConstraintValidator implements ConstraintValidator<ValidDate, Date> {

	@Override
	public boolean isValid(Date date, ConstraintValidatorContext context) {
		LocalDate localDate = 
				Instant.ofEpochMilli(date.getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		if(localDate.isEqual(LocalDate.now()) || localDate.isAfter(LocalDate.now()) )
		{
			return false;
		}
		else {
			return true;
		}
	}


}