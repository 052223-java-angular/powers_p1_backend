package com.revature.hikingbuddy.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="trail_rating")
public class TrailRating {

    @Id
    private String id;
    @Column
    private double rating;
    @Column
    private String comment;
 
    @ManyToOne
    @JoinColumn(name = "trail_name")
    @JsonBackReference
    private Trail trail_name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user_id;

   
}
