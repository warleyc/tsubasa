/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MTeamEffectBaseService } from 'app/entities/m-team-effect-base/m-team-effect-base.service';
import { IMTeamEffectBase, MTeamEffectBase } from 'app/shared/model/m-team-effect-base.model';

describe('Service Tests', () => {
  describe('MTeamEffectBase Service', () => {
    let injector: TestBed;
    let service: MTeamEffectBaseService;
    let httpMock: HttpTestingController;
    let elemDefault: IMTeamEffectBase;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MTeamEffectBaseService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MTeamEffectBase(0, 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'AAAAAAA');
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

      it('should create a MTeamEffectBase', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MTeamEffectBase(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MTeamEffectBase', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            rarity: 1,
            effectValueMin: 1,
            effectValueMax: 1,
            triggerProbabilityMin: 1,
            triggerProbabilityMax: 1,
            effectId: 1,
            effectValueMin2: 1,
            effectValueMax2: 1,
            triggerProbabilityMin2: 1,
            triggerProbabilityMax2: 1,
            effectId2: 1,
            description: 'BBBBBB'
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

      it('should return a list of MTeamEffectBase', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            rarity: 1,
            effectValueMin: 1,
            effectValueMax: 1,
            triggerProbabilityMin: 1,
            triggerProbabilityMax: 1,
            effectId: 1,
            effectValueMin2: 1,
            effectValueMax2: 1,
            triggerProbabilityMin2: 1,
            triggerProbabilityMax2: 1,
            effectId2: 1,
            description: 'BBBBBB'
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

      it('should delete a MTeamEffectBase', async () => {
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
