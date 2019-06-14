/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MQuestStageConditionDescriptionService } from 'app/entities/m-quest-stage-condition-description/m-quest-stage-condition-description.service';
import {
  IMQuestStageConditionDescription,
  MQuestStageConditionDescription
} from 'app/shared/model/m-quest-stage-condition-description.model';

describe('Service Tests', () => {
  describe('MQuestStageConditionDescription Service', () => {
    let injector: TestBed;
    let service: MQuestStageConditionDescriptionService;
    let httpMock: HttpTestingController;
    let elemDefault: IMQuestStageConditionDescription;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MQuestStageConditionDescriptionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MQuestStageConditionDescription(0, 0, 0, 0, 0, 0, 'AAAAAAA');
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

      it('should create a MQuestStageConditionDescription', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MQuestStageConditionDescription(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MQuestStageConditionDescription', async () => {
        const returnedFromService = Object.assign(
          {
            successConditionType: 1,
            successConditionDetailTypeValue: 1,
            hasExistTargetCharacterGroup: 1,
            hasSuccessConditionValueOneOnly: 1,
            failureConditionTypeValue: 1,
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

      it('should return a list of MQuestStageConditionDescription', async () => {
        const returnedFromService = Object.assign(
          {
            successConditionType: 1,
            successConditionDetailTypeValue: 1,
            hasExistTargetCharacterGroup: 1,
            hasSuccessConditionValueOneOnly: 1,
            failureConditionTypeValue: 1,
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

      it('should delete a MQuestStageConditionDescription', async () => {
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
