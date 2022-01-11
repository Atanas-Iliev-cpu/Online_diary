package com.example.myproject.web.controllers;

import com.example.myproject.services.EducationService;
import com.example.myproject.services.models.EducationServiceModel;
import com.example.myproject.web.controllers.models.EducationModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class EducationController {
    private final EducationService educationService;
    private final ModelMapper mapper;

    @Autowired
    public EducationController(EducationService educationService, ModelMapper modelMapper) {
        this.educationService = educationService;
        this.mapper = modelMapper;
    }


    @PostMapping("/create_education")
    public String createEducation(@ModelAttribute EducationModel model, Authentication principal) throws IOException {
        String user = principal.getName();
        EducationServiceModel receipt=this.mapper.map(model, EducationServiceModel.class);
        this.educationService.save(receipt, user);
        return "redirect:/education";
    }

    @PostMapping("/education_delete/{id}")
    public String deleteReceipt(@PathVariable String id) throws Exception {
        this.educationService.deleteReceipt(id);
        return "redirect:/education";
    }
}
