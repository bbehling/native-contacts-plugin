/* declare module '@capacitor/core' {
  interface PluginRegistry {
    ContactsPlugin: ContactsPluginPlugin;
  } 
}*/
/* export interface ScreenOrientationPlugin {
  getContacts(filter: string): Promise<{ results: any[] }>;
} */
export interface ContactsPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  getContacts(filter: string): Promise<{ results: any[] }>;
}
