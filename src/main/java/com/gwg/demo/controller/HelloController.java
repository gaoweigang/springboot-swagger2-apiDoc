package com.gwg.demo.controller;


import com.sun.org.apache.bcel.internal.generic.RETURN;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(value = "helloController", tags = "测试")
public class HelloController {

    @ApiOperation(value = "哈喽")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(@RequestParam Long id){
        return "hello World";
    }

}
