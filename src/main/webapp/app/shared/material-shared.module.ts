import { NgModule } from '@angular/core';
import { MatCardModule, MatButtonModule, MatCheckboxModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
    imports: [MatCardModule, BrowserAnimationsModule, MatButtonModule, MatCheckboxModule],
    exports: [MatCardModule, BrowserAnimationsModule, MatButtonModule, MatCheckboxModule]
})
export class HslashartMaterialSharedModule {
    static forRoot() {
        return {
            ngModule: HslashartMaterialSharedModule
        };
    }
}
