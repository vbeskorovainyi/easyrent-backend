package com.vbeskoro.easyrentbackend.apartment;

import com.vbeskoro.easyrentbackend.address.Address;
import com.vbeskoro.easyrentbackend.appuser.AppUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Apartment {

    @Id
    @SequenceGenerator(
            name = "apartment_sequence",
            sequenceName = "apartment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "apartment_sequence"
    )
    private Long id;

    @Column(name = "apartment_title")
    private String title;

    @Column(name = "apartment_description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "apartment_id")
    private List<ApartmentPhoto> photos = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "price")
    private Long price;


}
