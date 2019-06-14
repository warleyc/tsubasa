/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MPlayableCardService } from 'app/entities/m-playable-card/m-playable-card.service';
import { IMPlayableCard, MPlayableCard } from 'app/shared/model/m-playable-card.model';

describe('Service Tests', () => {
  describe('MPlayableCard Service', () => {
    let injector: TestBed;
    let service: MPlayableCardService;
    let httpMock: HttpTestingController;
    let elemDefault: IMPlayableCard;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MPlayableCardService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MPlayableCard(
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
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

      it('should create a MPlayableCard', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MPlayableCard(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MPlayableCard', async () => {
        const returnedFromService = Object.assign(
          {
            modelId: 1,
            properPositionGk: 1,
            properPositionFw: 1,
            properPositionOmf: 1,
            properPositionDmf: 1,
            properPositionDf: 1,
            characterId: 1,
            nickName: 'BBBBBB',
            teamId: 1,
            nationalityId: 1,
            rarity: 1,
            attribute: 1,
            thumbnailAssetsId: 1,
            cardIllustAssetsId: 1,
            playableAssetsId: 1,
            teamEffectId: 1,
            triggerEffectId: 1,
            maxActionSlot: 1,
            initialActionId1: 1,
            initialActionId2: 1,
            initialActionId3: 1,
            initialActionId4: 1,
            initialActionId5: 1,
            initialStamina: 1,
            initialDribble: 1,
            initialShoot: 1,
            initialPass: 1,
            initialTackle: 1,
            initialBlock: 1,
            initialIntercept: 1,
            initialSpeed: 1,
            initialPower: 1,
            initialTechnique: 1,
            initialPunching: 1,
            initialCatching: 1,
            maxStamina: 1,
            maxDribble: 1,
            maxShoot: 1,
            maxPass: 1,
            maxTackle: 1,
            maxBlock: 1,
            maxIntercept: 1,
            maxSpeed: 1,
            maxPower: 1,
            maxTechnique: 1,
            maxPunching: 1,
            maxCatching: 1,
            maxPlusDribble: 1,
            maxPlusShoot: 1,
            maxPlusPass: 1,
            maxPlusTackle: 1,
            maxPlusBlock: 1,
            maxPlusIntercept: 1,
            maxPlusSpeed: 1,
            maxPlusPower: 1,
            maxPlusTechnique: 1,
            maxPlusPunching: 1,
            maxPlusCatching: 1,
            highBallBonus: 1,
            lowBallBonus: 1,
            isDropOnly: 1,
            sellCoinGroupNum: 1,
            sellMedalId: 1,
            characterBookId: 1,
            bookNo: 1,
            isShowBook: 1,
            levelGroupId: 1,
            startAt: 1
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

      it('should return a list of MPlayableCard', async () => {
        const returnedFromService = Object.assign(
          {
            modelId: 1,
            properPositionGk: 1,
            properPositionFw: 1,
            properPositionOmf: 1,
            properPositionDmf: 1,
            properPositionDf: 1,
            characterId: 1,
            nickName: 'BBBBBB',
            teamId: 1,
            nationalityId: 1,
            rarity: 1,
            attribute: 1,
            thumbnailAssetsId: 1,
            cardIllustAssetsId: 1,
            playableAssetsId: 1,
            teamEffectId: 1,
            triggerEffectId: 1,
            maxActionSlot: 1,
            initialActionId1: 1,
            initialActionId2: 1,
            initialActionId3: 1,
            initialActionId4: 1,
            initialActionId5: 1,
            initialStamina: 1,
            initialDribble: 1,
            initialShoot: 1,
            initialPass: 1,
            initialTackle: 1,
            initialBlock: 1,
            initialIntercept: 1,
            initialSpeed: 1,
            initialPower: 1,
            initialTechnique: 1,
            initialPunching: 1,
            initialCatching: 1,
            maxStamina: 1,
            maxDribble: 1,
            maxShoot: 1,
            maxPass: 1,
            maxTackle: 1,
            maxBlock: 1,
            maxIntercept: 1,
            maxSpeed: 1,
            maxPower: 1,
            maxTechnique: 1,
            maxPunching: 1,
            maxCatching: 1,
            maxPlusDribble: 1,
            maxPlusShoot: 1,
            maxPlusPass: 1,
            maxPlusTackle: 1,
            maxPlusBlock: 1,
            maxPlusIntercept: 1,
            maxPlusSpeed: 1,
            maxPlusPower: 1,
            maxPlusTechnique: 1,
            maxPlusPunching: 1,
            maxPlusCatching: 1,
            highBallBonus: 1,
            lowBallBonus: 1,
            isDropOnly: 1,
            sellCoinGroupNum: 1,
            sellMedalId: 1,
            characterBookId: 1,
            bookNo: 1,
            isShowBook: 1,
            levelGroupId: 1,
            startAt: 1
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

      it('should delete a MPlayableCard', async () => {
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
