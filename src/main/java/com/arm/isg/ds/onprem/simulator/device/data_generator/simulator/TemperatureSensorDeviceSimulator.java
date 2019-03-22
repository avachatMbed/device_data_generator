package com.arm.isg.ds.onprem.simulator.device.data_generator.simulator;

import com.arm.isg.ds.onprem.simulator.device.data_generator.data.DataItem;
import com.arm.isg.ds.onprem.simulator.device.data_generator.data.ResourceDataItem;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.Getter;

import java.util.List;
import java.util.Random;

public class TemperatureSensorDeviceSimulator implements DeviceSimulator {

    @Getter private final String deviceId;
    @Getter private final String accountId;
    @Getter private final String resourceId;
    private final Random random;

    public TemperatureSensorDeviceSimulator(String deviceId, String accountId, String resourceId) {
        this.deviceId = deviceId;
        this.accountId = accountId;
        this.resourceId = resourceId;
        this.random = new Random();
    }

    @Override
    public ResourceDataItem generateResourceDataItem() {
        return new ResourceDataItem("temperature_resource_1", generateDataItem());
    }

    private List<DataItem> generateDataItem() {
        return ImmutableList.of(new DataItem("temp", "temperature", "Fahrenheit",
                ImmutableMap.of("value", generateTemperature())));
    }

    private int generateTemperature() {
        return 40 + random.nextInt(51); // return temp between 40 - 90
    }
}
