package com.poc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TokenController {

    @Resource
    private ConsumerTokenServices tokenServices;

    @RequestMapping(method = RequestMethod.DELETE, value = "/oauth/token/{tokenId:.*}")
    @ResponseBody
    public ResponseEntity<?> revokeToken(@PathVariable String tokenId) {
        tokenServices.revokeToken(tokenId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
