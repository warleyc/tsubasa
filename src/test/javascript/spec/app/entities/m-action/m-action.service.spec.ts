/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MActionService } from 'app/entities/m-action/m-action.service';
import { IMAction, MAction } from 'app/shared/model/m-action.model';

describe('Service Tests', () => {
  describe('MAction Service', () => {
    let injector: TestBed;
    let service: MActionService;
    let httpMock: HttpTestingController;
    let elemDefault: IMAction;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MActionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MAction(
        0,
        'AAAAAAA',
        'AAAAAAA',
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

      it('should create a MAction', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MAction(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MAction', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            shortName: 'BBBBBB',
            nameRuby: 'BBBBBB',
            description: 'BBBBBB',
            effectDescription: 'BBBBBB',
            initialCommand: 1,
            rarity: 1,
            commandType: 1,
            ballConditionGround: 1,
            ballConditionLow: 1,
            ballConditionHigh: 1,
            staminaLvMin: 1,
            staminaLvMax: 1,
            powerLvMin: 1,
            powerLvMax: 1,
            blowOffCount: 1,
            mShootId: 1,
            foulRate: 1,
            distanceDecayType: 1,
            activateCharacterCount: 1,
            actionCutId: 1,
            powerupGroup: 1
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

      it('should return a list of MAction', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            shortName: 'BBBBBB',
            nameRuby: 'BBBBBB',
            description: 'BBBBBB',
            effectDescription: 'BBBBBB',
            initialCommand: 1,
            rarity: 1,
            commandType: 1,
            ballConditionGround: 1,
            ballConditionLow: 1,
            ballConditionHigh: 1,
            staminaLvMin: 1,
            staminaLvMax: 1,
            powerLvMin: 1,
            powerLvMax: 1,
            blowOffCount: 1,
            mShootId: 1,
            foulRate: 1,
            distanceDecayType: 1,
            activateCharacterCount: 1,
            actionCutId: 1,
            powerupGroup: 1
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

      it('should delete a MAction', async () => {
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
