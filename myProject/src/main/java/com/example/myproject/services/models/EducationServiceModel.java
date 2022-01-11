package com.example.myproject.services.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class EducationServiceModel extends BaseServiceModel{

    private String startDate;

    private String endDate;

    private String educationLevel;

    private String schoolName;

    private String city;

    private String country;
}
