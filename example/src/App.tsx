import * as React from 'react';
import { StyleSheet, View, TouchableOpacity, Text } from 'react-native';
import NotificationBadge from 'react-native-notification-badge';

export default function App() {
  React.useEffect(() => {
    NotificationBadge.configure('Title', 'There are %count% new messages');
  }, []);

  return (
    <View style={styles.container}>
      <TouchableOpacity
        onPress={() => NotificationBadge.setNumber(8)}
        style={styles.button}
      >
        <Text>Set 8</Text>
      </TouchableOpacity>
      <TouchableOpacity
        onPress={() => NotificationBadge.setNumber(0)}
        style={[styles.marginTop, styles.button]}
      >
        <Text>Set 0</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  button: {
    backgroundColor: '#0275d8',
    padding: 5,
    borderRadius: 5,
  },
  marginTop: {
    marginTop: 20,
  },
});
