package com.syd.tshub.controller;

import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 类描述：
 *
 * @ClassName NameSpaceController
 * @Description TODO
 * @Author syd
 * @Date 2021/12/28 23:14
 * @Version 1.0
 */
@RestController
@RequestMapping("/case")
public class CaseController {

    @Autowired
    private CaseService caseService;

    @GetMapping("/get/{id}")
    public BaseResponse getCase(@PathVariable("id") String caseId) {
        return null;
    }

    @GetMapping("/list/{projectId}")
    public BaseResponse listCaseByProject(@PathVariable("projectId") String projectId) {
        return null;
    }

    @GetMapping("/list/{caseId}")
    public BaseResponse listCaseByCase(@PathVariable("caseId") String caseId) {
        return null;
    }

    @PostMapping("/create")
    public BaseResponse createCase() {
        return null;
    }

    @PutMapping("/update")
    public BaseResponse updateCase() {
        return null;
    }

    @DeleteMapping("/del/{id}")
    public BaseResponse deleteCase(@PathVariable("id") String caseId) {
        return null;
    }


}
