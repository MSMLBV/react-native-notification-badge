# react-native-notification-badge

Show a badge on the app icon on both Android and iOS.

## Installation

```sh
npm install @msml/react-native-notification-badge
```

or

```sh
yarn add @msml/react-native-notification-badge
```

Additionally on iOS you need to run:

```sh
npx pod-install
```

## Usage

```js
import NotificationBadge from '@msml/react-native-notification-badge';
```

### Configure (android only)

You can configure the title and text that will be displayed on the notification block. `%count%` will be replaced with the actual notifications open.

```js
NotificationBadge.configure('Title', 'There are %count% new messages');
```

### Set Number

Update the value of the badge. When set to 0 the badge will disappear.

```js
NotificationBadge.setNumber(12);
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
