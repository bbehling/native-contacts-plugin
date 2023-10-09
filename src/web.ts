import { WebPlugin } from '@capacitor/core';

import type { ContactsPluginPlugin } from './definitions';

export class ContactsPluginWeb
  extends WebPlugin
  implements ContactsPluginPlugin
{
  constructor() {
    super({
      name: 'ContactsPlugin',
      platforms: ['web'],
    });
  }

  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async getContacts(filter: string): Promise<{ results: any[] }> {
    console.log('filter: ', filter);
    return {
      results: [
        {
          firstName: 'Dummy',
          lastName: 'Entry',
          telephone: '123456',
        },
      ],
    };
  }
}

const ContactsPlugin = new ContactsPluginWeb();

export { ContactsPlugin };

//import { registerWebPlugin } from '@capacitor/core';
//registerWebPlugin(ContactsPlugin);
