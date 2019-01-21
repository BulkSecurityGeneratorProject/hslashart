/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { HslashartTestModule } from '../../../test.module';
import { ArtworkDeleteDialogComponent } from 'app/entities/artwork/artwork-delete-dialog.component';
import { ArtworkService } from 'app/entities/artwork/artwork.service';

describe('Component Tests', () => {
    describe('Artwork Management Delete Component', () => {
        let comp: ArtworkDeleteDialogComponent;
        let fixture: ComponentFixture<ArtworkDeleteDialogComponent>;
        let service: ArtworkService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HslashartTestModule],
                declarations: [ArtworkDeleteDialogComponent]
            })
                .overrideTemplate(ArtworkDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ArtworkDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ArtworkService);
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
