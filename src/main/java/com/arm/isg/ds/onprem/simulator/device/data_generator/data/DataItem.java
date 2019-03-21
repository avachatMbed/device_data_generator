package com.arm.isg.ds.onprem.simulator.device.data_generator.data;

import com.google.common.collect.ImmutableMap;

import java.util.Map;


public final class DataItem {

    private final String name;
    private final String type; // TODO : Change this to enumeration
    private final String uom; // TODO : Change this to enumeration
    private final Map<String, Object> value;

    public DataItem(String name, String type, String uom, Map<String, Object> value) {
        this.name = name;
        this.type = type;
        this.uom = uom;
        this.value = ImmutableMap.copyOf(value);
    }
}
