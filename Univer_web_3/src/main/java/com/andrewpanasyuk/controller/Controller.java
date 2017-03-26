package com.andrewpanasyuk.controller;

import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.service.*;

public class Controller {
	public static GroupService groupService;
	public static StudentService studentService;
	public static TeacherService teacherService;
	public static ScheduleService scheduleService;
	
	static {
		groupService = new GroupDao();
		studentService = new StudentDao();
		teacherService = new TeacherDao();
		scheduleService = new ScheduleDao();
	}

}
