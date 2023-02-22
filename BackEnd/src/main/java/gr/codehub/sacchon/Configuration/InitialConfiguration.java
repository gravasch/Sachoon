package gr.codehub.sacchon.Configuration;


import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.DoctorType;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.repository.ConsultationRepository;
import gr.codehub.sacchon.repository.DoctorRepository;
import gr.codehub.sacchon.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class InitialConfiguration {

        @Bean
        CommandLineRunner commandLineRunner(
                DoctorRepository doctorRepository, ConsultationRepository consultationRepository, PatientRepository patientRepository) {
            return args -> {
                Doctor doctor1 = new Doctor(  1L,  "Malvina", "Zatka", "mzatka", LocalDate.now(),
                        DoctorType.DOCTOR, true ,null, null);

                Doctor doctor2 = new Doctor(  2L,  "Evaggelia", "Milosi", "emilosi", LocalDate.now(),
                        DoctorType.CHIEF, true ,null, null);

                List<Doctor> list =  doctorRepository.saveAll(List.of(doctor1, doctor2));

                Patient patient1 = new Patient(  1L,  "Malvina", "Zatka", "martiou 25", "thessaloniki" , LocalDate.now(),
                        "A", true ,doctor1, null);

                Patient patient2 = new Patient(  2L,  "Evaggelia", "Milosi", "karamanli", "Drama", LocalDate.now(),
                        "B", true ,doctor2, null);

                List<Patient> list2 =  patientRepository.saveAll(List.of(patient1, patient2));



                Consultation consultation1 = new Consultation( 1L,"xanax", 2.2, "once a day",LocalDate.now() ,doctor1, patient2);
                Consultation consultation2 = new Consultation( 2L,"panadol", 1.4, "three every day",LocalDate.now() ,doctor2, patient1);

                consultationRepository.saveAll(List.of(consultation1, consultation2));


                System.out.println(consultation1);
                System.out.println(doctor1);

            };
        }
}
