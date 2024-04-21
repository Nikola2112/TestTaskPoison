package com.testtask.house.model;


import com.testtask.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "house")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @ManyToMany
    @JoinTable(
            name = "house_user",
            joinColumns = @JoinColumn(name = "house_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> residents = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
