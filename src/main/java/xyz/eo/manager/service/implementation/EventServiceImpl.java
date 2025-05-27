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
import xyz.eo.manager.service.EventService;
import xyz.eo.manager.service.UserService;
import xyz.eo.manager.util.ErrorMessage;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventDetailRepository eventDetailRepository;

    @Autowired
    private BanquetRepository banquetRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public AddUpdateEventResponse addUpdateEvent(AddUpdateEventRequest request) {
        GetUserPermissionsResponse permissions = userService.getUserPermissions(request.getUserId());
        if (!permissions.getCanAddUpdateEvent())
            throw new ErrorMessageException("You don't have required permissions to add/update the event", 0);

        else if (request.getEventId() == null){  //add a new event
            EventDetail event = modelMapper.map(request, EventDetail.class);
            eventDetailRepository.save(event);
            return modelMapper.map(event, AddUpdateEventResponse.class);
        } else {    //update the event
            EventDetail event = eventDetailRepository.findById(request.getEventId()).orElseThrow(() -> new ErrorMessageException(ErrorMessage.RESOURCE_NOT_FOUND, 0));
            Banquet banquet = banquetRepository.getBanquetById(request.getBanquetId()).orElseThrow(() -> new ErrorMessageException("Banquet not found", 0));
            event = modelMapper.map(request, EventDetail.class);
            eventDetailRepository.save(event);
            return modelMapper.map(event, AddUpdateEventResponse.class);
        }
    }
}
