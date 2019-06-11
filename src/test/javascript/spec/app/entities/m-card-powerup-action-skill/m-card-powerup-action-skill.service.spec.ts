/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MCardPowerupActionSkillService } from 'app/entities/m-card-powerup-action-skill/m-card-powerup-action-skill.service';
import { IMCardPowerupActionSkill, MCardPowerupActionSkill } from 'app/shared/model/m-card-powerup-action-skill.model';

describe('Service Tests', () => {
  describe('MCardPowerupActionSkill Service', () => {
    let injector: TestBed;
    let service: MCardPowerupActionSkillService;
    let httpMock: HttpTestingController;
    let elemDefault: IMCardPowerupActionSkill;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MCardPowerupActionSkillService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MCardPowerupActionSkill(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
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

      it('should create a MCardPowerupActionSkill', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MCardPowerupActionSkill(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MCardPowerupActionSkill', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            shortName: 'BBBBBB',
            description: 'BBBBBB',
            rarity: 1,
            attribute: 1,
            actionRarity: 1,
            gainActionExp: 1,
            coin: 1,
            sellMedalId: 1,
            thumbnailAssetsId: 1,
            cardIllustAssetsId: 1,
            targetActionCommandType: 1,
            targetCharacterId: 1
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

      it('should return a list of MCardPowerupActionSkill', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            shortName: 'BBBBBB',
            description: 'BBBBBB',
            rarity: 1,
            attribute: 1,
            actionRarity: 1,
            gainActionExp: 1,
            coin: 1,
            sellMedalId: 1,
            thumbnailAssetsId: 1,
            cardIllustAssetsId: 1,
            targetActionCommandType: 1,
            targetCharacterId: 1
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

      it('should delete a MCardPowerupActionSkill', async () => {
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