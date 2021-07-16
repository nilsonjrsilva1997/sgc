package br.com.sgc.repository;

import br.com.sgc.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerReposity extends JpaRepository<Customer, Long> {
    Customer findById(long id);
}
