package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
		@Autowired
        public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

		public List<Student> getStudents() {
		return studentRepository.findAll();
	}
		public void createstudent(Student student){
			Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
			if(studentByEmail.isPresent()){
				throw new IllegalStateException("this email already exist");
			}
			studentRepository.save(student);
			System.out.println(student);
		}
		public void deleteStudent(Long studentId){
			boolean exists = studentRepository.existsById((studentId));
			if(!exists) {
				throw new IllegalStateException("this Student doesn't exist to be deleted");
			}
			studentRepository.deleteById(studentId);
		}
}
 