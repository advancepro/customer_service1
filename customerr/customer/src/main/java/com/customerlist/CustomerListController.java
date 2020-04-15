package com.customerlist;

import java.util.List;

import com.customerlist.io.Customer;
import com.customerlist.io.CustomerListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CustomerListController {

	private CustomerListRepository customerListRepository;

	@Autowired
	public CustomerListController(CustomerListRepository customerListRepository) {
		this.customerListRepository = customerListRepository;
	}

	@GetMapping(value = "/")
	public String homePage(Model model) {
		model.addAttribute("date", new java.util.Date());
		return "index";
	}

	@GetMapping(value = "/customers")
	public String ReceiveCustomer(Model model) {
		List<Customer> customerList = customerListRepository.findAll();
		if (customerList != null) {
			model.addAttribute("customers", customerList);
		}
		return "customers";
	}

	@GetMapping(value = "/customers/add")
	public String linkToAdd(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customerApp";
	}

	@PostMapping(value = "/customers/submit")
	public String ToCustomer(Customer customer) {
		customerListRepository.save(customer);
		return "redirect:/customers";
	}

	@GetMapping(value = "/customers/login")
	public String goToLoginPage() {
		return "customerApp";
	}
}