package com.example.RestCrudDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.RestCrudDemo.model.Customer;
import com.example.RestCrudDemo.repository.CustomerRespository;
import com.example.RestCrudDemo.service.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class RestCrudDemoApplicationTests {

	@Autowired
	private CustomerServiceImpl service;

	@MockBean
	private CustomerRespository repository;

	@Test
	public void testlistCustomers() {

		Mockito.when(repository.findAll())
				.thenReturn(Stream.of(new Customer(1, "chandan", "vyas", "chandanvyass@gmail.com"),
						new Customer(1, "vikas", "vatlekar", "virus@gmail.com")).collect(Collectors.toList()));

		assertEquals(2, service.listCustomers().size());

	}

	@Test
	public void testGetCustomerByCustID() {

		int custId = '1';
		Customer cus = new Customer();
		Mockito.when(repository.findByCustomerId(custId)).thenReturn(cus);

		assertEquals(cus, service.getCustomerByCustID(custId));

	}

	@Test
	public void testSaveCustomer() {

		Customer cus = new Customer(1, "chandan", "vyas", "chandanvyass@gmail.com");
		Mockito.when(repository.save(cus)).thenReturn(cus);

		assertEquals(cus, service.saveCustomer(cus));

	}

	@Test
	public void testUpdateCustomer() {

		Customer cus = new Customer(1, "chandan", "vyas", "chandanvyass@gmail.com");
		Mockito.when(repository.save(cus)).thenReturn(cus);

		assertEquals(cus, service.updateCustomer(cus));

	}

	@Test
	public void testDeleteCustomerByCustID() {

		int custId = '1';
		service.deleteCustomerByCustID(custId);
		Mockito.verify(repository, times(1)).deleteById(custId);

	}

}
