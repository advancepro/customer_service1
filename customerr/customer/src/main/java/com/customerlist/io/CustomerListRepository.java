package com.customerlist.io;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerListRepository extends JpaRepository<Customer, Long> {
	List<Customer> findAll();
}
