/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MEncountersCommandBranchService } from 'app/entities/m-encounters-command-branch/m-encounters-command-branch.service';
import { IMEncountersCommandBranch, MEncountersCommandBranch } from 'app/shared/model/m-encounters-command-branch.model';

describe('Service Tests', () => {
  describe('MEncountersCommandBranch Service', () => {
    let injector: TestBed;
    let service: MEncountersCommandBranchService;
    let httpMock: HttpTestingController;
    let elemDefault: IMEncountersCommandBranch;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MEncountersCommandBranchService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MEncountersCommandBranch(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
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

      it('should create a MEncountersCommandBranch', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MEncountersCommandBranch(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MEncountersCommandBranch', async () => {
        const returnedFromService = Object.assign(
          {
            ballFloatCondition: 1,
            condition: 1,
            encountersType: 1,
            isSuccess: 1,
            commandType: 1,
            successRate: 1,
            looseBallRate: 1,
            touchLightlyRate: 1,
            postRate: 1
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

      it('should return a list of MEncountersCommandBranch', async () => {
        const returnedFromService = Object.assign(
          {
            ballFloatCondition: 1,
            condition: 1,
            encountersType: 1,
            isSuccess: 1,
            commandType: 1,
            successRate: 1,
            looseBallRate: 1,
            touchLightlyRate: 1,
            postRate: 1
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

      it('should delete a MEncountersCommandBranch', async () => {
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
