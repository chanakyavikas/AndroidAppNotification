package com.android.notification;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.android.notification.appinterface.Notification;

/**
 * AndroidNotification
 * Created by Vikas B L on 4/17/2018.
 * Copyright(C) 2018 by Vikas B L.  All rights reserved
 */
public class NotificationWithTextOrImage extends NotificationBuilder{

    private NotificationType mNotificationType;

    public NotificationWithTextOrImage(Context aContext, String aChannelID
            , Notification.NotificationType aNotificationType) {
        super(aContext, aChannelID);
        this.mNotificationType = aNotificationType;
    }

    public void addLargeText(@NonNull String aLargeContentText){
        this.mLargeContentText = aLargeContentText;
    }

    public void addLargeImage(Bitmap aLargeBitmap){
        this.mLargeImage = aLargeBitmap;
    }

    public void build() throws IllegalStateException{
        super.build();
        if(this.mLargeImage != null) {
            mNotificationBuilder.setLargeIcon(mLargeImage);
        }
    }

}
