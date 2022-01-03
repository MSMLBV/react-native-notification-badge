import { NativeModules } from 'react-native';

type NotificationBadgeType = {
  multiply(a: number, b: number): Promise<number>;
};

const { NotificationBadge } = NativeModules;

export default NotificationBadge as NotificationBadgeType;
