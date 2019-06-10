/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MChallengeQuestAchievementRewardService } from 'app/entities/m-challenge-quest-achievement-reward/m-challenge-quest-achievement-reward.service';
import {
  IMChallengeQuestAchievementReward,
  MChallengeQuestAchievementReward
} from 'app/shared/model/m-challenge-quest-achievement-reward.model';

describe('Service Tests', () => {
  describe('MChallengeQuestAchievementReward Service', () => {
    let injector: TestBed;
    let service: MChallengeQuestAchievementRewardService;
    let httpMock: HttpTestingController;
    let elemDefault: IMChallengeQuestAchievementReward;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MChallengeQuestAchievementRewardService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MChallengeQuestAchievementReward(0, 0, 0, 0);
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

      it('should create a MChallengeQuestAchievementReward', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MChallengeQuestAchievementReward(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MChallengeQuestAchievementReward', async () => {
        const returnedFromService = Object.assign(
          {
            worldId: 1,
            stageId: 1,
            rewardGroupId: 1
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

      it('should return a list of MChallengeQuestAchievementReward', async () => {
        const returnedFromService = Object.assign(
          {
            worldId: 1,
            stageId: 1,
            rewardGroupId: 1
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

      it('should delete a MChallengeQuestAchievementReward', async () => {
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
