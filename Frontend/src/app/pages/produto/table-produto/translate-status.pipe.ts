import { Pipe, PipeTransform } from '@angular/core';

interface StatusMap {
  [key: string]: string;
}

@Pipe({
  name: 'translateStatus'
})
export class TranslateStatusPipe implements PipeTransform {
  transform(status: string): string {
    const statusMap: StatusMap = {
      'UNPUBLISHED': 'Não Publicado',
      'PUBLISHED': 'Publicado'
    };

    if (status in statusMap) {
      return statusMap[status];
    } else {
      return status;
    }
  }
}
