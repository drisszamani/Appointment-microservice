package com.security.med.suivi_rendezvous.service;

import com.security.med.suivi_rendezvous.dto.AppointmentRequest;
import com.security.med.suivi_rendezvous.dto.AppointmentResponse;

import java.util.List;

public interface AppointmentService {

    AppointmentResponse createAppointment(AppointmentRequest request);

    List<AppointmentResponse> getAllAppointments();

    AppointmentResponse getAppointmentById(Long id);

    AppointmentResponse updateAppointment(Long id, AppointmentRequest request);

    void deleteAppointment(Long id);
}
