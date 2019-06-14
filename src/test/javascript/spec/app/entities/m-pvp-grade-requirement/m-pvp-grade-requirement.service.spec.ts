/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MPvpGradeRequirementService } from 'app/entities/m-pvp-grade-requirement/m-pvp-grade-requirement.service';
import { IMPvpGradeRequirement, MPvpGradeRequirement } from 'app/shared/model/m-pvp-grade-requirement.model';

describe('Service Tests', () => {
  describe('MPvpGradeRequirement Service', () => {
    let injector: TestBed;
    let service: MPvpGradeRequirementService;
    let httpMock: HttpTestingController;
    let elemDefault: IMPvpGradeRequirement;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MPvpGradeRequirementService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MPvpGradeRequirement(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0);
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

      it('should create a MPvpGradeRequirement', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MPvpGradeRequirement(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MPvpGradeRequirement', async () => {
        const returnedFromService = Object.assign(
          {
            upDescription: 'BBBBBB',
            downDescription: 'BBBBBB',
            targetType: 1,
            targetWave: 1,
            targetLowerGrade: 1,
            targetPoint: 1
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

      it('should return a list of MPvpGradeRequirement', async () => {
        const returnedFromService = Object.assign(
          {
            upDescription: 'BBBBBB',
            downDescription: 'BBBBBB',
            targetType: 1,
            targetWave: 1,
            targetLowerGrade: 1,
            targetPoint: 1
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

      it('should delete a MPvpGradeRequirement', async () => {
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
