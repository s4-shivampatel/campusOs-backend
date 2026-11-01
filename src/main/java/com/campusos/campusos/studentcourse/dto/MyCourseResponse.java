
package com.campusos.campusos.studentcourse.dto;

import com.campusos.campusos.course.dto.CourseUnitResponse;
import com.campusos.campusos.course.dto.CourseMaterialResponse;
import com.campusos.campusos.assignment.dto.AssignmentResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MyCourseResponse {

    private Long id;
    private String name;
    private String code;

    private Integer credits;
    private Integer semester;

    private String faculty;
    private String category;
    private String description;

    private Integer progress;
    private Integer completedTopics;
    private Integer totalTopics;

    private List<CourseUnitResponse> units = new ArrayList<>();

    private List<CourseMaterialResponse> materials = new ArrayList<>();

    private List<AssignmentResponse> assignments = new ArrayList<>();
}
