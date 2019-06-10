/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MDeckConditionService } from 'app/entities/m-deck-condition/m-deck-condition.service';
import { IMDeckCondition, MDeckCondition } from 'app/shared/model/m-deck-condition.model';

describe('Service Tests', () => {
  describe('MDeckCondition Service', () => {
    let injector: TestBed;
    let service: MDeckConditionService;
    let httpMock: HttpTestingController;
    let elemDefault: IMDeckCondition;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MDeckConditionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MDeckCondition(
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

      it('should create a MDeckCondition', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MDeckCondition(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MDeckCondition', async () => {
        const returnedFromService = Object.assign(
          {
            targetFormationGroupId: 1,
            targetCharacterGroupMinId: 1,
            targetCharacterGroupMaxId: 1,
            targetPlayableCardGroupMinId: 1,
            targetPlayableCardGroupMaxId: 1,
            targetRarityGroupId: 1,
            targetAttribute: 1,
            targetNationalityGroupMinId: 1,
            targetNationalityGroupMaxId: 1,
            targetTeamGroupMinId: 1,
            targetTeamGroupMaxId: 1,
            targetActionGroupMinId: 1,
            targetActionGroupMaxId: 1,
            targetTriggerEffectGroupMinId: 1,
            targetTriggerEffectGroupMaxId: 1,
            targetCharacterMinCount: 1,
            targetCharacterMaxCount: 1,
            targetPlayableCardMinCount: 1,
            targetPlayableCardMaxCount: 1,
            targetRarityMaxCount: 1,
            targetAttributeMinCount: 1,
            targetNationalityMinCount: 1,
            targetNationalityMaxCount: 1,
            targetTeamMinCount: 1,
            targetTeamMaxCount: 1,
            targetActionMinCount: 1,
            targetActionMaxCount: 1,
            targetTriggerEffectMinCount: 1,
            targetTriggerEffectMaxCount: 1,
            targetCharacterIsStartingMin: 1,
            targetCharacterIsStartingMax: 1,
            targetPlayableCardIsStartingMin: 1,
            targetPlayableCardIsStartingMax: 1,
            targetRarityIsStarting: 1,
            targetAttributeIsStarting: 1,
            targetNationalityIsStartingMin: 1,
            targetNationalityIsStartingMax: 1,
            targetTeamIsStartingMin: 1,
            targetTeamIsStartingMax: 1,
            targetActionIsStartingMin: 1,
            targetActionIsStartingMax: 1,
            targetTriggerEffectIsStartingMin: 1,
            targetTriggerEffectIsStartingMax: 1
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

      it('should return a list of MDeckCondition', async () => {
        const returnedFromService = Object.assign(
          {
            targetFormationGroupId: 1,
            targetCharacterGroupMinId: 1,
            targetCharacterGroupMaxId: 1,
            targetPlayableCardGroupMinId: 1,
            targetPlayableCardGroupMaxId: 1,
            targetRarityGroupId: 1,
            targetAttribute: 1,
            targetNationalityGroupMinId: 1,
            targetNationalityGroupMaxId: 1,
            targetTeamGroupMinId: 1,
            targetTeamGroupMaxId: 1,
            targetActionGroupMinId: 1,
            targetActionGroupMaxId: 1,
            targetTriggerEffectGroupMinId: 1,
            targetTriggerEffectGroupMaxId: 1,
            targetCharacterMinCount: 1,
            targetCharacterMaxCount: 1,
            targetPlayableCardMinCount: 1,
            targetPlayableCardMaxCount: 1,
            targetRarityMaxCount: 1,
            targetAttributeMinCount: 1,
            targetNationalityMinCount: 1,
            targetNationalityMaxCount: 1,
            targetTeamMinCount: 1,
            targetTeamMaxCount: 1,
            targetActionMinCount: 1,
            targetActionMaxCount: 1,
            targetTriggerEffectMinCount: 1,
            targetTriggerEffectMaxCount: 1,
            targetCharacterIsStartingMin: 1,
            targetCharacterIsStartingMax: 1,
            targetPlayableCardIsStartingMin: 1,
            targetPlayableCardIsStartingMax: 1,
            targetRarityIsStarting: 1,
            targetAttributeIsStarting: 1,
            targetNationalityIsStartingMin: 1,
            targetNationalityIsStartingMax: 1,
            targetTeamIsStartingMin: 1,
            targetTeamIsStartingMax: 1,
            targetActionIsStartingMin: 1,
            targetActionIsStartingMax: 1,
            targetTriggerEffectIsStartingMin: 1,
            targetTriggerEffectIsStartingMax: 1
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

      it('should delete a MDeckCondition', async () => {
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
