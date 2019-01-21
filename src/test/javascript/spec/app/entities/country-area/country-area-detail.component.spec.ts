/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HslashartTestModule } from '../../../test.module';
import { CountryAreaDetailComponent } from 'app/entities/country-area/country-area-detail.component';
import { CountryArea } from 'app/shared/model/country-area.model';

describe('Component Tests', () => {
    describe('CountryArea Management Detail Component', () => {
        let comp: CountryAreaDetailComponent;
        let fixture: ComponentFixture<CountryAreaDetailComponent>;
        const route = ({ data: of({ countryArea: new CountryArea('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HslashartTestModule],
                declarations: [CountryAreaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CountryAreaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CountryAreaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.countryArea).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
