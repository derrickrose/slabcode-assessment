package com.slabcode.assessment.entity;

import com.slabcode.assessment.constant.ProgressStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Size(min = 4, max = 255, message = "Minimum project name length: 4 characters")
    @Column(unique = true, nullable = false)
    private String name;

    @Size(min = 4, message = "Minimum project description length: 4 characters")
    @Column(unique = true, nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar terminationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    ProgressStatus status;

    @PrePersist
    void onCreate() {
        this.setCreationDate(Calendar.getInstance());
        this.setStatus(ProgressStatus.CREATED);
    }
}
