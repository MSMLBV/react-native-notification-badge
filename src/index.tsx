import { NativeModules } from 'react-native';

type NotificationBadgeType = {
  setNumber(number: number): void;
  configure(title: string, text: string): void;
};

const { NotificationBadge } = NativeModules;

export default NotificationBadge as NotificationBadgeType;
