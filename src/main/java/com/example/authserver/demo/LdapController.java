package com.example.authserver.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/ldap")
@RequiredArgsConstructor
public class LdapController {
    private final LdapService service;


    @GetMapping("search/name/{name}")
    public List<Map<String,Object>> getUserByName(@PathVariable("name")String name){
        return service.findUserByName(name);
    }

    @GetMapping("/search/login/{login}")
    public List<Map<String,Object>> getUserByLogin(@PathVariable("login") String login){
        return service.findByLogin(login);
    }

    @GetMapping("/all")
    public List<Map<String,Object>> getUsers() throws Exception {

        return service.findAll();
    }
}
