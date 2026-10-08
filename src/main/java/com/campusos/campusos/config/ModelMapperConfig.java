package com.campusos.campusos.config;


import com.campusos.campusos.student.dto.CreateStudentRequest;
import com.campusos.campusos.student.entity.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper=new ModelMapper();


        modelMapper.getConfiguration()
                //setMatchingStrategy STRICT nesting mismatching nhi karta
                .setMatchingStrategy(MatchingStrategies.STRICT)
                //jo values null hongi usko skip kr dega
                        .setSkipNullEnabled(true);

        //Student Long id mismatched configuration
        modelMapper.typeMap(CreateStudentRequest.class, Student.class)
                .addMappings(mapper->{
                    mapper.skip(Student::setId);
//                    mapper.skip(Student::setUser);
                });
        return modelMapper;
    }
}
