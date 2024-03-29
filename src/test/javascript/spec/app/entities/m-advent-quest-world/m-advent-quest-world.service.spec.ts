/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MAdventQuestWorldService } from 'app/entities/m-advent-quest-world/m-advent-quest-world.service';
import { IMAdventQuestWorld, MAdventQuestWorld } from 'app/shared/model/m-advent-quest-world.model';

describe('Service Tests', () => {
  describe('MAdventQuestWorld Service', () => {
    let injector: TestBed;
    let service: MAdventQuestWorldService;
    let httpMock: HttpTestingController;
    let elemDefault: IMAdventQuestWorld;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MAdventQuestWorldService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MAdventQuestWorld(0, 0, 0, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 0, 0, 0);
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

      it('should create a MAdventQuestWorld', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MAdventQuestWorld(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MAdventQuestWorld', async () => {
        const returnedFromService = Object.assign(
          {
            setId: 1,
            number: 1,
            name: 'BBBBBB',
            symbolType: 1,
            imagePath: 'BBBBBB',
            description: 'BBBBBB',
            stageUnlockPattern: 1,
            arousalBanner: 'BBBBBB',
            specialRewardContentType: 1,
            specialRewardContentId: 1,
            isEnableCoop: 1
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

      it('should return a list of MAdventQuestWorld', async () => {
        const returnedFromService = Object.assign(
          {
            setId: 1,
            number: 1,
            name: 'BBBBBB',
            symbolType: 1,
            imagePath: 'BBBBBB',
            description: 'BBBBBB',
            stageUnlockPattern: 1,
            arousalBanner: 'BBBBBB',
            specialRewardContentType: 1,
            specialRewardContentId: 1,
            isEnableCoop: 1
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

      it('should delete a MAdventQuestWorld', async () => {
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
