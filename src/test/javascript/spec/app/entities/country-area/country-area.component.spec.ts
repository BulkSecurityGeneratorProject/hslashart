/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { HslashartTestModule } from '../../../test.module';
import { CountryAreaComponent } from 'app/entities/country-area/country-area.component';
import { CountryAreaService } from 'app/entities/country-area/country-area.service';
import { CountryArea } from 'app/shared/model/country-area.model';

describe('Component Tests', () => {
    describe('CountryArea Management Component', () => {
        let comp: CountryAreaComponent;
        let fixture: ComponentFixture<CountryAreaComponent>;
        let service: CountryAreaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HslashartTestModule],
                declarations: [CountryAreaComponent],
                providers: []
            })
                .overrideTemplate(CountryAreaComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CountryAreaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CountryAreaService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CountryArea('123')],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.countryAreas[0]).toEqual(jasmine.objectContaining({ id: '123' }));
        });
    });
});
