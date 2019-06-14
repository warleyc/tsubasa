/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MNpcCardService } from 'app/entities/m-npc-card/m-npc-card.service';
import { IMNpcCard, MNpcCard } from 'app/shared/model/m-npc-card.model';

describe('Service Tests', () => {
  describe('MNpcCard Service', () => {
    let injector: TestBed;
    let service: MNpcCardService;
    let httpMock: HttpTestingController;
    let elemDefault: IMNpcCard;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MNpcCardService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MNpcCard(
        0,
        0,
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

      it('should create a MNpcCard', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MNpcCard(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MNpcCard', async () => {
        const returnedFromService = Object.assign(
          {
            characterId: 1,
            shortName: 'BBBBBB',
            nickName: 'BBBBBB',
            teamId: 1,
            nationalityId: 1,
            rarity: 1,
            attribute: 1,
            modelId: 1,
            level: 1,
            thumbnailAssetsId: 1,
            cardIllustAssetsId: 1,
            playableAssetsId: 1,
            teamEffectId: 1,
            teamEffectLevel: 1,
            triggerEffectId: 1,
            action1Id: 1,
            action1Level: 1,
            action2Id: 1,
            action2Level: 1,
            action3Id: 1,
            action3Level: 1,
            action4Id: 1,
            action4Level: 1,
            action5Id: 1,
            action5Level: 1,
            stamina: 1,
            dribble: 1,
            shoot: 1,
            ballPass: 1,
            tackle: 1,
            block: 1,
            intercept: 1,
            speed: 1,
            power: 1,
            technique: 1,
            punching: 1,
            catching: 1,
            highBallBonus: 1,
            lowBallBonus: 1,
            personalityId: 1,
            uniformNo: 1,
            levelGroupId: 1
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

      it('should return a list of MNpcCard', async () => {
        const returnedFromService = Object.assign(
          {
            characterId: 1,
            shortName: 'BBBBBB',
            nickName: 'BBBBBB',
            teamId: 1,
            nationalityId: 1,
            rarity: 1,
            attribute: 1,
            modelId: 1,
            level: 1,
            thumbnailAssetsId: 1,
            cardIllustAssetsId: 1,
            playableAssetsId: 1,
            teamEffectId: 1,
            teamEffectLevel: 1,
            triggerEffectId: 1,
            action1Id: 1,
            action1Level: 1,
            action2Id: 1,
            action2Level: 1,
            action3Id: 1,
            action3Level: 1,
            action4Id: 1,
            action4Level: 1,
            action5Id: 1,
            action5Level: 1,
            stamina: 1,
            dribble: 1,
            shoot: 1,
            ballPass: 1,
            tackle: 1,
            block: 1,
            intercept: 1,
            speed: 1,
            power: 1,
            technique: 1,
            punching: 1,
            catching: 1,
            highBallBonus: 1,
            lowBallBonus: 1,
            personalityId: 1,
            uniformNo: 1,
            levelGroupId: 1
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

      it('should delete a MNpcCard', async () => {
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
