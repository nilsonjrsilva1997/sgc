package br.com.sgc.repository;

import br.com.sgc.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findById(long id);
    ResponseEntity deleteById(long id);

    String aux = "(*) > 0";
    @Query("SELECT CASE WHEN COUNT "+ aux +" THEN true ELSE false END FROM Customer c WHERE c.cpfCnpj = :cpfCnpj")
    boolean existsByCpfCnpj(@Param("cpfCnpj") String cpfCnpj);

    List<Customer> findAllByUserId(long id);
}
