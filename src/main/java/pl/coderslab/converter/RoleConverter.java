package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.model.Institution;
import pl.coderslab.model.UserRole;
import pl.coderslab.service.InstitutionService;
import pl.coderslab.service.UserService;

public class RoleConverter implements Converter<String, UserRole> {

    @Autowired
    private UserService userService;

    @Override
    public UserRole convert(String s) {
        return userService.findRole(Long.parseLong(s));
    }
}
