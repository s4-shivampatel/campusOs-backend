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

        // nesting mismatching nhi karta
//        modelMapper.getConfiguration()
//                .setMatchingStrategy(MatchingStrategies.STRICT);

        //Student Long id mismatched configuration
        modelMapper.typeMap(CreateStudentRequest.class, Student.class)
                .addMappings(mapper->{
                    mapper.skip(Student::setId);
//                    mapper.skip(Student::setUser);
                });
        return modelMapper;
    }
}
