package com.example.osmarspetitions.repository;

import com.example.osmarspetitions.model.Signature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureRepository extends JpaRepository<Signature, Long> {
    // Se precisar, pode adicionar métodos personalizados aqui
}
