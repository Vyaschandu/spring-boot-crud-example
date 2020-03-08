package com.example.RestCrudDemo.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.RestCrudDemo.model.Customer;
import com.example.RestCrudDemo.service.CustomerServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerResource.class)
public class CustomerResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerServiceImpl customerServiceImpl;

	@Test
	public void testCreateCustomer() throws Exception {

		Customer mockCustomer = new Customer(1, "chandan", "vyas", "chandanvyass@gmail.com");
		String expectedJson = this.mapToJson(mockCustomer);
		String Uri = "/customer/addCustomer";

		Mockito.when(customerServiceImpl.saveCustomer(Mockito.any(Customer.class))).thenReturn(mockCustomer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(Uri).accept(MediaType.APPLICATION_JSON)
				.content(expectedJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputJson = response.getContentAsString();
		assertEquals(outputJson, expectedJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testGetCustomer() throws Exception {

		Customer mockCustomer = new Customer(1, "chandan", "vyas", "chandanvyass@gmail.com");
		String Uri = "/customer/1";
		Mockito.when(customerServiceImpl.getCustomerByCustID(Mockito.anyInt())).thenReturn(mockCustomer);
		String expectedJson = this.mapToJson(mockCustomer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(Uri).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String outputJson = result.getResponse().getContentAsString();
		assertEquals(outputJson, expectedJson);

	}

	@Test
	public void testGetCustomersList() throws Exception {
		Customer mockCustomer1 = new Customer(1, "chandan", "vyas", "chandanvyass@gmail.com");
		Customer mockCustomer2 = new Customer(2, "vikas", "vatlekar", "vikasvatlekar@gmail.com");

		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(mockCustomer1);
		customerList.add(mockCustomer2);
		String Uri = "/customer/findAllCustomers";

		Mockito.when(customerServiceImpl.listCustomers()).thenReturn(customerList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(Uri).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(customerList);
		String outputJson = result.getResponse().getContentAsString();
		assertEquals(outputJson, expectedJson);

	}

	@Test
	public void testUpdateCustomer() throws Exception {
		String uri = "/customer/updateCustomer";
		Customer customer = new Customer();
		customer.setFirstName("chandan");
		String expectedJson = this.mapToJson(customer);

		Mockito.when(customerServiceImpl.updateCustomer(Mockito.any(Customer.class))).thenReturn(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri).accept(MediaType.APPLICATION_JSON)
				.content(expectedJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputJson = response.getContentAsString();
		assertEquals(outputJson, expectedJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testDeleteCustomer() throws Exception {

		String Uri = "/customer/1";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(Uri)).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(200, status);

	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapperObj = new ObjectMapper();
		return mapperObj.writeValueAsString(object);
	}

}
