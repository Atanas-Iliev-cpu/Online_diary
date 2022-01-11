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
@Table(name="jobs")
public class Job extends BaseEntity{

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = true)
    private String endDate;

    @Column(nullable = false)
    private String workPlace;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String position;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id")
    private User user;
}
