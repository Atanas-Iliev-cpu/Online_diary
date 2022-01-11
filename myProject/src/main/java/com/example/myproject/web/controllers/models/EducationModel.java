package com.example.myproject.web.controllers.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EducationModel {
    private String startDate;

    private String endDate;

    private String educationLevel;

    private String schoolName;

    private String city;

    private String country;
}
