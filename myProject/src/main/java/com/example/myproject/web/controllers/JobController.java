package com.example.myproject.web.controllers;

import com.example.myproject.services.EducationService;
import com.example.myproject.services.JobService;
import com.example.myproject.services.models.EducationServiceModel;
import com.example.myproject.services.models.JobServiceModel;
import com.example.myproject.web.controllers.models.EducationModel;
import com.example.myproject.web.controllers.models.JobModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class JobController {

    private final JobService jobService;
    private final ModelMapper mapper;

    @Autowired
    public JobController(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.mapper = modelMapper;
    }


    @PostMapping("/create_job")
    public String createEducation(@ModelAttribute JobModel model, Authentication principal) throws IOException {
        String user = principal.getName();
        JobServiceModel job = this.mapper.map(model, JobServiceModel.class);
        this.jobService.save(job, user);
        return "redirect:/job";
    }

    @PostMapping("/job_delete/{id}")
    public String deleteReceipt(@PathVariable String id) throws Exception {
        this.jobService.deleteReceipt(id);
        return "redirect:/job";
    }
}
