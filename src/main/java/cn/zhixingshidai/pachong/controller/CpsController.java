package cn.zhixingshidai.pachong.controller;


import cn.zhixingshidai.pachong.service.CodeService;
import cn.zhixingshidai.pachong.service.CpsAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CpsController {

    @Autowired
    private CpsAdvertService cpsAdvertService;

    @RequestMapping("aaaabb")
    public String getBarcodeInto(String barcode) {
        cpsAdvertService.updateGoodsClassId();
        return "sdcsdcsd";
    }


}
