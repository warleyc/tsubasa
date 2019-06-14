/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MTriggerEffectBaseService } from 'app/entities/m-trigger-effect-base/m-trigger-effect-base.service';
import { IMTriggerEffectBase, MTriggerEffectBase } from 'app/shared/model/m-trigger-effect-base.model';

describe('Service Tests', () => {
  describe('MTriggerEffectBase Service', () => {
    let injector: TestBed;
    let service: MTriggerEffectBaseService;
    let httpMock: HttpTestingController;
    let elemDefault: IMTriggerEffectBase;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MTriggerEffectBaseService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MTriggerEffectBase(0, 'AAAAAAA', 0, 0, 0, 0, 0, 'AAAAAAA');
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

      it('should create a MTriggerEffectBase', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MTriggerEffectBase(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MTriggerEffectBase', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            rarity: 1,
            effectValue: 1,
            triggerProbability: 1,
            targetCardParameter: 1,
            effectId: 1,
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

      it('should return a list of MTriggerEffectBase', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            rarity: 1,
            effectValue: 1,
            triggerProbability: 1,
            targetCardParameter: 1,
            effectId: 1,
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

      it('should delete a MTriggerEffectBase', async () => {
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
