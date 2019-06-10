/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MGachaRenditionAfterInputCutInTextColorService } from 'app/entities/m-gacha-rendition-after-input-cut-in-text-color/m-gacha-rendition-after-input-cut-in-text-color.service';
import {
  IMGachaRenditionAfterInputCutInTextColor,
  MGachaRenditionAfterInputCutInTextColor
} from 'app/shared/model/m-gacha-rendition-after-input-cut-in-text-color.model';

describe('Service Tests', () => {
  describe('MGachaRenditionAfterInputCutInTextColor Service', () => {
    let injector: TestBed;
    let service: MGachaRenditionAfterInputCutInTextColorService;
    let httpMock: HttpTestingController;
    let elemDefault: IMGachaRenditionAfterInputCutInTextColor;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MGachaRenditionAfterInputCutInTextColorService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MGachaRenditionAfterInputCutInTextColor(0, 0, 0, 'AAAAAAA');
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

      it('should create a MGachaRenditionAfterInputCutInTextColor', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MGachaRenditionAfterInputCutInTextColor(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MGachaRenditionAfterInputCutInTextColor', async () => {
        const returnedFromService = Object.assign(
          {
            isSsr: 1,
            weight: 1,
            color: 'BBBBBB'
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

      it('should return a list of MGachaRenditionAfterInputCutInTextColor', async () => {
        const returnedFromService = Object.assign(
          {
            isSsr: 1,
            weight: 1,
            color: 'BBBBBB'
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

      it('should delete a MGachaRenditionAfterInputCutInTextColor', async () => {
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
