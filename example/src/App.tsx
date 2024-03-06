import React, { useEffect } from 'react';
import { StyleSheet, View, Text, Button, SafeAreaView } from 'react-native';
import Survicate from '@survicate/react-native-survicate';
import '@survicate/survicate-uxcam-integration-react-native';
import RNUxcam from 'react-native-ux-cam';

export default function App() {
  useEffect(() => {
    RNUxcam.optIntoSchematicRecordings();
    const configuration = { userAppKey: 'YOUR API KEY' };
    RNUxcam.startWithConfiguration(configuration);
    Survicate.initializeSdk();
    Survicate.integrations.addUXCamIntegration();
  });

  const handlePress = () => {
    Survicate.invokeEvent('example_event');
  };

  return (
    <SafeAreaView style={styles.container}>
      <Text>Survicate UXCam Example</Text>
      <View style={styles.spacer} />
      <Button title="Show Survey" onPress={handlePress} />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'flex-start',
    alignItems: 'flex-start',
    margin: 20,
  },
  spacer: {
    height: 20,
  },
});
