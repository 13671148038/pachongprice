package cn.zhixingshidai.pachong.controller;

import cn.zhixingshidai.pachong.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeAttrController {

    @Autowired
    private CodeService codeService;

    @RequestMapping("start")
    public String start() {
        String result = null;
        try {
            codeService.start();
            result = "success";
        } catch (Exception e) {
            e.printStackTrace();
            result = "error";
        }
        return result;
    }

}
