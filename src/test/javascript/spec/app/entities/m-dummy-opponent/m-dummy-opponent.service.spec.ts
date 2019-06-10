/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MDummyOpponentService } from 'app/entities/m-dummy-opponent/m-dummy-opponent.service';
import { IMDummyOpponent, MDummyOpponent } from 'app/shared/model/m-dummy-opponent.model';

describe('Service Tests', () => {
  describe('MDummyOpponent Service', () => {
    let injector: TestBed;
    let service: MDummyOpponentService;
    let httpMock: HttpTestingController;
    let elemDefault: IMDummyOpponent;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MDummyOpponentService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MDummyOpponent(0, 0, 0, 'AAAAAAA', 0, 0, 0, 'AAAAAAA', 0, 'AAAAAAA', 0, 'AAAAAAA', 0, 'AAAAAAA');
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

      it('should create a MDummyOpponent', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MDummyOpponent(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MDummyOpponent', async () => {
        const returnedFromService = Object.assign(
          {
            npcDeckId: 1,
            totalParameter: 1,
            name: 'BBBBBB',
            rank: 1,
            emblemId: 1,
            fpUniformUpId: 1,
            fpUniformUpColor: 'BBBBBB',
            fpUniformBottomId: 1,
            fpUniformBottomColor: 'BBBBBB',
            gkUniformUpId: 1,
            gkUniformUpColor: 'BBBBBB',
            gkUniformBottomId: 1,
            gkUniformBottomColor: 'BBBBBB'
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

      it('should return a list of MDummyOpponent', async () => {
        const returnedFromService = Object.assign(
          {
            npcDeckId: 1,
            totalParameter: 1,
            name: 'BBBBBB',
            rank: 1,
            emblemId: 1,
            fpUniformUpId: 1,
            fpUniformUpColor: 'BBBBBB',
            fpUniformBottomId: 1,
            fpUniformBottomColor: 'BBBBBB',
            gkUniformUpId: 1,
            gkUniformUpColor: 'BBBBBB',
            gkUniformBottomId: 1,
            gkUniformBottomColor: 'BBBBBB'
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

      it('should delete a MDummyOpponent', async () => {
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
