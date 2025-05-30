package xyz.eo.manager.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.eo.manager.dto.request.AddUpdateEventTypeRequest;
import xyz.eo.manager.dto.response.AddUpdateEventTypeResponse;
import xyz.eo.manager.dto.response.GetAllEventTypesResponse;
import xyz.eo.manager.dto.response.StatusUpdateResponse;
import xyz.eo.manager.entity.EventType;
import xyz.eo.manager.exception.ErrorMessageException;
import xyz.eo.manager.repository.ConfigDetailsRepository;
import xyz.eo.manager.repository.EventTypeRepository;
import xyz.eo.manager.repository.UserBanquetDetailRepository;
import xyz.eo.manager.service.EventTypeService;
import xyz.eo.manager.util.enums.GenericStatus;

import java.util.List;

@Service
public class EventTypeServiceImpl implements EventTypeService {

    private final EventTypeRepository repository;
    private final ModelMapper modelMapper;
    private final UserBanquetDetailRepository ubdRepo;
    private final ConfigDetailsRepository configRepo;
    public EventTypeServiceImpl(EventTypeRepository repository, ModelMapper modelMapper, UserBanquetDetailRepository ubdRepo, ConfigDetailsRepository configRepo) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.ubdRepo = ubdRepo;
        this.configRepo = configRepo;
    }

    @Override
    @Transactional
    public AddUpdateEventTypeResponse addUpdateEventType(AddUpdateEventTypeRequest request) {
        // Validate user-banquet mapping
        ubdRepo.getActiveUbdByUserAndBanquet(request.getUserId(), request.getBanquetId())
                .orElseThrow(() -> new ErrorMessageException("User-Banquet mapping not found", 0));

        EventType eventType;
        if (request.getEventTypeId() != null) {
            // Update case
            eventType = repository.findById(request.getEventTypeId())
                    .orElseThrow(() -> new ErrorMessageException("Event Type not found", 0));
            modelMapper.map(request, eventType);
        } else {
            // Add case
            eventType = modelMapper.map(request, EventType.class);
        }

        eventType.setStatus(GenericStatus.ACTIVE.getStatus());
        repository.save(eventType);
        return modelMapper.map(eventType, AddUpdateEventTypeResponse.class);
    }

    @Override
    public StatusUpdateResponse deleteEventType(Long eventTypeId, Long userId, Long banquetId) {
        EventType eventType = repository.findEventTypeByUserBanquet(userId, banquetId, eventTypeId).orElseThrow(() -> new ErrorMessageException("User banquet mapping not found", 0));
        eventType.setStatus(GenericStatus.DELETED.getStatus());
        repository.save(eventType);
        return new StatusUpdateResponse("Event Type deleted successfully!");
    }

    @Override
    public GetAllEventTypesResponse getAllEventTypesByBanquet(Long userId, Long banquetId) {
        List<AddUpdateEventTypeResponse> eventTypeList = repository.findAllEventTypeByUserBanquet(userId, banquetId).orElseThrow(() -> new ErrorMessageException("Something went wrong", 0));
        return new GetAllEventTypesResponse(eventTypeList);
    }

    @Override
    public GetAllEventTypesResponse getDefaultEventTypes() {
        String value = configRepo.findByKey("default_event_types").orElseThrow(() -> new ErrorMessageException("Something went wrong", 0));
//        return new GetAllEventTypesResponse(modelMapper.map(value, AddUpdateEventTypeResponse.class));
        return null;
    }
}
