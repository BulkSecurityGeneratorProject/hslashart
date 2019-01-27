import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgJhipsterModule } from 'ng-jhipster';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { CookieModule } from 'ngx-cookie';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HslashartMaterialSharedModule } from './material-shared.module';

@NgModule({
    imports: [NgbModule.forRoot(), InfiniteScrollModule, CookieModule.forRoot(), FontAwesomeModule, HslashartMaterialSharedModule],
    exports: [
        FormsModule,
        CommonModule,
        NgbModule,
        NgJhipsterModule,
        InfiniteScrollModule,
        FontAwesomeModule,
        HslashartMaterialSharedModule
    ]
})
export class HslashartSharedLibsModule {
    static forRoot() {
        return {
            ngModule: HslashartSharedLibsModule
        };
    }
}
