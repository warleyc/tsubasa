/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MMissionService } from 'app/entities/m-mission/m-mission.service';
import { IMMission, MMission } from 'app/shared/model/m-mission.model';

describe('Service Tests', () => {
  describe('MMission Service', () => {
    let injector: TestBed;
    let service: MMissionService;
    let httpMock: HttpTestingController;
    let elemDefault: IMMission;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MMissionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MMission(0, 0, 0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'AAAAAAA', 0, 0);
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

      it('should create a MMission', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MMission(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MMission', async () => {
        const returnedFromService = Object.assign(
          {
            term: 1,
            roundNum: 1,
            title: 'BBBBBB',
            description: 'BBBBBB',
            missionType: 1,
            absolute: 1,
            param1: 1,
            param2: 1,
            param3: 1,
            param4: 1,
            param5: 1,
            trigger: 1,
            triggerCondition: 1,
            rewardId: 1,
            startAt: 1,
            endAt: 1,
            link: 1,
            sceneTransitionParam: 'BBBBBB',
            pickup: 1,
            orderNum: 1
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

      it('should return a list of MMission', async () => {
        const returnedFromService = Object.assign(
          {
            term: 1,
            roundNum: 1,
            title: 'BBBBBB',
            description: 'BBBBBB',
            missionType: 1,
            absolute: 1,
            param1: 1,
            param2: 1,
            param3: 1,
            param4: 1,
            param5: 1,
            trigger: 1,
            triggerCondition: 1,
            rewardId: 1,
            startAt: 1,
            endAt: 1,
            link: 1,
            sceneTransitionParam: 'BBBBBB',
            pickup: 1,
            orderNum: 1
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

      it('should delete a MMission', async () => {
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
