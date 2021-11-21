package com.vbeskoro.easyrentbackend.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {

    @Autowired
    private AppUserService appUserService;
}
