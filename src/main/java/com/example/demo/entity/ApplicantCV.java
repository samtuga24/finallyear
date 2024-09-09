package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicantCV {
    @SequenceGenerator(name = "cv_sequence", sequenceName = "cv_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cv_sequence")
    @Id
    private Long id;
    private String name;
    private String type;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @JsonIgnore
    private byte[] picture;
}
