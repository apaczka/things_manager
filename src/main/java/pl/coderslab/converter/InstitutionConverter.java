package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.model.Institution;
import pl.coderslab.service.InstitutionService;

public class InstitutionConverter implements Converter<String, Institution> {

    @Autowired
    private InstitutionService institutionService;

    @Override
    public Institution convert(String s) {
        return institutionService.findById(Long.parseLong(s));
    }


}
