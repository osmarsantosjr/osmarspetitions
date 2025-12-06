package com.example.osmarspetitions.config;

import com.example.osmarspetitions.model.Petition;
import com.example.osmarspetitions.repository.PetitionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(PetitionRepository petitionRepo) {
        return args -> {
            Petition p1 = new Petition();
            p1.setTitle("Save the Amazon Rainforest");
            p1.setDescription("We need to protect the Amazon from deforestation.");

            Petition p2 = new Petition();
            p2.setTitle("Reduce the use of plastics");
            p2.setDescription("Campaign to reduce the consumption of disposable plastics.");

            Petition p3 = new Petition();
            p3.setTitle("Improve public transportation");
            p3.setDescription("Investment in electric buses and more subway lines.");

            petitionRepo.save(p1);
            petitionRepo.save(p2);
            petitionRepo.save(p3);
        };
    }
}
