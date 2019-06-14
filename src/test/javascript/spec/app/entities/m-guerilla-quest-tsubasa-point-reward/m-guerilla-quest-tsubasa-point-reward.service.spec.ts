/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MGuerillaQuestTsubasaPointRewardService } from 'app/entities/m-guerilla-quest-tsubasa-point-reward/m-guerilla-quest-tsubasa-point-reward.service';
import {
  IMGuerillaQuestTsubasaPointReward,
  MGuerillaQuestTsubasaPointReward
} from 'app/shared/model/m-guerilla-quest-tsubasa-point-reward.model';

describe('Service Tests', () => {
  describe('MGuerillaQuestTsubasaPointReward Service', () => {
    let injector: TestBed;
    let service: MGuerillaQuestTsubasaPointRewardService;
    let httpMock: HttpTestingController;
    let elemDefault: IMGuerillaQuestTsubasaPointReward;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MGuerillaQuestTsubasaPointRewardService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MGuerillaQuestTsubasaPointReward(0, 0, 0, 0, 0, 0);
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

      it('should create a MGuerillaQuestTsubasaPointReward', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MGuerillaQuestTsubasaPointReward(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MGuerillaQuestTsubasaPointReward', async () => {
        const returnedFromService = Object.assign(
          {
            stageId: 1,
            tsubasaPoint: 1,
            contentType: 1,
            contentId: 1,
            contentAmount: 1
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

      it('should return a list of MGuerillaQuestTsubasaPointReward', async () => {
        const returnedFromService = Object.assign(
          {
            stageId: 1,
            tsubasaPoint: 1,
            contentType: 1,
            contentId: 1,
            contentAmount: 1
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

      it('should delete a MGuerillaQuestTsubasaPointReward', async () => {
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
