package xyz.eo.manager.service;

import xyz.eo.manager.dto.request.event.AddUpdateEventRequest;
import xyz.eo.manager.dto.response.event.AddUpdateEventResponse;

public interface EventService {
    AddUpdateEventResponse addUpdateEvent(AddUpdateEventRequest request);
}
