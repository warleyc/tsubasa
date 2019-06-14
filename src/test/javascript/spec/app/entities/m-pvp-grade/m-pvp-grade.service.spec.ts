/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MPvpGradeService } from 'app/entities/m-pvp-grade/m-pvp-grade.service';
import { IMPvpGrade, MPvpGrade } from 'app/shared/model/m-pvp-grade.model';

describe('Service Tests', () => {
  describe('MPvpGrade Service', () => {
    let injector: TestBed;
    let service: MPvpGradeService;
    let httpMock: HttpTestingController;
    let elemDefault: IMPvpGrade;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MPvpGradeService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MPvpGrade(
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
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
        0,
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

      it('should create a MPvpGrade', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MPvpGrade(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MPvpGrade', async () => {
        const returnedFromService = Object.assign(
          {
            waveId: 1,
            grade: 1,
            isHigher: 1,
            isLower: 1,
            gradeName: 'BBBBBB',
            look: 1,
            upRequirementId: 1,
            downRequirementId: 1,
            winPoint: 1,
            losePoint: 1,
            gradeUpSerif: 'BBBBBB',
            gradeDownSerif: 'BBBBBB',
            gradeUpCharaAssetName: 'BBBBBB',
            gradeDownCharaAssetName: 'BBBBBB',
            gradeUpVoiceCharaId: 'BBBBBB',
            gradeDownVoiceCharaId: 'BBBBBB',
            totalParameterRangeUpper: 1,
            totalParameterRangeLower: 1
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

      it('should return a list of MPvpGrade', async () => {
        const returnedFromService = Object.assign(
          {
            waveId: 1,
            grade: 1,
            isHigher: 1,
            isLower: 1,
            gradeName: 'BBBBBB',
            look: 1,
            upRequirementId: 1,
            downRequirementId: 1,
            winPoint: 1,
            losePoint: 1,
            gradeUpSerif: 'BBBBBB',
            gradeDownSerif: 'BBBBBB',
            gradeUpCharaAssetName: 'BBBBBB',
            gradeDownCharaAssetName: 'BBBBBB',
            gradeUpVoiceCharaId: 'BBBBBB',
            gradeDownVoiceCharaId: 'BBBBBB',
            totalParameterRangeUpper: 1,
            totalParameterRangeLower: 1
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

      it('should delete a MPvpGrade', async () => {
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
