package com.example.osmarspetitions.controller;

import com.example.osmarspetitions.model.Petition;
import com.example.osmarspetitions.model.Signature;
import com.example.osmarspetitions.repository.PetitionRepository;
import com.example.osmarspetitions.repository.SignatureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PetitionController {

    @Autowired
    private PetitionRepository petitionRepo;

    @Autowired
    private SignatureRepository signatureRepo;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("petition", new Petition());
        return "create";
    }

    @GetMapping("/list")
    public String listPetitions(Model model) {
        model.addAttribute("petitions", petitionRepo.findAll());
        model.addAttribute("petition", new Petition()); // para o formulário
        return "list";
    }

    @GetMapping("/search")
    public String searchForm() {
        return "search";
    }

    @PostMapping("/search")
    public String searchResult(@RequestParam String query, Model model) {
        model.addAttribute("results", petitionRepo.findByTitleContainingIgnoreCase(query));
        return "result";
    }

    @GetMapping("/view/{id}")
    public String viewPetition(@PathVariable Long id, Model model) {
        Petition petition = petitionRepo.findById(id).orElseThrow();

        // Force load list
        petition.getSignatures().size();

        model.addAttribute("petition", petition);
        model.addAttribute("signature", new Signature());
        model.addAttribute("signatures", petition.getSignatures());
        return "view";
    }

    @PostMapping("/view/{id}/sign")
    public String signPetition(@PathVariable Long id, @ModelAttribute Signature signature) {
        Petition petition = petitionRepo.findById(id).orElseThrow();

        signature.setId(null); // Force new records
        petition.addSignature(signature);

        petitionRepo.save(petition);
        return "redirect:/view/" + id;
    }


}