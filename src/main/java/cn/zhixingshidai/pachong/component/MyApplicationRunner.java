package cn.zhixingshidai.pachong.component;

import cn.zhixingshidai.pachong.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
//启动后执行
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private CodeService codeService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        codeService.start();
        System.out.println("全部执行完了");
    }
}
