/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MCutService } from 'app/entities/m-cut/m-cut.service';
import { IMCut, MCut } from 'app/shared/model/m-cut.model';

describe('Service Tests', () => {
  describe('MCut Service', () => {
    let injector: TestBed;
    let service: MCutService;
    let httpMock: HttpTestingController;
    let elemDefault: IMCut;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MCutService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MCut(0, 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
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

      it('should create a MCut', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MCut(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MCut', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            fpA: 1,
            fpB: 1,
            fpC: 1,
            fpD: 1,
            fpE: 1,
            fpF: 1,
            gkA: 1,
            gkB: 1,
            showEnvironmentalEffect: 1,
            cutType: 1,
            groupId: 1,
            isCombiCut: 1
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

      it('should return a list of MCut', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            fpA: 1,
            fpB: 1,
            fpC: 1,
            fpD: 1,
            fpE: 1,
            fpF: 1,
            gkA: 1,
            gkB: 1,
            showEnvironmentalEffect: 1,
            cutType: 1,
            groupId: 1,
            isCombiCut: 1
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

      it('should delete a MCut', async () => {
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
