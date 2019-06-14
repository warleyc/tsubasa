/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MRegulationMatchTutorialMessageService } from 'app/entities/m-regulation-match-tutorial-message/m-regulation-match-tutorial-message.service';
import {
  IMRegulationMatchTutorialMessage,
  MRegulationMatchTutorialMessage
} from 'app/shared/model/m-regulation-match-tutorial-message.model';

describe('Service Tests', () => {
  describe('MRegulationMatchTutorialMessage Service', () => {
    let injector: TestBed;
    let service: MRegulationMatchTutorialMessageService;
    let httpMock: HttpTestingController;
    let elemDefault: IMRegulationMatchTutorialMessage;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MRegulationMatchTutorialMessageService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MRegulationMatchTutorialMessage(0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
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

      it('should create a MRegulationMatchTutorialMessage', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MRegulationMatchTutorialMessage(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MRegulationMatchTutorialMessage', async () => {
        const returnedFromService = Object.assign(
          {
            ruleId: 1,
            orderNum: 1,
            position: 1,
            message: 'BBBBBB',
            assetName: 'BBBBBB',
            title: 'BBBBBB'
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

      it('should return a list of MRegulationMatchTutorialMessage', async () => {
        const returnedFromService = Object.assign(
          {
            ruleId: 1,
            orderNum: 1,
            position: 1,
            message: 'BBBBBB',
            assetName: 'BBBBBB',
            title: 'BBBBBB'
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

      it('should delete a MRegulationMatchTutorialMessage', async () => {
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
