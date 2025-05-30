package xyz.eo.manager.service;

import xyz.eo.manager.dto.request.AddUpdateEventTypeRequest;
import xyz.eo.manager.dto.response.AddUpdateEventTypeResponse;
import xyz.eo.manager.dto.response.GetAllEventTypesResponse;
import xyz.eo.manager.dto.response.StatusUpdateResponse;

public interface EventTypeService {
    AddUpdateEventTypeResponse addUpdateEventType(AddUpdateEventTypeRequest request);
    StatusUpdateResponse deleteEventType(Long eventTypeId, Long userId, Long banquetId);
    GetAllEventTypesResponse getAllEventTypesByBanquet(Long userId, Long banquetId);
    GetAllEventTypesResponse getDefaultEventTypes();
}
