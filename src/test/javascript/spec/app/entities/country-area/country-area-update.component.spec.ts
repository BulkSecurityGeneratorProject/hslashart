/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { HslashartTestModule } from '../../../test.module';
import { CountryAreaUpdateComponent } from 'app/entities/country-area/country-area-update.component';
import { CountryAreaService } from 'app/entities/country-area/country-area.service';
import { CountryArea } from 'app/shared/model/country-area.model';

describe('Component Tests', () => {
    describe('CountryArea Management Update Component', () => {
        let comp: CountryAreaUpdateComponent;
        let fixture: ComponentFixture<CountryAreaUpdateComponent>;
        let service: CountryAreaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HslashartTestModule],
                declarations: [CountryAreaUpdateComponent]
            })
                .overrideTemplate(CountryAreaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CountryAreaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CountryAreaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CountryArea('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.countryArea = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CountryArea();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.countryArea = entity;
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
