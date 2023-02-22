package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reporter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reporter_id;

    private LocalDate date;

//    @ManyToOne
//    @JoinColumn(name = "patient")
//    private Patient patient;
//    @ManyToOne
//    @JoinColumn(name = "doctor")
//    private Doctor doctor;

    @OneToMany(mappedBy = "reporter_id")
    private List<ConsultationReporter> consultationReporters;

}
