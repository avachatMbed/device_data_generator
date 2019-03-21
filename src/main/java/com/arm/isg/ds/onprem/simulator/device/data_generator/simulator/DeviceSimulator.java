package com.arm.isg.ds.onprem.simulator.device.data_generator.simulator;

import com.arm.isg.ds.onprem.simulator.device.data_generator.data.ResourceDataItem;

public interface DeviceSimulator {

    String getDeviceId();

    String getAccountId();

    ResourceDataItem generateResourceDataItem();
}
