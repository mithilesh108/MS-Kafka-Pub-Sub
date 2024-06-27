package com.cts.controller;

import com.cts.dto.EmployeeDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    public static List<EmployeeDTO> listEmpDto = new ArrayList<>();

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/id/{empId}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable("empId") int id) {
        EmployeeDTO employeeDTO = listEmpDto.stream().filter(emp -> emp.getId() == id).findFirst().get();
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("employee-topic12", 1, employeeDTO.getName(), "QWERTYUIOPASDFGHJKLZXCVBNM");
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message = " + "QWERTYUIOPASDFGHJKLZXCVBNM");
                    System.out.println("Topic Name = " + result.getRecordMetadata().topic()+" Partition No = "
                            + result.getRecordMetadata().partition()+" Offset = " + result.getRecordMetadata().offset());
                    System.out.println("SerializedKeySize = " + result.getRecordMetadata().serializedKeySize());
                    System.out.println("SerializedValueSize = " + result.getRecordMetadata().serializedValueSize());
                    System.out.println("---------------------------------------------------------------------------");
                } else {
                    System.out.println("Unable to send message=[" + employeeDTO + "] due to : " + ex.getMessage());
                }
            });
        }catch(Exception e ) {
            System.out.println("Unable to send message=[" + employeeDTO + "] due to : " + e.getMessage());
        }
        return listEmpDto.size() > id
                ? ResponseEntity.status(HttpStatus.OK)
                .body(listEmpDto.stream().filter(emp -> emp.getId() == id).findFirst().get())
                : ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/name/{empName}")
    public ResponseEntity<List<EmployeeDTO>> findByName(@PathVariable("empName") String name) {
        List<EmployeeDTO> collect = listEmpDto.stream().map(emp -> { emp.getName().toUpperCase(); return emp;}).collect(Collectors.toList());
        collect.forEach(emp -> {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("employee-topic10", 2, emp.getName(), emp);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message = " + emp);
                    System.out.println("Topic Name = " + result.getRecordMetadata().topic()+" Partition No = "
                            + result.getRecordMetadata().partition()+" Offset = " + result.getRecordMetadata().offset());
                    //System.out.println("SerializedKeySize = " + result.getRecordMetadata().serializedKeySize());
                    //System.out.println("SerializedValueSize = " + result.getRecordMetadata().serializedValueSize());
                    System.out.println("---------------------------------------------------------------------------");
                } else {
                    System.out.println("Unable to send message=[" + collect + "] due to : " + ex.getMessage());
                }
            });
        });
        return listEmpDto != null
                ? ResponseEntity.status(HttpStatus.OK).body(
                listEmpDto.stream().filter(emp -> emp.getName().equals(name)).collect(Collectors.toList()))
                : ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
            for (EmployeeDTO employeeDTO : listEmpDto) {
                CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("employee-topic", employeeDTO.getName(), employeeDTO);
                future.whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println("Sent message = " + employeeDTO);
                        System.out.println("Topic Name = " + result.getRecordMetadata().topic()+" Partition No = "
                                + result.getRecordMetadata().partition()+" Offset = " + result.getRecordMetadata().offset());
                        //System.out.println("SerializedKeySize = " + result.getRecordMetadata().serializedKeySize());
                        System.out.println("SerializedValueSize = " + result.getRecordMetadata().serializedValueSize());
                        System.out.println("---------------------------------------------------------------------------");
                    } else {
                        System.out.println("Unable to send message=[" + listEmpDto + "] due to : " + ex.getMessage());
                    }
                });
            }
        return ResponseEntity.status(HttpStatus.OK).body(listEmpDto);
    }

    @PostMapping
    public ResponseEntity<String> getAllEmployees(@RequestBody EmployeeDTO dto) {
        if (dto instanceof EmployeeDTO)
            listEmpDto.add(dto);
        return ResponseEntity.status(HttpStatus.OK).body("Record added in List");
    }

    @PostConstruct
    private void init() {
        System.out.println("init method");
        for(int i =0 ; i < 10000; i++) {
            listEmpDto.add(new EmployeeDTO(1, "mk", "bihar", 10000));
            listEmpDto.add(new EmployeeDTO(2, "ramesh", "delhi", 70000));
            listEmpDto.add(new EmployeeDTO(3, "ashish", "hyd", 40000));
            listEmpDto.add(new EmployeeDTO(4, "kumar", "up", 15000));
            listEmpDto.add(EmployeeDTO.builder().id(5).name("ram").address("up").salary(20000).build());
        }
    }
}
