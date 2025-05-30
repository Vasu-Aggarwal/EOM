package xyz.eo.manager.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.eo.manager.dto.request.AddUpdateEventTypeRequest;
import xyz.eo.manager.dto.response.AddUpdateEventTypeResponse;
import xyz.eo.manager.dto.response.GetAllEventTypesResponse;
import xyz.eo.manager.dto.response.StatusUpdateResponse;
import xyz.eo.manager.service.EventTypeService;
import xyz.eo.manager.util.endpoints.EventEndpoints;

@RestController
@RequestMapping(EventEndpoints.EVENT_TYPE)
public class EventTypeController {

    @Autowired
    private EventTypeService eventTypeService;

    @PostMapping(EventEndpoints.ADD_UPDATE_EVENT_TYPE)
    public ResponseEntity<AddUpdateEventTypeResponse> addUpdateEventType(@RequestBody @Valid AddUpdateEventTypeRequest request){
        return new ResponseEntity<>(eventTypeService.addUpdateEventType(request), HttpStatus.CREATED);
    }

    @GetMapping(EventEndpoints.GET_ALL_EVENT_TYPE_BY_USER_BANQUET)
    public ResponseEntity<GetAllEventTypesResponse> getAllEventTypesByUserBanquet (@RequestParam Long userId, @RequestParam Long banquetId){
        return new ResponseEntity<>(eventTypeService.getAllEventTypesByBanquet(userId, banquetId), HttpStatus.CREATED);
    }

    @GetMapping(EventEndpoints.GET_DEFAULT_EVENT_TYPES)
    public ResponseEntity<GetAllEventTypesResponse> getDefaultEventTypes (){
        return new ResponseEntity<>(eventTypeService.getDefaultEventTypes(), HttpStatus.CREATED);
    }

    @DeleteMapping(EventEndpoints.DELETE_EVENT_TYPE)
    public ResponseEntity<StatusUpdateResponse> deleteEventType(@PathVariable Long eventTypeId, @RequestParam Long userId, @RequestParam Long banquetId){
        return new ResponseEntity<>(eventTypeService.deleteEventType(eventTypeId, userId, banquetId), HttpStatus.OK);
    }
}
