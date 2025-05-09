package com.tentalk.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Report {

    @Column(name = "report_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reporter_id;

    private Long target_post_id;

    private String reason;

    private LocalDateTime created_at;
}
