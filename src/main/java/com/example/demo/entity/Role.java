package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @SequenceGenerator(name = "role_sequence", sequenceName = "role_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "role_sequence")
    @Id
    private long roleId;
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
