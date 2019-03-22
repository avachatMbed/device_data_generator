package com.arm.isg.ds.onprem.simulator.device.data_generator.data;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;

import java.util.Map;


public final class DataItem {

    @Getter private final String name;
    @Getter private final String type; // TODO : Change this to enumeration
    @Getter private final String uom; // TODO : Change this to enumeration
    @Getter private final Map<String, Object> value;

    public DataItem(String name, String type, String uom, Map<String, Object> value) {
        this.name = name;
        this.type = type;
        this.uom = uom;
        this.value = ImmutableMap.copyOf(value);
    }
}
