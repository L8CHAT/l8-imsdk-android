package com.l8chat.imsdk.interfaces;

import com.l8chat.imsdk.entity.WKReminder;

import java.util.List;

public interface IReminderResult {
    void onResult(List<WKReminder> reminders);
}
