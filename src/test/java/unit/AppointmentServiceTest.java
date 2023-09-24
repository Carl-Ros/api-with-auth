package unit;

import com.carlros.secureapi.model.Appointment;
import com.carlros.secureapi.repository.AppointmentRepository;
import com.carlros.secureapi.service.AppointmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {
    @Mock
    private AppointmentRepository repository;

    @InjectMocks
    private AppointmentService service;

    @Test
    @DisplayName("Update appointment")
    void update() {
        Long id = 1L;
        Appointment appointment = new Appointment();
        appointment.setId(id);
        appointment.setDateTime(LocalDateTime.parse("2023-01-01T00:00:00"));

        when(repository.findById(appointment.getId())).thenReturn(Optional.of(appointment));

        Appointment updateAppointment = new Appointment();
        updateAppointment.setDateTime(LocalDateTime.parse("2023-02-01T00:00:00"));

        service.update(id, updateAppointment);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(appointment);

        assertEquals(LocalDateTime.parse("2023-02-01T00:00:00"), appointment.getDateTime());
    }
}