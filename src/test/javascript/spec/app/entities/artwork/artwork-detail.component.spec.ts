/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HslashartTestModule } from '../../../test.module';
import { ArtworkDetailComponent } from 'app/entities/artwork/artwork-detail.component';
import { Artwork } from 'app/shared/model/artwork.model';

describe('Component Tests', () => {
    describe('Artwork Management Detail Component', () => {
        let comp: ArtworkDetailComponent;
        let fixture: ComponentFixture<ArtworkDetailComponent>;
        const route = ({ data: of({ artwork: new Artwork('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HslashartTestModule],
                declarations: [ArtworkDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ArtworkDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ArtworkDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.artwork).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
