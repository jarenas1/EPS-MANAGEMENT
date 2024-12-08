package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.entities.DateEntity;
import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.PatientEntity;
import com.todoTask.crud.repaso.entities.ShiftEntity;
import com.todoTask.crud.repaso.error_handler.*;
import com.todoTask.crud.repaso.repositories.DateRepository;
import com.todoTask.crud.repaso.repositories.DoctorRepository;
import com.todoTask.crud.repaso.repositories.PatientRepository;
import com.todoTask.crud.repaso.repositories.ShiftRepository;
import com.todoTask.crud.repaso.services.interfaces.IDateService;
import com.todoTask.crud.repaso.tools.enums.DateStatus;
import com.todoTask.crud.repaso.tools.enums.Day;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DateServiceImp implements IDateService {

    @Autowired
    DateRepository dateRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ShiftRepository shiftRepository;

    @Override
    public List<DateEntity> findByDoctorAndDateTimeBetween(Long doctorId, LocalDateTime start, LocalDateTime end) {
        Optional<DoctorEntity> optionalDoctorEntity = doctorRepository.findById(doctorId);
        if (optionalDoctorEntity.isPresent()){
            List<DateEntity> dates = dateRepository.findByDoctorAndDateTimeBetween(optionalDoctorEntity.get(), start,end);
            return dates;
        }else{
            throw new DoctorNotFoundException( String.format("We cant found the doctor with the id %a", doctorId));
        }
    }

    @Override
    public List<DateEntity> findByPatientAndStatus(Long PatientId, DateStatus status) {
        Optional<PatientEntity> optionalPatientEntity = patientRepository.findById(PatientId);
        if (optionalPatientEntity.isPresent()){
            List<DateEntity> dates = dateRepository.findByPatientAndStatus(optionalPatientEntity.get(), status);
            return dates;
        }else{
            throw new PatientNotFoundException(String.format("We cant found the patient with the id %a", PatientId));
        }
    }

    @Override
    public List<DateEntity> findByDoctorAndStatus(Long doctorId, DateStatus status) {
        Optional<DoctorEntity> optionalDoctorEntity = doctorRepository.findById(doctorId);
        if (optionalDoctorEntity.isPresent()){
            List<DateEntity> dates = dateRepository.findByDoctorAndStatus(optionalDoctorEntity.get(), status);
            return dates;
        }else{
            throw new DoctorNotFoundException( String.format("We cant found the doctor with the id %a", doctorId));
        }
    }

    @Transactional
    @Override
    public ResponseEntity<DateEntity> rescheduleDate(Long id, LocalDateTime newDate){
        DateEntity dateEntity = dateRepository.findById(id).orElseThrow(()->new DateNotFoundException("No encuentro la cita pa"));
        checkIfDoctorIsAvalible(dateEntity);
        validateConflictsDates(dateEntity);
        dateEntity.setDateTime(newDate);
        dateEntity.setStatus(DateStatus.SCHEDULED);
        DateEntity date = dateRepository.save(dateEntity);

        return ResponseEntity.ok(date);
    }

    @Transactional
    @Override
    public ResponseEntity<DateEntity> save(DateEntity dateEntity) {
        checkIfDoctorIsAvalible(dateEntity);
        validateConflictsDates(dateEntity);
        dateEntity.setStatus(DateStatus.SCHEDULED);
        DateEntity dateEntityCreated = dateRepository.save(dateEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(dateEntityCreated);
    }


    private void checkIfDoctorIsAvalible(DateEntity dateEntity) {
        Day day = Day.values()[dateEntity.getDateTime().getDayOfWeek().getValue() - 1];
        LocalTime dateHour = dateEntity.getDateTime().toLocalTime();

        List<ShiftEntity> dailyShifts = shiftRepository.findByDoctorAndDayAndActive(
                dateEntity.getDoctor(),
                day,
                true
        );
        boolean validShift = dailyShifts.stream()
                .anyMatch(shift ->
                        !dateHour.isBefore(shift.getStartTime()) &&
                                !dateHour.isAfter(shift.getEndTime())
                );
        if (!validShift) {
            throw new DateOverTheHourException("The date is over the doctor shift");
        }
    }

    private void validateConflictsDates(DateEntity dateEntity) {
        LocalDateTime startInterval = dateEntity.getDateTime();
        LocalDateTime endInterval = startInterval.plusMinutes(dateEntity.getDuration());

        List<DateEntity> existentDates = dateRepository.findByDoctorAndDateTimeBetween(
                dateEntity.getDoctor(),
                startInterval,
                endInterval
        );
        if (!existentDates.isEmpty()) {
            throw new ConflictDatesException("the date time is already in use");
        }
    }

    @Transactional
    public DateEntity cancelDate(Long dateId) {
        DateEntity dateEntity = dateRepository.findById(dateId)
                .orElseThrow(() -> new DateNotFoundException("Twe cant found the date"));
        dateEntity.setStatus(DateStatus.CANCELED);
        return dateRepository.save(dateEntity);
    }

    @Override
    public ResponseEntity<DateEntity> update(DateEntity dateEntity) {
        DateEntity dateUpdated = dateRepository.save(dateEntity);
        return ResponseEntity.ok(dateUpdated);
    }

    @Transactional
    @Override
    public ResponseEntity<DateEntity>putDateAsDone(Long dateId){
        Optional<DateEntity> dateEntityOptional = dateRepository.findById(dateId);
        if (dateEntityOptional.isPresent()){
            dateEntityOptional.get().setStatus(DateStatus.DONE);
            DateEntity dateEntity = dateRepository.save(dateEntityOptional.get());
            return ResponseEntity.ok(dateEntity);
        }else{
            throw new DateNotFoundException("We cant found the date with this id");
        }
    }

    @Override
    public Page<DateEntity> findAll(Pageable pageable) {
        return dateRepository.findAll(pageable);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<DateEntity> dateOptional = dateRepository.findById(id);
        if (dateOptional.isPresent()){
            dateRepository.delete(dateOptional.get());
            return true;
        }else{
            throw  new DateNotFoundException(String.format("We can't find a date with the id %s", id));
        }
    }
}