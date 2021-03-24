package com.zgkxzx.modbus4And.msg;

import com.zgkxzx.modbus4And.exception.ModbusTransportException;
import com.zgkxzx.modbus4And.sero.util.queue.ByteQueue;

public class ReadBrightnessRegisterResponse extends ReadResponse{

    private static final int BRIGHTNESS_DATA_LEN = 13;

    private byte deviceStatus; // match modbus protocol function code

    ReadBrightnessRegisterResponse(int slaveId) throws ModbusTransportException {
        super(slaveId);
    }

    @Override
    public byte getFunctionCode() {
        return deviceStatus;
    }

    @Override
    protected void readResponse(ByteQueue queue) {
        deviceStatus = queue.pop();
        data = new byte[BRIGHTNESS_DATA_LEN];
        queue.pop(data);
    }

    @Override
    protected void writeResponse(ByteQueue queue) {
        queue.push(data);
    }
}
