package com.example.exam_ha_minh_tu.controller;

import com.example.exam_ha_minh_tu.dto.EmployeeDto;
import com.example.exam_ha_minh_tu.dto.MapperDto;
import com.example.exam_ha_minh_tu.entity.Employee;
import com.example.exam_ha_minh_tu.model.BaseResponse;
import com.example.exam_ha_minh_tu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    MapperDto mapperDto;

    @GetMapping
    private List<Employee> getAll(){
        return  (List<Employee>) employeeService.findAll();
    }
    @RequestMapping( "/{name}" )
    public List<Employee> foo(@PathVariable String name, HttpServletRequest request ) {
        return  (List<Employee>) employeeService.searchByName(name);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        Optional<Employee> categoryOptional = employeeService.findById(id);
        return categoryOptional.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping()
    private ResponseEntity createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        System.out.println("aaa");
        BaseResponse res = new BaseResponse();
        Employee userEntity = mapperDto.convertToEntity(employeeDto);
        res.data = employeeService.createEmployee(userEntity);
        return ResponseEntity.ok(res);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Optional<Employee> categoryOptional = employeeService.findById(id);
        return categoryOptional.map(category1 -> {
            employee.setId(category1.getId());
            return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deletEemployee(@PathVariable Long id) {
        Optional<Employee> userOptional = employeeService.findById(id);
        return userOptional.map(employee -> {
            employeeService.remove(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public @ResponseBody String searchPerson(HttpServletRequest request) {
//        String query = request.getParameter("name");
//        Person person = searchPersonByName(query);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String ajaxResponse = "";
//        try {
//            ajaxResponse = mapper.writeValueAsString(person);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        return ajaxResponse;
//    }
//    public @ResponseBody
//    CharSequence searchPerson(HttpServletRequest request) throws JsonProcessingException {
//        String name = request.getParameter("name");
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String json = ow.writeValueAsString( employeeService.searchByName(name));
//
//        return json;
//    }
}
