import { registerPlugin } from '@capacitor/core';

import type { ContactsPluginsPlugin } from './definitions';

const ContactsPlugins = registerPlugin<ContactsPluginsPlugin>(
  'ContactsPlugins',
  {
    web: () => import('./web').then(m => new m.ContactsPluginsWeb()),
  },
);

export * from './definitions';
export { ContactsPlugins };
