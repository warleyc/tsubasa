/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MGachaRenditionExtraCutinService } from 'app/entities/m-gacha-rendition-extra-cutin/m-gacha-rendition-extra-cutin.service';
import { IMGachaRenditionExtraCutin, MGachaRenditionExtraCutin } from 'app/shared/model/m-gacha-rendition-extra-cutin.model';

describe('Service Tests', () => {
  describe('MGachaRenditionExtraCutin Service', () => {
    let injector: TestBed;
    let service: MGachaRenditionExtraCutinService;
    let httpMock: HttpTestingController;
    let elemDefault: IMGachaRenditionExtraCutin;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MGachaRenditionExtraCutinService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MGachaRenditionExtraCutin(0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
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

      it('should create a MGachaRenditionExtraCutin', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MGachaRenditionExtraCutin(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MGachaRenditionExtraCutin', async () => {
        const returnedFromService = Object.assign(
          {
            renditionId: 1,
            mainPrefabName: 'BBBBBB',
            voiceStartCutIn: 'BBBBBB',
            serif: 'BBBBBB'
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

      it('should return a list of MGachaRenditionExtraCutin', async () => {
        const returnedFromService = Object.assign(
          {
            renditionId: 1,
            mainPrefabName: 'BBBBBB',
            voiceStartCutIn: 'BBBBBB',
            serif: 'BBBBBB'
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

      it('should delete a MGachaRenditionExtraCutin', async () => {
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
