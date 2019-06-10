/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MEncountersCommandCompatibilityService } from 'app/entities/m-encounters-command-compatibility/m-encounters-command-compatibility.service';
import {
  IMEncountersCommandCompatibility,
  MEncountersCommandCompatibility
} from 'app/shared/model/m-encounters-command-compatibility.model';

describe('Service Tests', () => {
  describe('MEncountersCommandCompatibility Service', () => {
    let injector: TestBed;
    let service: MEncountersCommandCompatibilityService;
    let httpMock: HttpTestingController;
    let elemDefault: IMEncountersCommandCompatibility;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MEncountersCommandCompatibilityService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MEncountersCommandCompatibility(0, 0, 0, 0, 0, 0);
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

      it('should create a MEncountersCommandCompatibility', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MEncountersCommandCompatibility(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MEncountersCommandCompatibility', async () => {
        const returnedFromService = Object.assign(
          {
            encountersType: 1,
            offenseCommandType: 1,
            defenseCommandType: 1,
            offenseMagnification: 1,
            defenseMagnification: 1
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

      it('should return a list of MEncountersCommandCompatibility', async () => {
        const returnedFromService = Object.assign(
          {
            encountersType: 1,
            offenseCommandType: 1,
            defenseCommandType: 1,
            offenseMagnification: 1,
            defenseMagnification: 1
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

      it('should delete a MEncountersCommandCompatibility', async () => {
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
