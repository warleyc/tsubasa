/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MQuestWorldService } from 'app/entities/m-quest-world/m-quest-world.service';
import { IMQuestWorld, MQuestWorld } from 'app/shared/model/m-quest-world.model';

describe('Service Tests', () => {
  describe('MQuestWorld Service', () => {
    let injector: TestBed;
    let service: MQuestWorldService;
    let httpMock: HttpTestingController;
    let elemDefault: IMQuestWorld;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MQuestWorldService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MQuestWorld(0, 0, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0);
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

      it('should create a MQuestWorld', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MQuestWorld(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MQuestWorld', async () => {
        const returnedFromService = Object.assign(
          {
            number: 1,
            name: 'BBBBBB',
            startAt: 1,
            imagePath: 'BBBBBB',
            backgroundImagePath: 'BBBBBB',
            description: 'BBBBBB',
            stageUnlockPattern: 1,
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

      it('should return a list of MQuestWorld', async () => {
        const returnedFromService = Object.assign(
          {
            number: 1,
            name: 'BBBBBB',
            startAt: 1,
            imagePath: 'BBBBBB',
            backgroundImagePath: 'BBBBBB',
            description: 'BBBBBB',
            stageUnlockPattern: 1,
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

      it('should delete a MQuestWorld', async () => {
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
