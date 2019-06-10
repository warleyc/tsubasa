/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MDeckRarityConditionDescriptionService } from 'app/entities/m-deck-rarity-condition-description/m-deck-rarity-condition-description.service';
import {
  IMDeckRarityConditionDescription,
  MDeckRarityConditionDescription
} from 'app/shared/model/m-deck-rarity-condition-description.model';

describe('Service Tests', () => {
  describe('MDeckRarityConditionDescription Service', () => {
    let injector: TestBed;
    let service: MDeckRarityConditionDescriptionService;
    let httpMock: HttpTestingController;
    let elemDefault: IMDeckRarityConditionDescription;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MDeckRarityConditionDescriptionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MDeckRarityConditionDescription(0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
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

      it('should create a MDeckRarityConditionDescription', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MDeckRarityConditionDescription(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MDeckRarityConditionDescription', async () => {
        const returnedFromService = Object.assign(
          {
            rarityGroupId: 1,
            countType: 1,
            isStarting: 1,
            description: 'BBBBBB',
            iconName: 'BBBBBB',
            smallIconName: 'BBBBBB'
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

      it('should return a list of MDeckRarityConditionDescription', async () => {
        const returnedFromService = Object.assign(
          {
            rarityGroupId: 1,
            countType: 1,
            isStarting: 1,
            description: 'BBBBBB',
            iconName: 'BBBBBB',
            smallIconName: 'BBBBBB'
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

      it('should delete a MDeckRarityConditionDescription', async () => {
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
