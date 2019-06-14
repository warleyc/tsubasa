/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MQuestDropRateCampaignContentService } from 'app/entities/m-quest-drop-rate-campaign-content/m-quest-drop-rate-campaign-content.service';
import { IMQuestDropRateCampaignContent, MQuestDropRateCampaignContent } from 'app/shared/model/m-quest-drop-rate-campaign-content.model';

describe('Service Tests', () => {
  describe('MQuestDropRateCampaignContent Service', () => {
    let injector: TestBed;
    let service: MQuestDropRateCampaignContentService;
    let httpMock: HttpTestingController;
    let elemDefault: IMQuestDropRateCampaignContent;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MQuestDropRateCampaignContentService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MQuestDropRateCampaignContent(0, 0, 0, 0, 0);
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

      it('should create a MQuestDropRateCampaignContent', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new MQuestDropRateCampaignContent(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MQuestDropRateCampaignContent', async () => {
        const returnedFromService = Object.assign(
          {
            groupId: 1,
            contentType: 1,
            contentId: 1,
            rate: 1
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

      it('should return a list of MQuestDropRateCampaignContent', async () => {
        const returnedFromService = Object.assign(
          {
            groupId: 1,
            contentType: 1,
            contentId: 1,
            rate: 1
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

      it('should delete a MQuestDropRateCampaignContent', async () => {
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
