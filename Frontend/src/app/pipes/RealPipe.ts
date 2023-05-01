import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'real'
})
export class RealPipe implements PipeTransform {
  transform(value: number | null): string {
    if (value === null || isNaN(value)) {
      return '';
    }
    try {
      const formatter = new Intl.NumberFormat('pt-BR', {
        style: 'currency',
        currency: 'BRL',
        minimumFractionDigits: 2,
        maximumFractionDigits: 2,
        useGrouping: true
      });
      return formatter.format(value).replace('.', ',');
    } catch (e) {
      console.error(`Error formatting value "${value}" to Real:`, e);
      return '';
    }
  }
}
