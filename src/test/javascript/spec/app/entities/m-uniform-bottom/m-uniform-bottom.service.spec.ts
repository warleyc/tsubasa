/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MUniformBottomService } from 'app/entities/m-uniform-bottom/m-uniform-bottom.service';
import { IMUniformBottom, MUniformBottom } from 'app/shared/model/m-uniform-bottom.model';

describe('Service Tests', () => {
  describe('MUniformBottom Service', () => {
    let injector: TestBed;
    let service: MUniformBottomService;
    let httpMock: HttpTestingController;
    let elemDefault: IMUniformBottom;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MUniformBottomService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MUniformBottom(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 0, 0, 0, 'AAAAAAA');
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

      it('should create a MUniformBottom', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MUniformBottom(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MUniformBottom', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            shortName: 'BBBBBB',
            menuName: 'BBBBBB',
            modelId: 1,
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

      it('should return a list of MUniformBottom', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            shortName: 'BBBBBB',
            menuName: 'BBBBBB',
            modelId: 1,
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

      it('should delete a MUniformBottom', async () => {
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
