package ru.zakharov.samboss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zakharov.samboss.entities.Target;
import ru.zakharov.samboss.services.TargetService;
import ru.zakharov.samboss.ulits.TargetErrorResponse;
import ru.zakharov.samboss.ulits.TargetNotFoudException;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class TargetRestController {
    private TargetService targetService;

    @Autowired
    public void setTargetService(TargetService targetService) {
        this.targetService = targetService;
    }


    @GetMapping("/targets/{id}")
    public Target getTarget(@PathVariable int id) {
        Optional<Target> target = Optional.ofNullable(targetService.findTargetById((long) id));

        if (!target.isPresent()) {
            throw new TargetNotFoudException("Target with id = " + id + " is not found!");
        }
        return target.get();
    }

    @GetMapping("/targets/")
    public List<Target> getTarget() {
        return targetService.showAllTargets();
    }

    @PostMapping("/targets/")
    public Target addTarget(@RequestBody Target newTarget) {
        newTarget.setId(0L);
        targetService.addTarget(newTarget);
        return newTarget;
    }

    @PutMapping("/targets/")
    public Target updateTarget(@RequestBody Target newTarget) {
        targetService.addTarget(newTarget);
        return newTarget;
    }

    @DeleteMapping("/targets/")
    public void deleteTarget(@RequestBody Target newTarget) {
        targetService.removeTarget(newTarget);
    }

    @ExceptionHandler
    public ResponseEntity<TargetErrorResponse> handleException(TargetNotFoudException exception) {
        TargetErrorResponse response = new TargetErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setTimestamp(System.currentTimeMillis());
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<TargetErrorResponse> handleAllException(Exception exception) {
        TargetErrorResponse response = new TargetErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(System.currentTimeMillis());
        response.setMessage("Bad Request");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
