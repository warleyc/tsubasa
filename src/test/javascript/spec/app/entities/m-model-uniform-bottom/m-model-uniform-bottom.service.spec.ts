/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MModelUniformBottomService } from 'app/entities/m-model-uniform-bottom/m-model-uniform-bottom.service';
import { IMModelUniformBottom, MModelUniformBottom } from 'app/shared/model/m-model-uniform-bottom.model';

describe('Service Tests', () => {
  describe('MModelUniformBottom Service', () => {
    let injector: TestBed;
    let service: MModelUniformBottomService;
    let httpMock: HttpTestingController;
    let elemDefault: IMModelUniformBottom;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MModelUniformBottomService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MModelUniformBottom(0, 0, 0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0);
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

      it('should create a MModelUniformBottom', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MModelUniformBottom(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MModelUniformBottom', async () => {
        const returnedFromService = Object.assign(
          {
            uniformBottomId: 1,
            defaultDressingType: 1,
            uniformModel: 1,
            uniformTexture: 1,
            uniformNoTexture: 1,
            defaultColor: 'BBBBBB',
            uniformNoColor: 'BBBBBB',
            numberRightLeg: 1,
            numberLeftLeg: 1,
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

      it('should return a list of MModelUniformBottom', async () => {
        const returnedFromService = Object.assign(
          {
            uniformBottomId: 1,
            defaultDressingType: 1,
            uniformModel: 1,
            uniformTexture: 1,
            uniformNoTexture: 1,
            defaultColor: 'BBBBBB',
            uniformNoColor: 'BBBBBB',
            numberRightLeg: 1,
            numberLeftLeg: 1,
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

      it('should delete a MModelUniformBottom', async () => {
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
