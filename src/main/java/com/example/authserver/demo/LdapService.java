package com.example.authserver.demo;

import com.sun.jndi.ldap.LdapCtx;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapEntryIdentification;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import javax.naming.directory.DirContext;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class LdapService {
    @Autowired
    LdapTemplate ldapTemplate;

    @Value("${ldap.base}")
    String base;

    @Value("${ldap.host}")
    String host;

    @Value("${ldap.port}")
    int port;

    @Autowired
    LdapContextSource ldapContextSource;


    public LdapService(LdapTemplate template,LdapContextSource source){
        this.ldapTemplate = template;
        this.ldapContextSource = source;
    }

    public List<Map<String,Object>> findUserByName(String name){
        LdapContextSource ldapContextSourceTmp = (LdapContextSource) ldapTemplate.getContextSource();
        ldapContextSourceTmp.getBaseLdapName().toString();
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass","*"));
        andFilter.and(new EqualsFilter("cn",name));
        log.info("init search at findUserByName");
        return ldapTemplate.search("",andFilter.encode(),new LdapAttributesMapper());
    }

    public List<Map<String,Object>> findByLogin(String login){
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass","person"));
        andFilter.and(new EqualsFilter("uid",login));
        return ldapTemplate.search("",andFilter.encode(),new LdapAttributesMapper());
    }

    public List<Map<String,Object>> findAll() throws Exception {
        LdapContextSource source = (LdapContextSource)ldapTemplate.getContextSource();
        String name = source.getBaseLdapPathAsString();
        System.out.println(name);
        return ldapTemplate.search("","(objectclass=person)",new LdapAttributesMapper());
    }

}
