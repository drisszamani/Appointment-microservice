package com.security.med.suivi_rendezvous.service;

import com.security.med.suivi_rendezvous.dto.AppointmentRequest;
import com.security.med.suivi_rendezvous.dto.AppointmentResponse;

import com.security.med.suivi_rendezvous.mapper.AppointmentMapper;
import com.security.med.suivi_rendezvous.model.Appointment;
import com.security.med.suivi_rendezvous.repository.AppointmentRepository;
import com.security.med.suivi_rendezvous.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public AppointmentResponse createAppointment(AppointmentRequest request) {
        Appointment appointment = appointmentMapper.requestToEntity(request);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.entityToResponse(savedAppointment);
    }

    @Override
    public List<AppointmentResponse> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponse getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found."));
        return appointmentMapper.entityToResponse(appointment);
    }

    @Override
    public AppointmentResponse updateAppointment(Long id, AppointmentRequest request) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found."));

        // Update fields
        existingAppointment.setPatientId(request.getPatientId());
        existingAppointment.setDoctorId(request.getDoctorId());
        existingAppointment.setAppointmentDateTime(request.getAppointmentDateTime());
        existingAppointment.setStatus(request.getStatus());
        existingAppointment.setNotes(request.getNotes());
        existingAppointment.setRemoteAppointment(request.getRemoteAppointment());
        existingAppointment.setLocationDetails(request.getLocationDetails());

        Appointment updatedAppointment = appointmentRepository.save(existingAppointment);
        return appointmentMapper.entityToResponse(updatedAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found."));
        appointmentRepository.delete(appointment);
    }
}
