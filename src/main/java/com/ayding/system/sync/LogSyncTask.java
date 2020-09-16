package com.ayding.system.sync;

import com.ayding.butterfly.log.domain.vo.AuthUserLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogSyncTask {

//    @Autowired
//    private AuthUserLogService sysLogServiceImpl;

    @Async(value = "asyncExecutor")
    public void addLog(AuthUserLogVO sysLog) {
        log.info("<===================asyncExecutor异步插入日志sysLog:{}",sysLog);
//        this.sysLogServiceImpl.saveLogs(sysLog);
    }
}
