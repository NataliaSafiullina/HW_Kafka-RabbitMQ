package ru.safiullina.HWCreditRequest.controller;

import org.springframework.web.bind.annotation.*;
import ru.safiullina.HWCreditRequest.dto.CreditRequestDto;
import ru.safiullina.HWCreditRequest.service.CreditService;

@RestController
public class CreditController {

    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping("/create")
    public Integer createCreditRequest(@RequestBody CreditRequestDto creditRequestDto) {
        System.out.println(creditRequestDto);
        return creditService.createCreditRequest(creditRequestDto);
    }

    @GetMapping("/status/{id}")
    public String getCreditStatus(@PathVariable Integer id) {
        return creditService.getCreditStatus(id);
    }
}
