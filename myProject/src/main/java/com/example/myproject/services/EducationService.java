package com.example.myproject.services;

import com.example.myproject.data.models.Education;
import com.example.myproject.data.models.User;
import com.example.myproject.data.repositories.EducationRepository;
import com.example.myproject.data.repositories.UserRepository;
import com.example.myproject.services.models.EducationServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationService {
    private final EducationRepository educationRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public EducationService(EducationRepository educationRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.educationRepository = educationRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public List<EducationServiceModel> findAll() {
        return this.educationRepository.findAll().stream().map(e->modelMapper.map(e, EducationServiceModel.class)).collect(Collectors.toList());
    }



    public void save(EducationServiceModel eventServiceModel, String user){
        Education ed = this.modelMapper.map(eventServiceModel, Education.class);
        User usr = userRepository.findUserByUsername(user);
        usr.getEducations().add(ed);
        ed.setUser(usr);
        this.educationRepository.save(ed);
        this.userRepository.save(usr);
    }

    public List<EducationServiceModel> getByUsername(String username){
        return this.educationRepository.getAllByUsername(username).stream().map(e-> modelMapper.map(e,EducationServiceModel.class)).collect(Collectors.toList());
    }

    @Transactional
    public void deleteReceipt(String id) throws Exception {
        Education education= this.educationRepository.findById(id).orElseThrow(() -> new Exception("Education card not found"));;
        this.educationRepository.delete(education);

    }

}
