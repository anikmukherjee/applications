package com.points.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.points.entity.CustomerTransaction;
import com.points.repository.TransactionRepository;
import com.points.vo.PointsVO;

@Service
public class PointsServiceImpl implements PointsService {
	
	@Autowired
	TransactionRepository transactionRepository;
	   
	public PointsVO getPointsByCustomerId(Long customerId) {
		
		PointsVO points = new PointsVO();
		
		if(customerId == null)
			return points;
		
		Calendar calendar = Calendar.getInstance();
		Timestamp currentDateTime = new Timestamp(calendar.getTimeInMillis());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Timestamp currentMonthBegining = new Timestamp(calendar.getTimeInMillis());
		calendar.add(Calendar.MONTH, -1);
		Timestamp lastMonthBegining = new Timestamp(calendar.getTimeInMillis());
		calendar.add(Calendar.MONTH, -1);
		Timestamp lastToLastMonthBegining = new Timestamp(calendar.getTimeInMillis());

		List<CustomerTransaction> allTransactions = transactionRepository.findAllByCustomerIdAndDateBetween(
				customerId, lastToLastMonthBegining, currentDateTime);
		
		List<CustomerTransaction> currMonthTran = new ArrayList<CustomerTransaction>(); 
		List<CustomerTransaction> lastMonthTrans = new ArrayList<CustomerTransaction>(); 
		List<CustomerTransaction> lastToLastMonthTrans = new ArrayList<CustomerTransaction>(); 
		
		if(allTransactions != null)
		{
			for(CustomerTransaction trans : allTransactions)
			{
				if(trans.getDate().equals(currentMonthBegining) || 
						trans.getDate().after(currentMonthBegining))
					currMonthTran.add(trans);
				else if(trans.getDate().equals(lastMonthBegining)
						|| trans.getDate().after(lastMonthBegining))
					lastMonthTrans.add(trans);
				else if(trans.getDate().equals(lastToLastMonthBegining) 
						|| trans.getDate().after(lastToLastMonthBegining))
					lastToLastMonthTrans.add(trans);
			}
		}
			
		points.setCustomerId(customerId);
		points.setCurrentMonthPoints(getPoints(currMonthTran));
		points.setLastMonthPoints(getPoints(lastMonthTrans));
		points.setLastToLastMonthPoints(getPoints(lastToLastMonthTrans));
		points.setTotalPoints(points.getCurrentMonthPoints() + 
				points.getLastMonthPoints() + points.getLastToLastMonthPoints());

		return points;
	}

	private Long getPoints(List<CustomerTransaction> transactions) 
	{
		Long points = 0L;
		
		for(CustomerTransaction trans : transactions)
			points += calculatePoints(trans);
		
		return points;
	}

	private Long calculatePoints(CustomerTransaction trans) {
		if (trans.getAmount() > 50 && trans.getAmount() <= 100) {
			return Math.round(trans.getAmount() - 50);
		} else if (trans.getAmount() > 100) {
			return Math.round(trans.getAmount() - 100) * 2 + 50;
		} else
			return 0l;

	}	
}
