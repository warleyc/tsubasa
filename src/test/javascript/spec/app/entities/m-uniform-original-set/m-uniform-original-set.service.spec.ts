/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MUniformOriginalSetService } from 'app/entities/m-uniform-original-set/m-uniform-original-set.service';
import { IMUniformOriginalSet, MUniformOriginalSet } from 'app/shared/model/m-uniform-original-set.model';

describe('Service Tests', () => {
  describe('MUniformOriginalSet Service', () => {
    let injector: TestBed;
    let service: MUniformOriginalSetService;
    let httpMock: HttpTestingController;
    let elemDefault: IMUniformOriginalSet;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MUniformOriginalSetService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MUniformOriginalSet(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', 0, 0, 0, 'AAAAAAA');
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

      it('should create a MUniformOriginalSet', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MUniformOriginalSet(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MUniformOriginalSet', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            shortName: 'BBBBBB',
            menuName: 'BBBBBB',
            upModelId: 1,
            bottomModelId: 1,
            thumbnailAssetName: 'BBBBBB',
            uniformType: 1,
            isDefault: 1,
            orderNum: 1,
            description: 'BBBBBB'
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

      it('should return a list of MUniformOriginalSet', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            shortName: 'BBBBBB',
            menuName: 'BBBBBB',
            upModelId: 1,
            bottomModelId: 1,
            thumbnailAssetName: 'BBBBBB',
            uniformType: 1,
            isDefault: 1,
            orderNum: 1,
            description: 'BBBBBB'
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

      it('should delete a MUniformOriginalSet', async () => {
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
