package com.cg.vms.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cg.vms.entities.Customer;
import com.cg.vms.entities.Vehicle;
import com.cg.vms.repository.ICustomerRepository;

@ExtendWith(SpringExtension.class)
public class CustomerServiceMokitoTest {
	@InjectMocks
	CustomerServiceImpl custService;

	@MockBean
	ICustomerRepository custRep;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateCustomer() {
		Customer customer = new Customer(1, "tom", "son", "951771122", "tom@gmail.com");
		Mockito.when(custRep.save(customer)).thenReturn(customer);
		Customer persistedCust = custService.addCustomer(customer);
		assertEquals(1, persistedCust.getCustomerId());
	}

	@Test
	void testViewAllCustomer() {
		Customer customer1 = new Customer(1, "tom", "son", "951771122", "tom@gmail.com");
		Customer customer2 = new Customer(2, "jerry", "lee", "951998122", "jerry@gmail.com");
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer1);
		customerList.add(customer2);
		Mockito.when(custRep.findAll()).thenReturn(customerList);
		List<Customer> customer = custService.findAllCustomer();
		assertEquals(2, customer.size());
	}

	@Test
	void testUpdateCustomer() {
		Customer customer1 = new Customer(1, "tommy", "cruise", "951771122", "tom@gmail.com");
		Mockito.when(custRep.findById(1)).thenReturn(Optional.of(customer1));
		Mockito.when(custRep.save(customer1)).thenReturn(customer1);
		Customer persistedCust = custService.update(customer1);
		assertEquals(1, persistedCust.getCustomerId());
	}

	@Test
	void testDeleteCustomer() {
		Customer customer = new Customer(1, "tommy", "cruise", "951771122", "tom@gmail.com");
		Mockito.when(custRep.findById(1)).thenReturn(Optional.of(customer));
		custRep.deleteById(1);
		Customer persistedCust = custService.deleteCustomerbyId(1);
		assertEquals(1, persistedCust.getCustomerId());
		assertEquals("tommy", persistedCust.getFirstName());

	}

	@Test
	void testUpdateCustomerbyFirstname() {
		Customer customer = new Customer(1, "jen", "cru", "951771122", "tom@gmail.com");
		Mockito.when(custRep.findById(1)).thenReturn(Optional.of(customer));
		Mockito.when(custRep.save(customer)).thenReturn(customer);
		Customer persistedCust = custService.updateFirstName(1, customer);
		assertEquals(1, persistedCust.getCustomerId());
		assertEquals("jen", persistedCust.getFirstName());
	}

	@Test
	void testViewCustomerbyId() {
		Customer customer = new Customer(1, "jen", "cru", "951771122", "tom@gmail.com");
		Mockito.when(custRep.findById(1)).thenReturn(Optional.of(customer));
		Customer persistedCust = custService.viewCustomerbyId(1);
		assertEquals("jen", persistedCust.getFirstName());
	}

	@Test
	void testViewCustomerbyVehicleType() {
		Customer customer1 = new Customer(1, "tommy", "cruise", "951771122", "tom@gmail.com");
		Vehicle vehicle1 = new Vehicle(101, "TN02J0666", "bus", "A/C", "prime", "goa", "13", 600.0, 8000.0);
		Customer customer2 = new Customer(2, "jerry", "lee", "951998122", "jerry@gmail.com");
		Vehicle vehicle2 = new Vehicle(102, "TN02J0666", "car", "A/C", "prime", "goa", "13", 600.0, 8000.0);
		customer1.setVehicle(vehicle1);
		customer2.setVehicle(vehicle2);
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer1);
		customerList.add(customer2);
		Mockito.when(custRep.findbyType("car")).thenReturn(customerList);
		List<Customer> cust3 = custService.findbyType("car");
		assertEquals(1, cust3.size());
	}

	@Test
	void testViewCustomerbyVehicleLocation() {

		Customer customer1 = new Customer(1, "tommy", "cruise", "951771122", "tom@gmail.com");
		Vehicle vehicle1 = new Vehicle(101, "TN02J0666", "bus", "A/C", "prime", "goa", "13", 600.0, 8000.0);
		Customer customer2 = new Customer(2, "jerry", "lee", "951998122", "jerry@gmail.com");
		Vehicle vehicle2 = new Vehicle(102, "TN02J0776", "car", "nonA/C", "prime", "chennai", "13", 600.0, 8000.0);
		customer1.setVehicle(vehicle1);
		customer2.setVehicle(vehicle2);
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer1);
		customerList.add(customer2);
		Mockito.when(custRep.findbyVehicleLocation("chennai")).thenReturn(customerList);
		List<Customer> cust3 = custService.findbyVehicleLocation("chennai");
		assertEquals(1, cust3.size());
	}

}
