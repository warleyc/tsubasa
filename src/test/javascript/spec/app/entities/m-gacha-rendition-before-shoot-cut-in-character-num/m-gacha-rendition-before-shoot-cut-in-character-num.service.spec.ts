/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MGachaRenditionBeforeShootCutInCharacterNumService } from 'app/entities/m-gacha-rendition-before-shoot-cut-in-character-num/m-gacha-rendition-before-shoot-cut-in-character-num.service';
import {
  IMGachaRenditionBeforeShootCutInCharacterNum,
  MGachaRenditionBeforeShootCutInCharacterNum
} from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-character-num.model';

describe('Service Tests', () => {
  describe('MGachaRenditionBeforeShootCutInCharacterNum Service', () => {
    let injector: TestBed;
    let service: MGachaRenditionBeforeShootCutInCharacterNumService;
    let httpMock: HttpTestingController;
    let elemDefault: IMGachaRenditionBeforeShootCutInCharacterNum;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MGachaRenditionBeforeShootCutInCharacterNumService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MGachaRenditionBeforeShootCutInCharacterNum(0, 0, 0, 0, 0, 0);
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

      it('should create a MGachaRenditionBeforeShootCutInCharacterNum', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MGachaRenditionBeforeShootCutInCharacterNum(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MGachaRenditionBeforeShootCutInCharacterNum', async () => {
        const returnedFromService = Object.assign(
          {
            isManySsr: 1,
            isSsr: 1,
            pattern: 1,
            weight: 1,
            num: 1
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

      it('should return a list of MGachaRenditionBeforeShootCutInCharacterNum', async () => {
        const returnedFromService = Object.assign(
          {
            isManySsr: 1,
            isSsr: 1,
            pattern: 1,
            weight: 1,
            num: 1
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

      it('should delete a MGachaRenditionBeforeShootCutInCharacterNum', async () => {
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
