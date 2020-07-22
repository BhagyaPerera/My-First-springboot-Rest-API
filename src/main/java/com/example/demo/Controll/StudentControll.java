package com.example.demo.Controll;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repo.StudentRepo;

import com.example.demo.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class StudentControll {
	
	@Autowired
	private StudentRepo repo;
	
	
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student) {
        return repo.save(student);
    }
	
	@GetMapping ("/students")
	public List<Student> getAllStudents(){
		
		return repo.findAll();
		
	}
	
	@GetMapping("/students/{id}")
    public ResponseEntity < Student > getStudentById(@PathVariable(value = "id") Integer id)
    throws ResourceNotFoundException {
        Student student = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + id));
        return ResponseEntity.ok().body(student);
    }

	

    
	
	@DeleteMapping("/students/{id}")
    public Map < String, Boolean > deleteStudent(@PathVariable(value = "id") Integer id)
    throws ResourceNotFoundException {
        Student student = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " +id));

        repo.delete(student);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
 
	
	
	
}

