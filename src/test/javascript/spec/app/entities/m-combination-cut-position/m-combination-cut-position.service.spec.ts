/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MCombinationCutPositionService } from 'app/entities/m-combination-cut-position/m-combination-cut-position.service';
import { IMCombinationCutPosition, MCombinationCutPosition } from 'app/shared/model/m-combination-cut-position.model';

describe('Service Tests', () => {
  describe('MCombinationCutPosition Service', () => {
    let injector: TestBed;
    let service: MCombinationCutPositionService;
    let httpMock: HttpTestingController;
    let elemDefault: IMCombinationCutPosition;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MCombinationCutPositionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MCombinationCutPosition(0, 0, 0, 0, 0, 0, 0, 0, 0);
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

      it('should create a MCombinationCutPosition', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MCombinationCutPosition(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MCombinationCutPosition', async () => {
        const returnedFromService = Object.assign(
          {
            actionCutId: 1,
            characterId: 1,
            activatorPosition: 1,
            participantPosition1: 1,
            participantPosition2: 1,
            participantPosition3: 1,
            participantPosition4: 1,
            participantPosition5: 1
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

      it('should return a list of MCombinationCutPosition', async () => {
        const returnedFromService = Object.assign(
          {
            actionCutId: 1,
            characterId: 1,
            activatorPosition: 1,
            participantPosition1: 1,
            participantPosition2: 1,
            participantPosition3: 1,
            participantPosition4: 1,
            participantPosition5: 1
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

      it('should delete a MCombinationCutPosition', async () => {
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
