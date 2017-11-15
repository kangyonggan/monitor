package com.kangyonggan.monitor.model.dto;

import lombok.Data;

/**
 * @author kangyonggan
 * @since 11/15/17
 */
@Data
public class MonitorDto {

    private int concurrencyCount;

    private double usedTime;

    private double tps;

    private long beginTime;

    private long endTime;

}
