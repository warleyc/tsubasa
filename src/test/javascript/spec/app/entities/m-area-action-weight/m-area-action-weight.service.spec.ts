/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MAreaActionWeightService } from 'app/entities/m-area-action-weight/m-area-action-weight.service';
import { IMAreaActionWeight, MAreaActionWeight } from 'app/shared/model/m-area-action-weight.model';

describe('Service Tests', () => {
  describe('MAreaActionWeight Service', () => {
    let injector: TestBed;
    let service: MAreaActionWeightService;
    let httpMock: HttpTestingController;
    let elemDefault: IMAreaActionWeight;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MAreaActionWeightService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MAreaActionWeight(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
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

      it('should create a MAreaActionWeight', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MAreaActionWeight(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MAreaActionWeight', async () => {
        const returnedFromService = Object.assign(
          {
            areaType: 1,
            dribbleRate: 1,
            passingRate: 1,
            onetwoRate: 1,
            shootRate: 1,
            volleyShootRate: 1,
            headingShootRate: 1,
            tackleRate: 1,
            blockRate: 1,
            passCutRate: 1,
            clearRate: 1,
            competeRate: 1,
            trapRate: 1
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

      it('should return a list of MAreaActionWeight', async () => {
        const returnedFromService = Object.assign(
          {
            areaType: 1,
            dribbleRate: 1,
            passingRate: 1,
            onetwoRate: 1,
            shootRate: 1,
            volleyShootRate: 1,
            headingShootRate: 1,
            tackleRate: 1,
            blockRate: 1,
            passCutRate: 1,
            clearRate: 1,
            competeRate: 1,
            trapRate: 1
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

      it('should delete a MAreaActionWeight', async () => {
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
