package com.l8chat.imsdk.interfaces;

import com.l8chat.imsdk.entity.WKReminder;

import java.util.List;

public interface INewReminderListener {
    void newReminder(List<WKReminder> list);
}
