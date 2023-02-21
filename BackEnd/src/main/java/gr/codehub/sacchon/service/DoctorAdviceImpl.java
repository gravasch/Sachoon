package gr.codehub.sacchon.service;

import gr.codehub.sacchon.Dto.ConsultationDTO;
import gr.codehub.sacchon.Dto.DoctorDTO;
import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.repository.ConsultationRepository;
import gr.codehub.sacchon.repository.DoctorRepository;
import lombok.AllArgsConstructor;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorAdviceImpl implements DoctorAdviceService {

    private final DoctorRepository doctorRepository;
    private final ConsultationRepository consultationRepository;

    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorDto) {
        Doctor doctor = doctorDto.asDoctor();
        return new DoctorDTO(doctorRepository.save(doctor)) ;
    }

    @Override
    public ConsultationDTO createConsultation(ConsultationDTO consultationDTO) {
        Consultation consultation = consultationDTO.asConsultation();
        return new ConsultationDTO(consultationRepository.save(consultation));
    }



    @Override
    public List<ConsultationDTO> readConsultation() {
        return consultationRepository
                .findAll()
                .stream()
                .map(ConsultationDTO::new)
                .collect(Collectors.toList());
    }



    @Override
    public DoctorDTO readDoctor(Long id) throws Exception {
        return new DoctorDTO( readDoctorDb(id));
    }

    @Override
    public ConsultationDTO readConsultation(Long id) throws Exception {
        return new ConsultationDTO( readConsultationDb(id));
    }

    private Doctor readDoctorDb(Long id) throws Exception {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isPresent())
            return   doctorOptional.get() ;
        throw new Exception("Doctor not found id= " + id);
    }

    private Consultation readConsultationDb(Long id) throws Exception {
        Optional<Consultation> consultationOptional = consultationRepository.findById(id);
        if (consultationOptional.isPresent())
            return   consultationOptional.get() ;
        throw new Exception("Consultation not found id= " + id);
    }

    @Override
    public boolean updateDoctor(DoctorDTO doctor, Long id) {
        boolean action;
        try {
            Doctor dbDoctor = readDoctorDb(id);
            dbDoctor.setFirstName(doctor.getFirstName());
            dbDoctor.setLastName(doctor.getLastName());
            dbDoctor.setUsername(doctor.getUsername());
            dbDoctor.setDateOfBirth(doctor.getDateOfBirth());
            dbDoctor.setIsActive(doctor.getIsActive());
            doctorRepository.save(dbDoctor);
            action = true;
        } catch (Exception e) {
            action = false;
        }
        return action;
    }

    @Override
    public boolean updateConsultation(ConsultationDTO consult, Long id) {
        boolean action;
        try {
            Consultation dbConsult = readConsultationDb(id);
            dbConsult.setMedicationName(consult.getMedicationName());
            dbConsult.setDosage(consult.getDosage());
            dbConsult.setAdvice(consult.getAdvice());
            dbConsult.setConsultationDate(consult.getConsultationDate());
            consultationRepository.save(dbConsult);
            action = true;
        } catch (Exception e) {
            action = false;
        }
        return action;
    }

    @Override
    public boolean deleteDoctor(Long id) {
        boolean action;
        try {
            Doctor dbDoctor = readDoctorDb(id);
            doctorRepository.delete(dbDoctor);
            action = true;
        } catch (Exception e) {
            action = false;
        }
        return action;
    }

    @Override
    public boolean deleteConsultation(Long id) {
        boolean action;
        try {
            Consultation dbConsultation = readConsultationDb(id);
            consultationRepository.delete(dbConsultation);
            action = true;
        } catch (Exception e) {
            action = false;
        }
        return action;
    }

    @Override
    public String ping() {
        return "This is doctor's endpoints";
    }
}