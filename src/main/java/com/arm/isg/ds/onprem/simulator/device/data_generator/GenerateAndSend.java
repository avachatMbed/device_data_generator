package com.arm.isg.ds.onprem.simulator.device.data_generator;

import com.arm.isg.ds.onprem.simulator.device.data_generator.data.ResourceDataItem;
import com.arm.isg.ds.onprem.simulator.device.data_generator.send.DataSender;
import com.arm.isg.ds.onprem.simulator.device.data_generator.send.HttpDataSender;
import com.arm.isg.ds.onprem.simulator.device.data_generator.simulator.DeviceSimulator;
import com.arm.isg.ds.onprem.simulator.device.data_generator.simulator.TemperatureSensorDeviceSimulator;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateAndSend {

    private static final Logger log = LoggerFactory.getLogger(GenerateAndSend.class);

    private static final String FLUENTD_PUBLISHER_EC2 = "http://ec2-18-216-243-125.us-east-2.compute.amazonaws.com:8080/topic.test";

    private static final String JSON_DEVICE_ID = "did";
    private static final String JSON_ACCOUNT_ID = "aid";
    private static final String JSON_TIMESTAMP = "ts";
    private static final String JSON_RESOURCE_ID = "rid";
    private static final String JSON_DATA = "data";

    private final List<DeviceSimulator> deviceSimulatorList;
    private final DataSender dataSender;

    public GenerateAndSend () {
        this.deviceSimulatorList = ImmutableList.copyOf(initDeviceSimulators());
        this.dataSender = new HttpDataSender(FLUENTD_PUBLISHER_EC2);
    }

    public void run() {

        for (DeviceSimulator deviceSimulator:deviceSimulatorList) {
            Map<String, Object> dataMap = generateDataForSingleDevice(deviceSimulator);
            dataSender.sendDataItem(dataMap);
        }

    }

    private List<DeviceSimulator> initDeviceSimulators() {
        return ImmutableList.of(new TemperatureSensorDeviceSimulator("deviceID_1", "accountID_1", "resourceId_1"));
    }

    private Map<String, Object> generateDataForSingleDevice(DeviceSimulator deviceSimulator) {

        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put(JSON_DEVICE_ID, deviceSimulator.getDeviceId());
        dataMap.put(JSON_ACCOUNT_ID, deviceSimulator.getAccountId());
        dataMap.put(JSON_TIMESTAMP, System.currentTimeMillis());

        ResourceDataItem resourceDataItem = deviceSimulator.generateResourceDataItem();
        dataMap.put(JSON_RESOURCE_ID, resourceDataItem.getResourceId());
        dataMap.put(JSON_DATA, resourceDataItem.getDataItemList());

        return dataMap;
    }


}
