package com.recommend.app;

import com.recommend.utils.errors.*;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class HomeController {

    @Autowired
    private CacheRepository cacheRepository;

    @GetMapping("/home")
    public List<Movies> moviesAPI(
        @RequestParam Map<String, String> requestParam
    ) {
        return cacheRepository.findByType(requestParam.get("type"));
    }
}
