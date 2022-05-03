//
//  RCTNotificationBadge.m
//  NotificationBadge
//
//  Created by Mees Maas on 03/05/2022.
//  Copyright © 2022 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "RCTNotificationBadge.h"
#import <React/RCTLog.h>

@implementation RCTNotificationBadge

// To export a module named RCTNotificationBadge
RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(setNumber:(NSInteger *)count)
{
    UIUserNotificationSettings* notificationSettings = [UIUserNotificationSettings settingsForTypes:UIUserNotificationTypeAlert | UIUserNotificationTypeBadge | UIUserNotificationTypeSound categories:nil];
    [[UIApplication sharedApplication] registerUserNotificationSettings:notificationSettings];
    [UIApplication sharedApplication].applicationIconBadgeNumber = count;
}

RCT_EXPORT_METHOD(configure:(NSString *)title text:(NSString *) text)
{
    // Dummy method so it does not crash
    // This method is only used on Android
}

@end
