/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MEncountersCutService } from 'app/entities/m-encounters-cut/m-encounters-cut.service';
import { IMEncountersCut, MEncountersCut } from 'app/shared/model/m-encounters-cut.model';

describe('Service Tests', () => {
  describe('MEncountersCut Service', () => {
    let injector: TestBed;
    let service: MEncountersCutService;
    let httpMock: HttpTestingController;
    let elemDefault: IMEncountersCut;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MEncountersCutService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MEncountersCut(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA');
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

      it('should create a MEncountersCut', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MEncountersCut(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MEncountersCut', async () => {
        const returnedFromService = Object.assign(
          {
            condition: 1,
            ballFloatCondition: 1,
            command1Type: 1,
            command1IsSkill: 1,
            command2Type: 1,
            command2IsSkill: 1,
            result: 1,
            shootResult: 1,
            isThrown: 1,
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

      it('should return a list of MEncountersCut', async () => {
        const returnedFromService = Object.assign(
          {
            condition: 1,
            ballFloatCondition: 1,
            command1Type: 1,
            command1IsSkill: 1,
            command2Type: 1,
            command2IsSkill: 1,
            result: 1,
            shootResult: 1,
            isThrown: 1,
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

      it('should delete a MEncountersCut', async () => {
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
