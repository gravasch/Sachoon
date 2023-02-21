package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;



@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String medicationName;
    private double dosage;
    private String advice;
    private LocalDate consultationDate;

//    @ManyToOne
//    @JoinColumn(name= "doctor_id")
//    private Doctor doctor;
//
//    @ManyToOne
//    @JoinColumn(name= "patient_id")
//    private Patient patient;



//    @ManyToOne
//    @JoinColumn(name= "doctor_id")
//    private Doctor doctor;
//    @ManyToOne
//    @JoinColumn(name= "patient_id")
//    private Patient patient;
//
//    @OneToMany(mappedBy = "consultation")
//    private List<MediData> mediData = new ArrayList<>();
}