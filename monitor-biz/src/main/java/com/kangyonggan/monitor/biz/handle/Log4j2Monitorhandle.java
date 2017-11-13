package com.kangyonggan.monitor.biz.handle;

import com.kangyonggan.extra.core.handle.MonitorHandle;
import com.kangyonggan.extra.core.model.MonitorInfo;
import lombok.extern.log4j.Log4j2;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
@Log4j2
public class Log4j2Monitorhandle implements MonitorHandle {

    @Override
    public void error(String msg, Exception e, MonitorInfo monitorInfo) {
        if (monitorInfo != null) {
            msg += ", " + monitorInfo;
        }

        log.error(msg, e);
    }
}
