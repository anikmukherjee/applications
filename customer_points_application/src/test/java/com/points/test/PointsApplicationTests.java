package com.points.test;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.points.entity.CustomerTransaction;
import com.points.repository.TransactionRepository;
import com.points.service.PointsServiceImpl;
import com.points.vo.PointsVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PointsApplicationTests {

    @Autowired
    private PointsServiceImpl service;
    
	@MockBean
    private TransactionRepository transRepository;
	
	@Test
	public void test_PointsService()
	{
		Calendar calendar = Calendar.getInstance();
		Timestamp currentDateTime = new Timestamp(calendar.getTimeInMillis());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Timestamp currentMonthBegining = new Timestamp(calendar.getTimeInMillis());
		
		Timestamp lastMonthEnd = currentMonthBegining;
		calendar.add(Calendar.MONTH, -1);
		Timestamp lastMonthBegining = new Timestamp(calendar.getTimeInMillis());
		
		Timestamp lastToLastMonthEnd = lastMonthBegining;
		calendar.add(Calendar.MONTH, -1);
		Timestamp lastToLastMonthBegining = new Timestamp(calendar.getTimeInMillis());
		
		List<CustomerTransaction> listTrans = new ArrayList<CustomerTransaction>();
		listTrans.add(new CustomerTransaction(1L, 1001L, currentMonthBegining, 100.0));
		listTrans.add(new CustomerTransaction(2L, 1001L, lastMonthBegining, 200.0));
		listTrans.add(new CustomerTransaction(3L, 1001L, lastToLastMonthBegining, 300.0));
		
		Mockito.when(transRepository.findAllByCustomerIdAndDateBetween(Mockito.eq(1001L),
				Mockito.any(), Mockito.any())).thenReturn(listTrans);
		
		PointsVO vo = service.getPointsByCustomerId(1001L);
		
		assertTrue(vo.getCurrentMonthPoints() == 50);
		assertTrue(vo.getLastMonthPoints() == 250);
		assertTrue(vo.getLastToLastMonthPoints() == 450);
		assertTrue(vo.getTotalPoints() == 750);
	}
	
	@Test
	public void test_PointsService_NullCustomerTransactionList()
	{
		Mockito.when(transRepository.findAllByCustomerIdAndDateBetween(Mockito.eq(1001L),
				Mockito.any(), Mockito.any())).thenReturn(null);
		
		PointsVO vo = service.getPointsByCustomerId(1001L);
		
		assertTrue(vo.getCurrentMonthPoints() == 0);
		assertTrue(vo.getLastMonthPoints() == 0);
		assertTrue(vo.getLastToLastMonthPoints() == 0);
		assertTrue(vo.getTotalPoints() == 0);
		
		System.out.println(vo);
	}
	
	@Test
	public void test_PointsService_EmptyCustomerTransactionList()
	{
		Mockito.when(transRepository.findAllByCustomerIdAndDateBetween(Mockito.eq(1001L),
				Mockito.any(), Mockito.any())).thenReturn(new ArrayList<CustomerTransaction>());
		
		PointsVO vo = service.getPointsByCustomerId(1001L);
		
		assertTrue(vo.getCurrentMonthPoints() == 0);
		assertTrue(vo.getLastMonthPoints() == 0);
		assertTrue(vo.getLastToLastMonthPoints() == 0);
		assertTrue(vo.getTotalPoints() == 0);
		
		System.out.println(vo);
	}

	@Test
	public void test_PointsService_CustomerId_Null()
	{
		Mockito.when(transRepository.findAllByCustomerIdAndDateBetween(Mockito.eq(1001L),
				Mockito.any(), Mockito.any())).thenReturn(null);
		
		PointsVO vo = service.getPointsByCustomerId(null);
		
		assertTrue(vo.getCurrentMonthPoints() == 0);
		assertTrue(vo.getLastMonthPoints() == 0);
		assertTrue(vo.getLastToLastMonthPoints() == 0);
		assertTrue(vo.getTotalPoints() == 0);
		
		System.out.println(vo);
	}

}
