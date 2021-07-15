package br.com.sgc.customer.repository;

import br.com.sgc.customer.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerReposity extends JpaRepository<Customer, Long> {

}
