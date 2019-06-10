/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MDistributeParamPointService } from 'app/entities/m-distribute-param-point/m-distribute-param-point.service';
import { IMDistributeParamPoint, MDistributeParamPoint } from 'app/shared/model/m-distribute-param-point.model';

describe('Service Tests', () => {
  describe('MDistributeParamPoint Service', () => {
    let injector: TestBed;
    let service: MDistributeParamPointService;
    let httpMock: HttpTestingController;
    let elemDefault: IMDistributeParamPoint;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MDistributeParamPointService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MDistributeParamPoint(0, 0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
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

      it('should create a MDistributeParamPoint', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MDistributeParamPoint(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MDistributeParamPoint', async () => {
        const returnedFromService = Object.assign(
          {
            targetAttribute: 1,
            targetNationalityGroupId: 1,
            name: 'BBBBBB',
            description: 'BBBBBB',
            thumbnailAssetName: 'BBBBBB',
            iconAssetName: 'BBBBBB'
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

      it('should return a list of MDistributeParamPoint', async () => {
        const returnedFromService = Object.assign(
          {
            targetAttribute: 1,
            targetNationalityGroupId: 1,
            name: 'BBBBBB',
            description: 'BBBBBB',
            thumbnailAssetName: 'BBBBBB',
            iconAssetName: 'BBBBBB'
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

      it('should delete a MDistributeParamPoint', async () => {
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
