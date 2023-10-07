package unit;

import com.carlros.secureapi.model.TimerTask;
import com.carlros.secureapi.repository.TimerTaskRepository;
import com.carlros.secureapi.service.TimerTaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimerTaskServiceTest {
    @Mock
    private TimerTaskRepository repository;

    @InjectMocks
    private TimerTaskService service;
    @Test
    void update() {
        Long id = 1L;
        TimerTask timerTask = new TimerTask();
        timerTask.setId(id);
        timerTask.setDurationInDays(7L);
        Long workspaceId = 1L;

        when(repository.findByIdAndWorkspaceId(timerTask.getId(), workspaceId)).thenReturn(Optional.of(timerTask));

        TimerTask updateTimerTask = new TimerTask();

        updateTimerTask.setDurationInDays(7L);
        service.update(workspaceId, id, updateTimerTask);


        verify(repository, times(1)).findByIdAndWorkspaceId(id, workspaceId);
        verify(repository, times(1)).save(timerTask);

        assertEquals(7L, timerTask.getDurationInDays());
    }
}