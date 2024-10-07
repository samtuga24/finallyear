package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Passport {
    @SequenceGenerator(name = "passport_sequence", sequenceName = "passport_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "passport_sequence")
    @Id
    private Long id;
    private String name;
    private String type;
    private long adminId;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    @JsonIgnore
    private byte[] picture;
}
