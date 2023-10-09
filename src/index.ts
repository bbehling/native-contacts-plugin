import { registerPlugin } from '@capacitor/core';

import type { ContactsPluginPlugin } from './definitions';

const ContactsPlugin = registerPlugin<ContactsPluginPlugin>('ContactsPlugin', {
  web: () => import('./web').then(m => new m.ContactsPluginWeb()),
});

export * from './definitions';
export { ContactsPlugin };
