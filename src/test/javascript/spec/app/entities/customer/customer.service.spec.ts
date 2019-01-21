/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { CustomerService } from 'app/entities/customer/customer.service';
import { ICustomer, Customer, Gender } from 'app/shared/model/customer.model';

describe('Service Tests', () => {
    describe('Customer Service', () => {
        let injector: TestBed;
        let service: CustomerService;
        let httpMock: HttpTestingController;
        let elemDefault: ICustomer;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(CustomerService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Customer(
                'ID',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                Gender.MALE,
                Gender.MALE,
                Gender.MALE
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find('123')
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Customer', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 'ID'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Customer(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Customer', async () => {
                const returnedFromService = Object.assign(
                    {
                        firstName: 'BBBBBB',
                        lastName: 'BBBBBB',
                        genderOther: 'BBBBBB',
                        phoneMain: 'BBBBBB',
                        phoneMobile: 'BBBBBB',
                        shippingLastName: 'BBBBBB',
                        shippingFirstName: 'BBBBBB',
                        shippingGenderOther: 'BBBBBB',
                        shippingAddressLine1: 'BBBBBB',
                        shippingAddressLine2: 'BBBBBB',
                        shippingCity: 'BBBBBB',
                        shippingCommentary: 'BBBBBB',
                        billingLastName: 'BBBBBB',
                        billingFirstName: 'BBBBBB',
                        billingGenderOther: 'BBBBBB',
                        billingAddressLine1: 'BBBBBB',
                        billingAddressLine2: 'BBBBBB',
                        shippingPostalCode: 'BBBBBB',
                        billingPostalCode: 'BBBBBB',
                        shippingCountry: 'BBBBBB',
                        shippingProvince: 'BBBBBB',
                        shippingCountryState: 'BBBBBB',
                        shippingTerritory: 'BBBBBB',
                        billingCity: 'BBBBBB',
                        billingCountry: 'BBBBBB',
                        billingProvince: 'BBBBBB',
                        billingTerritory: 'BBBBBB',
                        billingCountryState: 'BBBBBB',
                        gender: 'BBBBBB',
                        shippingGender: 'BBBBBB',
                        billingGender: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Customer', async () => {
                const returnedFromService = Object.assign(
                    {
                        firstName: 'BBBBBB',
                        lastName: 'BBBBBB',
                        genderOther: 'BBBBBB',
                        phoneMain: 'BBBBBB',
                        phoneMobile: 'BBBBBB',
                        shippingLastName: 'BBBBBB',
                        shippingFirstName: 'BBBBBB',
                        shippingGenderOther: 'BBBBBB',
                        shippingAddressLine1: 'BBBBBB',
                        shippingAddressLine2: 'BBBBBB',
                        shippingCity: 'BBBBBB',
                        shippingCommentary: 'BBBBBB',
                        billingLastName: 'BBBBBB',
                        billingFirstName: 'BBBBBB',
                        billingGenderOther: 'BBBBBB',
                        billingAddressLine1: 'BBBBBB',
                        billingAddressLine2: 'BBBBBB',
                        shippingPostalCode: 'BBBBBB',
                        billingPostalCode: 'BBBBBB',
                        shippingCountry: 'BBBBBB',
                        shippingProvince: 'BBBBBB',
                        shippingCountryState: 'BBBBBB',
                        shippingTerritory: 'BBBBBB',
                        billingCity: 'BBBBBB',
                        billingCountry: 'BBBBBB',
                        billingProvince: 'BBBBBB',
                        billingTerritory: 'BBBBBB',
                        billingCountryState: 'BBBBBB',
                        gender: 'BBBBBB',
                        shippingGender: 'BBBBBB',
                        billingGender: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Customer', async () => {
                const rxPromise = service.delete('123').subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
