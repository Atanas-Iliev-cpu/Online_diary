package com.example.myproject.data.models;

import com.example.myproject.data.models.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="educations")
public class Education extends BaseEntity {

    @Column(nullable = true)
    private String startDate;

    @Column(nullable = true)
    private String endDate;

    @Column(nullable = false)
    private String educationLevel;

    @Column(nullable = false)
    private String schoolName;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id")
    private User user;


}
