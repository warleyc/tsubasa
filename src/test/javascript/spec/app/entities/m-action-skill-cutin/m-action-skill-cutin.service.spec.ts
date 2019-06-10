/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MActionSkillCutinService } from 'app/entities/m-action-skill-cutin/m-action-skill-cutin.service';
import { IMActionSkillCutin, MActionSkillCutin } from 'app/shared/model/m-action-skill-cutin.model';

describe('Service Tests', () => {
  describe('MActionSkillCutin Service', () => {
    let injector: TestBed;
    let service: MActionSkillCutinService;
    let httpMock: HttpTestingController;
    let elemDefault: IMActionSkillCutin;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MActionSkillCutinService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MActionSkillCutin(
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
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
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

      it('should create a MActionSkillCutin', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MActionSkillCutin(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MActionSkillCutin', async () => {
        const returnedFromService = Object.assign(
          {
            actionCutId: 1,
            characterId: 1,
            cutName: 'BBBBBB',
            type: 1,
            startSynchronize: 1,
            finishSynchronize: 1,
            startDelay: 1,
            chapter1Character: 1,
            chapter1Text: 'BBBBBB',
            chapter1SoundEvent: 'BBBBBB',
            chapter2Character: 1,
            chapter2Text: 'BBBBBB',
            chapter2SoundEvent: 'BBBBBB',
            chapter3Character: 1,
            chapter3Text: 'BBBBBB',
            chapter3SoundEvent: 'BBBBBB',
            chapter4Character: 1,
            chapter4Text: 'BBBBBB',
            chapter4SoundEvent: 'BBBBBB',
            chapter5Character: 1,
            chapter5Text: 'BBBBBB',
            chapter5SoundEvent: 'BBBBBB',
            synchronizeText: 'BBBBBB',
            synchronizeSoundEvent: 'BBBBBB'
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

      it('should return a list of MActionSkillCutin', async () => {
        const returnedFromService = Object.assign(
          {
            actionCutId: 1,
            characterId: 1,
            cutName: 'BBBBBB',
            type: 1,
            startSynchronize: 1,
            finishSynchronize: 1,
            startDelay: 1,
            chapter1Character: 1,
            chapter1Text: 'BBBBBB',
            chapter1SoundEvent: 'BBBBBB',
            chapter2Character: 1,
            chapter2Text: 'BBBBBB',
            chapter2SoundEvent: 'BBBBBB',
            chapter3Character: 1,
            chapter3Text: 'BBBBBB',
            chapter3SoundEvent: 'BBBBBB',
            chapter4Character: 1,
            chapter4Text: 'BBBBBB',
            chapter4SoundEvent: 'BBBBBB',
            chapter5Character: 1,
            chapter5Text: 'BBBBBB',
            chapter5SoundEvent: 'BBBBBB',
            synchronizeText: 'BBBBBB',
            synchronizeSoundEvent: 'BBBBBB'
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

      it('should delete a MActionSkillCutin', async () => {
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
