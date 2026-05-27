package com.RafA.tft_tracker.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RafA.tft_tracker.config.RiotApiConfig;
import com.RafA.tft_tracker.service.RiotApiService;

import tools.jackson.databind.JsonNode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/test")
public class TestController {
    private RiotApiService riotApiService;
    public TestController(RiotApiService riotApiService){
        this.riotApiService = riotApiService;
    }

    @GetMapping("/account/{gameName}/{tagLine}")
    public JsonNode getAccount(@PathVariable String gameName, @PathVariable String tagLine) {
        return riotApiService.getAccountByRiotID(gameName, tagLine);
    }
    
}
