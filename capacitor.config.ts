import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.retro.calculator',
  appName: 'Retro Calculator',
  webDir: 'dist/public',
  server: {
    androidScheme: 'https'
  },
  plugins: {
    SplashScreen: {
      launchShowDuration: 2000,
      backgroundColor: "#0a0a1a",
      showSpinner: false
    },
    StatusBar: {
      style: 'dark',
      backgroundColor: '#0a0a1a'
    },
    Keyboard: {
      resize: 'body',
      style: 'dark'
    }
  }
};

export default config;