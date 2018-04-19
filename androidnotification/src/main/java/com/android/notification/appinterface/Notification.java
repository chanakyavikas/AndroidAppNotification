package com.android.notification.appinterface;

/**
 * AndroidNotification.java
 * Created by Vikas B L on 4/17/2018.
 * Copyright(C) 2018 by Vikas B L.  All rights reserved
 */
public interface Notification {

    enum NotificationType {
        SimpleNotification,
        NotificationWithLargeImage,
        NotificationWithLargeText,
        NotificationWithTextAndAction,
        NotificationWithImageAndAction
    }
    void build();
    void show();
}
