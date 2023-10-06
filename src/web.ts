import { WebPlugin } from '@capacitor/core';

import type { ContactsPluginsPlugin } from './definitions';

export class ContactsPluginsWeb
  extends WebPlugin
  implements ContactsPluginsPlugin
{
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
