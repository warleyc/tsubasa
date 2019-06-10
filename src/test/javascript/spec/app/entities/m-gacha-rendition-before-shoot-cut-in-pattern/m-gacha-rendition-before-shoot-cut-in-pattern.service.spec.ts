/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MGachaRenditionBeforeShootCutInPatternService } from 'app/entities/m-gacha-rendition-before-shoot-cut-in-pattern/m-gacha-rendition-before-shoot-cut-in-pattern.service';
import {
  IMGachaRenditionBeforeShootCutInPattern,
  MGachaRenditionBeforeShootCutInPattern
} from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-pattern.model';

describe('Service Tests', () => {
  describe('MGachaRenditionBeforeShootCutInPattern Service', () => {
    let injector: TestBed;
    let service: MGachaRenditionBeforeShootCutInPatternService;
    let httpMock: HttpTestingController;
    let elemDefault: IMGachaRenditionBeforeShootCutInPattern;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MGachaRenditionBeforeShootCutInPatternService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MGachaRenditionBeforeShootCutInPattern(
        0,
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0
      );
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

      it('should create a MGachaRenditionBeforeShootCutInPattern', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MGachaRenditionBeforeShootCutInPattern(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MGachaRenditionBeforeShootCutInPattern', async () => {
        const returnedFromService = Object.assign(
          {
            renditionId: 1,
            isManySsr: 1,
            isSsr: 1,
            weight: 1,
            pattern: 1,
            normalPrefabName: 'BBBBBB',
            flashBackPrefabName1: 'BBBBBB',
            flashBackPrefabName2: 'BBBBBB',
            flashBackPrefabName3: 'BBBBBB',
            flashBackPrefabName4: 'BBBBBB',
            voicePrefix: 'BBBBBB',
            seNormal: 'BBBBBB',
            seFlashBack: 'BBBBBB',
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

      it('should return a list of MGachaRenditionBeforeShootCutInPattern', async () => {
        const returnedFromService = Object.assign(
          {
            renditionId: 1,
            isManySsr: 1,
            isSsr: 1,
            weight: 1,
            pattern: 1,
            normalPrefabName: 'BBBBBB',
            flashBackPrefabName1: 'BBBBBB',
            flashBackPrefabName2: 'BBBBBB',
            flashBackPrefabName3: 'BBBBBB',
            flashBackPrefabName4: 'BBBBBB',
            voicePrefix: 'BBBBBB',
            seNormal: 'BBBBBB',
            seFlashBack: 'BBBBBB',
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

      it('should delete a MGachaRenditionBeforeShootCutInPattern', async () => {
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
