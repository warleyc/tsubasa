/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MGachaRenditionFirstGimmickService } from 'app/entities/m-gacha-rendition-first-gimmick/m-gacha-rendition-first-gimmick.service';
import { IMGachaRenditionFirstGimmick, MGachaRenditionFirstGimmick } from 'app/shared/model/m-gacha-rendition-first-gimmick.model';

describe('Service Tests', () => {
  describe('MGachaRenditionFirstGimmick Service', () => {
    let injector: TestBed;
    let service: MGachaRenditionFirstGimmickService;
    let httpMock: HttpTestingController;
    let elemDefault: IMGachaRenditionFirstGimmick;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MGachaRenditionFirstGimmickService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MGachaRenditionFirstGimmick(0, 0, 0, 0, 0);
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

      it('should create a MGachaRenditionFirstGimmick', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MGachaRenditionFirstGimmick(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MGachaRenditionFirstGimmick', async () => {
        const returnedFromService = Object.assign(
          {
            isSsr: 1,
            weight: 1,
            birdNum: 1,
            isTickerTape: 1
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

      it('should return a list of MGachaRenditionFirstGimmick', async () => {
        const returnedFromService = Object.assign(
          {
            isSsr: 1,
            weight: 1,
            birdNum: 1,
            isTickerTape: 1
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

      it('should delete a MGachaRenditionFirstGimmick', async () => {
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
