package com.android.notification;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.android.notification.appinterface.Notification;

/**
 * AndroidNotification
 * Created by Vikas B L on 4/17/2018.
 * Copyright(C) 2018 by Vikas B L.  All rights reserved
 */

abstract class NotificationBuilder implements Notification{

    protected  @NonNull Context mContext;
    protected String mChannelID, mTitle, mContentText, mLargeContentText;
    protected int mSmallIcon = -1, mPriority = NotificationCompat.PRIORITY_DEFAULT;
    protected Bitmap mLargeImage = null;
    protected NotificationType mNotificationType = NotificationType.SimpleNotification;
    protected NotificationCompat.Builder mNotificationBuilder;
    protected NotificationManagerCompat mNotificationManager;
    protected android.app.Notification mNotification;

    protected NotificationBuilder(Context aContext, String aChannelID){
        this.mContext = aContext;
        this.mChannelID = aChannelID;
    }

    public NotificationCompat.Builder getNotificationBuilder(){
        return mNotificationBuilder;
    }

    public void setNotificationType(@NonNull NotificationType aNotificationType) throws IllegalArgumentException{
        if(aNotificationType == null){
            throw new IllegalArgumentException("aNotificationType is null");
        }
        this.mNotificationType = aNotificationType;
    }

    public void setTitle(@NonNull String aTitle) throws IllegalArgumentException{
        if(aTitle == null){
            throw new IllegalArgumentException("Title is null");
        }
        this.mTitle = aTitle;
    }

    public void setContentText(@NonNull String aContentText){
        if(aContentText == null){
            throw new IllegalArgumentException("Content text is null");
        }
        this.mContentText = aContentText;
    }

    public void setSmallIcon(int aSmallIconResource){
        this.mSmallIcon = aSmallIconResource;
    }

    public void setPriority(int aPriority){
        this.mPriority = aPriority;
    }

    /**
     * Method to build a notification before @show()
     * Before calling this method, Title, contentText and smallIcon are necessary.
     * @throws IllegalStateException
     */
    @Override
    public void build() throws IllegalStateException{
        this.mNotificationBuilder = new NotificationCompat.Builder(mContext, mChannelID);
        if(mTitle == null){
            throw new IllegalStateException("Title is null");
        }
        if(mContentText == null){
            throw new IllegalStateException("ContentText is null");
        }
        if(mSmallIcon == -1){
            throw new IllegalStateException("small icon is not defined");
        }

        this.mNotificationBuilder.setSmallIcon(mSmallIcon);
        this.mNotificationBuilder.setContentTitle(mTitle);
        this.mNotificationBuilder.setContentText(mContentText);
        this.mNotificationBuilder.setPriority(mPriority);
        this.mNotification = this.mNotificationBuilder.build();

    }

    public synchronized void addNotificationAction(NotificationCompat.Action aAction) throws IllegalStateException
                                                                                    , IllegalArgumentException{
        if(this.mNotificationBuilder == null){
            throw new IllegalStateException("NotificationBuilder is null");
        }

        if(aAction == null){
            throw new IllegalArgumentException("aAction is null");
        }

        this.mNotificationBuilder.addAction(aAction);
    }

    @Override
    public void show() throws IllegalStateException{
        if(mNotification == null){
            throw new IllegalStateException("mNotification is null");
        }

        NotificationManagerCompat.from(mContext).notify(0, mNotification);
    }
}
