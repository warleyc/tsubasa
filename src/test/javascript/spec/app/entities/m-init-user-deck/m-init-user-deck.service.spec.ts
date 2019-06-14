/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MInitUserDeckService } from 'app/entities/m-init-user-deck/m-init-user-deck.service';
import { IMInitUserDeck, MInitUserDeck } from 'app/shared/model/m-init-user-deck.model';

describe('Service Tests', () => {
  describe('MInitUserDeck Service', () => {
    let injector: TestBed;
    let service: MInitUserDeckService;
    let httpMock: HttpTestingController;
    let elemDefault: IMInitUserDeck;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MInitUserDeckService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MInitUserDeck(0, 0, 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
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

      it('should create a MInitUserDeck', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MInitUserDeck(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MInitUserDeck', async () => {
        const returnedFromService = Object.assign(
          {
            deckId: 1,
            name: 'BBBBBB',
            formationId: 1,
            captainCardId: 1,
            gkCardId: 1,
            fp1CardId: 1,
            fp2CardId: 1,
            fp3CardId: 1,
            fp4CardId: 1,
            fp5CardId: 1,
            fp6CardId: 1,
            fp7CardId: 1,
            fp8CardId: 1,
            fp9CardId: 1,
            fp10CardId: 1,
            sub1CardId: 1,
            sub2CardId: 1,
            sub3CardId: 1,
            sub4CardId: 1,
            sub5CardId: 1,
            teamEffect1CardId: 1,
            teamEffect2CardId: 1,
            teamEffect3CardId: 1
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

      it('should return a list of MInitUserDeck', async () => {
        const returnedFromService = Object.assign(
          {
            deckId: 1,
            name: 'BBBBBB',
            formationId: 1,
            captainCardId: 1,
            gkCardId: 1,
            fp1CardId: 1,
            fp2CardId: 1,
            fp3CardId: 1,
            fp4CardId: 1,
            fp5CardId: 1,
            fp6CardId: 1,
            fp7CardId: 1,
            fp8CardId: 1,
            fp9CardId: 1,
            fp10CardId: 1,
            sub1CardId: 1,
            sub2CardId: 1,
            sub3CardId: 1,
            sub4CardId: 1,
            sub5CardId: 1,
            teamEffect1CardId: 1,
            teamEffect2CardId: 1,
            teamEffect3CardId: 1
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

      it('should delete a MInitUserDeck', async () => {
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
