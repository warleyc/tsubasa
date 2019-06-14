/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MModelUniformUpService } from 'app/entities/m-model-uniform-up/m-model-uniform-up.service';
import { IMModelUniformUp, MModelUniformUp } from 'app/shared/model/m-model-uniform-up.model';

describe('Service Tests', () => {
  describe('MModelUniformUp Service', () => {
    let injector: TestBed;
    let service: MModelUniformUpService;
    let httpMock: HttpTestingController;
    let elemDefault: IMModelUniformUp;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MModelUniformUpService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MModelUniformUp(0, 0, 0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 0);
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

      it('should create a MModelUniformUp', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MModelUniformUp(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MModelUniformUp', async () => {
        const returnedFromService = Object.assign(
          {
            uniformUpId: 1,
            defaultDressingType: 1,
            uniformModel: 1,
            uniformTexture: 1,
            uniformNoTexture: 1,
            defaultColor: 'BBBBBB',
            uniformNoColor: 'BBBBBB',
            numberChest: 1,
            numberBelly: 1,
            numberBack: 1,
            uniformNoPositionX: 1,
            uniformNoPositionY: 1
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

      it('should return a list of MModelUniformUp', async () => {
        const returnedFromService = Object.assign(
          {
            uniformUpId: 1,
            defaultDressingType: 1,
            uniformModel: 1,
            uniformTexture: 1,
            uniformNoTexture: 1,
            defaultColor: 'BBBBBB',
            uniformNoColor: 'BBBBBB',
            numberChest: 1,
            numberBelly: 1,
            numberBack: 1,
            uniformNoPositionX: 1,
            uniformNoPositionY: 1
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

      it('should delete a MModelUniformUp', async () => {
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
