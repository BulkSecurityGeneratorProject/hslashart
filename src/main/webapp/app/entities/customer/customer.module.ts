import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HslashartSharedModule } from 'app/shared';
import { HslashartAdminModule } from 'app/admin/admin.module';
import {
    CustomerComponent,
    CustomerDetailComponent,
    CustomerUpdateComponent,
    CustomerDeletePopupComponent,
    CustomerDeleteDialogComponent,
    customerRoute,
    customerPopupRoute
} from './';

const ENTITY_STATES = [...customerRoute, ...customerPopupRoute];

@NgModule({
    imports: [HslashartSharedModule, HslashartAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CustomerComponent,
        CustomerDetailComponent,
        CustomerUpdateComponent,
        CustomerDeleteDialogComponent,
        CustomerDeletePopupComponent
    ],
    entryComponents: [CustomerComponent, CustomerUpdateComponent, CustomerDeleteDialogComponent, CustomerDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HslashartCustomerModule {}
