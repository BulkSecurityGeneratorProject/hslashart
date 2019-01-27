import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HslashArtCurrencyPipe } from './hslashart-currency.pipe';

@NgModule({
    declarations: [HslashArtCurrencyPipe],
    imports: [CommonModule],
    exports: [HslashArtCurrencyPipe],
    providers: []
})
export class HslashArtPipeModule {
    static forRoot() {
        return {
            ngModule: HslashArtPipeModule
        };
    }
}
