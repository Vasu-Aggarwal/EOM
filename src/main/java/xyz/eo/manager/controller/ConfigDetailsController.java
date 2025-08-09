package xyz.eo.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.eo.manager.dto.response.ConfigKeyResponse;
import xyz.eo.manager.service.ConfigDetailsService;

@RestController
@RequestMapping("api/config")
public class ConfigDetailsController {

    @Autowired
    private ConfigDetailsService configDetailsService;

    @GetMapping("/getConfigKey/{key}")
    public ResponseEntity<ConfigKeyResponse> getConfigKey(@PathVariable String key){
        return new ResponseEntity<>(configDetailsService.getConfigKeyDetails(key), HttpStatus.OK);
    }
}
