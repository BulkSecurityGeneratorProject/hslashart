import { NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material';

@NgModule({
    imports: [MatCardModule],
    exports: []
})
export class HslashartMaterialSharedModule {
    static forRoot() {
        return {
            ngModule: HslashartMaterialSharedModule
        };
    }
}
