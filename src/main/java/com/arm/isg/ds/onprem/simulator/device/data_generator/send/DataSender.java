package com.arm.isg.ds.onprem.simulator.device.data_generator.send;

import java.util.Map;

public interface DataSender {

    void sendDataItem (Map<String, Object> dataMap);

}
