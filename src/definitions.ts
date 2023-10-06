export interface ContactsPluginsPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
