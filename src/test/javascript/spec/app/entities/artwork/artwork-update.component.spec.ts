/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { HslashartTestModule } from '../../../test.module';
import { ArtworkUpdateComponent } from 'app/entities/artwork/artwork-update.component';
import { ArtworkService } from 'app/entities/artwork/artwork.service';
import { Artwork } from 'app/shared/model/artwork.model';

describe('Component Tests', () => {
    describe('Artwork Management Update Component', () => {
        let comp: ArtworkUpdateComponent;
        let fixture: ComponentFixture<ArtworkUpdateComponent>;
        let service: ArtworkService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HslashartTestModule],
                declarations: [ArtworkUpdateComponent]
            })
                .overrideTemplate(ArtworkUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ArtworkUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ArtworkService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Artwork('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.artwork = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Artwork();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.artwork = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
