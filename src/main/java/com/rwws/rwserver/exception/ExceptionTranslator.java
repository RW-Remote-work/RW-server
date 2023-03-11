package com.rwws.rwserver.exception;

import com.rwws.rwserver.common.constant.ErrorConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionTranslator implements ProblemHandling {

    @Override
    public ResponseEntity<Problem> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, NativeWebRequest request) {
        BindingResult result = exception.getBindingResult();
        List<FieldErrorVM> fieldErrorList = result.getFieldErrors().stream()
                .map(f -> new FieldErrorVM(f.getObjectName(), f.getField(), f.getCode()))
                .collect(Collectors.toList());
        Problem problem = Problem.builder()
                .withType(ErrorConstant.CONSTRAINT_VIOLATION_TYPE)
                .withTitle("Method argument not valid")
                .withStatus(defaultConstraintViolationStatus())
                .with("message", ErrorConstant.ERR_VALIDATION)
                .with("fieldErrors", fieldErrorList)
                .build();
        return create(exception, problem, request);
    }
}
