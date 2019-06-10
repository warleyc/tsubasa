/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MDummyEmblemService } from 'app/entities/m-dummy-emblem/m-dummy-emblem.service';
import { IMDummyEmblem, MDummyEmblem } from 'app/shared/model/m-dummy-emblem.model';

describe('Service Tests', () => {
  describe('MDummyEmblem Service', () => {
    let injector: TestBed;
    let service: MDummyEmblemService;
    let httpMock: HttpTestingController;
    let elemDefault: IMDummyEmblem;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MDummyEmblemService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MDummyEmblem(0, 0, 'AAAAAAA', 0, 'AAAAAAA', 0, 'AAAAAAA', 0, 'AAAAAAA');
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

      it('should create a MDummyEmblem', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MDummyEmblem(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MDummyEmblem', async () => {
        const returnedFromService = Object.assign(
          {
            backgroundId: 1,
            backgroundColor: 'BBBBBB',
            upperId: 1,
            upperColor: 'BBBBBB',
            middleId: 1,
            middleColor: 'BBBBBB',
            lowerId: 1,
            lowerColor: 'BBBBBB'
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

      it('should return a list of MDummyEmblem', async () => {
        const returnedFromService = Object.assign(
          {
            backgroundId: 1,
            backgroundColor: 'BBBBBB',
            upperId: 1,
            upperColor: 'BBBBBB',
            middleId: 1,
            middleColor: 'BBBBBB',
            lowerId: 1,
            lowerColor: 'BBBBBB'
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

      it('should delete a MDummyEmblem', async () => {
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
