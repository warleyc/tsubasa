/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MNpcDeckService } from 'app/entities/m-npc-deck/m-npc-deck.service';
import { IMNpcDeck, MNpcDeck } from 'app/shared/model/m-npc-deck.model';

describe('Service Tests', () => {
  describe('MNpcDeck Service', () => {
    let injector: TestBed;
    let service: MNpcDeckService;
    let httpMock: HttpTestingController;
    let elemDefault: IMNpcDeck;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MNpcDeckService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MNpcDeck(0, 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
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

      it('should create a MNpcDeck', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MNpcDeck(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MNpcDeck', async () => {
        const returnedFromService = Object.assign(
          {
            teamName: 'BBBBBB',
            uniformBottomFp: 1,
            uniformUpFp: 1,
            uniformBottomGk: 1,
            uniformUpGk: 1,
            formationId: 1,
            captainCardId: 1,
            teamEffect1CardId: 1,
            teamEffect2CardId: 1,
            teamEffect3CardId: 1,
            npcCardId1: 1,
            npcCardId2: 1,
            npcCardId3: 1,
            npcCardId4: 1,
            npcCardId5: 1,
            npcCardId6: 1,
            npcCardId7: 1,
            npcCardId8: 1,
            npcCardId9: 1,
            npcCardId10: 1,
            npcCardId11: 1,
            tick: 1
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

      it('should return a list of MNpcDeck', async () => {
        const returnedFromService = Object.assign(
          {
            teamName: 'BBBBBB',
            uniformBottomFp: 1,
            uniformUpFp: 1,
            uniformBottomGk: 1,
            uniformUpGk: 1,
            formationId: 1,
            captainCardId: 1,
            teamEffect1CardId: 1,
            teamEffect2CardId: 1,
            teamEffect3CardId: 1,
            npcCardId1: 1,
            npcCardId2: 1,
            npcCardId3: 1,
            npcCardId4: 1,
            npcCardId5: 1,
            npcCardId6: 1,
            npcCardId7: 1,
            npcCardId8: 1,
            npcCardId9: 1,
            npcCardId10: 1,
            npcCardId11: 1,
            tick: 1
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

      it('should delete a MNpcDeck', async () => {
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
