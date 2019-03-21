package com.arm.isg.ds.onprem.simulator.device.data_generator.data;

import com.google.common.collect.ImmutableList;

import java.util.List;

public final class ResourceDataItem {

    private final String resourceId;
    private final List<DataItem> dataItemList;

    public ResourceDataItem(String resourceId, List<DataItem> dataItemList) {
        this.resourceId = resourceId;
        this.dataItemList = ImmutableList.copyOf(dataItemList);
    }
}
