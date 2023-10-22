package com.github.valentinkarnaukhov.education.producer.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "company")
@SequenceGenerator(name = "company_id_generator", sequenceName = "company_id_sequence", allocationSize = 1)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_generator")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;
}
