package com.security.med.suivi_rendezvous.mapper;

import com.security.med.suivi_rendezvous.dto.AppointmentRequest;
import com.security.med.suivi_rendezvous.dto.AppointmentResponse;
import com.security.med.suivi_rendezvous.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mappings({
            @Mapping(source = "patientId", target = "patientId"),
            @Mapping(source = "doctorId", target = "doctorId"),
            @Mapping(source = "appointmentDateTime", target = "appointmentDateTime"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "notes", target = "notes"),
            @Mapping(source = "remoteAppointment", target = "remoteAppointment"),
            @Mapping(source = "locationDetails", target = "locationDetails")
    })
    Appointment requestToEntity(AppointmentRequest request);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "patientId", target = "patientId"),
            @Mapping(source = "doctorId", target = "doctorId"),
            @Mapping(source = "appointmentDateTime", target = "appointmentDateTime"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "notes", target = "notes"),
            @Mapping(source = "remoteAppointment", target = "remoteAppointment"),
            @Mapping(source = "locationDetails", target = "locationDetails")
    })
    AppointmentResponse entityToResponse(Appointment appointment);
}
