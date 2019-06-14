/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MWeeklyQuestStageService } from 'app/entities/m-weekly-quest-stage/m-weekly-quest-stage.service';
import { IMWeeklyQuestStage, MWeeklyQuestStage } from 'app/shared/model/m-weekly-quest-stage.model';

describe('Service Tests', () => {
  describe('MWeeklyQuestStage Service', () => {
    let injector: TestBed;
    let service: MWeeklyQuestStageService;
    let httpMock: HttpTestingController;
    let elemDefault: IMWeeklyQuestStage;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MWeeklyQuestStageService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MWeeklyQuestStage(
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
      );
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

      it('should create a MWeeklyQuestStage', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MWeeklyQuestStage(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MWeeklyQuestStage', async () => {
        const returnedFromService = Object.assign(
          {
            worldId: 1,
            number: 1,
            name: 'BBBBBB',
            imagePath: 'BBBBBB',
            characterThumbnailPath: 'BBBBBB',
            difficulty: 1,
            stageUnlockPattern: 1,
            storyOnly: 1,
            firstHalfNpcDeckId: 1,
            firstHalfEnvironmentId: 1,
            secondHalfNpcDeckId: 1,
            secondHalfEnvironmentId: 1,
            extraFirstHalfNpcDeckId: 1,
            extraSecondHalfNpcDeckId: 1,
            consumeAp: 1,
            kickOffType: 1,
            matchMinute: 1,
            enableExtra: 1,
            enablePk: 1,
            aiLevel: 1,
            startAtSecondHalf: 1,
            startScore: 1,
            startScoreOpponent: 1,
            conditionId: 1,
            optionId: 1,
            deckConditionId: 1
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

      it('should return a list of MWeeklyQuestStage', async () => {
        const returnedFromService = Object.assign(
          {
            worldId: 1,
            number: 1,
            name: 'BBBBBB',
            imagePath: 'BBBBBB',
            characterThumbnailPath: 'BBBBBB',
            difficulty: 1,
            stageUnlockPattern: 1,
            storyOnly: 1,
            firstHalfNpcDeckId: 1,
            firstHalfEnvironmentId: 1,
            secondHalfNpcDeckId: 1,
            secondHalfEnvironmentId: 1,
            extraFirstHalfNpcDeckId: 1,
            extraSecondHalfNpcDeckId: 1,
            consumeAp: 1,
            kickOffType: 1,
            matchMinute: 1,
            enableExtra: 1,
            enablePk: 1,
            aiLevel: 1,
            startAtSecondHalf: 1,
            startScore: 1,
            startScoreOpponent: 1,
            conditionId: 1,
            optionId: 1,
            deckConditionId: 1
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

      it('should delete a MWeeklyQuestStage', async () => {
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
