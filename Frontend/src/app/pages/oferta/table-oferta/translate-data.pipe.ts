import { Pipe, PipeTransform } from '@angular/core';

import * as moment from 'moment';

@Pipe({ name: 'formatarData' })
export class DataPipe implements PipeTransform {
  transform(value: string): string {
    const data = moment(value, 'DD/MM/YYYY').format('YYYY-MM-DD');
    const dataFormatada = new Date(data).toLocaleDateString('pt-BR', { day: '2-digit', month: '2-digit', year: 'numeric' });
    return dataFormatada;
  }
}

