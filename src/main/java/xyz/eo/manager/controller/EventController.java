package xyz.eo.manager.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.eo.manager.dto.request.event.AddUpdateEventRequest;
import xyz.eo.manager.dto.response.event.AddUpdateEventResponse;
import xyz.eo.manager.service.EventService;
import xyz.eo.manager.util.endpoints.EventEndpoints;

@RestController
@RequestMapping(EventEndpoints.EVENT)
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(EventEndpoints.ADD_UPDATE_EVENT)
    public ResponseEntity<AddUpdateEventResponse> addUpdateEvent(@RequestBody @Valid AddUpdateEventRequest request){
        AddUpdateEventResponse response = eventService.addUpdateEvent(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
