/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MLoginBonusRoundService } from 'app/entities/m-login-bonus-round/m-login-bonus-round.service';
import { IMLoginBonusRound, MLoginBonusRound } from 'app/shared/model/m-login-bonus-round.model';

describe('Service Tests', () => {
  describe('MLoginBonusRound Service', () => {
    let injector: TestBed;
    let service: MLoginBonusRoundService;
    let httpMock: HttpTestingController;
    let elemDefault: IMLoginBonusRound;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MLoginBonusRoundService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MLoginBonusRound(
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA'
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

      it('should create a MLoginBonusRound', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MLoginBonusRound(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MLoginBonusRound', async () => {
        const returnedFromService = Object.assign(
          {
            roundId: 1,
            bonusType: 1,
            startAt: 1,
            endAt: 1,
            serifSanae: 'BBBBBB',
            serifYayoi: 'BBBBBB',
            serifYoshiko: 'BBBBBB',
            serifMaki: 'BBBBBB',
            sanaeImage: 'BBBBBB',
            yayoiImage: 'BBBBBB',
            yoshikoImage: 'BBBBBB',
            makiImage: 'BBBBBB',
            soundSwitchEventName: 'BBBBBB',
            nextId: 1,
            lastDayAppealComment: 'BBBBBB',
            backgroundImage: 'BBBBBB'
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

      it('should return a list of MLoginBonusRound', async () => {
        const returnedFromService = Object.assign(
          {
            roundId: 1,
            bonusType: 1,
            startAt: 1,
            endAt: 1,
            serifSanae: 'BBBBBB',
            serifYayoi: 'BBBBBB',
            serifYoshiko: 'BBBBBB',
            serifMaki: 'BBBBBB',
            sanaeImage: 'BBBBBB',
            yayoiImage: 'BBBBBB',
            yoshikoImage: 'BBBBBB',
            makiImage: 'BBBBBB',
            soundSwitchEventName: 'BBBBBB',
            nextId: 1,
            lastDayAppealComment: 'BBBBBB',
            backgroundImage: 'BBBBBB'
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

      it('should delete a MLoginBonusRound', async () => {
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
