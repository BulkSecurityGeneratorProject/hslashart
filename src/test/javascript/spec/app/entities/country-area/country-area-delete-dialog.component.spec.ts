/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { HslashartTestModule } from '../../../test.module';
import { CountryAreaDeleteDialogComponent } from 'app/entities/country-area/country-area-delete-dialog.component';
import { CountryAreaService } from 'app/entities/country-area/country-area.service';

describe('Component Tests', () => {
    describe('CountryArea Management Delete Component', () => {
        let comp: CountryAreaDeleteDialogComponent;
        let fixture: ComponentFixture<CountryAreaDeleteDialogComponent>;
        let service: CountryAreaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HslashartTestModule],
                declarations: [CountryAreaDeleteDialogComponent]
            })
                .overrideTemplate(CountryAreaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CountryAreaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CountryAreaService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete('123');
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith('123');
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
