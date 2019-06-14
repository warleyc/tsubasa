/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MGuerillaQuestStageRewardService } from 'app/entities/m-guerilla-quest-stage-reward/m-guerilla-quest-stage-reward.service';
import { IMGuerillaQuestStageReward, MGuerillaQuestStageReward } from 'app/shared/model/m-guerilla-quest-stage-reward.model';

describe('Service Tests', () => {
  describe('MGuerillaQuestStageReward Service', () => {
    let injector: TestBed;
    let service: MGuerillaQuestStageRewardService;
    let httpMock: HttpTestingController;
    let elemDefault: IMGuerillaQuestStageReward;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MGuerillaQuestStageRewardService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MGuerillaQuestStageReward(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
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

      it('should create a MGuerillaQuestStageReward', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MGuerillaQuestStageReward(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MGuerillaQuestStageReward', async () => {
        const returnedFromService = Object.assign(
          {
            stageId: 1,
            exp: 1,
            coin: 1,
            guildPoint: 1,
            clearRewardGroupId: 1,
            clearRewardWeightId: 1,
            achievementRewardGroupId: 1,
            coopGroupId: 1,
            specialRewardGroupId: 1,
            specialRewardAmount: 1,
            goalRewardGroupId: 1
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

      it('should return a list of MGuerillaQuestStageReward', async () => {
        const returnedFromService = Object.assign(
          {
            stageId: 1,
            exp: 1,
            coin: 1,
            guildPoint: 1,
            clearRewardGroupId: 1,
            clearRewardWeightId: 1,
            achievementRewardGroupId: 1,
            coopGroupId: 1,
            specialRewardGroupId: 1,
            specialRewardAmount: 1,
            goalRewardGroupId: 1
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

      it('should delete a MGuerillaQuestStageReward', async () => {
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
