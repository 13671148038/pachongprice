package cn.zhixingshidai.pachong.component;

import cn.zhixingshidai.pachong.service.CodeService;
import cn.zhixingshidai.pachong.service.CpsAdvertService;
import cn.zhixingshidai.pachong.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//启动后执行
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private CodeService codeService;
    @Autowired
    private WordService wordService;
    @Autowired
    private CpsAdvertService cpsAdvertService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        codeService.start();
//        wordService.asyncWordFaceTypeId();
//        wordService.asyncWordTypeId();
//        wordService.asyncWordDeclareTypeId();
//        codeService.startUpdateWordDataisNull();
        codeService.asyncCodeAttrToSubmeter();
//        codeService.asyncShowSearch();

        System.out.println("开始执行完了");
//        cpsAdvertService.updateGoodsClassId();
        System.out.println("全部执行完了");

    }
}
