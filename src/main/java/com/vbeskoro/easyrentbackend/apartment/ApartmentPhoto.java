package com.vbeskoro.easyrentbackend.apartment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class ApartmentPhoto {

    @Id
    @SequenceGenerator(
            name = "apartment_photo_sequence",
            sequenceName = "apartment_photo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "apartment_photo_sequence"
    )
    private Long id;

    @Column(name = "photo_url")
    private String url;

    @Column(name = "photo_alt_text")
    private String altText;

}
