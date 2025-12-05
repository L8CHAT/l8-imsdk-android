package com.l8chat.imsdk.message.timer;

import com.l8chat.imsdk.message.WKConnection;
import com.l8chat.imsdk.protocol.WKPingMsg;

import java.util.concurrent.locks.ReentrantLock;

public class HeartbeatManager {
    private final ReentrantLock heartbeatLock = new ReentrantLock();
    public void startHeartbeat() {
        TimerManager.getInstance().addTask(
                TimerTasks.HEARTBEAT,
                () -> {
                    heartbeatLock.lock();
                    try {
                        WKConnection.getInstance().sendMessage(new WKPingMsg());
                    } finally {
                        heartbeatLock.unlock();
                    }
                },
                0,
                1000 * 60
        );
    }
}
