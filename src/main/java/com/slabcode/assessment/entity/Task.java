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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 4, max = 255, message = "Task name length should be between 4 and 255 characters")
    @Column(unique = true, nullable = false)
    private String name;

    @Size(max = 255, message = "Maximum task description length: 255 characters")
    @Column
    private String description;

    //@Temporal(TemporalType.TIMESTAMP)
    //@Column(nullable = false)
    //private Calendar creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar executionDate;

    //@Temporal(TemporalType.TIMESTAMP)
    // private Calendar terminationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    ProgressStatus status;

    @ManyToOne
    @NotNull
    Project project;

    @PrePersist
    void onCreate() {
        if (this.getStatus() == null) {
            this.setStatus(ProgressStatus.CREATED);
        }
        if (this.getExecutionDate() == null) {
            this.setExecutionDate(Calendar.getInstance());
        }
    }
}
