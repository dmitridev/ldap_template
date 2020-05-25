package com.example.authserver.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfiguration {
    @Value("${ldap.url}")
    public String url;
    @Value("${ldap.base}")
    public String base;
    @Value("${ldap.login}")
    public String login;
    @Value("${ldap.password}")
    public String password;

    @Bean
    public LdapContextSource contextSource(){

        LdapContextSource source = new LdapContextSource();
        source.setUrl(this.url);
        source.setBase(this.base);
        source.setPassword(this.password);
        source.setUserDn(login);
        return source;
    }

    @Bean
    public LdapTemplate template(){
        LdapTemplate template = new LdapTemplate();
        template.setContextSource(contextSource());
        return template;
    }



}
