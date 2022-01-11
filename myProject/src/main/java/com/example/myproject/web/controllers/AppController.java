package com.example.myproject.web.controllers;

import com.example.myproject.data.models.User;
import com.example.myproject.data.repositories.EducationRepository;
import com.example.myproject.data.repositories.UserRepository;
import com.example.myproject.services.EducationService;
import com.example.myproject.services.JobService;
import com.example.myproject.services.models.EducationServiceModel;
import com.example.myproject.services.models.JobServiceModel;
import com.example.myproject.web.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
class AppController extends BaseController {
    private final ModelMapper mapper;
    private final EducationService educationService;
    private final JobService jobService;

    @Autowired
    public AppController(ModelMapper mapper, EducationService educationService, JobService jobService) {
        this.mapper = mapper;
        this.educationService = educationService;
        this.jobService = jobService;
    }
    @GetMapping("/")
    public String getIndex(){
        return "index_guest";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/home")
    public ModelAndView getHome(Authentication principal, ModelAndView modelAndView){
        String username = principal.getName();
        String wel = "Welcome, " + username;
        modelAndView.addObject("username", wel);
        return super.view("fragments/navigation", modelAndView);
    }

    @GetMapping("/education")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getEducations(ModelAndView modelAndView, Authentication principal){
        String user = principal.getName();
        List<EducationServiceModel> educations = this.educationService.getByUsername(user);
        modelAndView.addObject("educations",educations);
        return super.view("education",modelAndView);
    }

    @GetMapping("/job")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView viewJob(ModelAndView modelAndView, Authentication principal){
        String user = principal.getName();
        List<JobServiceModel> jobs = this.jobService.getByUsername(user);
        modelAndView.addObject("jobs", jobs);
        return super.view("job",modelAndView);
//        return new ModelAndView( "job");
    }

    @GetMapping("/create_job")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getJobs(ModelAndView modelAndView, Authentication principal){
        String user = principal.getName();
        List<JobServiceModel> job = this.jobService.getByUsername(user);
        modelAndView.addObject("job",job);
        return super.view("create_job",modelAndView);
    }


    @GetMapping("/create_education")
    public ModelAndView viewCreateEducation(){
        return new ModelAndView( "create_education");
    }



}
