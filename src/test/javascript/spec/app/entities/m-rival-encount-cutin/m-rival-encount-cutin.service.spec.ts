/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MRivalEncountCutinService } from 'app/entities/m-rival-encount-cutin/m-rival-encount-cutin.service';
import { IMRivalEncountCutin, MRivalEncountCutin } from 'app/shared/model/m-rival-encount-cutin.model';

describe('Service Tests', () => {
  describe('MRivalEncountCutin Service', () => {
    let injector: TestBed;
    let service: MRivalEncountCutinService;
    let httpMock: HttpTestingController;
    let elemDefault: IMRivalEncountCutin;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MRivalEncountCutinService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MRivalEncountCutin(0, 0, 0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
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

      it('should create a MRivalEncountCutin', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MRivalEncountCutin(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MRivalEncountCutin', async () => {
        const returnedFromService = Object.assign(
          {
            offenseCharacterId: 1,
            offenseTeamId: 1,
            defenseCharacterId: 1,
            defenseTeamId: 1,
            cutinType: 1,
            chapter1Text: 'BBBBBB',
            chapter1SoundEvent: 'BBBBBB',
            chapter2Text: 'BBBBBB',
            chapter2SoundEvent: 'BBBBBB'
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

      it('should return a list of MRivalEncountCutin', async () => {
        const returnedFromService = Object.assign(
          {
            offenseCharacterId: 1,
            offenseTeamId: 1,
            defenseCharacterId: 1,
            defenseTeamId: 1,
            cutinType: 1,
            chapter1Text: 'BBBBBB',
            chapter1SoundEvent: 'BBBBBB',
            chapter2Text: 'BBBBBB',
            chapter2SoundEvent: 'BBBBBB'
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

      it('should delete a MRivalEncountCutin', async () => {
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
