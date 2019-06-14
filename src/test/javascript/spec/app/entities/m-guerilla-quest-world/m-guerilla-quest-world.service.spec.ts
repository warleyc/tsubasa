/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MGuerillaQuestWorldService } from 'app/entities/m-guerilla-quest-world/m-guerilla-quest-world.service';
import { IMGuerillaQuestWorld, MGuerillaQuestWorld } from 'app/shared/model/m-guerilla-quest-world.model';

describe('Service Tests', () => {
  describe('MGuerillaQuestWorld Service', () => {
    let injector: TestBed;
    let service: MGuerillaQuestWorldService;
    let httpMock: HttpTestingController;
    let elemDefault: IMGuerillaQuestWorld;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MGuerillaQuestWorldService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MGuerillaQuestWorld(0, 0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 0, 0, 0);
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

      it('should create a MGuerillaQuestWorld', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MGuerillaQuestWorld(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MGuerillaQuestWorld', async () => {
        const returnedFromService = Object.assign(
          {
            setId: 1,
            number: 1,
            name: 'BBBBBB',
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

      it('should return a list of MGuerillaQuestWorld', async () => {
        const returnedFromService = Object.assign(
          {
            setId: 1,
            number: 1,
            name: 'BBBBBB',
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

      it('should delete a MGuerillaQuestWorld', async () => {
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
