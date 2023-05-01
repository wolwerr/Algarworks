import { Pipe, PipeTransform } from '@angular/core';

interface Active {
  [key: string]: string;
}

@Pipe({
  name: 'active'
})
export class TranslateActivePipe implements PipeTransform {
  transform(status: string): string {
    const active: Active = {
      'true': 'Sim',
      'false': 'Não'
    };

    if (status in active) {
      return active[status];
    } else {
      return status;
    }
  }
}
