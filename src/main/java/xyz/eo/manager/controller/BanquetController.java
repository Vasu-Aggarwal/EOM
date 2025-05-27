package xyz.eo.manager.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.eo.manager.dto.request.banquet.AddUpdateBanquetRequest;
import xyz.eo.manager.dto.request.banquet.UpdateUserBanquetStatusRequest;
import xyz.eo.manager.dto.response.StatusUpdateResponse;
import xyz.eo.manager.dto.response.banquet.GetBanquetDetailsByIdResponse;
import xyz.eo.manager.service.BanquetService;
import xyz.eo.manager.util.endpoints.BanquetEndpoints;

@RestController
@RequestMapping(BanquetEndpoints.BANQUET)
public class BanquetController {

    @Autowired
    private BanquetService banquetService;

    @PostMapping(BanquetEndpoints.ADD_UPDATE_BANQUET_DETAILS)
    public ResponseEntity<GetBanquetDetailsByIdResponse> addUpdateBanquetDetails(HttpServletRequest request,
                                                                                 @RequestBody @Valid AddUpdateBanquetRequest addUpdateBanquetRequest){
        GetBanquetDetailsByIdResponse banquetDetailsByIdResponse =
                banquetService.addUpdateBanquetDetails((Integer) request.getAttribute("roleId"),
                        addUpdateBanquetRequest);
        return new ResponseEntity<>(banquetDetailsByIdResponse, HttpStatus.OK);
    }

    @GetMapping(BanquetEndpoints.GET_BANQUET_DETAILS)
    public ResponseEntity<GetBanquetDetailsByIdResponse> getBanquetDetailsById(@PathVariable Long banquetId){
        GetBanquetDetailsByIdResponse banquetDetailsByIdResponse = banquetService.getBanquetDetails(banquetId);
        return new ResponseEntity<>(banquetDetailsByIdResponse, HttpStatus.OK);
    }

    @PatchMapping(BanquetEndpoints.UPDATE_USER_BANQUET_STATUS)
    public ResponseEntity<StatusUpdateResponse> updateUserBanquetStatus(HttpServletRequest request, @RequestBody @Valid UpdateUserBanquetStatusRequest updateUserBanquetStatusRequest){
        StatusUpdateResponse statusUpdateResponse = banquetService.updateUserBanquetStatus((Integer) request.getAttribute("roleId"), updateUserBanquetStatusRequest);
        return new ResponseEntity<>(statusUpdateResponse, HttpStatus.OK);
    }

}
