/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MPassiveEffectRangeService } from 'app/entities/m-passive-effect-range/m-passive-effect-range.service';
import { IMPassiveEffectRange, MPassiveEffectRange } from 'app/shared/model/m-passive-effect-range.model';

describe('Service Tests', () => {
  describe('MPassiveEffectRange Service', () => {
    let injector: TestBed;
    let service: MPassiveEffectRangeService;
    let httpMock: HttpTestingController;
    let elemDefault: IMPassiveEffectRange;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MPassiveEffectRangeService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MPassiveEffectRange(0, 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'AAAAAAA');
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

      it('should create a MPassiveEffectRange', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MPassiveEffectRange(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MPassiveEffectRange', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            effectParamType: 1,
            targetPositionGk: 1,
            targetPositionFw: 1,
            targetPositionOmf: 1,
            targetPositionDmf: 1,
            targetPositionDf: 1,
            targetAttribute: 1,
            targetNationalityGroupId: 1,
            targetTeamGroupId: 1,
            targetPlayableCardGroupId: 1,
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

      it('should return a list of MPassiveEffectRange', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            effectParamType: 1,
            targetPositionGk: 1,
            targetPositionFw: 1,
            targetPositionOmf: 1,
            targetPositionDmf: 1,
            targetPositionDf: 1,
            targetAttribute: 1,
            targetNationalityGroupId: 1,
            targetTeamGroupId: 1,
            targetPlayableCardGroupId: 1,
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

      it('should delete a MPassiveEffectRange', async () => {
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
