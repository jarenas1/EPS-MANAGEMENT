package com.todoTask.crud.repaso.controllers;

import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.ShiftEntity;
import com.todoTask.crud.repaso.services.interfaces.IShiftService;
import com.todoTask.crud.repaso.tools.enums.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;

@RestController
@RequestMapping("/shifts")
public class ShiftController {

    @Autowired
    IShiftService iShiftService;

    @GetMapping("/doctor-day-active")
    public ResponseEntity<List<ShiftEntity>> getShiftsByDoctorDayAndActive(
            @RequestParam Long doctorId,
            @RequestParam Day day,
            @RequestParam Boolean active) {

        return ResponseEntity.ok(iShiftService.findByDoctorAndDayAndActive(doctorId,day,active));
    }

    @GetMapping("/doctor-active")
    public ResponseEntity<List<ShiftEntity>> getShiftsByDoctorAndActive(
            @RequestParam Long doctorId,
            @RequestParam Boolean active) {

        DoctorEntity doctor = new DoctorEntity();
        doctor.setId(doctorId);

        List<ShiftEntity> shifts = iShiftService.findByDoctorAndActive(doctor, active);
        return ResponseEntity.ok(shifts);
    }

    @GetMapping
    public ResponseEntity<Page<ShiftEntity>> getAllShifts(Pageable pageable) {
        Page<ShiftEntity> shifts = iShiftService.findAll(pageable);
        return ResponseEntity.ok(shifts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftEntity> getShiftById(@PathVariable Long id) {
        ShiftEntity shift = iShiftService.findById(id);
        return ResponseEntity.ok(shift);
    }

    @PostMapping
    public ResponseEntity<ShiftEntity> createShift(@RequestBody ShiftEntity shiftEntity) {
        return iShiftService.save(shiftEntity);
    }

    @PutMapping("/update")
    public ResponseEntity<ShiftEntity> updateShift(@RequestBody ShiftEntity shiftEntity) {
        return iShiftService.update(shiftEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ShiftEntity> deleteShift(@PathVariable Long id) {
        ShiftEntity deletedShift = iShiftService.delete(id);
        return ResponseEntity.ok(deletedShift);
    }
}
