package com.dreamsearcher.sender.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification
{
    private String notificationId;
    private String notificationText;
    private String itemId;
    private String userId;
    private String userWishId;
    private String shopName;
    private boolean deliveryStatus;
    private int retryCount;
    private String creationTime;
    private String deliveredTime;

    private String username;
    private String userEmail;
    private boolean isSend = false;
}
