/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MPenaltyKickCutService } from 'app/entities/m-penalty-kick-cut/m-penalty-kick-cut.service';
import { IMPenaltyKickCut, MPenaltyKickCut } from 'app/shared/model/m-penalty-kick-cut.model';

describe('Service Tests', () => {
  describe('MPenaltyKickCut Service', () => {
    let injector: TestBed;
    let service: MPenaltyKickCutService;
    let httpMock: HttpTestingController;
    let elemDefault: IMPenaltyKickCut;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MPenaltyKickCutService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MPenaltyKickCut(0, 0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA');
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

      it('should create a MPenaltyKickCut', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MPenaltyKickCut(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MPenaltyKickCut', async () => {
        const returnedFromService = Object.assign(
          {
            kickerCourse: 1,
            keeperCourse: 1,
            keeperCommand: 1,
            resultType: 1,
            offenseSequenceText: 'BBBBBB',
            defenseSequenceText: 'BBBBBB'
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

      it('should return a list of MPenaltyKickCut', async () => {
        const returnedFromService = Object.assign(
          {
            kickerCourse: 1,
            keeperCourse: 1,
            keeperCommand: 1,
            resultType: 1,
            offenseSequenceText: 'BBBBBB',
            defenseSequenceText: 'BBBBBB'
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

      it('should delete a MPenaltyKickCut', async () => {
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
