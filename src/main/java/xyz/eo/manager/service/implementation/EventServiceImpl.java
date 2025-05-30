package xyz.eo.manager.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.eo.manager.dto.request.event.AddUpdateEventRequest;
import xyz.eo.manager.dto.response.GetUserPermissionsResponse;
import xyz.eo.manager.dto.response.event.AddUpdateEventResponse;
import xyz.eo.manager.entity.Banquet;
import xyz.eo.manager.entity.EventDetail;
import xyz.eo.manager.exception.ErrorMessageException;
import xyz.eo.manager.repository.BanquetRepository;
import xyz.eo.manager.repository.EventDetailRepository;
import xyz.eo.manager.repository.UserBanquetDetailRepository;
import xyz.eo.manager.service.EventService;
import xyz.eo.manager.service.UserService;
import xyz.eo.manager.util.ErrorMessage;

@Service
public class EventServiceImpl implements EventService {

    private final ModelMapper modelMapper;
    private final EventDetailRepository eventDetailRepository;
    private final UserService userService;
    private final UserBanquetDetailRepository ubdRepository;

    public EventServiceImpl(ModelMapper modelMapper, EventDetailRepository eventDetailRepository, BanquetRepository banquetRepository, UserService userService, UserBanquetDetailRepository ubdRepository) {
        this.modelMapper = modelMapper;
        this.eventDetailRepository = eventDetailRepository;
        this.userService = userService;
        this.ubdRepository = ubdRepository;
    }

    @Override
    @Transactional
    public AddUpdateEventResponse addUpdateEvent(AddUpdateEventRequest request) {
        GetUserPermissionsResponse permissions = userService.getUserPermissions(request.getUserId());
        if (!permissions.getCanAddUpdateEvent())
            throw new ErrorMessageException("You don't have required permissions to add/update the event", 0);

        if (ubdRepository.getUbdByUserAndBanquet(request.getUserId(), request.getBanquetId()).isEmpty())
            throw new ErrorMessageException("User banquet mapping not found", 0);

        else if (request.getEventId() == null){  //add a new event
            boolean isInvalidRequest =
                    request.getMealTime() == null ||
                            request.getFunctionType() == null ||
                            request.getDateBooked() == null ||
                            request.getMenuTypeId() == null ||
                            request.getPaxCount() == null || request.getPaxCount() == 0;
            if (isInvalidRequest)
                throw new ErrorMessageException("Invalid request", 0);
            EventDetail event = modelMapper.map(request, EventDetail.class);
            eventDetailRepository.save(event);
            return modelMapper.map(event, AddUpdateEventResponse.class);
        } else {    //update the event
            EventDetail event = eventDetailRepository.findById(request.getEventId()).orElseThrow(() -> new ErrorMessageException(ErrorMessage.RESOURCE_NOT_FOUND, 0));
            modelMapper.map(request, event);
            eventDetailRepository.save(event);
            return modelMapper.map(event, AddUpdateEventResponse.class);
        }
    }
}
