package com.example.myproject.services;

import com.example.myproject.data.models.Education;
import com.example.myproject.data.models.Job;
import com.example.myproject.data.models.User;
import com.example.myproject.data.repositories.EducationRepository;
import com.example.myproject.data.repositories.JobRepository;
import com.example.myproject.data.repositories.UserRepository;
import com.example.myproject.services.models.EducationServiceModel;
import com.example.myproject.services.models.JobServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public JobService(JobRepository jobRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public List<JobServiceModel> findAll() {
        return this.jobRepository.findAll().stream().map(e->modelMapper.map(e, JobServiceModel.class)).collect(Collectors.toList());
    }



    public void save(JobServiceModel jobServiceModel, String user){
        Job job = this.modelMapper.map(jobServiceModel, Job.class);
        User usr = userRepository.findUserByUsername(user);
        usr.getJobs().add(job);
        job.setUser(usr);
        this.jobRepository.save(job);
        this.userRepository.save(usr);
    }

    public List<JobServiceModel> getByUsername(String username){
        return this.jobRepository.getAllByUsername(username).stream().map(e-> modelMapper.map(e,JobServiceModel.class)).collect(Collectors.toList());
    }

    @Transactional
    public void deleteReceipt(String id) throws Exception {
        Job job = this.jobRepository.findById(id).orElseThrow(() -> new Exception("Job card not found"));;
        this.jobRepository.delete(job);

    }
}
