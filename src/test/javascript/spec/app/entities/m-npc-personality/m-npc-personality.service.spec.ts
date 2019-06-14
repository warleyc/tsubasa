/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MNpcPersonalityService } from 'app/entities/m-npc-personality/m-npc-personality.service';
import { IMNpcPersonality, MNpcPersonality } from 'app/shared/model/m-npc-personality.model';

describe('Service Tests', () => {
  describe('MNpcPersonality Service', () => {
    let injector: TestBed;
    let service: MNpcPersonalityService;
    let httpMock: HttpTestingController;
    let elemDefault: IMNpcPersonality;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MNpcPersonalityService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MNpcPersonality(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
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

      it('should create a MNpcPersonality', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MNpcPersonality(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MNpcPersonality', async () => {
        const returnedFromService = Object.assign(
          {
            passingTargetRate: 1,
            actionSkillRate: 1,
            dribbleMagnification: 1,
            passingMagnification: 1,
            onetwoMagnification: 1,
            shootMagnification: 1,
            volleyShootMagnification: 1,
            headingShootMagnification: 1,
            tackleMagnification: 1,
            blockMagnification: 1,
            passCutMagnification: 1,
            clearMagnification: 1,
            competeMagnification: 1,
            trapMagnification: 1
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

      it('should return a list of MNpcPersonality', async () => {
        const returnedFromService = Object.assign(
          {
            passingTargetRate: 1,
            actionSkillRate: 1,
            dribbleMagnification: 1,
            passingMagnification: 1,
            onetwoMagnification: 1,
            shootMagnification: 1,
            volleyShootMagnification: 1,
            headingShootMagnification: 1,
            tackleMagnification: 1,
            blockMagnification: 1,
            passCutMagnification: 1,
            clearMagnification: 1,
            competeMagnification: 1,
            trapMagnification: 1
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

      it('should delete a MNpcPersonality', async () => {
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
