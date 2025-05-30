package xyz.eo.manager.service.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import xyz.eo.manager.dto.model.UserBanquetDetailAndRoleDto;
import xyz.eo.manager.dto.request.AddUpdateEventTypeRequest;
import xyz.eo.manager.dto.response.AddUpdateEventTypeResponse;
import xyz.eo.manager.entity.EventType;
import xyz.eo.manager.entity.UserBanquetDetail;
import xyz.eo.manager.exception.ErrorMessageException;
import xyz.eo.manager.repository.ConfigDetailsRepository;
import xyz.eo.manager.repository.EventTypeRepository;
import xyz.eo.manager.repository.UserBanquetDetailRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EventTypeServiceImplTest {

    @Mock
    private EventTypeRepository eventTypeRepository;

    @Mock
    private UserBanquetDetailRepository userBanquetDetailRepository;

    @Mock
    private ConfigDetailsRepository configDetailsRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EventTypeServiceImpl service;

    @Test
    void testAddEventType_Success() {
        AddUpdateEventTypeRequest request = AddUpdateEventTypeRequest.builder()
                .userId(1L)
                .banquetId(100L)
                .eventTypeName("Wedding")
                .build();

        // Mocking user-banquet exists
        Mockito.when(userBanquetDetailRepository.getActiveUbdByUserAndBanquet(1L, 100L))
                .thenReturn(Optional.of(new UserBanquetDetailAndRoleDto(1, 1))); // anything non-empty

        EventType mockEntity = new EventType();
        Mockito.when(modelMapper.map(request, EventType.class)).thenReturn(mockEntity);
        Mockito.when(eventTypeRepository.save(mockEntity)).thenReturn(mockEntity);

        AddUpdateEventTypeResponse expectedResponse = new AddUpdateEventTypeResponse();
        Mockito.when(modelMapper.map(mockEntity, AddUpdateEventTypeResponse.class)).thenReturn(expectedResponse);

        AddUpdateEventTypeResponse actualResponse = service.addUpdateEventType(request);

        Assertions.assertNotNull(actualResponse);
        Mockito.verify(eventTypeRepository).save(mockEntity);
    }

    @Test
    void testUpdateEventType_Success() {
        AddUpdateEventTypeRequest request = AddUpdateEventTypeRequest.builder()
                .userId(1L)
                .banquetId(100L)
                .eventTypeId(10L)
                .eventTypeName("Birthday")
                .build();

        Mockito.when(userBanquetDetailRepository.getActiveUbdByUserAndBanquet(1L, 100L))
                .thenReturn(Optional.of(new UserBanquetDetailAndRoleDto(1, 1)));

        EventType existing = new EventType();
        Mockito.when(eventTypeRepository.findById(10L)).thenReturn(Optional.of(existing));

        Mockito.doAnswer(invocation -> {
            AddUpdateEventTypeRequest source = invocation.getArgument(0);
            EventType dest = invocation.getArgument(1);
            dest.setEventTypeName(source.getEventTypeName());
            return null;
        }).when(modelMapper).map(Mockito.eq(request), Mockito.eq(existing));
        Mockito.when(eventTypeRepository.save(existing)).thenReturn(existing);
        Mockito.when(modelMapper.map(existing, AddUpdateEventTypeResponse.class)).thenReturn(new AddUpdateEventTypeResponse());

        AddUpdateEventTypeResponse response = service.addUpdateEventType(request);

        Assertions.assertNotNull(response);
        Mockito.verify(eventTypeRepository).save(existing);
    }

    @Test
    void testAddEventType_UserBanquetNotFound() {
        AddUpdateEventTypeRequest request = AddUpdateEventTypeRequest.builder()
                .userId(1L)
                .banquetId(100L)
                .build();

        Mockito.when(userBanquetDetailRepository.getActiveUbdByUserAndBanquet(1L, 100L))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ErrorMessageException.class, () -> {
            service.addUpdateEventType(request);
        });
    }
}