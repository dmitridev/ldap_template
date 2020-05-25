package com.example.authserver.demo;

import lombok.SneakyThrows;
import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import java.util.*;

public class LdapAttributesMapper implements AttributesMapper<Map<String,Object>> {
    @SneakyThrows
    @Override
    public Map<String, Object> mapFromAttributes(Attributes attributes) throws NamingException {
        Map<String, Object> map = new HashMap<>();

        NamingEnumeration enumeration = attributes.getAll();

        while (enumeration.hasMoreElements()) {

            Attribute attr = (Attribute) enumeration.next();

            if (attr != null) {
                map.put(attr.getID(), attr.get());
            }

        }

        return map;
    }
}
