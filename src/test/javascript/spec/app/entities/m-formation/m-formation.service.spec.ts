/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MFormationService } from 'app/entities/m-formation/m-formation.service';
import { IMFormation, MFormation } from 'app/shared/model/m-formation.model';

describe('Service Tests', () => {
  describe('MFormation Service', () => {
    let injector: TestBed;
    let service: MFormationService;
    let httpMock: HttpTestingController;
    let elemDefault: IMFormation;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MFormationService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MFormation(
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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

      it('should create a MFormation', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MFormation(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MFormation', async () => {
        const returnedFromService = Object.assign(
          {
            effectValue: 1,
            effectProbability: 1,
            formationArrangementFw: 'BBBBBB',
            formationArrangementOmf: 'BBBBBB',
            formationArrangementDmf: 'BBBBBB',
            formationArrangementDf: 'BBBBBB',
            effectId: 1,
            description: 'BBBBBB',
            shortDescription: 'BBBBBB',
            name: 'BBBBBB',
            thumbnailAssetName: 'BBBBBB',
            startingUniformNos: 'BBBBBB',
            subUniformNos: 'BBBBBB',
            exType: 1,
            matchFormationId: 1
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

      it('should return a list of MFormation', async () => {
        const returnedFromService = Object.assign(
          {
            effectValue: 1,
            effectProbability: 1,
            formationArrangementFw: 'BBBBBB',
            formationArrangementOmf: 'BBBBBB',
            formationArrangementDmf: 'BBBBBB',
            formationArrangementDf: 'BBBBBB',
            effectId: 1,
            description: 'BBBBBB',
            shortDescription: 'BBBBBB',
            name: 'BBBBBB',
            thumbnailAssetName: 'BBBBBB',
            startingUniformNos: 'BBBBBB',
            subUniformNos: 'BBBBBB',
            exType: 1,
            matchFormationId: 1
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

      it('should delete a MFormation', async () => {
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
