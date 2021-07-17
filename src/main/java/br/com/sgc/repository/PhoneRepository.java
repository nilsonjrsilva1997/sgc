package br.com.sgc.repository;

import br.com.sgc.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Phone findById(long id);
    List<Phone> findAllByUserId(Long id);
}
