import { Pipe, PipeTransform } from '@angular/core';
import { CURRENCY } from '../constants/currency';

@Pipe({
    name: 'hslashArtCurrency'
})
export class HslashArtCurrencyPipe implements PipeTransform {
    transform(value: string, args?: string[]): string {
        const selectedCurrency = args ? CURRENCY[args[0].toUpperCase()] : '';
        console.log(value, selectedCurrency);
        return null;
    }
}
