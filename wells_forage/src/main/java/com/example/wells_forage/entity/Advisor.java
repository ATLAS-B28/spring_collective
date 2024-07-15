package com.example.wells_forage.entity;

import com.example.wells_forage.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "advisor")
public class Advisor {

    @Id
    @Column(name = "adv_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adv_id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Getter
    @OneToMany
    @JoinTable(
            name = "adv_user",
            joinColumns = @JoinColumn(name = "adv_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new LinkedHashSet<>();

    public void setUser(UserDTO user) {
    }
}
