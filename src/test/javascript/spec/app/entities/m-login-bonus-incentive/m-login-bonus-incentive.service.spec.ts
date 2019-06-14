/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MLoginBonusIncentiveService } from 'app/entities/m-login-bonus-incentive/m-login-bonus-incentive.service';
import { IMLoginBonusIncentive, MLoginBonusIncentive } from 'app/shared/model/m-login-bonus-incentive.model';

describe('Service Tests', () => {
  describe('MLoginBonusIncentive Service', () => {
    let injector: TestBed;
    let service: MLoginBonusIncentiveService;
    let httpMock: HttpTestingController;
    let elemDefault: IMLoginBonusIncentive;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MLoginBonusIncentiveService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MLoginBonusIncentive(0, 0, 0, 0, 0, 0, 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a MLoginBonusIncentive', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MLoginBonusIncentive(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MLoginBonusIncentive', async () => {
        const returnedFromService = Object.assign(
          {
            roundId: 1,
            day: 1,
            contentType: 1,
            contentId: 1,
            contentAmount: 1,
            presentDetail: 'BBBBBB',
            isDecorated: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of MLoginBonusIncentive', async () => {
        const returnedFromService = Object.assign(
          {
            roundId: 1,
            day: 1,
            contentType: 1,
            contentId: 1,
            contentAmount: 1,
            presentDetail: 'BBBBBB',
            isDecorated: 1
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
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a MLoginBonusIncentive', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
