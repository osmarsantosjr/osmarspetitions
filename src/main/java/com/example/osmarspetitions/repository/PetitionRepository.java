package com.example.osmarspetitions.repository;

import com.example.osmarspetitions.model.Petition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PetitionRepository extends JpaRepository<Petition, Long> {
    List<Petition> findByTitleContainingIgnoreCase(String title);
}
