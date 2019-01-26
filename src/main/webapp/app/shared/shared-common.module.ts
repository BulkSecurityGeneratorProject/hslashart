import { NgModule } from '@angular/core';

import { HslashartSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent } from './';
import { HslashartMaterialSharedModule } from './material-shared.module';

@NgModule({
    imports: [HslashartSharedLibsModule, HslashartMaterialSharedModule],
    declarations: [FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent],
    exports: [HslashartSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent]
})
export class HslashartSharedCommonModule {}
