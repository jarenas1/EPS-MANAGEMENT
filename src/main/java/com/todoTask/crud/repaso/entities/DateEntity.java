package com.todoTask.crud.repaso.entities;

import com.todoTask.crud.repaso.tools.enums.DateStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "dates")
public class DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    private Integer duration = 30;

    private String reason;

    @Enumerated(EnumType.STRING)
    private DateStatus status;

    private String notes;
}
