package com.security.med.suivi_rendezvous.repository;


import com.security.med.suivi_rendezvous.model.Appointment;
import com.security.med.suivi_rendezvous.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByAppointmentDateTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Appointment> findByPatientIdAndStatus(Long patientId, AppointmentStatus status);
    List<Appointment> findByDoctorIdAndStatus(Long doctorId, AppointmentStatus status);

    @Query("SELECT a FROM Appointment a WHERE a.patientId = :patientId AND a.appointmentDateTime >= :now ORDER BY a.appointmentDateTime")
    List<Appointment> findUpcomingAppointmentsByPatient(@Param("patientId") Long patientId, @Param("now") LocalDateTime now);

    @Query("SELECT a FROM Appointment a WHERE a.doctorId = :doctorId AND a.appointmentDateTime >= :now ORDER BY a.appointmentDateTime")
    List<Appointment> findUpcomingAppointmentsByDoctor(@Param("doctorId") Long doctorId, @Param("now") LocalDateTime now);

    @Query("SELECT COUNT(a) > 0 FROM Appointment a WHERE a.doctorId = :doctorId AND a.appointmentDateTime BETWEEN :start AND :end")
    boolean hasConflictingAppointment(@Param("doctorId") Long doctorId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}