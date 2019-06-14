/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MGachaRenditionTrajectoryCutInService } from 'app/entities/m-gacha-rendition-trajectory-cut-in/m-gacha-rendition-trajectory-cut-in.service';
import {
  IMGachaRenditionTrajectoryCutIn,
  MGachaRenditionTrajectoryCutIn
} from 'app/shared/model/m-gacha-rendition-trajectory-cut-in.model';

describe('Service Tests', () => {
  describe('MGachaRenditionTrajectoryCutIn Service', () => {
    let injector: TestBed;
    let service: MGachaRenditionTrajectoryCutInService;
    let httpMock: HttpTestingController;
    let elemDefault: IMGachaRenditionTrajectoryCutIn;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MGachaRenditionTrajectoryCutInService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MGachaRenditionTrajectoryCutIn(0, 0, 0, 'AAAAAAA', 0, 'AAAAAAA', 0);
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

      it('should create a MGachaRenditionTrajectoryCutIn', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MGachaRenditionTrajectoryCutIn(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MGachaRenditionTrajectoryCutIn', async () => {
        const returnedFromService = Object.assign(
          {
            renditionId: 1,
            trajectoryType: 1,
            spriteName: 'BBBBBB',
            weight: 1,
            voice: 'BBBBBB',
            exceptKickerId: 1
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

      it('should return a list of MGachaRenditionTrajectoryCutIn', async () => {
        const returnedFromService = Object.assign(
          {
            renditionId: 1,
            trajectoryType: 1,
            spriteName: 'BBBBBB',
            weight: 1,
            voice: 'BBBBBB',
            exceptKickerId: 1
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

      it('should delete a MGachaRenditionTrajectoryCutIn', async () => {
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
