/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MCharacterBookService } from 'app/entities/m-character-book/m-character-book.service';
import { IMCharacterBook, MCharacterBook } from 'app/shared/model/m-character-book.model';

describe('Service Tests', () => {
  describe('MCharacterBook Service', () => {
    let injector: TestBed;
    let service: MCharacterBookService;
    let httpMock: HttpTestingController;
    let elemDefault: IMCharacterBook;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MCharacterBookService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MCharacterBook(
        0,
        'AAAAAAA',
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

      it('should create a MCharacterBook', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MCharacterBook(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MCharacterBook', async () => {
        const returnedFromService = Object.assign(
          {
            cvName: 'BBBBBB',
            series: 1,
            height: 1,
            weight: 1,
            introduction: 'BBBBBB',
            firstAppearedComic: 'BBBBBB',
            firstAppearedComicLink: 'BBBBBB',
            skill1: 'BBBBBB',
            skill1Comic: 'BBBBBB',
            skill1ComicLink: 'BBBBBB',
            skill2: 'BBBBBB',
            skill2Comic: 'BBBBBB',
            skill2ComicLink: 'BBBBBB',
            skill3: 'BBBBBB',
            skill3Comic: 'BBBBBB',
            skill3ComicLink: 'BBBBBB'
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

      it('should return a list of MCharacterBook', async () => {
        const returnedFromService = Object.assign(
          {
            cvName: 'BBBBBB',
            series: 1,
            height: 1,
            weight: 1,
            introduction: 'BBBBBB',
            firstAppearedComic: 'BBBBBB',
            firstAppearedComicLink: 'BBBBBB',
            skill1: 'BBBBBB',
            skill1Comic: 'BBBBBB',
            skill1ComicLink: 'BBBBBB',
            skill2: 'BBBBBB',
            skill2Comic: 'BBBBBB',
            skill2ComicLink: 'BBBBBB',
            skill3: 'BBBBBB',
            skill3Comic: 'BBBBBB',
            skill3ComicLink: 'BBBBBB'
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

      it('should delete a MCharacterBook', async () => {
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
